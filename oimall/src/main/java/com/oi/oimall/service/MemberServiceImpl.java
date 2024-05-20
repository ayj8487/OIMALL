package com.oi.oimall.service;

import org.springframework.stereotype.Service;

import com.oi.oimall.dao.MemberDao;
import com.oi.oimall.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberDao md;

	@Override
	public Member findMember(String user_id, String user_pw) {
		System.out.println("MemberServiceImpl findMember user_id >> " + user_id);
		System.out.println("MemberServiceImpl findMember user_pw >> " + user_pw);
		
		 Member findMember = md.findMember(user_id, user_pw);
		return findMember;
	}
	
}
