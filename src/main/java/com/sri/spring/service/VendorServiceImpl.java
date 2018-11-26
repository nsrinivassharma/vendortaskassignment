package com.sri.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sri.spring.dao.VendorDAO;
import com.sri.spring.model.RestRequest;
import com.sri.spring.model.RestResponse;
import com.sri.spring.model.Task;
import com.sri.spring.model.Vendor;
import com.sri.spring.model.VendorTaskRequest;
import com.sri.spring.model.VendorWorkLoadResponse;

@Service
public class VendorServiceImpl implements VendorService {
	
	private VendorDAO vendorDAO;

	public void setvendorDAO(VendorDAO vendorDAO) {
		this.vendorDAO = vendorDAO;
	}

	@Override
	@Transactional
	public void addVendor(Vendor p) {
		this.vendorDAO.addVendor(p);
	}

	@Override
	@Transactional
	public void updateVendor(Vendor p) {
		this.vendorDAO.updateVendor(p);
	}

	@Override
	@Transactional
	public List<Vendor> listVendors() {
		return this.vendorDAO.listVendors();
	}

	@Override
	@Transactional
	public List<Task> listTasks() {
		return this.vendorDAO.listTasks();
	}

	@Override
	@Transactional
	public Vendor getVendorById(int id) {
		return this.vendorDAO.getVendorById(id);
	}

	@Override
	@Transactional
	public void removeVendor(int id) {
		this.vendorDAO.removeVendor(id);
	}

	@Override
	@Transactional
	public void addTask(Task p) {
		this.vendorDAO.addTask(p);
	}

	@Override
	@Transactional
	public void updateTask(Task p) {
		this.vendorDAO.updateTask(p);
	}

	@Override
	@Transactional
	public Object getTaskById(int id) {
		return this.vendorDAO.getTaskById(id);
	}

	@Override
	@Transactional
	public void removeTask(int id) {
		this.vendorDAO.removeTask(id);
	}

	@Override
	public RestResponse shareWorkLoad(RestRequest req) {
		int per=0;
		RestResponse res = new RestResponse();
		List<VendorTaskRequest> vendorReqList = new ArrayList<VendorTaskRequest>();
		vendorReqList = req.getVendorTaskRequest();
		for (VendorTaskRequest row : vendorReqList) {
			per = per + row.getPercentage();
		}
		if((per<100) || (per>100))
		{
			res.setStatusCode("200");
			res.setStatusMessage("Please provide 100 percentage for task allocation");
		}
		
		
		else
		{
		this.vendorDAO.shareWorkLoad(req);
		res.setStatusCode("200");
		res.setStatusMessage("updated sucesfully");
		}
		return res;
	}

	@Override
	public List<VendorWorkLoadResponse> showVendorWorkLoad() {
		return vendorDAO.showVendorWorkLoad();
	}


}
