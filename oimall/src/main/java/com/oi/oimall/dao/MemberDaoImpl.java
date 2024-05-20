package com.oi.oimall.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oi.oimall.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Repository
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao{
	
	private final SqlSession session;

	@Override
	public Member findMember(String user_id, String user_pw) {
		Member findMember = null;
		try {
			Map<String, Object> memberMap = new HashMap<>();
			memberMap.put("user_id", user_id);
			memberMap.put("user_pw", user_pw);
			
			
			findMember = session.selectOne("findMember", memberMap);
			log.info("MemberDaoImpl findMember >> " + findMember);
		} catch (Exception e) {
			System.out.println("MemberDaoImpl >> " + e.getMessage());
		}
		return findMember;
	}

	@Override
	public Member findByMemId(String user_id) {
		System.out.println("memberDaoImpl findByMemId Start@@@@@@@@");
	    System.out.println("user_id: " + user_id);

	    Member member = null;
	    try {
	        // Execute the query with the provided member1 object
	        member = session.selectOne("findByMemId", user_id);
	        
	        // Check if the member is null after query execution
	        if (member == null) {
	            System.out.println("No member found with user_id: " + user_id);
	        } else {
	            System.out.println("Member found: " + member);
	        }
	    } catch (Exception e) {
	        System.out.println("Error executing query: " + e.getMessage());
	        e.printStackTrace(); // Print the stack trace for detailed error information
	    }
	    System.out.println("memberDaoImpl findByMemId member : " + member);
	    System.out.println("memberDaoImpl findByMemId End@@@@@@@@");
	    
	    return member;
	}
}
