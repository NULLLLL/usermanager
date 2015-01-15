package com.usermanager.user.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sf.json.JSONObject;

import com.usermanager.user.entity.User;
import com.util.StringHelper;

public class UserDaoImpl implements UserDaoCustom {

	private static final String COMMONHQL = " select distinct u from User as u ";

	private static final String COUNTHQL = " select count(distinct u.id) from User as u ";

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(String params) {
		StringBuilder hql = new StringBuilder(COMMONHQL);
		StringBuilder makeWhere = makeWhere(params);
		hql.append(makeWhere).append(" order by id asc");
		Query query = em.createQuery(hql.toString());
		return query.getResultList();
	}

	public Long getCountWithParams(String params) {
		StringBuilder hql = new StringBuilder(COUNTHQL);
		StringBuilder makeWhere = makeWhere(params);
		hql.append(makeWhere);
		Query query = em.createQuery(hql.toString());
		return (Long) query.getSingleResult();
	}

	private StringBuilder makeWhere(String params) {
		StringBuilder hql = new StringBuilder();
		hql.append(" where 1=1 ");
		if (StringHelper.isEmpty(params)) {
			return hql;
		}
		JSONObject jsonObject = JSONObject.fromObject(params);
		if (jsonObject.has("name") && StringHelper.isNotEmpty(jsonObject.getString("name")))
			hql.append(" and u.name like '%").append(jsonObject.getString("name")).append("%' ");

		if (jsonObject.has("loginName") && StringHelper.isNotEmpty(jsonObject.getString("loginName")))
			hql.append(" and u.loginName like '%").append(jsonObject.getString("loginName")).append("%' ");

		if (jsonObject.has("startregisterDate") && StringHelper.isNotEmpty(jsonObject.getString("startregisterDate"))) {
			hql.append(" and u.registerDate >='").append(jsonObject.getString("startregisterDate")).append("' ");
		}

		if (jsonObject.has("endregisterDate") && StringHelper.isNotEmpty(jsonObject.getString("endregisterDate"))) {
			hql.append(" and u.registerDate <='").append(jsonObject.getString("endregisterDate")).append("' ");
		}
		return hql;
	}
}
