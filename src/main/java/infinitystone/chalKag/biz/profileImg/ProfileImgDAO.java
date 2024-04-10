package infinitystone.chalKag.biz.profileImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("profileImgDAO")
public class ProfileImgDAO { // 프로필 이미지 DAO

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // 프로필 사진 출력
  private static String SELECTONE = "SELECT PROFILEIMG_name" +
      "FROM PROFILEIMG" +
      "WHERE MEMBER_id = ?" +
      "ORDER BY PROFILEIMG_id DESC" +
      "LIMIT 1";

  // 프로필 사진 등록
  private static String INSERT = "INSERT INTO PROFILEIMG (MEMBER_id, PROFILEIMG_name)" +
      "VALUES (?, ?)";

  public List<ProfileImgDTO> selectAll(ProfileImgDTO profileImgDTO) {
    return null;
  }

  // 프로필 사진 출력
  public ProfileImgDTO selectOne(ProfileImgDTO profileImgDTO) {
    try {
      Object[] args = {profileImgDTO.getMemberId()};

      return jdbcTemplate.queryForObject(SELECTONE, args, new ProfileImgRowMapper());

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // 프로필 사진 등록
  public boolean insert(ProfileImgDTO profileImgDTO) {
    System.out.println("ProfileImgDAO(insert) In로그 = [" + profileImgDTO + "]");
    if (jdbcTemplate.update(INSERT, profileImgDTO.getMemberId(), profileImgDTO.getProfileImgName()) <= 0) {
      return false;
    }
    return true;
  }

  public boolean update(ProfileImgDTO profileImgDTO) {
    return false;
  }

  public boolean delete(ProfileImgDTO profileImgDTO) {
    return false;
  }

}

// 프로필 출력 시 필요한 데이터를 저장할 RowMapper 클래스
class ProfileImgRowMapper implements RowMapper<ProfileImgDTO> {
  @Override
  public ProfileImgDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    ProfileImgDTO profileImgDTO = new ProfileImgDTO();
    profileImgDTO.setProfileImgName(rs.getString("PROFILEIMG_name"));
    return profileImgDTO;
  }
}
