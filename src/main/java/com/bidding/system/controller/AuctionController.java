package com.bidding.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.service.AuctionService;

@RestController
@RequestMapping("auction")
public class AuctionController {

	@Autowired
	AuctionService auctionService;

	@GetMapping
	public FetchAuctionsResponseDto fetchAllAuctions(FetchAuctionsRequestDto fetchAuctionsRequestDto) {
		return auctionService.fetchAllAuctions(fetchAuctionsRequestDto);
	}

}
