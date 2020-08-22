package com.bidding.system.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.service.AuctionService;

/*
 * Test class for AuctionController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctionControllerTest {

	@Mock
	private AuctionService auctionService;

	@InjectMocks
	private AuctionController auctionController;

	@Test
	public void shouldFetchAllAuctions() {
		// Given
		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		FetchAuctionsResponseDto response = new FetchAuctionsResponseDto();
		given(auctionService.fetchAllAuctions(request)).willReturn(response);

		// When
		ResponseEntity<FetchAuctionsResponseDto> actualResponse = auctionController.fetchAllAuctions(request);

		// Then
		assertEquals(response, actualResponse.getBody());
		then(auctionService).should().fetchAllAuctions(request);
		then(auctionService).shouldHaveNoMoreInteractions();
	}
}
