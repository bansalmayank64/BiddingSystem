package com.bidding.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bidding.system.dto.AuctionBidRequestDto;
import com.bidding.system.dto.AuctionModel;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.exception.AuctionNotFoundException;
import com.bidding.system.exception.BidNotAcceptedException;
import com.bidding.system.model.Auction;
import com.bidding.system.model.AuctionStatus;
import com.bidding.system.model.Item;
import com.bidding.system.model.UserBid;
import com.bidding.system.repository.AuctionRepository;
import com.bidding.system.repository.AuctionRepositoryAddMultiplePredicateSpecification;
import com.bidding.system.repository.ItemRepository;
import com.bidding.system.repository.UserBidRepository;
import com.bidding.system.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserBidRepository userBidRepository;

	@Override
	public FetchAuctionsResponseDto fetchAllAuctions(FetchAuctionsRequestDto request) {
		AuctionRepositoryAddMultiplePredicateSpecification specification = new AuctionRepositoryAddMultiplePredicateSpecification(
				request.getStatus());
		FetchAuctionsResponseDto response = new FetchAuctionsResponseDto();
		List<Auction> auctions;
		if (Objects.nonNull(request.getStartFrom()) && Objects.nonNull(request.getLimit())) {
			Pageable pageable = PageRequest.of(request.getStartFrom(), request.getLimit());
			Page<Auction> auctionsPage = auctionRepository.findAll(specification, pageable);
			response.setResultsAvailable(auctionsPage.getTotalElements());
			response.setResultsReturned(auctionsPage.getNumberOfElements());
			auctions = auctionsPage.getContent();
		} else {
			auctions = auctionRepository.findAll(specification);
			response.setResultsAvailable((long) auctions.size());
			response.setResultsReturned(auctions.size());
		}

		List<AuctionModel> auctionModels = new ArrayList<>();
		auctions.forEach(auction -> {
			List<UserBid> userBids = userBidRepository.findAllByAuctionAndIsAccepted(auction, true);
			AuctionModel auctionModel = new AuctionModel(auction.getAuctionId(), auction.getItem().getItemCode(),
					getMaxBidding(userBids, auction.getMinimumBasePrice()), auction.getStepRate());
			auctionModels.add(auctionModel);
		});
		response.setData(auctionModels);
		return response;
	}

	@Override
	@Transactional
	public void placeBid(String itemCode, AuctionBidRequestDto auctionBidRequestDto, Long version)
			throws AuctionNotFoundException, BidNotAcceptedException {
		Optional<Item> findItemById = itemRepository.findById(itemCode);
		if (findItemById.isPresent()) {
			Item item = findItemById.get();
			Optional<Auction> findAuctionByItem = auctionRepository.findByItemAndAuctionStatus(item,
					AuctionStatus.RUNNING);
			if (findAuctionByItem.isPresent()) {
				validateAndPlaceBid(auctionBidRequestDto.getBidAmount(), findAuctionByItem.get(), version);
			} else {
				throw new AuctionNotFoundException("No auction is running with item code: " + itemCode);
			}
		} else {
			throw new AuctionNotFoundException("Invalid item code: " + itemCode);
		}
	}

	private void validateAndPlaceBid(Double bidAmount, Auction auction, Long version) throws BidNotAcceptedException {
		List<UserBid> userBids = auction.getUserBids().stream().filter(u -> u.isAccepted())
				.collect(Collectors.toList());
		if (Objects.isNull(userBids)) {
			userBids = new ArrayList<>();
		}
		Double maxBidding = getMaxBidding(userBids, auction.getMinimumBasePrice());
		Double stepRate = auction.getStepRate();
		Double minimumBasePrice = auction.getMinimumBasePrice();
		auction.setVersion(version);
		if (bidAmount > maxBidding && bidAmount > minimumBasePrice && (bidAmount - maxBidding) % stepRate == 0) {
			userBids.add(new UserBid(null, bidAmount, true));
			auction.setUserBids(userBids);
			auctionRepository.save(auction);
		} else {
			userBids.add(new UserBid(null, bidAmount, false));
			auction.setUserBids(userBids);
			auctionRepository.save(auction);
			throw new BidNotAcceptedException(
					"Bid can not be accepted. Please check the bid amount, it should be greater than the minimum base price and in the multiple of step rate.");
		}
	}

	private Double getMaxBidding(List<UserBid> userBids, Double minimumBasePrice) {
		Double max = minimumBasePrice;
		for (UserBid userBid : userBids) {
			max = Double.max(userBid.getAmount(), max);
		}
		return max;
	}
}