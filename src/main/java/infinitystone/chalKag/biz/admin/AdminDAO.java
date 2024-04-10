package infinitystone.chalKag.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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
  private static final String SELECTALL_SIGNUPCOUNTBYAGEGROUP = "SELECT FLOOR(TIMESTAMPDIFF(YEAR, STR_TO_DATE(MEMBER_birth, '%Y-%m-%d'), NOW()) / 10) * 10 AS AGEGROUP, "
      + "COUNT(*) AS SIGNUPCOUNT " + "FROM MEMBER " + "WHERE MEMBER_grade = 'USER' " + "GROUP BY AGEGROUP "
      + "ORDER BY AGEGROUP ";

  // (관리자)월 날짜별 로그인 수.안승준
  private static final String SELECTALL_SIGNINCOUNTBYYEARMONTHDATE = "WITH RECURSIVE DATERANGE AS ( " +
      "SELECT DATE_FORMAT(CONCAT(?, '-', LPAD(?, 2, '0'), '-01'), '%Y-%m-%d') AS DATE " +
      "UNION ALL " +
      "SELECT DATE_ADD(DATE, INTERVAL 1 DAY) " +
      "FROM DATERANGE " +
      "WHERE DATE < LAST_DAY(DATE) " +
      ")" +
      "SELECT " +
      "DAY(DATERANGE.DATE) AS DATE, " +
      "COALESCE(COUNT(MEMBER.MEMBER_id), 0) AS SIGNINCOUNT " +
      "FROM DATERANGE " +
      "LEFT JOIN SIGNINLOG ON DATE(SIGNINLOG.SIGNINLOG_date) = DATERANGE.DATE " +
      "LEFT JOIN MEMBER ON SIGNINLOG.MEMBER_id = MEMBER.MEMBER_id AND MEMBER.MEMBER_grade = 'USER' " +
      "GROUP BY DATE " +
      "ORDER BY DATE";

  // (관리자)요일별 로그인 수.안승준
  private static final String SELECTALL_SIGNINCOUNTBYDAYOFWEEK = "SELECT " +
      "DAYOFWEEK, " +
      "IFNULL(COUNT(MEMBER_id), 0) AS SIGNINCOUNT " +
      "FROM " +
      "(SELECT 1 AS WEEKDAY, 'SUN' AS DAYOFWEEK UNION ALL " +
      "SELECT 2, 'MON' UNION ALL " +
      "SELECT 3, 'TUE' UNION  ALL " +
      "SELECT 4, 'WED' UNION ALL " +
      "SELECT 5, 'THU' UNION ALL " +
      "SELECT 6, 'FRI' UNION ALL " +
      "SELECT 7, 'SAT') D " +
      "LEFT JOIN SIGNINLOG ON DAYOFWEEK(SIGNINLOG_date) = D.WEEKDAY " +
      "GROUP BY D.WEEKDAY, D.DAYOFWEEK " +
      "ORDER BY D.WEEKDAY";

  //(관리자)연별 회원 수.안승준
  private static final String SELECTALL_SIGNUPCOUNTBYYEAR = "SELECT YEAR(MEMBER_signupdate) AS YEAR, " +
      "COUNT(MEMBER_id) AS SIGNUPCOUNT " +
      "FROM MEMBER " +
      "WHERE MEMBER_grade = 'USER' " +
      "GROUP BY YEAR(MEMBER_signupdate) " +
      "ORDER BY YEAR(MEMBER_signupdate)";

  private static final String SELECTALL = "";

  // (관리자)성별별 가입자 수.안승준
  private static final String SELECTONE_SIGNUPCOUNTBYGENDERGROUP = "SELECT SUM(CASE WHEN MEMBER_gender = 'male' THEN 1 ELSE 0 END) AS MALEGROUP, "
      + "SUM(CASE WHEN MEMBER_gender = 'female' THEN 1 ELSE 0 END) AS FEMALEGROUP "
      + "FROM MEMBER "
      + "WHERE MEMBER_grade = 'USER'";

// 관리자 메인 페이지 상단 정보 출력 쿼리
  private static final String SELECTONE_ADMINHEADER = "SELECT COALESCE((SELECT SUM(AMOUNT) " +
      "FROM PAYMENT),0) AS REVENUE, " +
      "(SELECT SUM(POST_count) AS TOTAL_posts " +
      "FROM (SELECT COUNT(*) AS POST_count " +
      "FROM HEADHUNTPOST " +
      "UNION ALL " +
      "SELECT COUNT(*) " +
      "FROM JOBHUNTPOST " +
      "UNION ALL " +
      "SELECT COUNT(*) " +
      "FROM FREEPOST " +
      "UNION ALL " +
      "SELECT COUNT(*) " +
      "FROM MARKETPOST) AS COUNTS) AS POSTDATAS, " +
      "(SELECT COUNT(*) " +
      "FROM MEMBER " +
      "WHERE MEMBER_grade = 'PREMIUM') AS PREMIUMUSERS, " +
      "(SELECT COUNT(*) " +
      "FROM MEMBER " +
      "WHERE MEMBER_grade != 'ADMIN') AS USERS";

  private static final String SELECTONE = "";
  private static final String INSERT = "";
  private static final String UPDATE = "";
  private static final String DELETE = "";

// 목록 출력
  public List<AdminDTO> selectAll(AdminDTO adminDTO) {
    List<AdminDTO> result = null;
    System.out.println("AdminDAO(selectAll) In로그 = [" + adminDTO + "]");
    try {
	// 나이별 회원가입 수
      if (adminDTO.getSearchCondition().equals("signUpCountByAgeGroup")) {
        result = (List<AdminDTO>) jdbcTemplate.query(SELECTALL_SIGNUPCOUNTBYAGEGROUP,
            new SignUpCountByAgeGroupRowMapper());
        System.out.println("AdminDAO(selectAll) Out로그 = [" + result + "]");
        return result;
	// 년월일 별 로그인 수
      } else if (adminDTO.getSearchCondition().equals("signInCountByYearMonthDate")) {
        Object[] args = {adminDTO.getYear(), adminDTO.getMonth()};
        result = (List<AdminDTO>) jdbcTemplate.query(SELECTALL_SIGNINCOUNTBYYEARMONTHDATE, args,
            new SignInCountByYearMonthDateRowMapper());
        System.out.println("AdminDAO(selectAll) Out로그 = [" + result + "]");
        return result;
	// 년월일 별 로그인 수
      } else if (adminDTO.getSearchCondition().equals("signInCountByDayOfWeek")) {
        result = (List<AdminDTO>) jdbcTemplate.query(SELECTALL_SIGNINCOUNTBYDAYOFWEEK, new SignInCountByDayOfWeekRowMapper());
        System.out.println("AdminDAO(selectAll) Out로그 = [" + result + "]");
        return result;
	// 년 별 로그인 수
      } else if (adminDTO.getSearchCondition().equals("signUpCountByYear")) {
        result = (List<AdminDTO>) jdbcTemplate.query(SELECTALL_SIGNUPCOUNTBYYEAR, new SignUpCountByYearRowMapper());
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
// 성별별 회원가입 수 
      if (adminDTO.getSearchCondition().equals("signUpCountByGenderGroup")) {
        result = jdbcTemplate.queryForObject(SELECTONE_SIGNUPCOUNTBYGENDERGROUP,
            new SignUpCountByGenderGroupRowMapper());
        System.out.println("AdminDAO(selectOne) Out로그 = [" + result + "]");
        return result;
// 관리자 메인 페이지 상단 정보 출력
      } else if (adminDTO.getSearchCondition().equals("adminHeader")) {
        result = jdbcTemplate.queryForObject(SELECTONE_ADMINHEADER, new AdminHeaderRowMapper());
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
// 년월일 별 로그인 수 RowMapper
class SignInCountByYearMonthDateRowMapper implements RowMapper<AdminDTO> {
  @Override
  public AdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    AdminDTO adminDTO = new AdminDTO();
    adminDTO.setDate(rs.getString("DATE"));
    adminDTO.setSignInCount(rs.getString("SIGNINCOUNT"));
    return adminDTO;
  }
}
// 일주일 별 로그인 수 RowMapper
class SignInCountByDayOfWeekRowMapper implements RowMapper<AdminDTO> {
  @Override
  public AdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    AdminDTO adminDTO = new AdminDTO();
    adminDTO.setDayOfWeek(rs.getString("DAYOFWEEK"));
    adminDTO.setSignInCount(rs.getString("SIGNINCOUNT"));
    return adminDTO;
  }
}
// 년 별 회원가입 수 RowMapper
class SignUpCountByYearRowMapper implements RowMapper<AdminDTO> {
  @Override
  public AdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    AdminDTO adminDTO = new AdminDTO();
    adminDTO.setYear(rs.getString("YEAR"));
    adminDTO.setSignUpCount(rs.getString("SIGNUPCOUNT"));
    return adminDTO;
  }
}
// 관리자 메인 페이지 상단 출력부분 RowMapper
class AdminHeaderRowMapper implements RowMapper<AdminDTO> {
  @Override
  public AdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    AdminDTO adminDTO = new AdminDTO();
    adminDTO.setRevenue(rs.getString("REVENUE"));
    adminDTO.setPostDatas(rs.getString("POSTDATAS"));
    adminDTO.setPremiumUsers(rs.getString("PREMIUMUSERS"));
    adminDTO.setUsers(rs.getString("USERS"));
    return adminDTO;
  }
}