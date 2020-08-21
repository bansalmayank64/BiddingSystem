package com.bidding.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidding.system.model.Auction;
import com.bidding.system.repository.AuctionRepository;
import com.bidding.system.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionRepository auctionRepository;

	@Override
	public List<Auction> getAllActive() throws Exception {
		return null;
	}
}