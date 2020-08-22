package com.bidding.system.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.bidding.system.dto.AuctionModel;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.model.Auction;
import com.bidding.system.model.AuctionStatus;
import com.bidding.system.model.Item;
import com.bidding.system.model.User;
import com.bidding.system.model.UserBid;
import com.bidding.system.repository.AuctionRepository;
import com.bidding.system.repository.AuctionRepositoryAddMultiplePredicateSpecification;

/*
 * Test class for AuctionService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctionServiceTest {

	@Captor
	private ArgumentCaptor<AuctionRepositoryAddMultiplePredicateSpecification> argSpecificationCaptor;

	@Captor
	private ArgumentCaptor<Pageable> argPageableCaptor;

	@Mock
	private AuctionRepository auctionRepository;

	@InjectMocks
	private AuctionServiceImpl auctionService;

	@Test
	public void shouldFetchAllAuctions() {
		// Given
		Auction auction = getAuction();
		given(auctionRepository.findAll(any(AuctionRepositoryAddMultiplePredicateSpecification.class)))
				.willReturn(Arrays.asList(auction));

		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		FetchAuctionsResponseDto expectedFetchAllAuctions = getExpectedFetchAuctionsResponse();
		assertEquals(expectedFetchAllAuctions, fetchAllAuctions);

		then(auctionRepository).should().findAll(argSpecificationCaptor.capture());
		assertEquals(AuctionStatus.RUNNING, argSpecificationCaptor.getValue().getAuctionStatus());
		then(auctionRepository).shouldHaveNoMoreInteractions();
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
		FetchAuctionsResponseDto expectedFetchAllAuctions = new FetchAuctionsResponseDto();
		expectedFetchAllAuctions.setResultsAvailable(1L);
		expectedFetchAllAuctions.setResultsReturned(1);
		List<AuctionModel> data = Arrays.asList(new AuctionModel("auctionId", "itemCode", 0d, 100d));
		expectedFetchAllAuctions.setData(data);
		assertEquals(expectedFetchAllAuctions, fetchAllAuctions);

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

		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);
		request.setLimit(10);
		request.setStartFrom(0);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		FetchAuctionsResponseDto expectedFetchAllAuctions = getExpectedFetchAuctionsResponse();
		assertEquals(expectedFetchAllAuctions, fetchAllAuctions);

		then(auctionRepository).should().findAll(argSpecificationCaptor.capture(), argPageableCaptor.capture());
		assertEquals(AuctionStatus.RUNNING, argSpecificationCaptor.getValue().getAuctionStatus());
		assertEquals(10, argPageableCaptor.getValue().getPageSize());
		assertEquals(0, argPageableCaptor.getValue().getOffset());
		then(auctionRepository).shouldHaveNoMoreInteractions();
	}

	private FetchAuctionsResponseDto getExpectedFetchAuctionsResponse() {
		FetchAuctionsResponseDto expectedFetchAllAuctions = new FetchAuctionsResponseDto();
		expectedFetchAllAuctions.setResultsAvailable(1L);
		expectedFetchAllAuctions.setResultsReturned(1);
		List<AuctionModel> data = Arrays.asList(new AuctionModel("auctionId", "itemCode", 8000d, 100d));
		expectedFetchAllAuctions.setData(data);
		return expectedFetchAllAuctions;
	}

	private Auction getAuction() {
		User user = new User("userId", "email", "firstName", "lastName");
		List<UserBid> userBids = Arrays.asList(new UserBid(user, 6000d, true), new UserBid(user, 8000d, true),
				new UserBid(user, 5500d, true));
		Item item = new Item("itemCode", "name", "description");
		Auction auction = new Auction(item, AuctionStatus.RUNNING, 5000d, 100d, userBids);
		return auction;
	}

}
