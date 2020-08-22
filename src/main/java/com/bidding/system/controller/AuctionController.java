package com.bidding.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.system.dto.AuctionBidRequestDto;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.exception.AuctionNotFoundException;
import com.bidding.system.exception.BidNotAcceptedException;
import com.bidding.system.service.AuctionService;

@RestController
@RequestMapping("auction")
public class AuctionController {

	@Autowired
	AuctionService auctionService;

	@GetMapping
	public ResponseEntity<FetchAuctionsResponseDto> fetchAllAuctions(FetchAuctionsRequestDto fetchAuctionsRequestDto) {
		return ResponseEntity.ok().body(auctionService.fetchAllAuctions(fetchAuctionsRequestDto));
	}

	@PostMapping(value = "/{itemCode}/bid")
	public ResponseEntity<Object> placeBid(@PathVariable String itemCode,
			@RequestBody AuctionBidRequestDto auctionBidRequestDto)
			throws AuctionNotFoundException, BidNotAcceptedException {
		auctionService.placeBid(itemCode, auctionBidRequestDto);
		return ResponseEntity.created(null).build();
	}

}
