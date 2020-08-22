package com.bidding.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bidding.system.model.Auction;
import com.bidding.system.model.UserBid;

@Repository
public interface UserBidRepository extends JpaRepository<UserBid, String> {

	List<UserBid> findAllByAuctionAndIsAccepted(Auction auction, boolean isAccepted);

}
