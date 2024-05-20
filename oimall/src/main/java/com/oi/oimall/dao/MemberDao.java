package com.oi.oimall.dao;

import com.oi.oimall.model.Member;

public interface MemberDao {

	Member findMember(String user_id, String user_pw);

	Member findByMemId(String user_id);

}
