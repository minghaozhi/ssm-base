package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page")
public class PageController {
	

	@RequestMapping(value = "{system}/{model}/{pageName}", method = RequestMethod.GET)
	public String toPage(@PathVariable("system") String system,@PathVariable("model") String model, @PathVariable("pageName") String pageName) {
		return system + "/" + model + "/" + pageName;
	}

	@RequestMapping(value = "{model}/{pageName}", method = RequestMethod.GET)
	public String toPage(@PathVariable("model") String model, @PathVariable("pageName") String pageName) {
		
		
		return model + "/" + pageName;
	}

	@RequestMapping(value = "{pageName}", method = RequestMethod.GET)
	public String toPage(@PathVariable("pageName") String pageName) {
		return pageName;
	}

}
