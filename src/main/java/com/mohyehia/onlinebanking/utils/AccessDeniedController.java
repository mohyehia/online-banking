package com.mohyehia.onlinebanking.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {
	@RequestMapping("/403")
	public String accessDenied() {
	    return "errors/403";
	}
}
