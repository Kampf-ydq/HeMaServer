package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * 页面跳转Controller
 */
@Controller
public class PageController {

	@RequestMapping("/index.html")
	public ModelAndView indexPage(){
		return new ModelAndView("index");
	}
	
	@RequestMapping("/login.html")
	public ModelAndView loginPage(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/register.html")
	public ModelAndView registerPage(){
		return new ModelAndView("register");
	}
	
	@RequestMapping("/user_center_info.html")
	public ModelAndView user_center_infoPage(){
		return new ModelAndView("user_center_info");
	}
	
	@RequestMapping("/user_center_order.html")
	public ModelAndView user_center_orderPage(){
		return new ModelAndView("user_center_order");
	}
	
	@RequestMapping("/cart.html")
	public ModelAndView cartPage(){
		return new ModelAndView("cart");
	}
}
