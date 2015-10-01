package com.ipm.web.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.adsf.ipm.ws.PlanService;
import com.adsf.ipm.ws.dto.ResourcesWS;
import com.adsf.ipm.ws.dto.TasksWS;

public class PlanController {

	public void getPlan() throws MalformedURLException
	{
		
		URL url = new URL("http://ipm-gc.rhcloud.com/ws?wsdl");
//		URL url = new URL("http://127.0.0.1:8888/ts?wsdl");
		QName qname = new QName("http://impl.ws.ipm.adsf.com/",
				"PlanServiceImplService");
		Service planService = Service.create(url, qname);
		PlanService service = planService.getPort(PlanService.class);
		service.getPlan(new ResourcesWS(), new TasksWS());
	}
}
