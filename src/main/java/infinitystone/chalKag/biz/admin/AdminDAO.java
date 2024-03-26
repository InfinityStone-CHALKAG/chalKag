package infinitystone.chalKag.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("adminDAO")
public class AdminDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // (관리자)연령별 가입자 수.안승준
  private static final String SELECTALL_SIGNUPCOUNTBYAGEGROUP = "SELECT FLOOR(TIMESTAMPDIFF(YEAR, STR_TO_DATE(MEMBER_birth, '%Y-%m-%d'), NOW()) / 10) * 10 AS AGEGROUP, " +
      "COUNT(*) AS SIGNUPCOUNT" +
      "FROM MEMBER " +
      "WHERE MEMBER_grade = 'USER' " +
      "GROUP BY AGEGROUP " +
      "ORDER BY AGEGROUP ";

  private static final String SELECTALL = "";

  // (관리자)성별별 가입자 수.안승준
  private static final String SELECTONE_SIGNUPCOUNTBYGENDERGROUP = "SELECT SUM(CASE WHEN MEMBER_gender = 'male' THEN 1 ELSE 0 END) AS MALEGROUP, " +
      "SUM(CASE WHEN MEMBER_gender = 'female' THEN 1 ELSE 0 END) AS FEMALEGROUP " +
      "FROM MEMBER " +
      "WHERE MEMBER_grade = 'USER'";

  private static final String SELECTONE = "";
  private static final String INSERT = "";
  private static final String UPDATE = "";
  private static final String DELETE = "";

  public List<AdminDTO> selectAll(AdminDTO adminDTO) {
    List<AdminDTO> result = null;
    System.out.println("AdminDAO(selectAll) In로그 = [" + adminDTO + "]");
    try {
      if (adminDTO.getSearchCondition().equals("signUpCountByAgeGroup")) {
        result = (List<AdminDTO>) jdbcTemplate.query(SELECTALL_SIGNUPCOUNTBYAGEGROUP, new SignUpCountByAgeGroupRowMapper());
        System.out.println("AdminDAO(selectAll) Out로그 = [" + result + "]");
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    System.out.println("AdminDAO(selectAll) Error로그 = [" + adminDTO.getSearchCondition() + "]");
    return null;
  }

  public AdminDTO selectOne(AdminDTO adminDTO) {
    AdminDTO result = null;
    System.out.println("AdminDAO(selectOne) In로그 = [" + adminDTO + "]");
    try {
      if (adminDTO.getSearchCondition().equals("signUpCountByGenderGroup")) {
        result = jdbcTemplate.queryForObject(SELECTONE_SIGNUPCOUNTBYGENDERGROUP, new SignUpCountByGenderGroupRowMapper());
        System.out.println("AdminDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    System.out.println("AdminDAO(selectOne) Error로그 = [" + adminDTO.getSearchCondition() + "]");
    return null;
  }

  public boolean insert(AdminDTO adminDTO) {
    return false;
  }

  public boolean update(AdminDTO adminDTO) {
    return false;
  }

  public boolean delete(AdminDTO adminDTO) {
    return false;
  }

}

// 연령별 회원가입자 수 RowMapper.안승준
class SignUpCountByAgeGroupRowMapper implements RowMapper<AdminDTO> {
  @Override
  public AdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    AdminDTO adminDTO = new AdminDTO();
    adminDTO.setAgeGroup(rs.getString("AGEGROUP"));
    adminDTO.setSignUpCount(rs.getString("SIGNUPCOUNT"));
    return adminDTO;
  }
}

// 성별별 회원가입자 수 RowMapper.안승준
class SignUpCountByGenderGroupRowMapper implements RowMapper<AdminDTO> {
  @Override
  public AdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    AdminDTO adminDTO = new AdminDTO();
    adminDTO.setMaleGroup(rs.getString("MALEGROUP"));
    adminDTO.setFemaleGroup(rs.getString("FEMALEGROUP"));
    return adminDTO;
  }
}
