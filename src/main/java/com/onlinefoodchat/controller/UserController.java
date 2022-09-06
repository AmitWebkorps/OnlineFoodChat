package com.onlinefoodchat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.UserEntity;
import com.onlinefoodchat.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/userLoginPage")
	public String loginPage() {
		return "userLogin";
	}

	@RequestMapping("/userSignUpPage")
	public String signPage() {
		return "userSignUp";
	}

	@PostMapping("/userLogin")
	public ModelAndView userLogin(@ModelAttribute UserEntity userEntity,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		userEntity = userService.getLogin(userEntity);
		if (userEntity != null) {
			modelAndView.setViewName("userDashboardPage");
			modelAndView.addObject("restro", userService.getRestro());
			request.getSession().setAttribute("userId", userEntity.getId());
			request.getSession().setAttribute("userName", userEntity.getName());
		} else {
			modelAndView.setViewName("userLoginPage");
			modelAndView.addObject("Failer", "Invalid Credentials");
		}
		return modelAndView;
	}

	@PostMapping("/userSignUp")
	public ModelAndView userSignUp(@ModelAttribute UserEntity userEntity) {
		ModelAndView modelAndView = new ModelAndView();
		if (userService.getSignUp(userEntity) != null) {
			modelAndView.setViewName("userLogin");
			modelAndView.addObject("Success", "Signup Successful ! Now Login");
		} else {

			modelAndView.setViewName("userSignUpPage");
			modelAndView.addObject("Failer", "Signup failed ! Try Again Later");
		}
		return modelAndView;
	}

}
