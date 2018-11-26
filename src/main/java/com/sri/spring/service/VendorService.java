package com.sri.spring.service;

import java.util.List;

import com.sri.spring.model.RestRequest;
import com.sri.spring.model.RestResponse;
import com.sri.spring.model.Task;
import com.sri.spring.model.Vendor;
import com.sri.spring.model.VendorTaskRequest;
import com.sri.spring.model.VendorWorkLoadResponse;

public interface VendorService {

	public void addVendor(Vendor p);
	public void updateVendor(Vendor p);
	public List<Vendor> listVendors();
	public Vendor getVendorById(int id);
	public void removeVendor(int id);
	public Object listTasks();
	public void addTask(Task p);
	public void updateTask(Task p);
	public void removeTask(int id);
	public Object getTaskById(int id);
	public RestResponse shareWorkLoad(RestRequest req);
	public List<VendorWorkLoadResponse> showVendorWorkLoad();
	
}
