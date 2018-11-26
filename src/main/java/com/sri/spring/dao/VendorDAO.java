package com.sri.spring.dao;

import java.util.List;

import com.sri.spring.model.RestRequest;
import com.sri.spring.model.RestResponse;
import com.sri.spring.model.Task;
import com.sri.spring.model.Vendor;
import com.sri.spring.model.VendorTaskRequest;
import com.sri.spring.model.VendorWorkLoadResponse;

public interface VendorDAO {

	public void addVendor(Vendor p);
	public void updateVendor(Vendor p);
	public List<Vendor> listVendors();
	public Vendor getVendorById(int id);
	public void removeVendor(int id);
	public List<Task> listTasks();
	public void addTask(Task p);
	void updateTask(Task p);
	public Object getTaskById(int id);
	public void removeTask(int id);
	public void shareWorkLoad(RestRequest req);
	public List<VendorWorkLoadResponse> showVendorWorkLoad();
}
