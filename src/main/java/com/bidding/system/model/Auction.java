package com.bidding.system.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Auction implements Serializable {

	private static final long serialVersionUID = 12342342L;

	@Id
	@GeneratedValue
	@Column(length = 15)
	private String auctionId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemCode")
	private Item item;

	@Column(columnDefinition = "enum('RUNNING','OVER')")
	@Enumerated(EnumType.STRING)
	private AuctionStatus auctionStatus;

	private Double minimumBasePrice;
	private Double stepRate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bidId")
	private List<UserBid> userBids;

	public Auction() {
		super();
	}

	public Auction(String id, Item item, AuctionStatus auctionStatus, Double minimumBasePrice, Double stepRate,
			List<UserBid> userBids) {
		this.auctionId = id;
		this.item = item;
		this.auctionStatus = auctionStatus;
		this.minimumBasePrice = minimumBasePrice;
		this.stepRate = stepRate;
		this.setUserBids(userBids);
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(AuctionStatus auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public Double getMinimumBasePrice() {
		return minimumBasePrice;
	}

	public void setMinimumBasePrice(Double minimumBasePrice) {
		this.minimumBasePrice = minimumBasePrice;
	}

	public Double getStepRate() {
		return stepRate;
	}

	public void setStepRate(Double stepRate) {
		this.stepRate = stepRate;
	}

	public List<UserBid> getUserBids() {
		return userBids;
	}

	public void setUserBids(List<UserBid> userBids) {
		this.userBids = userBids;
	}

	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", item=" + item + ", auctionStatus=" + auctionStatus
				+ ", minimumBasePrice=" + minimumBasePrice + ", stepRate=" + stepRate + ", userBids=" + userBids + "]";
	}
}
