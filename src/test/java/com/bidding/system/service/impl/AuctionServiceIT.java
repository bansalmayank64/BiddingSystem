package com.bidding.system.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bidding.system.dto.AuctionBidRequestDto;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.exception.AuctionNotFoundException;
import com.bidding.system.exception.BidNotAcceptedException;
import com.bidding.system.model.AuctionStatus;
import com.bidding.system.model.UserBid;
import com.bidding.system.repository.UserBidRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctionServiceIT {

	@Autowired
	private UserBidRepository userBidRepository;

	@Autowired
	private AuctionServiceImpl auctionService;

	@Test
	public void shouldFetchAllAuctions() {
		// Given
		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		assertEquals(3L, fetchAllAuctions.getResultsAvailable());
		assertEquals(3, fetchAllAuctions.getResultsReturned());

		assertEquals(2500d, fetchAllAuctions.getData().get(0).getHighestBidAmount());
		assertEquals(100d, fetchAllAuctions.getData().get(0).getStepRate());
		assertEquals("TT123ki98011", fetchAllAuctions.getData().get(0).getItemCode());

		assertEquals(6500d, fetchAllAuctions.getData().get(2).getHighestBidAmount());
		assertEquals(500d, fetchAllAuctions.getData().get(2).getStepRate());
		assertEquals("HTK718adg1123", fetchAllAuctions.getData().get(2).getItemCode());
	}

	@Test
	public void shouldFetchAllAuctionsGivenPagination() {
		// Given
		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		request.setStatus(AuctionStatus.RUNNING);
		request.setLimit(2);
		request.setStartFrom(0);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		assertEquals(3L, fetchAllAuctions.getResultsAvailable());
		assertEquals(2, fetchAllAuctions.getResultsReturned());

		assertEquals(2500d, fetchAllAuctions.getData().get(0).getHighestBidAmount());
		assertEquals(100d, fetchAllAuctions.getData().get(0).getStepRate());
		assertEquals("TT123ki98011", fetchAllAuctions.getData().get(0).getItemCode());

		assertEquals(3500d, fetchAllAuctions.getData().get(1).getHighestBidAmount());
		assertEquals(200d, fetchAllAuctions.getData().get(1).getStepRate());
		assertEquals("VKh128901111", fetchAllAuctions.getData().get(1).getItemCode());
	}

	@Test
	public void shouldPlaceBid() throws AuctionNotFoundException, BidNotAcceptedException {
		// Given
		String itemCode = "TT123ki98011";
		AuctionBidRequestDto auctionBidRequestDto = new AuctionBidRequestDto(9000d, "userId");

		// When
		auctionService.placeBid(itemCode, auctionBidRequestDto);

		// Then
		List<UserBid> userBids = userBidRepository.findAll();
		assertEquals(9000d, userBids.get(0).getAmount());
	}

}
