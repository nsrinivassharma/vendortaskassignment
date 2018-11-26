package com.sri.spring.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sri.spring.model.RestRequest;
import com.sri.spring.model.RestResponse;
import com.sri.spring.model.VendorTaskRequest;
import com.sri.spring.model.VendorWorkLoadResponse;
import com.sri.spring.service.VendorService;

@RestController
public class VendorRestController {
	
private VendorService vendorService;
	
	@Autowired(required=true)
	@Qualifier(value="vendorService")
	public void setVendorService(VendorService vs){
		this.vendorService = vs;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
	@RequestMapping(value = "/shareWorkLoad", method = RequestMethod.POST, consumes = "application/json")
	public RestResponse shareWorkLoad(@RequestBody RestRequest req) {

		RestResponse response = response = new RestResponse();

		try {
			return vendorService.shareWorkLoad(req);
		} catch (Exception ex) {

			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			System.out.println("Exception Message: " + errors.toString());

			response.setStatusCode("300");
			response.setStatusMessage("Unknown Error");
		}
		response.setStatusCode("200");
		response.setStatusMessage("Work load distributed sucesfully");
		return response;
	}
 
	@RequestMapping(value = "/showVendorWorkLoad", method = RequestMethod.GET, produces = "application/json")
	public List<VendorWorkLoadResponse> showVendorWorkLoad() {
		//List<VendorWorkLoadResponse> response = null;
		List<VendorWorkLoadResponse> response = new ArrayList<VendorWorkLoadResponse>();
		try {
			response = vendorService.showVendorWorkLoad();
		} catch (Exception ex) {

			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			System.out.println("Exception Message: " + errors.toString());

			
			}
		return response;
	}
 
	
}
