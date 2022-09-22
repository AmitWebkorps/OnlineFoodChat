package com.onlinefoodchat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.Cart;
import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.User;
import com.onlinefoodchat.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

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

	@RequestMapping("/userDashboard")
	public String userDashboard() {
		return "userDashboardPage";
	}

	@RequestMapping("/userLogin")
	public ModelAndView userLogin(@ModelAttribute User user, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		user = userService.getLogin(user);
		if (user != null) {
			modelAndView.setViewName("userDashboardPage");
			modelAndView.addObject("restro", userService.getRestro());
			request.getSession().setAttribute("id", user.getId());
			request.getSession().setAttribute("userName", user.getName());
		} else {
			modelAndView.setViewName("userLoginPage");
			modelAndView.addObject("Failer", "Invalid Credentials");
		}
		return modelAndView;
	}

	@PostMapping("/userSignUp")
	public ModelAndView userSignUp(@ModelAttribute User user) {
		String check=userService.check(user);
		ModelAndView modelAndView = new ModelAndView();
		if (check.equals("ok")) {
		try {
			user = userService.getSignUp(user);
		} catch (Exception e) {
			modelAndView.setViewName("redirect:userSignUpPage");
			modelAndView.addObject("Failer", "This Email is Already Used");
			return modelAndView;
		}
		
			modelAndView.setViewName("redirect:userLoginPage");
			modelAndView.addObject("Success", "Signup Successful ! Now Login");
		} else if(check.equals("blank")){

			modelAndView.setViewName("redirect:userSignUpPage");
			modelAndView.addObject("Failer", "Please Fill All The Fields First");
		}
		else if(check=="password"){
			modelAndView.addObject("Failer", "Please Follow Correct Sequence for password");
			modelAndView.setViewName("redirect:userSignUpPage");
		}
		return modelAndView;
	}
	
	
	@GetMapping("/logout")
	public ModelAndView userLogout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userLogin");
		modelAndView.addObject("Success", "Logout Successsully");
		session.invalidate();
		return modelAndView;
	}

	@ResponseBody
	@GetMapping("/get")
	public List<Client> getRestro(@RequestParam String restro) {
		return userService.getRestro(restro);
	}

	@GetMapping("/getRestro")
	public ModelAndView getDish(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewRestro");
		Client client = userService.getClient(id);
		modelAndView.addObject("restro", client);
		modelAndView.addObject("dish", userService.getDish(client));
		return modelAndView;

	}

	@RequestMapping("/cart")
	public ModelAndView showCart(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		List<Cart> list= userService.getCartDetails((String) session.getAttribute("id"));
		modelAndView.addObject("details",list);
		modelAndView.addObject("total",userService.getValue(list));
		modelAndView.setViewName("cart");
		return modelAndView;
	}

	@ResponseBody
	@PostMapping("/addtocart")
	public String addToCart(@RequestParam int quantity, @RequestParam int menuId, HttpSession session) {
		userService.addToCart(quantity, menuId, (String) session.getAttribute("id"));
		return "ohk";
	}
	
	@ResponseBody
	@PostMapping("/deletefromcart")
	public String deleteFromCart(@RequestParam int cart,HttpSession session) {
		userService.deleteFromCart(cart);
		return ""+userService.getValue( userService.getCartDetails((String) session.getAttribute("id")));
	}

	@ResponseBody
	@RequestMapping("/payment")
	public String getPayment(@RequestBody Map<String, Object> data) throws RazorpayException {
		
		System.out.println("aaya tha");
		System.out.println(data.get("amount"));
		//getting amount from frontend
		double amount = Double.parseDouble(data.get("amount").toString());
		
		//creating razorpayclient object for creating order
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_sgXOFdvGGQTLKV", "vjBjHIOGkSAlRNiVAlvKz7pE");
		
		//creating jsonObject with all deatils like amount currency
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", amount * 100);
		jsonObject.put("currency", "INR");
		jsonObject.put("receipt", "tx_123456");
		
		//creating order
		Order order = razorpayClient.orders.create(jsonObject);
		System.out.println(order);
		return order.toString();
	}	  
	
	@ResponseBody
	@RequestMapping("/addtoorder")
	public String addToOrder(@RequestBody Map<String, Object> data,HttpSession session)
	{
		System.out.println(data.get("amount"));
		userService.addToOrder( Double.parseDouble(data.get("amount").toString()),(String) session.getAttribute("id"));
     	return "working";
	}

	@RequestMapping("/Orders")
	public ModelAndView getAllOrders(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewOrder");
		modelAndView.addObject("order",userService.getAllOrders((String)session.getAttribute("id")));
		return modelAndView;
	}
	
	@RequestMapping("/Notification")
	public ModelAndView getAllNotification(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userNotification");
		modelAndView.addObject("notify",userService.getAllNotification((String)session.getAttribute("id")));
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/cancelorder")
	public String cancelOrder(@RequestBody Map<String, Object> data)
	{
		System.out.println(data.get("id"));
		String status=userService.cancelOrder(Integer.parseInt(data.get("id").toString()));
		if(status!=null)
     	  return "working";
		return "not Working";
	}
	
}
