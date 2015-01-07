package com.usermanager.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.usermanager.entity.User;

public class UserDaoImpl implements UserDaoCustom {

	private static final String COMMONHQL = " select distinct u from User as u ";

	private static final String COUNTHQL = " select count(distinct u.id) from User as u ";

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(int pageSize, int pageNo, String order) {
		StringBuilder hql = new StringBuilder(COMMONHQL);
		hql.append(" order by id ").append(order);
		Query query = em.createQuery(hql.toString());
		int startIndex = pageNo * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public Long getCountWithParams(String params) {
		StringBuilder hql = new StringBuilder(COUNTHQL);
		Query query = em.createQuery(hql.toString());
		return (Long) query.getSingleResult();
	}

	private StringBuilder makeWhere() {
		StringBuilder hql = new StringBuilder();
		return hql;
	}
}
