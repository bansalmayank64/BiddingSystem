package com.bidding.system.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.model.Auction;
import com.bidding.system.repository.AuctionRepository;
import com.bidding.system.repository.AuctionRepositoryAddMultiplePredicateSpecification;

/*
 * Test class for AuctionService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuctionServiceTest {

	@Mock
	private AuctionRepository auctionRepository;

	@InjectMocks
	private AuctionServiceImpl auctionService;

	@Test
	public void shouldFetchAllAuctions() {
		// Given
		FetchAuctionsRequestDto request = new FetchAuctionsRequestDto();
		Auction auction = new Auction();
		Page<Auction> response = new PageImpl<>(Arrays.asList(auction));
		given(auctionRepository.findAll(any(AuctionRepositoryAddMultiplePredicateSpecification.class),
				any(Pageable.class))).willReturn(response);

		// When
		FetchAuctionsResponseDto fetchAllAuctions = auctionService.fetchAllAuctions(request);

		// Then
		// assertThat(response.getTotalElements()).,
		// fetchAllAuctions.getResultsAvailable());
		// then(auctionRepository).should().findAll(request);
		// then(auctionRepository).shouldHaveNoMoreInteractions();
	}

}
