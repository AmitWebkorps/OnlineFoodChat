package com.onlinefoodchat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.reflect.IReflectionWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.PlanEntity;
import com.onlinefoodchat.service.ClientService;
import com.onlinefoodchat.service.OtpService;

@Controller
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
@RequestMapping("/client")
public class ClientController {

	int otp;
	@Autowired
	private OtpService otpService;
	@Autowired
	private ClientService clientService;

	@RequestMapping("/clientsignuppage")
	public String signUpPage() {
		return "clientSignUp";
	}

	@RequestMapping("/clientloginpage")
	public String loginPage() {
		return "clientLogin";
	}

	@RequestMapping("/dashboard")
	public String getDashboard() {
		return "clientDashboard";
	}

	@PostMapping("/clientLogin")
	public ModelAndView clientLogin(@ModelAttribute ClientEntity clientEntity, HttpServletRequest request) {

		clientEntity = clientService.clientLogin(clientEntity);

		ModelAndView modelAndView = new ModelAndView();
		if (clientEntity != null) {
			modelAndView.setViewName("clientDashboard");
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("userName", clientEntity.getName());
			httpSession.setAttribute("userId", clientEntity.getId());
			return modelAndView;
		}
		modelAndView.setViewName("clientLogin");
		modelAndView.addObject("status", "Invalid Credentials");
		return modelAndView;
	}

	@PostMapping("/signup")
	public ModelAndView clientSignUp(@ModelAttribute ClientEntity clientEntity, @ModelAttribute PlanEntity planEntity) {

		clientEntity = clientService.clientSigUp(clientEntity, planEntity);

		ModelAndView modelAndView = new ModelAndView();
		if (clientEntity != null) {
			modelAndView.setViewName("clientLogin");
			modelAndView.addObject("status", "Successfully Registered ! Now you can Login");
			return modelAndView;
		}
		modelAndView.setViewName("clientSignUp");
		modelAndView.addObject("status", "SignUp Failed ! This Email is Already registered");
		return modelAndView;
	}

	@ResponseBody
	@GetMapping("/sendotp")
	public String sendOtp(@RequestParam String email) {
		this.otp = otpService.sendOtpMail(email);
		return "success";
	}

	@RequestMapping("/addrestro")
	public ModelAndView addRestro(HttpServletRequest request) {

		String restro = clientService.addRestro((int) request.getSession().getAttribute("userId"));
		ModelAndView modelAndView = new ModelAndView();
		if (restro == null || restro.equals("")) {
			modelAndView.setViewName("addRestro");
			return modelAndView;
		}
		modelAndView.addObject("dish", getMenuList((int) request.getSession().getAttribute("userId")));
		modelAndView.setViewName("addMenu");
		return modelAndView;
	}

	@PostMapping("/addrestroname")
	public ModelAndView addRestroName(@RequestParam String name, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		clientService.addRestroName(name, (int) request.getSession().getAttribute("userId"));
		modelAndView.setViewName("addMenu");
		return modelAndView;
	}

	@GetMapping("/getMenu")
	public List<MenuEntity> getMenuList(int id) {
		return clientService.getMenuList(id);
	}

	@PostMapping("/addDish")
	public ModelAndView addDish(@RequestParam("image") MultipartFile image,
			@ModelAttribute MenuEntity menuEntity, HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addDish");
		if (menuEntity == null) {
			modelAndView.addObject("Failed", "Please Fill All Mandatory deatils");
			return modelAndView;
		}
		if (clientService.addDish(menuEntity, image, (int) request.getSession().getAttribute("userId")) != null) {
			modelAndView.addObject("Sucess", "Dish Added SuccessFully");
			return modelAndView;
		}
		modelAndView.addObject("Failed", "Something Went Wrong");
		return modelAndView;
	}

	@GetMapping("/profile")
	public ModelAndView getProfile(HttpSession session)
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("clientProfile");
		modelAndView.addObject("client", clientService.getDetails((Integer)session.getAttribute("userId")));
		return modelAndView;
	}
	
	@GetMapping("/addDishPage")
	public String addDishPage() {
		return "addDish";
	}
	
	@PostMapping("/deleteDish")
	public ModelAndView deleteDish(@RequestParam int id)
	{
		System.out.println("Amit");
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("redirect:addrestro");
		System.out.println(id);
		clientService.deleteDish(id);
		return modelAndView; 
	}
	
	@GetMapping("/logout")
	public ModelAndView clientLogout(HttpSession session)
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("clientLogin");
		modelAndView.addObject("status","Logout Successsully");
		session.invalidate();
		return modelAndView;
	}
}
