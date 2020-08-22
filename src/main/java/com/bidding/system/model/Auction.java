package com.bidding.system.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Auction implements Serializable {

	private static final long serialVersionUID = 12342342L;

	@Id
	@Column(length = 36)
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
	@JoinColumn(name = "auctionId")
	private List<UserBid> userBids;

	@Version
	private Long version;

	public Auction() {
		super();
	}

	public Auction(Item item, AuctionStatus auctionStatus, Double minimumBasePrice, Double stepRate,
			List<UserBid> userBids) {
		this.auctionId = UUID.randomUUID().toString();
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Auction [auctionId=").append(auctionId).append(", item=").append(item)
				.append(", auctionStatus=").append(auctionStatus).append(", minimumBasePrice=").append(minimumBasePrice)
				.append(", stepRate=").append(stepRate).append(", userBids=").append(userBids).append(", version=")
				.append(version).append("]");
		return builder.toString();
	}

}
