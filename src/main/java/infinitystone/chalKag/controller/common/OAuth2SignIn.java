package infinitystone.chalKag.controller.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.customOAuth2.CustomOAuth2User;
import jakarta.servlet.http.HttpSession;

@Controller
public class OAuth2SignIn {

	@RequestMapping("/oauth2SignIn")
	public String myEndpoint(HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// 인증된 사용자가 CustomOAuth2User 객체인지 확인
		if (authentication.getPrincipal() instanceof CustomOAuth2User) {
			// CustomOAuth2User 객체로 캐스팅하여 사용자 정보에 접근
			CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

			// 사용자 정보 활용
			String email = customOAuth2User.getEmail();

			// 사용자 정보 반환
			System.out.println("email : " + email);
			
			session.setAttribute("member", email);
			
		} 
		return "redirect:/main";

	}

}