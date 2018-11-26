package com.sri.spring.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sri.spring.model.RestRequest;
import com.sri.spring.model.RestResponse;
import com.sri.spring.model.Task;
import com.sri.spring.model.Vendor;
import com.sri.spring.model.VendorTask;
import com.sri.spring.model.VendorTaskRequest;
import com.sri.spring.model.VendorWorkLoadResponse;

@Repository
public class VendorDAOImpl implements VendorDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addVendor(Vendor p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("vendor saved successfully, vendor Details="+p);
	}

	@Override
	public void updateVendor(Vendor p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("vendor updated successfully, vendor Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> listVendors() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vendor> vendorsList = session.createQuery("from Vendor").list();
		for(Vendor p : vendorsList){
			logger.info("vendor List::"+p);
		}
		return vendorsList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTasks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Task> tasksList = session.createQuery("from Task").list();
		for(Task p : tasksList){
			logger.info("tasks List::"+p);
		}
		return tasksList;
	}

	@Override
	public Vendor getVendorById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Vendor p = (Vendor) session.load(Vendor.class, new Integer(id));
		logger.info("vendor loaded successfully, vendor details="+p);
		return p;
	}

	@Override
	public void removeVendor(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Vendor p = (Vendor) session.load(Vendor.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("vendor deleted successfully, vendor details="+p);
	}

	@Override
	public void addTask(Task p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("task saved successfully, task Details="+p);
	}

	@Override
	public void updateTask(Task p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("task updated successfully, task Details="+p);
	}

	@Override
	public Object getTaskById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Task p = (Task) session.load(Task.class, new Integer(id));
		logger.info("Task loaded successfully, task details="+p);
		return p;
	}

	@Override
	public void removeTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Task p = (Task) session.load(Task.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("task deleted successfully, task details="+p);
	}

	@Override
	public void shareWorkLoad(RestRequest req) {
		Session session = this.sessionFactory.openSession();
		Session session2 = this.sessionFactory.openSession();
		List<Vendor> vendorList = new ArrayList<Vendor>();
		List<Task> taskList = session.createQuery("from Task").list();
		
		List<VendorTaskRequest> vendorReqList = new ArrayList<VendorTaskRequest>();
		vendorReqList = req.getVendorTaskRequest();
		for (VendorTaskRequest row : vendorReqList) {
			Vendor vendorObj = new Vendor();
			String vendor =row.getVendor();
			
			int percentage = row.getPercentage();
			System.out.println("vendor ="+vendor+" percentage ="+percentage);
				int size = taskList.size();
				int taskPer = (percentage*size)/100;
				VendorTask v = new VendorTask();
				for(Task p : taskList){
				v.setTaskId(Integer.toString(p.getId()));
				v.setVendorId(vendor);
				vendorList = session2.createQuery("from Vendor where id="+vendor).list();
				for(Vendor ve : vendorList){
					ve.setNumberOfTasks(taskPer);
					session2.saveOrUpdate(ve);
					session2.flush();
				}
			}
		}
	}
	@Override
	public List<VendorWorkLoadResponse> showVendorWorkLoad() {
		Session session = this.sessionFactory.openSession();
		List<VendorWorkLoadResponse> vendorWorkLoadResponse = new ArrayList<VendorWorkLoadResponse>();
		VendorTask v = new VendorTask();
		List<Vendor> vendorList = new ArrayList<Vendor>();
		vendorList = session.createQuery(" from Vendor ").list();
		for(Vendor ve : vendorList){
	        VendorWorkLoadResponse listRes = new VendorWorkLoadResponse();
	        listRes.setNumberOfTasks(String.valueOf(ve.getNumberOfTasks()));
	        listRes.setVendorName(ve.getName());
	        vendorWorkLoadResponse.add(listRes);
			
		}
		return vendorWorkLoadResponse;
	}

	}
