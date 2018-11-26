package com.sri.spring.model;

import java.util.ArrayList;
import java.util.List;

public class RestRequest {
	private List<VendorTaskRequest> vendorTaskRequest = new ArrayList<VendorTaskRequest>();

	public List<VendorTaskRequest> getVendorTaskRequest() {
		return vendorTaskRequest;
	}

	public void setVendorTaskRequest(List<VendorTaskRequest> vendorTaskRequest) {
		this.vendorTaskRequest = vendorTaskRequest;
	}

}
