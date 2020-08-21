package com.bidding.system.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class Auction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	// @DBRef
	private Product product;
	private AuctionStatus auctionStatus;
	private Double startingAmount;
	// @DBRef
	private Set<User> participants;
	private List<BidInformation> biddings;

	public Auction() {
		super();
	}

	public Auction(String id, Product product, AuctionStatus auctionStatus, Double startingAmount,
			Set<User> participants, List<BidInformation> biddings) {
		this.id = id;
		this.product = product;
		this.auctionStatus = auctionStatus;
		this.startingAmount = startingAmount;
		this.participants = participants;
		this.setBiddings(biddings);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(AuctionStatus auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public Double getStartingAmount() {
		return startingAmount;
	}

	public void setStartingAmount(Double startingAmount) {
		this.startingAmount = startingAmount;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public List<BidInformation> getBiddings() {
		return biddings;
	}

	public void setBiddings(List<BidInformation> biddings) {
		this.biddings = biddings;
	}

	@Override
	public String toString() {
		return "Auction [id=" + id + ", product=" + product + ", auctionStatus=" + auctionStatus + ", startingAmount="
				+ startingAmount + ", participants=" + participants + ", biddings=" + biddings + "]";
	}

}
