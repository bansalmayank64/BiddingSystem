package com.bidding.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.system.dto.Response;
import com.bidding.system.dto.ResponseTypeEnum;
import com.bidding.system.model.Auction;
import com.bidding.system.service.AuctionService;

@RestController
@RequestMapping("auction")
public class AuctionController {

	@Autowired
	AuctionService auctionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Response<?> getAllAuctions() {
		try {
			return new Response<List<Auction>>(ResponseTypeEnum.SUCCESS, auctionService.getAllActive());
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

}
