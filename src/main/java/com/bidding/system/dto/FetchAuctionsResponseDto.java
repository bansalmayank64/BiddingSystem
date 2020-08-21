package com.bidding.system.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FetchAuctionsResponseDto implements Serializable {

	private static final long serialVersionUID = 16961399613131L;

	private Integer resultsReturned;
	private Long resultsAvailable;
	private List<AuctionModel> data;

	public Integer getResultsReturned() {
		return resultsReturned;
	}

	public void setResultsReturned(Integer resultsReturned) {
		this.resultsReturned = resultsReturned;
	}

	public Long getResultsAvailable() {
		return resultsAvailable;
	}

	public void setResultsAvailable(Long resultsAvailable) {
		this.resultsAvailable = resultsAvailable;
	}

	public List<AuctionModel> getData() {
		return data;
	}

	public void setData(List<AuctionModel> data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, resultsAvailable, resultsReturned);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FetchAuctionsResponseDto other = (FetchAuctionsResponseDto) obj;
		return Objects.equals(data, other.data) && Objects.equals(resultsAvailable, other.resultsAvailable)
				&& Objects.equals(resultsReturned, other.resultsReturned);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FetchAuctionsResponseDto [resultsReturned=").append(resultsReturned)
				.append(", resultsAvailable=").append(resultsAvailable).append(", data=").append(data).append("]");
		return builder.toString();
	}

}
