package com.bidding.system.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserBid implements Serializable {

	private static final long serialVersionUID = 1596161991961L;

	@Id
	@GeneratedValue
	@Column(length = 15)
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

	public UserBid(String bidId, User user, Double amount, boolean isAccepted) {
		this.bidId = bidId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((bidId == null) ? 0 : bidId.hashCode());
		result = prime * result + (isAccepted ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBid other = (UserBid) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (bidId == null) {
			if (other.bidId != null)
				return false;
		} else if (!bidId.equals(other.bidId))
			return false;
		if (isAccepted != other.isAccepted)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBid [bidId=" + bidId + ", user=" + user + ", amount=" + amount + ", isAccepted=" + isAccepted + "]";
	}

}
