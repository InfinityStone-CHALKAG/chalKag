package infinitystone.chalKag.biz.customOAuth2;

public interface OAuth2Response {

    //제공자 (Ex. naver, google, ...)
    String getProvider();
    
    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    
    //이메일
    String getEmail();
    
    //사용자 실명 (설정한 이름)
    String getName();
    
    //사용자 닉네임
    String getNickname();
    
    //사용자 전화번호
    String getPh();
    
    //사용자 성별
    String getGender();
    
    //사용자 생일
    String getBirthday();
    
    //사용자 출생연도
    String getBirthYear();
    
}