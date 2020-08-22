package com.bidding.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bidding.system.dto.AuctionModel;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.model.Auction;
import com.bidding.system.model.UserBid;
import com.bidding.system.repository.AuctionRepository;
import com.bidding.system.repository.AuctionRepositoryAddMultiplePredicateSpecification;
import com.bidding.system.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionRepository auctionRepository;

	@Override
	public FetchAuctionsResponseDto fetchAllAuctions(FetchAuctionsRequestDto request) {
		AuctionRepositoryAddMultiplePredicateSpecification specification = new AuctionRepositoryAddMultiplePredicateSpecification(
				request.getAuctionStatus());
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
			AuctionModel auctionModel = new AuctionModel(auction.getAuctionId(), auction.getItem().getItemCode(),
					getMaxBidding(auction.getUserBids()), auction.getStepRate());
			auctionModels.add(auctionModel);
		});
		response.setData(auctionModels);
		return response;
	}

	private Double getMaxBidding(List<UserBid> userBids) {
		Double max = 0d;
		for (UserBid userBid : userBids) {
			max = Double.max(userBid.getAmount(), max);
		}
		return max;
	}
}