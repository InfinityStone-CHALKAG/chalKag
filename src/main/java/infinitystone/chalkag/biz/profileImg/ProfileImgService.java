package infinitystone.chalkag.biz.profileImg;

import java.util.List;

public interface ProfileImgService {

  public List<ProfileImgDTO> selectAll(ProfileImgDTO profileImgDTO);

  public ProfileImgDTO selectOne(ProfileImgDTO profileImgDTO);

  public boolean insert(ProfileImgDTO profileImgDTO);

  public boolean update(ProfileImgDTO profileImgDTO);

  public boolean delete(ProfileImgDTO profileImgDTO);

}
