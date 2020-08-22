package com.bidding.system.service.impl;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.bidding.system.dto.AuctionBidRequestDto;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.exception.AuctionNotFoundException;
import com.bidding.system.exception.BidNotAcceptedException;
import com.bidding.system.model.Auction;
import com.bidding.system.model.AuctionStatus;
import com.bidding.system.model.Item;
import com.bidding.system.model.User;
import com.bidding.system.model.UserBid;
import com.bidding.system.repository.AuctionRepository;
import com.bidding.system.repository.AuctionRepositoryAddMultiplePredicateSpecification;
import com.bidding.system.repository.ItemRepository;
import com.bidding.system.repository.UserBidRepository;

/*
 * Test class for AuctionService
 */
@ExtendWith(MockitoExtension.class)
public class AuctionServiceTest {

	@Captor
	private ArgumentCaptor<AuctionRepositoryAddMultiplePredicateSpecification> argSpecificationCaptor;

	@Captor
	private ArgumentCaptor<Pageable> argPageableCaptor;

	@Mock
	private AuctionRepository auctionRepository;

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private UserBidRepository userBidRepository;

	@InjectMocks
	private AuctionServiceImpl auctionService;

	@Test
	public void shouldFetchAllAuctions() {
		// Given
		Auction auction = getAuction();
		given(auctionRepository.findAll(any(AuctionRepositoryAddMultiplePredicateSpecification.class)))
				.willReturn(Arrays.asList(auction));

		given(userBidRepository.findAllByAuctionAndIsAccepted(auction, true)).willReturn(getUserBids());

		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		assertEquals(1L, fetchAllAuctions.getResultsAvailable());
		assertEquals(1, fetchAllAuctions.getResultsReturned());
		assertEquals(8000d, fetchAllAuctions.getData().get(0).getHighestBidAmount());
		assertEquals(100d, fetchAllAuctions.getData().get(0).getStepRate());
		assertEquals("itemCode", fetchAllAuctions.getData().get(0).getItemCode());

		then(auctionRepository).should().findAll(argSpecificationCaptor.capture());
		assertEquals(AuctionStatus.RUNNING, argSpecificationCaptor.getValue().getAuctionStatus());
		then(auctionRepository).shouldHaveNoMoreInteractions();

		then(userBidRepository).should().findAllByAuctionAndIsAccepted(auction, true);
		then(userBidRepository).shouldHaveNoMoreInteractions();
	}

	@Test
	public void shouldFetchAllAuctionsGivenEmptyUserBids() {
		// Given
		Auction auction = new Auction(new Item("itemCode", "name", "description"), AuctionStatus.RUNNING, 5000d, 100d,
				Collections.emptyList());
		given(auctionRepository.findAll(any(AuctionRepositoryAddMultiplePredicateSpecification.class)))
				.willReturn(Arrays.asList(auction));

		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		assertEquals(1L, fetchAllAuctions.getResultsAvailable());
		assertEquals(1, fetchAllAuctions.getResultsReturned());
		assertEquals(5000d, fetchAllAuctions.getData().get(0).getHighestBidAmount());
		assertEquals(100d, fetchAllAuctions.getData().get(0).getStepRate());
		assertEquals("itemCode", fetchAllAuctions.getData().get(0).getItemCode());

		then(auctionRepository).should().findAll(argSpecificationCaptor.capture());
		assertEquals(AuctionStatus.RUNNING, argSpecificationCaptor.getValue().getAuctionStatus());
		then(auctionRepository).shouldHaveNoMoreInteractions();
	}

	@Test
	public void shouldFetchAllAuctionsGivenPaginationParam() {
		// Given
		Auction auction = getAuction();
		Page<Auction> auctionsPage = new PageImpl<>(Arrays.asList(auction));
		given(auctionRepository.findAll(any(AuctionRepositoryAddMultiplePredicateSpecification.class),
				any(Pageable.class))).willReturn(auctionsPage);

		given(userBidRepository.findAllByAuctionAndIsAccepted(auction, true)).willReturn(getUserBids());

		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);
		request.setLimit(10);
		request.setStartFrom(0);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		assertEquals(1L, fetchAllAuctions.getResultsAvailable());
		assertEquals(1, fetchAllAuctions.getResultsReturned());
		assertEquals(8000d, fetchAllAuctions.getData().get(0).getHighestBidAmount());
		assertEquals(100d, fetchAllAuctions.getData().get(0).getStepRate());
		assertEquals("itemCode", fetchAllAuctions.getData().get(0).getItemCode());

		then(auctionRepository).should().findAll(argSpecificationCaptor.capture(), argPageableCaptor.capture());
		assertEquals(AuctionStatus.RUNNING, argSpecificationCaptor.getValue().getAuctionStatus());
		assertEquals(10, argPageableCaptor.getValue().getPageSize());
		assertEquals(0, argPageableCaptor.getValue().getOffset());
		then(auctionRepository).shouldHaveNoMoreInteractions();

		then(userBidRepository).should().findAllByAuctionAndIsAccepted(auction, true);
		then(userBidRepository).shouldHaveNoMoreInteractions();
	}

	@Test
	public void shouldPlaceBid() throws AuctionNotFoundException, BidNotAcceptedException {
		// Given
		String itemCode = "itemCode";
		AuctionBidRequestDto auctionBidRequestDto = new AuctionBidRequestDto(9000d, "userId");

		Item item = getItem();
		Optional<Item> findItemById = Optional.of(item);
		given(itemRepository.findById(itemCode)).willReturn(findItemById);
		Auction auction = getAuction();
		Optional<Auction> findAuctionByItem = Optional.of(auction);
		given(auctionRepository.findByItemAndAuctionStatus(item, AuctionStatus.RUNNING)).willReturn(findAuctionByItem);
		given(auctionRepository.save(auction)).willReturn(auction);

		// When
		auctionService.placeBid(itemCode, auctionBidRequestDto);

		// Then
		then(itemRepository).should().findById(itemCode);
		then(itemRepository).shouldHaveNoMoreInteractions();

		then(auctionRepository).should().findByItemAndAuctionStatus(item, AuctionStatus.RUNNING);
		then(auctionRepository).should().save(auction);
		then(auctionRepository).shouldHaveNoMoreInteractions();
	}

	@Test
	public void shouldPlaceBidGivenInvalidBidAmount() throws AuctionNotFoundException, BidNotAcceptedException {
		// Given
		String itemCode = "itemCode";
		AuctionBidRequestDto auctionBidRequestDto = new AuctionBidRequestDto(7000d, "userId");

		Item item = getItem();
		Optional<Item> findItemById = Optional.of(item);
		given(itemRepository.findById(itemCode)).willReturn(findItemById);

		Auction auction = getAuction();

		given(userBidRepository.findAllByAuctionAndIsAccepted(auction, true)).willReturn(getUserBids());

		Optional<Auction> findAuctionByItem = Optional.of(auction);
		given(auctionRepository.findByItemAndAuctionStatus(item, AuctionStatus.RUNNING)).willReturn(findAuctionByItem);
		given(auctionRepository.save(auction)).willReturn(auction);

		// When
		Throwable thrown = catchThrowable(() -> auctionService.placeBid(itemCode, auctionBidRequestDto));

		// Then
		assertEquals(BidNotAcceptedException.class, thrown.getClass());
		then(itemRepository).should().findById(itemCode);
		then(itemRepository).shouldHaveNoMoreInteractions();

		then(auctionRepository).should().findByItemAndAuctionStatus(item, AuctionStatus.RUNNING);
		then(auctionRepository).should().save(auction);
		then(auctionRepository).shouldHaveNoMoreInteractions();

		then(userBidRepository).should().findAllByAuctionAndIsAccepted(auction, true);
		then(userBidRepository).shouldHaveNoMoreInteractions();
	}

	@Test
	public void shouldPlaceBidGivenAuctionNotFound() throws AuctionNotFoundException, BidNotAcceptedException {
		// Given
		String itemCode = "itemCode";
		AuctionBidRequestDto auctionBidRequestDto = new AuctionBidRequestDto(7000d, "userId");

		Optional<Item> findItemById = Optional.empty();
		given(itemRepository.findById(itemCode)).willReturn(findItemById);

		// When
		Throwable thrown = catchThrowable(() -> auctionService.placeBid(itemCode, auctionBidRequestDto));

		// Then
		assertEquals(AuctionNotFoundException.class, thrown.getClass());
		then(itemRepository).should().findById(itemCode);
		then(itemRepository).shouldHaveNoMoreInteractions();
		then(auctionRepository).shouldHaveNoInteractions();
	}

	private Auction getAuction() {
		List<UserBid> userBids = getUserBids();
		Auction auction = new Auction(getItem(), AuctionStatus.RUNNING, 5000d, 100d, userBids);
		return auction;
	}

	private List<UserBid> getUserBids() {
		User user = new User("userId", "email", "firstName", "lastName");
		List<UserBid> userBids = new ArrayList<>();
		userBids.add(new UserBid(user, 6000d, true));
		userBids.add(new UserBid(user, 8000d, true));
		userBids.add(new UserBid(user, 5500d, true));
		return userBids;
	}

	private Item getItem() {
		Item item = new Item("itemCode", "name", "description");
		return item;
	}

}
