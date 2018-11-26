package com.sri.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sri.spring.model.Task;
import com.sri.spring.model.Vendor;
import com.sri.spring.service.VendorService;

@Controller
public class VendorController {
	
	private VendorService vendorService;
	
	@Autowired(required=true)
	@Qualifier(value="vendorService")
	public void setVendorService(VendorService vs){
		this.vendorService = vs;
	}
	 
	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public String listVendors(Model model) {
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("listVendors", this.vendorService.listVendors());
		return "vendor";
	}
	
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public String listTasks(Model model) {
		model.addAttribute("task", new Vendor());
		model.addAttribute("listTasks", this.vendorService.listTasks());
		return "task";
	}

	
	//For add and update vendor both
	@RequestMapping(value= "/vendor/add", method = RequestMethod.POST)
	public String addVendor(@ModelAttribute("vendor") Vendor p){
		
		if(p.getId() == 0){
			//new vendor, add it
			this.vendorService.addVendor(p);
		}else{
			//existing vendor, call update
			this.vendorService.updateVendor(p);
		}
		
		return "redirect:/vendors";
		
	}
	@RequestMapping(value= "/task/add", method = RequestMethod.POST)
	public String addTask(@ModelAttribute("task") Task p){
		
		if(p.getId() == 0){
			//new vendor, add it
			this.vendorService.addTask(p);
		}else{
			//existing vendor, call update
			this.vendorService.updateTask(p);
		}
		
		return "redirect:/tasks";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeVendor(@PathVariable("id") int id){
		
        this.vendorService.removeVendor(id);
        return "redirect:/vendors";
    }
 
    @RequestMapping("/edit/{id}")
    public String editvendor(@PathVariable("id") int id, Model model){
        model.addAttribute("vendor", this.vendorService.getVendorById(id));
        model.addAttribute("listVendors", this.vendorService.listVendors());
        return "vendor";
    }


	@RequestMapping("/taskremove/{id}")
    public String removeTask(@PathVariable("id") int id){
		
        this.vendorService.removeTask(id);
        return "redirect:/tasks";
    }
 
    @RequestMapping("/taskedit/{id}")
    public String editTask(@PathVariable("id") int id, Model model){
        model.addAttribute("task", this.vendorService.getTaskById(id));
        model.addAttribute("listTasks", this.vendorService.listTasks());
        return "task";
    }

}
