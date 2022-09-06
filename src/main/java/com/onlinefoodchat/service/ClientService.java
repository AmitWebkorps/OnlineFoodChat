package com.onlinefoodchat.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlinefoodchat.entity.ClientEntity;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.PlanEntity;
import com.onlinefoodchat.repository.ClientRepo;
import com.onlinefoodchat.repository.MenuRepo;

@Service
public class ClientService {

	@Autowired
	private ClientRepo clientRepo;

	@Autowired
	private MenuRepo menuRepo;

	public ClientEntity clientSigUp(ClientEntity clientEntity, PlanEntity planEntity) {

		java.util.Date date = new java.util.Date();
		Date date2 = null;
		planEntity.setBuyingDate(new Date(date.getYear(), date.getMonth(), date.getDate()));

		planEntity.setClientEntity(clientEntity);
		int planAmount = planEntity.getAmount();
		if (planAmount == 150) {
			date2 = new Date(date.getYear(), date.getMonth() + 1, date.getDate());
		} else if (planAmount == 290) {
			date2 = new Date(date.getYear(), date.getMonth() + 2, date.getDate());
		} else if (planAmount == 400) {
			date2 = new Date(date.getYear(), date.getMonth() + 3, date.getDate());
		}

		planEntity.setExpiryDate(date2);
		clientEntity.setPlanentity(planEntity);

		if (clientRepo.findByEmailAndPassword(clientEntity.getEmail(), clientEntity.getPassword()) == null) {
			return clientRepo.save(clientEntity);
		}
		return null;
	}

	public ClientEntity clientLogin(ClientEntity clientEntity) {
		return clientRepo.findByEmailAndPassword(clientEntity.getEmail(), clientEntity.getPassword());
	}

	public String addRestro(int id) {
		ClientEntity clientEntity = clientRepo.findById(id).orElse(null);
		if (clientEntity != null)
			return clientEntity.getRestro();
		return null;
	}

	public String addRestroName(String name, int id) {
		ClientEntity clientEntity = clientRepo.findById(id).orElse(null);
		clientEntity.setRestro(name);
		clientRepo.save(clientEntity);
		return name;
	}

	public List<MenuEntity> getMenuList(int id) {
		ClientEntity clientEntity = clientRepo.findById(id).orElse(null);
		System.out.println(clientEntity);
		return menuRepo.findByClientEntity(clientEntity);
	}

	public String addDish(MenuEntity menuEntity, MultipartFile image, int id) throws IOException {
		ClientEntity clientEntity = clientRepo.findById(id).orElse(null);
		if (clientEntity != null) {
			if (image != null && !image.isEmpty()) {
				String fileName = image.getOriginalFilename().trim();
				InputStream is = image.getInputStream();
				String path = "C:\\Users\\dell\\Documents\\workspace-sts-3.9.12.RELEASE\\Online-Food-Chat\\src\\main\\webapp\\images\\"
						+ fileName;
				int bytes = 0;
				FileOutputStream fs = new FileOutputStream(path);
				while ((bytes = is.read()) != -1)
					fs.write(bytes);
				fs.close();
				menuEntity.setImageName(fileName);
			}
			menuEntity.setClientEntity(clientEntity);
			menuRepo.save(menuEntity);
			return "success";
		}
		return null;
	}

	public ClientEntity getDetails(Integer id) {
		return clientRepo.findById(id).orElse(null);
	}

	public void deleteDish(int id) {
		menuRepo.deleteById(id);

	}

}
