package com.oi.oimall.controller;

import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oi.oimall.model.Member;
import com.oi.oimall.service.JwtService;
import com.oi.oimall.service.JwtServiceImpl;
import com.oi.oimall.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService ms;

    
	@PostMapping("/api/account/login")
	public ResponseEntity login(@RequestBody Map<String, String> params,
								HttpServletResponse res) {
		System.out.println("MemeberController login start>>>>>>>>>");
		log.info("MemeberController login params >> " + params);
		Member member = ms.findMember(params.get("user_id"), params.get("user_pw"));
		
		if(member != null) {
			JwtService jwtService = new JwtServiceImpl();
			
			String id = member.getUser_id();
			String token = jwtService.getToken("id", id);
			
			Cookie cookie = new Cookie("token", token);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			
			res.addCookie(cookie);
			
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
