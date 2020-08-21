package com.bidding.system.service;

import com.bidding.system.dto.FetchAuctionsRequestDto;
import com.bidding.system.dto.FetchAuctionsResponseDto;

public interface AuctionService {

	FetchAuctionsResponseDto fetchAllAuctions(FetchAuctionsRequestDto request);
}
