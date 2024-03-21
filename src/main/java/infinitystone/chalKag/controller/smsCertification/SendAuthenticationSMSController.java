package infinitystone.chalKag.controller.smsCertification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendAuthenticationSMSController{
	
    @Autowired
    private AuthenticationSMSController authenticationSMS;

    @Autowired
    private CreateVerificationCodeController verificationCodeGenerator;
    
	@RequestMapping("/sendAuthenticationSMS")
	
	// View에서 인증자의 번호를 인자로 받아옴
	public @ResponseBody String sendAuthenticationSMS(@RequestParam("memberPh")String memberPh) {
		System.out.println("[로그] SMS 인증 서블릿 접근");
		
		// 인증 코드 생성
		String verificationCode = verificationCodeGenerator.createVerificationCode();

		// 인증 SMS 전송
		boolean isSMSSent = authenticationSMS.sendMsg(memberPh, verificationCode);

		if (!isSMSSent) {
			return "fail";
		}

		return verificationCode;
	}
	
}
