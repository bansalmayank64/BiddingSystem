package com.bidding.system.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserBid implements Serializable {

	private static final long serialVersionUID = 1596161991961L;

	@Id
	@Column(length = 36)
	private String bidId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auctionId")
	private Auction auction;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User user;

	private Double amount;
	private boolean isAccepted;

	public UserBid() {
		super();
	}

	public UserBid(User user, Double amount, boolean isAccepted) {
		this.bidId = UUID.randomUUID().toString();
		this.user = user;
		this.amount = amount;
		this.isAccepted = isAccepted;
	}

	public String getId() {
		return bidId;
	}

	public void setId(String id) {
		this.bidId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserBid [bidId=").append(bidId).append(", auction=").append(auction).append(", user=")
				.append(user).append(", amount=").append(amount).append(", isAccepted=").append(isAccepted).append("]");
		return builder.toString();
	}

}
