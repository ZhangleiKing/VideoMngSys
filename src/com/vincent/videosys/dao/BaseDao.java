package com.vincent.videosys.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDao{

	@Autowired
	protected SessionFactory sessionFactory;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
}
