package infinitystone.chalKag.biz.profileImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("profileImgService") // @Service : 프로필 정보의 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class ProfileImgServiceImpl implements ProfileImgService { // 프로필 정보와 관련된 비즈니스 로직을 수행할 ServiceImpl 클래스

  @Autowired  // @Autowired : ProfileImgDA 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
  private ProfileImgDAO profileImgDAO;

  // ProfileImgService 인터페이스의 메서드를 구현
  // DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환
  @Override
  public List<ProfileImgDTO> selectAll(ProfileImgDTO profileImgDTO) {
    return profileImgDAO.selectAll(profileImgDTO);
  }

  @Override
  public ProfileImgDTO selectOne(ProfileImgDTO profileImgDTO) {
    return profileImgDAO.selectOne(profileImgDTO);
  }

  @Override
  public boolean insert(ProfileImgDTO profileImgDTO) {
    return profileImgDAO.insert(profileImgDTO);
  }

  @Override
  public boolean update(ProfileImgDTO profileImgDTO) {
    return profileImgDAO.update(profileImgDTO);
  }

  @Override
  public boolean delete(ProfileImgDTO profileImgDTO) {
    return profileImgDAO.delete(profileImgDTO);
  }

}
