package com.example.demo.services;

import java.util.List;

import com.example.demo.payload.userspayload;

public interface userservice {
	userspayload addusers(userspayload use);
	userspayload updateusers(userspayload use,int id);
	void deleteusers(int id);
	List<userspayload> getallusers();
	userspayload getbyid(int id);
	
	
}
