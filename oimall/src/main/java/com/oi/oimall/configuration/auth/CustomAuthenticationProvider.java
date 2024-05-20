package com.oi.oimall.configuration.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.oi.oimall.controller.MemberController;
import com.oi.oimall.dao.MemberDao;
import com.oi.oimall.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final MemberDao md;
	private final PasswordEncoder passwordEncoder; 
	private final MemberController mc; 
	
	//실제 인증 로직 이뤄짐
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UUID transactionId = UUID.randomUUID();
		String user_id = "";
		String user_pw = "";
//		List<GrantedAuthority> authorities = new ArrayList<>();
		try {
			log.info("[{}]{}:{}",transactionId, "AuthenticationProvider", "start");
			user_id = authentication.getName();//아이디
			user_pw = authentication.getCredentials().toString();//패스워드
	        log.info("user_id:{}",user_id);
	        log.info("user_pw:{}",user_pw);
	        
	        // 여기서 실제 디비에서 mmId에 해당하는 Member 검색
//            Member member = md.findByMemId(user_id);
	        Member member = mc.login(user_id, user_pw);
            System.out.println("CustomAuthenticationProvider 로그인 회원 : "+ member);
            
            if (member == null) {
            	throw new BadCredentialsException("아이디가 일치하지 않습니다." + user_pw);
            }
            
            String loggedInPswd = member.getUser_pw();
            System.out.println("CustomAuthenticationProvider 회원 비번 "+loggedInPswd);
            System.out.println("CustomAuthenticationProvider 로그인 하려는 비번 "+user_pw);
            
         // 패스워드를 암호화된 비밀번호와 비교
            //암호화 안된 비번과 비교하려 하면 에러남
            if (!passwordEncoder.matches(user_pw, loggedInPswd )) {
                throw new BadCredentialsException("비밀번호가 일치하지 않습니다." + user_pw);
            }

            // category에 따라 권한 부여
//            switch (member.getMemAdmin()) {
//                case 1:
//                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//                    break;
//                case 2:
//                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//                    break;
//                default:
//                    throw new BadCredentialsException("유효하지 않은 카테고리 값: " + member.getMemAdmin());
//            
//            }
//            
//            log.info("authorities:{}", authorities);
            
		} catch (Exception e) {
			
			log.error("[{}]{}:{}",transactionId, "AuthenticationProvider", e.getMessage());
			 throw e;  // 추가된 부분
		} finally {
			log.info("[{}]{}:{}",transactionId, "AuthenticationProvider", "end");
		}	
		
		// 로그인 성공 -> SecurityContextHolder에 Authentication 객체 저장
		// => SecurityContextHolder(SecurityContext(Authentication))
		//SecurityContextHolder.getContext().getAuthentication()로 Authentication 호출가능
//    	return new UsernamePasswordAuthenticationToken(memId,  memPw, authorities);
		return new UsernamePasswordAuthenticationToken(user_id,  user_pw);
    }

	// Provider 적용 가능 여부
	@Override
	public boolean supports(Class<?> authentication) {
		 return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	

}
