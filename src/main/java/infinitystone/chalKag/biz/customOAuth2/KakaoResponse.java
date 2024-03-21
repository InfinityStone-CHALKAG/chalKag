package infinitystone.chalKag.biz.customOAuth2;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

	private final Map<String, Object> attribute;

	public KakaoResponse(Map<String, Object> attribute) {
		this.attribute = attribute;
	}


	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getProviderId() {
		return attribute.get("id").toString();
	}

	@Override
	public String getEmail() {
		return (String) ((Map<String, Object>) attribute.get("kakao_account")).get("email");
	}

	@Override
	public String getName() {
		return (String) ((Map<String, Object>) attribute.get("properties")).get("nickname");
	}

	@Override
	public String getNickname() {
		return (String) ((Map<String, Object>) attribute.get("properties")).get("nickname");
	}

	@Override
	public String getPh() {
		String originalNumber = (String) ((Map<String, Object>) attribute.get("kakao_account")).get("phone_number");
        String formattedNumber = formatPhoneNumber(originalNumber);
		return formattedNumber;
	}

	@Override
	public String getGender() {
		return (String) ((Map<String, Object>) attribute.get("kakao_account")).get("gender");
	}

	@Override
	public String getBirthday() {
		String originalBirthday = (String) ((Map<String, Object>) attribute.get("kakao_account")).get("birthday");
		String formattedBirthday = formatBirthday(originalBirthday);
		return formattedBirthday;
	}

	@Override
	public String getBirthYear() {
		return (String) ((Map<String, Object>) attribute.get("kakao_account")).get("birthyear")+"-";
	}
	
	 public static String formatPhoneNumber(String phoneNumber) {
	        // 전화번호에서 숫자만 남기기
	        String cleanedNumber = phoneNumber.replaceAll("[^0-9]", "");
	        // 010 형식으로 재구성
	        String formattedNumber = "010" + cleanedNumber.substring(cleanedNumber.length() - 8);
	        return formattedNumber;
	    }
	 public static String formatBirthday(String birthday) {
	        // substring을 사용하여 앞의 두 문자와 뒤의 두 문자를 분리하고 그 사이에 "-"를 삽입
	        return birthday.substring(0, 2) + "-" + birthday.substring(2);
	    }
}
