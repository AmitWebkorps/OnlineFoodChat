package com.onlinefoodchat.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlinefoodchat.entity.Client;
import com.onlinefoodchat.entity.Menu;
import com.onlinefoodchat.entity.Notification;
import com.onlinefoodchat.entity.Order;
import com.onlinefoodchat.entity.Plan;
import com.onlinefoodchat.entity.User;
import com.onlinefoodchat.repository.ClientRepo;
import com.onlinefoodchat.repository.MenuRepo;
import com.onlinefoodchat.repository.NotificationRepo;
import com.onlinefoodchat.repository.OrderRepo;

@Service
public class ClientService {

	@Autowired
	private ClientRepo clientRepo;

	@Autowired
	private MenuRepo menuRepo;

	@Autowired
	private NotificationRepo notificationRepo;

	@Autowired
	private OrderRepo orderRepo;

	public Client clientSigUp(Client client, Plan plan) {

		java.util.Date date = new java.util.Date();
		Date date2 = null;
		plan.setBuyingDate(new Date(date.getYear(), date.getMonth(), date.getDate()));

		plan.setClient(client);
		int planAmount = plan.getAmount();
		if (planAmount == 150) {
			date2 = new Date(date.getYear(), date.getMonth() + 1, date.getDate());
		} else if (planAmount == 290) {
			date2 = new Date(date.getYear(), date.getMonth() + 2, date.getDate());
		} else if (planAmount == 400) {
			date2 = new Date(date.getYear(), date.getMonth() + 3, date.getDate());
		}

		plan.setExpiryDate(date2);
		client.setPlanentity(plan);

		if (clientRepo.findByEmailAndPassword(client.getEmail(), client.getPassword()) == null) {
			return clientRepo.save(client);
		}
		return null;
	}

	public Client clientLogin(Client client) {
		return clientRepo.findByEmailAndPassword(client.getEmail(), client.getPassword());
	}

	public String addRestro(int id) {
		Client client = clientRepo.findById(id).orElse(null);
		if (client != null)
			return client.getRestro();
		return null;
	}

	public String addRestroName(String name, int id) {
		Client client = clientRepo.findById(id).orElse(null);
		client.setRestro(name);
		clientRepo.save(client);
		return name;
	}

	public List<Menu> getMenuList(int id) {
		Client client = clientRepo.findById(id).orElse(null);
		System.out.println(client);
		return menuRepo.findByClient(client);
	}

	public String addDish(Menu menu, MultipartFile image, int id) {
		Client client = clientRepo.findById(id).orElse(null);
		if (client != null) {
			if (image != null && !image.isEmpty()) {
				String fileName = image.getOriginalFilename().trim();
				saveImage(image);
				menu.setImageName(fileName);
			}
			menu.setClient(client);
			menuRepo.save(menu);
			return "success";
		}
		return null;
	}

	public Client getDetails(Integer id) {
		return clientRepo.findById(id).orElse(null);
	}

	public void deleteDish(int id) {
		menuRepo.deleteById(id);

	}

	public List<Notification> getAllNotification(int clientId) {
		Client client = clientRepo.findById(clientId).orElse(null);
		System.out.println(client);
		return notificationRepo.findByClient(client);
	}

	public List<Order> getAllOrders(int userId) {
		Client client = clientRepo.findById(userId).orElse(null);
		List<Order> list = orderRepo.findByClient(client);
		System.out.println(list);
		return list;
	}

	public void setDetails(Client client, Integer id) {
		Client client2 = clientRepo.findById(id).orElse(null);
		client2.setEmail(client.getEmail());
		client2.setName(client.getName());
		client2.setPhone(client.getPhone());
		client2.setRestro(client.getRestro());
		clientRepo.save(client2);
	}

	public void editDish(Menu menu, MultipartFile image) {
		Menu menu2 = menuRepo.findById(menu.getId()).orElse(null);
		if (menu2 != null) {
			if (image != null && !image.isEmpty()) {
				String fileName = image.getOriginalFilename().trim();
				menu2.setImageName(fileName);
				saveImage(image);
			}
			menu2.setName(menu.getName());
			menu2.setPrice(menu.getPrice());
			menuRepo.save(menu2);
		}
	}

	public boolean saveImage(MultipartFile image) {
		String fileName = image.getOriginalFilename().trim();
		InputStream is;
		FileOutputStream fs = null;
		try {
			is = image.getInputStream();
			String path = "C:\\Users\\dell\\Documents\\workspace-sts-3.9.12.RELEASE\\Online-Food-Chat\\src\\main\\webapp\\images\\"
					+ fileName;
			int bytes = 0;
			fs = new FileOutputStream(path);
			while ((bytes = is.read()) != -1)
				fs.write(bytes);
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (fs != null)
					fs.close();
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	public String cancelOrder(int id) {
		Order order = orderRepo.findById(id).orElse(null);
		if (order != null) {
			order.setOrderStatus("Cancelled");
			Notification notification = notificationRepo.findByOrder(order);
			if (notification != null) {
				notification.setClientNotification("Order SuccessFully Cancelled From Your Side");
				notification.setUserNotification("Order Cancelled From Restaurant End");
				notificationRepo.save(notification);
			}
			orderRepo.save(order);
			return "";
		}
		return null;
	}

}
