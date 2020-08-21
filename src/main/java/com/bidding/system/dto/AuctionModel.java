package com.bidding.system.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuctionModel {

	private String auctionId;
	private String itemCode;
	private Double highestBidAmount;
	private Double stepRate;

	@JsonCreator
	public AuctionModel(@JsonProperty("auctionId") String auctionId, @JsonProperty("itemCode") String itemCode,
			@JsonProperty("highestBidAmount") Double highestBidAmount, @JsonProperty("stepRate") Double stepRate) {
		this.auctionId = auctionId;
		this.itemCode = itemCode;
		this.highestBidAmount = highestBidAmount;
		this.stepRate = stepRate;
	}

	public String getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getHighestBidAmount() {
		return highestBidAmount;
	}

	public void setHighestBidAmount(Double highestBidAmount) {
		this.highestBidAmount = highestBidAmount;
	}

	public Double getStepRate() {
		return stepRate;
	}

	public void setStepRate(Double stepRate) {
		this.stepRate = stepRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auctionId, highestBidAmount, itemCode, stepRate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuctionModel other = (AuctionModel) obj;
		return Objects.equals(auctionId, other.auctionId) && Objects.equals(highestBidAmount, other.highestBidAmount)
				&& Objects.equals(itemCode, other.itemCode) && Objects.equals(stepRate, other.stepRate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuctionModel [auctionId=").append(auctionId).append(", itemCode=").append(itemCode)
				.append(", highestBidAmount=").append(highestBidAmount).append(", stepRate=").append(stepRate)
				.append("]");
		return builder.toString();
	}

}
