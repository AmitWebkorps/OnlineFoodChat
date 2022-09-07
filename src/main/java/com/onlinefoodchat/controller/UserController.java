package com.onlinefoodchat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.PlanEntity;
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
	
	@GetMapping("/logout")
	public ModelAndView userLogout(HttpSession session)
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("userLogin");
		modelAndView.addObject("Success","Logout Successsully");
		session.invalidate();
		return modelAndView;
	}
	
	@GetMapping("/get")
	@ResponseBody
	public List<PlanEntity> getRestro(@RequestParam String restro)
	{
		return userService.getRestro();
	}
}
