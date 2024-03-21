package infinitystone.chalKag.biz.customOAuth2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String member_id);
}
