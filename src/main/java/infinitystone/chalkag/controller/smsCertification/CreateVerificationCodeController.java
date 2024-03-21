package infinitystone.chalkag.controller.smsCertification;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CreateVerificationCodeController {

	public String createVerificationCode() {

		Random rand = new Random();
        String verificationCode = "";
        for (int i = 0; i < 6; i++) {
            String randomNum = Integer.toString(rand.nextInt(10));
            verificationCode += randomNum;
        }

		System.out.println("[로그] verificationCode : " + verificationCode);

		return verificationCode;
	}

}
