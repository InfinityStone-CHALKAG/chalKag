package infinitystone.chalKag.biz.customOAuth2;

import java.util.UUID;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //DefaultOAuth2UserService OAuth2UserService의 구현체

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if(registrationId.equals("kakao")) {
        	oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }
        String memberEmail= oAuth2Response.getEmail();
        UserEntity existData = userRepository.findByEmail(memberEmail);
        String role = "ROLE_USER";
        if (existData == null) {

            UserEntity userEntity = new UserEntity();
            
    		UUID uuid = UUID.randomUUID();
    		String temporaryPassword = uuid.toString().substring(0, 8);
    		System.out.println("temporaryPassword 확인 ["+temporaryPassword+"]");
            
            userEntity.setUsername(oAuth2Response.getName());
            userEntity.setBirth(oAuth2Response.getBirthYear()+oAuth2Response.getBirthday());
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setNickname(oAuth2Response.getNickname());
            userEntity.setPh(oAuth2Response.getPh());
            userEntity.setPw(temporaryPassword);
            userEntity.setGender(oAuth2Response.getGender());
            userEntity.setRole(role);

            userRepository.save(userEntity);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
