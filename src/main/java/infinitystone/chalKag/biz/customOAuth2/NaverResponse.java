package infinitystone.chalKag.biz.customOAuth2;

import java.util.Map;

public class NaverResponse implements OAuth2Response{

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {

        return "naver";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {

        return attribute.get("email").toString();
    }

    @Override
    public String getName() {

        return attribute.get("name").toString();
    }

	@Override
	public String getNickname() {
		return attribute.get("nickname").toString();
	}

	@Override
	public String getPh() {
		return attribute.get("mobile").toString().replace("-", "");
	}

	@Override
	public String getGender() {
		String gender = attribute.get("gender").toString();
		if(gender.toUpperCase().equals("M")) {
			gender = "male";
		}
		else {
			gender ="female";
		}
		return gender;
	}

	@Override
	public String getBirthday() {
		return attribute.get("birthday").toString();
	}

	@Override
	public String getBirthYear() {
		return attribute.get("birthyear").toString()+"-";
	}
}
