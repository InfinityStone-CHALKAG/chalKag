package infinitystone.chalKag.biz.profileImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("profileImgService")
public class ProfileImgServiceImpl implements ProfileImgService {

  @Autowired
  private ProfileImgDAO profileImgDAO;

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
