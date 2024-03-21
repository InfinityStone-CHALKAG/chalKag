//package infinitystone.chalkag.controller.async;
//import java.util.UUID;
//
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import infinitystone.chalkag.biz.member.MemberDTO;
//import infinitystone.chalkag.biz.member.MemberService;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class SignUpWithNaverController {
//
//	@Autowired
//	private MemberService memberService;
//
//	@RequestMapping("/signUpWithNaver")
//	public String signUpWithNaverController(MemberDTO memberDTO, HttpSession session, Model model, JSONObject naverMember) {
//
//
//
//		memberDTO.setMemberId((String) naverMember.get("email"));
//
//		// UUID 생성.안승준
//		UUID uuid = UUID.randomUUID();
//		// UUID의 앞 8자리를 임시 비밀번호로 사용.안승준
//		String temporaryPassword = uuid.toString().substring(0, 8);
//		System.out.println("temporaryPassword 확인 ["+temporaryPassword+"]");
//
//		// memberDTO에 임시 비밀번호 설정.안승준
//		memberDTO.setMemberPw(temporaryPassword);
//		memberDTO.setMemberName((String) naverMember.get("name"));
//		memberDTO.setMemberNickname((String) naverMember.get("nickname"));
//		memberDTO.setMemberBirth((String) naverMember.get("birthyear") + '-' + (String) naverMember.get("birthday"));
//
//		String ph = (String) naverMember.get("mobile");
//
//		ph = ph.replace("-", "");
//
//		memberDTO.setMemberPh(ph);
//
//		boolean flag = memberService.insert(memberDTO);
//
//		if (flag) { // 성공시 메인으로 이동.안승준
//
//			System.out.println("회원가입 성공!");
//			session.invalidate();
//
//			return "main";
//
//		} else {
//
//			System.out.println("회원가입 실패! 다시 이용해 주세요");
//			return "signIn";
//
//		}
//
//	}
//}
