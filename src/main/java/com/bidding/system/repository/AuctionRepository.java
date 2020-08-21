package com.bidding.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bidding.system.model.Auction;
import com.bidding.system.model.Product;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {

	Auction findByProduct(Product product);
}
