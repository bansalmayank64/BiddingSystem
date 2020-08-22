package com.bidding.system.service;

import com.bidding.system.dto.AuctionBidRequestDto;
import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;
import com.bidding.system.exception.AuctionNotFoundException;
import com.bidding.system.exception.BidNotAcceptedException;

public interface AuctionService {

	FetchAuctionsResponseDto fetchAllAuctions(FetchAuctionsRequestDto request);

	void placeBid(String itemCode, AuctionBidRequestDto auctionBidRequestDto)
			throws AuctionNotFoundException, BidNotAcceptedException;
}
