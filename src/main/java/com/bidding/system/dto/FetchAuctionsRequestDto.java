package com.bidding.system.dto;

import java.io.Serializable;
import java.util.Objects;

import com.bidding.system.model.AuctionStatus;

public class FetchAuctionsRequestDto implements Serializable {

	private static final long serialVersionUID = 666669131319891L;

	private Integer limit;
	private Integer startFrom;
	private AuctionStatus auctionStatus;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(Integer startFrom) {
		this.startFrom = startFrom;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(AuctionStatus auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auctionStatus, limit, startFrom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FetchAuctionsRequestDto other = (FetchAuctionsRequestDto) obj;
		return auctionStatus == other.auctionStatus && Objects.equals(limit, other.limit)
				&& Objects.equals(startFrom, other.startFrom);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FetchAuctionsRequestDto [limit=").append(limit).append(", startFrom=").append(startFrom)
				.append(", auctionStatus=").append(auctionStatus).append("]");
		return builder.toString();
	}
}
