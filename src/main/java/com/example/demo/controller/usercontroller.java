package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception_handling.ApiResponce;
import com.example.demo.payload.userspayload;
import com.example.demo.services.userservice;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class usercontroller {
	@Autowired
	userservice service;
	
	@PostMapping("/insert")
	public ResponseEntity<userspayload>addusers(@Valid @RequestBody userspayload usp){
		userspayload user=this.service.addusers(usp);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<userspayload>updateusers(@Valid @PathVariable int id,@RequestBody userspayload usrp){
		userspayload userp=this.service.updateusers(usrp, id);
		return ResponseEntity.ok(userp);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponce>delete(@Valid @PathVariable int id){
		this.service.deleteusers(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("id deleted succesfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<userspayload>> getall(){
		return ResponseEntity.ok(this.service.getallusers());
		}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<userspayload>getbyid(@Valid @PathVariable int id){
		return ResponseEntity.ok(this.service.getbyid(id));
	}
}
