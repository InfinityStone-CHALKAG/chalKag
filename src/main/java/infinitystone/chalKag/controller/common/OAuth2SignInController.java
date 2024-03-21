package infinitystone.chalKag.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.customOAuth2.CustomOAuth2User;
import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
public class OAuth2SignInController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/oauth2SignIn")
	public String myEndpoint(HttpSession session, MemberDTO memberDTO) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// 인증된 사용자가 CustomOAuth2User 객체인지 확인
		if (authentication.getPrincipal() instanceof CustomOAuth2User) {
			// CustomOAuth2User 객체로 캐스팅하여 사용자 정보에 접근
			CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

			// 사용자 정보 활용
			String email = customOAuth2User.getEmail();

			// 사용자 정보 반환
			System.out.println("[OAuth2SignInController] email : " + email);
			
			memberDTO.setSearchCondition("OAuth2SignIn");
			memberDTO.setMemberId(email);
			
			// 기존에 있는 멤버 서비스를 활용하여 ID와 해당 유저의 등급을 세션에 저장
			MemberDTO result = memberService.selectOne(memberDTO);
			
			session.setAttribute("member", result.getMemberId());
			session.setAttribute("memberGrade", result.getMemberGrade());
			
		} 
		return "redirect:/main";

	}

}