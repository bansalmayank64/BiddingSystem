package com.bidding.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bidding.system.model.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String>, JpaSpecificationExecutor<Auction> {

}
