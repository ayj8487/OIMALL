package com.oi.oimall.service;

import com.oi.oimall.model.Member;

public interface MemberService {

	Member findMember(String user_id, String user_pw);

}
