package com.onlinefoodchat.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.Cart;
import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Menu;
import com.onlinefoodchat.entity.Notification;
import com.onlinefoodchat.entity.Order;
import com.onlinefoodchat.entity.OrderDetails;
import com.onlinefoodchat.entity.Plan;
import com.onlinefoodchat.entity.User;
import com.onlinefoodchat.repository.CartRepo;
import com.onlinefoodchat.repository.ClientRepo;
import com.onlinefoodchat.repository.MenuRepo;
import com.onlinefoodchat.repository.NotificationRepo;
import com.onlinefoodchat.repository.OrderDetailsRepo;
import com.onlinefoodchat.repository.OrderRepo;
import com.onlinefoodchat.repository.PlanRepo;
import com.onlinefoodchat.repository.UserRepo;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler.Cancelable;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private MenuRepo menuRepo;

	@Autowired
	private ClientRepo clientRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderDetailsRepo orderDetailsRepo;

	@Autowired
	private NotificationRepo notificationRepo;

	public User getSignUp(User user) throws Exception {
		user.setId(generateUserId(user.getName(), user.getEmail()));
		return userRepo.save(user);
	}

	public String check(User u) {
		Pattern patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Z a-z 0-9 \\s_]).{5,20}$");
		Matcher matcherPassword = patternPassword.matcher(u.getPassword());
		if (u.getName().equals("") || u.getEmail().equals("") || u.getPassword().equals(""))
			return "blank";
		if (!matcherPassword.find()) {
			return "password";
		}
		return "ok";
	}

	public String generateUserId(String name, String email) {
		Random random = new Random();
		String num1 = 1000 + random.nextInt(8999) + "";
		String num2 = 1000 + random.nextInt(8999) + "";
		String username = "UR" + num1 + name + num2 + email.substring(0, email.indexOf("@"));
		System.out.println(username);
		return username;
	}

	public User getLogin(User user) {
		return userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public List<Plan> getRestro() {
		java.util.Date date = new java.util.Date();
		List<Plan> plans = planRepo
				.findByExpiryDateGreaterThanEqual(new Date(date.getYear(), date.getMonth(), date.getDate()));
		System.out.println(plans);
		return plans;
	}

	public List<Client> getRestro(String restro) {
		if (restro.equals(""))
			return null;
		Date date = Date.valueOf(LocalDate.now());
		System.out.println(date);
		List<Client> list = clientRepo.findByRestroContains(restro).stream()
				.filter(x -> x.getPlan().getExpiryDate().after(date)).collect(Collectors.toList());
		System.out.println(list);
		return list;
	}

	public Client getClient(int id) {
		return clientRepo.findById(id).orElse(null);
	}

	public List<Menu> getDish(Client client) {
		return menuRepo.findByClient(client);
	}

	public Cart addToCart(int quantity, int menuId, String userId) {
		Menu menu = menuRepo.findById(menuId).orElse(null);
		User user = userRepo.findById(userId).orElse(null);
		List<Cart> list = cartRepo.findByUser(user);
		Client client = menu.getClient();
		if (list.size() > 0 && list.get(0).getClient() != client) {
			cartRepo.deleteAll(list);
		}
		Cart cart = new Cart();
		cart.setMenu(menu);
		cart.setUser(user);
		cart.setClient(client);
		cart.setQuantity(quantity);
		cartRepo.save(cart);
		return cart;
	}

	public List<Cart> getCartDetails(String userId) {
		User user = userRepo.findById(userId).orElse(null);
		List<Cart> list = cartRepo.findByUser(user);
		return list;
	}

	public double getValue(List<Cart> list) {
		double total = 0;
		for (Cart cart : list)
			total += cart.getQuantity() * cart.getMenu().getPrice();
		return total;
	}

	public void deleteFromCart(int cart) {
		cartRepo.deleteById(cart);
	}

	public void addToOrder(double amount, String userId) {
		User user = userRepo.findById(userId).orElse(null);
		List<Cart> cart = cartRepo.findByUser(user);

		// new Order creation
		Order order = new Order();
		order.setPrice(amount);
		order.setUser(cart.get(0).getUser());
		order.setClient(cart.get(0).getClient());
		order.setOrderStatus("Pending");
		Time time = Time.valueOf(LocalTime.now());
		order.setOrderTime(time);
		orderRepo.save(order);
		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

		// Shifting all item from cart to order table
		for (Cart temp : cart) {
			OrderDetails oDetails = new OrderDetails();
			oDetails.setQuantity(temp.getQuantity());
			oDetails.setMenu(temp.getMenu());
			oDetails.setOrder(order);
			orderDetails.add(oDetails);
		}
		// Notification Creation
		Notification notification = new Notification();
		notification.setClient(cart.get(0).getClient());
		notification.setUser(user);
		notification.setClientNotification("New Order from " + user.getName() + "& Order ID :-" + order.getId());
		notification.setUserNotification("Your Order is Placed from " + cart.get(0).getClient().getRestro() + " !");
        notification.setOrder(order);
		notificationRepo.save(notification);
		orderDetailsRepo.saveAll(orderDetails);
		order.setOrderDetails(orderDetails);
		orderRepo.save(order);
		cartRepo.deleteAll(cart);

	}

	public List<Order> getAllOrders(String userId) {
		User user = userRepo.findById(userId).orElse(null);
		return orderRepo.findByUser(user);
	}

	public List<Notification> getAllNotification(String userId) {
		User user = userRepo.findById(userId).orElse(null);
		return notificationRepo.findByUser(user);
	}

	public String cancelOrder(int id) {
		Order order = orderRepo.findById(id).orElse(null);
		if (order != null) {
			order.setOrderStatus("Cancelled");
			Notification notification=notificationRepo.findByOrder(order);
			notification.setUserNotification("Order SuccessFully Cancelled From Your Side");
			notification.setClientNotification("Order Cancelled From User End");
			notificationRepo.save(notification);
			orderRepo.save(order);
			return "";
		}
		return null;
      }

}
