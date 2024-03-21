//package infinitystone.chalkag.controller.async;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import infinitystone.chalkag.biz.member.MemberDTO;
//import infinitystone.chalkag.biz.member.MemberService;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class SignInWithNaverController {
//
//    @Autowired
//    private MemberService memberService;
//
//    @Value("${naver.client-id}")
//    private String clientId;
//
//    @Value("${naver.client-secret}")
//    private String clientSecret;
//
//    @RequestMapping("/signInWithNaver")
//    public String signInWithNaverController(MemberDTO memberDTO, HttpSession session, @RequestParam("code") String code, @RequestParam("state") String state) {
//
//        // 리다이랙트 URI 인코딩.안승준
//        String redirectURI = UriComponentsBuilder.fromUriString("http://localhost:8088/signInWithNaver").build().toUriString();
//        String apiURL;
//
//        // 네이버 인증 요청 API URI 생성.안승준
//        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
//        apiURL += "client_id=" + clientId;
//        apiURL += "&client_secret=" + clientSecret;
//        apiURL += "&redirect_uri=" + redirectURI;
//        apiURL += "&code=" + code;
//        apiURL += "&state=" + state;
//
//        try {
//            // 네이버 인증 요청.안승준
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
//            if (responseEntity.getStatusCode() == HttpStatus.OK) {
//                String responseBody = responseEntity.getBody();
//
//                JSONParser parsing = new JSONParser();
//                Object obj = parsing.parse(responseBody);
//                JSONObject jsonObj = (JSONObject) obj;
//
//                // 네이버 인증 응답에서 access_token과 refresh_token을 가져옴.안승준
//                String access_token = (String) jsonObj.get("access_token");
//                String header = "Bearer " + access_token;
//                String apiUrl = "https://openapi.naver.com/v1/nid/me";
//
//                // 사용자 프로필 API 호출.안승준
//                HttpHeaders headers = new HttpHeaders();
//                headers.set("Authorization", header);
//                HttpEntity<String> entity = new HttpEntity<>(headers);
//
//                ResponseEntity<String> profileResponseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
//                if (profileResponseEntity.getStatusCode() == HttpStatus.OK) {
//                    String profileResponseBody = profileResponseEntity.getBody();
//                    JSONObject profileObj = (JSONObject) parsing.parse(profileResponseBody);
//                    JSONObject responseObj = (JSONObject) profileObj.get("response");
//                    String email = (String) responseObj.get("email");
//                    String name = (String) responseObj.get("name");
//                    String nickname = (String) responseObj.get("nickname");
//                    String birthyear = (String) responseObj.get("birthyear");
//                    String birthday = (String) responseObj.get("birthday");
//                    String mobile = (String) responseObj.get("mobile");
//
//                    // 추출 데이터 확인 로그.안승준
//                    System.out.println(email);
//                    System.out.println(name);
//                    System.out.println(nickname);
//                    System.out.println(birthyear);
//                    System.out.println(birthday);
//                    System.out.println(mobile);
//
//                    // 로그인 기능 수행.안승준
//                    memberDTO.setMemberId(email);
//                    memberDTO.setSearchCondition("signInWithNaver");
//                    memberDTO = memberService.selectOne(memberDTO);
//
//                    System.out.println("[NaverLoginAction] = " + memberDTO);
//                    if (memberDTO != null) {
//                        if (!memberDTO.getMemberGrade().equals("탈퇴")) {
//                            session.setAttribute("member", memberDTO.getMemberId());
//                            System.out.println("[NaverLoginAction]로그인 성공");
//
//                            return "main";
//                        } else {
//                            System.out.println("[NaverLoginAction] LOGIN FAILED");
//                        }
//                    } else {
//                        System.out.println("[NaverLoginAction] 없는 회원입니다.");
//                        // 회원 정보를 세션에 저장하지 않고 다음 페이지로 리다이렉트
//                        return "signUpWithNaver";
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//}
