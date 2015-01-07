package com.usermanager.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TaskDaoImpl implements TaskDaoCustom{
	
	private static final String COMMONSQL = "select t from Task as t ";
	
	private static final String COUNTSQL = "select distinct t from Task as t ";
	
	@PersistenceContext
	private EntityManager em;
	
	

}
