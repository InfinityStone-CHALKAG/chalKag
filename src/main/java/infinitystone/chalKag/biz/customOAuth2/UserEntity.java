package infinitystone.chalKag.biz.customOAuth2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
public class UserEntity {

    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Transient
    private String id;
    
    @Transient
    private String role;
    
    @Column(name="MEMBER_name")
    private String username;
    
    @Id
    @Column(name="MEMBER_id")
    private String email;
    
    @Column(name="MEMBER_pw")
    private String pw;
    
    @Column(name="MEMBER_nickname")
    private String nickname;
    
    @Column(name="MEMBER_ph")
    private String ph;
    
    @Column(name="MEMBER_birth")
    private String birth;
    
    @Column(name="MEMBER_gender")
    private String gender;
    
    
    
}
