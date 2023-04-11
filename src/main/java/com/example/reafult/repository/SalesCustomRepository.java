package com.example.reafult.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Sales;

@Repository(value = "salesCustomRepository")
public class SalesCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Sales> findByMothInCheckinDate(Date startDate, Date endDate) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Sales> criteria = builder.createQuery(Sales.class);
		Root<Sales> salesRoot = criteria.from(Sales.class);
		criteria.select(salesRoot);
		criteria.where(builder.between(salesRoot.get("checkinDate"), startDate,endDate));
		Query query = entityManager.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Sales> list =  query.getResultList();
		return list;

	}

	public List<Sales> findByFormCheckTypeDateStatus(Date checkInDate,Date checkOutDate,String type,Integer price) throws HibernateException {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Sales> criteria = builder.createQuery(Sales.class);
		Root<Sales> salesRoot = criteria.from(Sales.class);
		criteria.select(salesRoot);
		criteria.where(builder.and(builder.equal(salesRoot.get("room").get("type"), type),		
				builder.lt(salesRoot.get("room").get("price"), price),				
				builder.or(builder.and(builder.between(salesRoot.get("checkinDate"), checkInDate, checkOutDate),
										builder.between(salesRoot.get("checkoutDate"), checkInDate, checkOutDate)),
							builder.and(builder.lessThan(salesRoot.get("checkinDate"), checkInDate),
										builder.greaterThan(salesRoot.get("checkoutDate"), checkInDate)),
							builder.and(builder.lessThan(salesRoot.get("checkinDate"), checkOutDate),
									builder.greaterThan(salesRoot.get("checkoutDate"), checkOutDate)))
				,
				builder.equal(salesRoot.get("status"), 0)));
		Query query = entityManager.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Sales> list = query.getResultList();
		return list;

	}

	public List<Sales> findByFormCheckRoomNameStatus(String roomName) throws HibernateException {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Sales> criteria = builder.createQuery(Sales.class);
		Root<Sales> salesRoot = criteria.from(Sales.class);
		criteria.select(salesRoot);
		criteria.where(builder.and(builder.equal(salesRoot.get("room").get("roomName"), roomName),
				builder.equal(salesRoot.get("status"), 0)));
		Query query = entityManager.createQuery(criteria);
		@SuppressWarnings("unchecked")
		List<Sales> list =  query.getResultList();
		return list;
	}
}
