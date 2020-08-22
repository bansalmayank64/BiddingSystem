package com.bidding.system.repository;

import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bidding.system.model.Auction;
import com.bidding.system.model.AuctionStatus;

public class AuctionRepositoryAddMultiplePredicateSpecification implements Specification<Auction> {

	private static final long serialVersionUID = 2775385172398561937L;

	private AuctionStatus auctionStatus;

	public AuctionRepositoryAddMultiplePredicateSpecification(AuctionStatus auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	@Override
	public Predicate toPredicate(Root<Auction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (Objects.nonNull(auctionStatus)) {
			return criteriaBuilder.equal(root.get("auctionStatus"), auctionStatus);
		} else {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
	}

}
