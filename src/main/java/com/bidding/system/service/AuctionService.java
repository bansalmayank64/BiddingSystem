package com.bidding.system.service;

import java.util.List;

import com.bidding.system.model.Auction;

public interface AuctionService {

	List<Auction> getAllActive() throws Exception;
}
