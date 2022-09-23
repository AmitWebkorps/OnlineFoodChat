package com.onlinefoodchat.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Menu;
import com.onlinefoodchat.entity.Plan;
import com.onlinefoodchat.service.ClientService;
import com.onlinefoodchat.service.OtpService;

@Controller
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
@RequestMapping("/client")
public class ClientController {

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
	public ModelAndView clientLogin(@ModelAttribute Client client, HttpServletRequest request) {

		client = clientService.clientLogin(client);

		ModelAndView modelAndView = new ModelAndView();
		if (client != null) {
			modelAndView.setViewName("clientDashboard");
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("userName", client.getName());
			httpSession.setAttribute("id", client.getId());
			return modelAndView;
		}
		modelAndView.setViewName("clientLogin");
		modelAndView.addObject("status", "Invalid Credentials");
		return modelAndView;
	}

	@PostMapping("/signup")
	public ModelAndView clientSignUp(@ModelAttribute Client client, @ModelAttribute Plan plan) {

		client = clientService.clientSigUp(client, plan);

		ModelAndView modelAndView = new ModelAndView();
		if (client != null) {
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
		otpService.sendOtpMail(email);
		return "success";
	}
	
	@ResponseBody
	@GetMapping("/checkotp")
	public String validateOtp(@RequestParam String otp) { 
	    if(otpService.validateOtp(otp))
	             return "success";
	    return "failer";
	}
	
	@RequestMapping("/editProfile")
	public ModelAndView editProfile(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clientProfileDetails");
		modelAndView.addObject("details",clientService.getDetails((Integer)session.getAttribute("id")));
		return modelAndView;
	}
	
	@RequestMapping("/getProfileDetails")
	public ModelAndView getProfileDetails(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clientProfile");
		modelAndView.addObject("details",clientService.getDetails((Integer)session.getAttribute("id")));
		return modelAndView;
	}
	
	@PostMapping("/updateProfile")
	public ModelAndView updateProfile(@ModelAttribute Client client,HttpSession session)
	{
		System.out.println(client);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:editProfile");
		clientService.setDetails(client,(Integer)session.getAttribute("id"));
		modelAndView.addObject("update","Updated successfully");
		return modelAndView;
	}

	@RequestMapping("/addrestro")
	public ModelAndView addRestro(HttpServletRequest request) {

		String restro = clientService.addRestro((int) request.getSession().getAttribute("id"));
		ModelAndView modelAndView = new ModelAndView();
		if (restro == null || restro.equals("")) {
			modelAndView.setViewName("addRestro");
			return modelAndView;
		}
		modelAndView.addObject("dish", getMenuList((int) request.getSession().getAttribute("id")));
		modelAndView.setViewName("addMenu");
		return modelAndView;
	}

	@PostMapping("/addrestroname")
	public ModelAndView addRestroName(@RequestParam String name, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		clientService.addRestroName(name, (int) request.getSession().getAttribute("id"));
		modelAndView.setViewName("addMenu");
		return modelAndView;
	}

	@GetMapping("/getMenu")
	public List<Menu> getMenuList(int id) {
		return clientService.getMenuList(id);
	}

	@PostMapping("/addDish")
	public ModelAndView addDish(@RequestParam("image") MultipartFile image,
			@ModelAttribute Menu menu, HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addDish");
		if (menu == null) {
			modelAndView.addObject("Failed", "Please Fill All Mandatory deatils");
			return modelAndView;
		}
		if (clientService.addDish(menu, image, (int) request.getSession().getAttribute("id")) != null) {
			modelAndView.addObject("Sucess", "Dish Added SuccessFully");
			return modelAndView;
		}
		modelAndView.addObject("Failed", "Something Went Wrong");
		return modelAndView;
	}
	
	@PostMapping("/editDish")
	public ModelAndView editDish(@RequestParam("image") MultipartFile image,@ModelAttribute Menu menu )
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("redirect:addrestro");
		clientService.editDish(menu,image);
		return modelAndView;
	}

	@GetMapping("/profile")
	public ModelAndView getProfile(HttpSession session)
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("clientProfile");
		modelAndView.addObject("client", clientService.getDetails((Integer)session.getAttribute("id")));
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
	
	@RequestMapping("/Orders")
	public ModelAndView getAllOrders(HttpSession session)
	{
		System.out.println("nhi aaya");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clientOrder");
		modelAndView.addObject("order",clientService.getAllOrders((Integer)session.getAttribute("id")));
		return modelAndView;
	}
	
	@RequestMapping("/cancelorder")
	public ModelAndView cancelOrder(@RequestParam int id )
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:Orders");
		String status=clientService.cancelOrder(id);
		return modelAndView;
	}
	
	@RequestMapping("/Notification")
	public ModelAndView getAllNotification(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clientNotification");
		modelAndView.addObject("notify",clientService.getAllNotification((Integer)session.getAttribute("id")));
		return modelAndView;
	}
}
