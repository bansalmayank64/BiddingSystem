package com.bidding.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bidding.system.model.Auction;
import com.bidding.system.model.AuctionStatus;
import com.bidding.system.model.Item;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String>, JpaSpecificationExecutor<Auction> {

	Optional<Auction> findByItemAndAuctionStatus(Item item, AuctionStatus auctionStatus);

}