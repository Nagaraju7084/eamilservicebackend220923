package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.request.EmailRequest;
import com.backend.service.EmailService;

@RestController
@RequestMapping("/api")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendmail")
	public ResponseEntity<?> sendMail(@RequestBody EmailRequest emailRequest){
		boolean flag = emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
		if(flag) {
			return new ResponseEntity<>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("bad request",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
