package com.example.demo.services.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception_handling.ResourceNotFoundException;
import com.example.demo.models.users;
import com.example.demo.payload.userspayload;
import com.example.demo.repo.usersrepo;
import com.example.demo.services.userservice;


@Service
public class users_services_imp implements userservice {

	@Autowired
	usersrepo repo;
	@Autowired
	ModelMapper modelmapper;
	@Override

	public userspayload addusers(userspayload use) {
		users user=this.dto_users(use);
		users savesUsers=this.repo.save(user);
		return this.users_dto(savesUsers);
	}

	@Override
	public userspayload updateusers(userspayload use, int id) {
		users user=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users", "id", id));
		user.setUsername(use.getUsername());
		user.setEmail(use.getEmail());
		user.setPassword(use.getPassword());
		users usr1=this.repo.save(user);
		userspayload up1=this.users_dto(usr1);
		return up1;
	}

	@Override
	public void deleteusers(int id) {
		users usr=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users","id", id));
		this.repo.delete(usr);
	}

	@Override
	public List<userspayload> getallusers() {
		List<users> us=(List<users>) this.repo.findAll();
		List<userspayload> usp=us.stream().map(userss -> this.users_dto(userss)).collect(Collectors.toList());
		return usp;
		}

	@Override
	public userspayload getbyid(int id) {
		users u=this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("users", "id", id));
		return this.users_dto(u);
	}
	
 
	
	public users dto_users(userspayload userp) {
		users usersdto=this.modelmapper.map(userp, users.class);
		return usersdto;
	}
	
	public userspayload users_dto(users use) {
		userspayload usersdto=this.modelmapper.map(use, userspayload.class);
		return usersdto;
	}

}
