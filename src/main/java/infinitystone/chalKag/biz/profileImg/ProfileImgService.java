package infinitystone.chalKag.biz.profileImg;

import java.util.List;

public interface ProfileImgService { // 프로필 이미지와 관련된 컨트롤러에서 사용할 서비스 (프로필 이미지와 관련된 비즈니스 로직을 정의하는 인터페이스)

  public List<ProfileImgDTO> selectAll(ProfileImgDTO profileImgDTO);

  public ProfileImgDTO selectOne(ProfileImgDTO profileImgDTO);

  public boolean insert(ProfileImgDTO profileImgDTO);

  public boolean update(ProfileImgDTO profileImgDTO);

  public boolean delete(ProfileImgDTO profileImgDTO);

}
