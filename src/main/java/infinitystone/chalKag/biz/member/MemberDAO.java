package infinitystone.chalKag.biz.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository("memberDAO")
public class MemberDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // 아이디 중복검사.안승준
  private static final String SELECTONE_CHECKID = "SELECT MEMBER_id " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ?";

  // 전화번호 중복검사 .안승준
  private static final String SELECTONE_CHECKPH = "SELECT MEMBER_ph " +
      "FROM MEMBER " +
      "WHERE MEMBER_PH = ?";

  // 닉네임 중복검사.안승준
  private static final String SELECTONE_CHECKNICKNAME = "SELECT MEMBER_nickname " +
      "FROM MEMBER " +
      "WHERE MEMBER_nickname = ?";

  // 메인페이지 레벨순위 맴버 출력.안승준
  private static final String SELECTALL_LEVELRANK = "SELECT MEMBER_nickname, " +
      "(SELECT MAX(LEVEL_id) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp) AS CURRENT_LEVEL " +
      "FROM MEMBER " +
      "ORDER BY MEMBER_exp DESC " +
      "LIMIT 10";

  // 로그인.안승준
  private static final String SELECTONE_SIGNIN = "SELECT MEMBER_id , MEMBER_grade " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ? " +
      "AND MEMBER_pw = ?";

  // 아이디 찾기.안승준
  private static final String SELECTONE_FINDID = "SELECT MEMBER_id " +
      "FROM MEMBER " +
      "WHERE MEMBER_name = ? " +
      "AND MEMBER_ph = ?";

  // 비밀번호 찾기.안승준
  private static final String SELECTONE_FINDPW = "SELECT MEMBER_pw " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ? " +
      "AND MEMBER_ph = ? ";

  // 메인페이지 내 정보 출력.안승준
  private static final String SELECTONE_MYPAGESIMPLE = "SELECT MEMBER.MEMBER_id, " +
      "MEMBER_name, " +
      "MEMBER_nickname, " +
      "MEMBER_introduction, " +
      "MEMBER_ph, " +
      "MEMBER_grade, " +
      "(SELECT AVG(REVIEW_score) " +
      "FROM REVIEW " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) AS CURRENT_score, " +
      "(SELECT MAX(LEVEL_id) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp) AS CURRENT_level, " +
      "(SELECT (MEMBER_exp - MAX(LEVEL_requiredexp)) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp) AS CURRENT_exp, " +
      "((SELECT MIN(LEVEL_requiredexp) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp > MEMBER_exp) - " +
      "(SELECT MAX(LEVEL_requiredexp) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp)) AS CURRENT_nextexp, " +
      "(SELECT PROFILEIMG.PROFILEIMG_name " +
      "FROM PROFILEIMG " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id " +
      "ORDER BY PROFILEIMG_id DESC " +
      "LIMIT 1) AS PROFILEIMG_name, " +
      "((SELECT " +
      "COUNT(*) " +
      "FROM HEADHUNTPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) + " +
      "(SELECT " +
      "COUNT(*) " +
      "FROM JOBHUNTPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) + " +
      "(SELECT " +
      "COUNT(*) " +
      "FROM FREEPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) + " +
      "(SELECT " +
      "COUNT(*) " +
      "FROM MARKETPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id)) AS POSTCOUNT " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ? ";

  // 마이페이지(유저페이지) 출력.안승준
  private static final String SELECTONE_MYPAGE = "SELECT MEMBER.MEMBER_id, " +
      "MEMBER_pw, " +
      "MEMBER_name, " +
      "MEMBER_nickname, " +
      "MEMBER_ph, " +
      "MEMBER_birth, " +
      "MEMBER_gender, " +
      "MEMBER_introduction, " +
      "MEMBER_grade, " +
      "(SELECT AVG(REVIEW_score) " +
      "FROM REVIEW " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) AS CURRENT_score, " +
      "(SELECT MAX(LEVEL_id) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp) AS CURRENT_level, " +
      "(SELECT (MEMBER_exp - MAX(LEVEL_requiredexp)) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp) AS CURRENT_exp, " +
      "((SELECT MIN(LEVEL_requiredexp) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp > MEMBER_exp) - " +
      "(SELECT MAX(LEVEL_requiredexp) " +
      "FROM LEVEL " +
      "WHERE LEVEL_requiredexp <= MEMBER_exp)) AS CURRENT_nextexp, " +
      "(SELECT PROFILEIMG.PROFILEIMG_name " +
      "FROM PROFILEIMG " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id " +
      "ORDER BY PROFILEIMG_id DESC " +
      "LIMIT 1) AS PROFILEIMG_name, " +
      "((SELECT " +
      "COUNT(*) " +
      "FROM HEADHUNTPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) + " +
      "(SELECT " +
      "COUNT(*) " +
      "FROM JOBHUNTPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) + " +
      "(SELECT " +
      "COUNT(*) " +
      "FROM FREEPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id) + " +
      "(SELECT " +
      "COUNT(*) " +
      "FROM MARKETPOST " +
      "WHERE MEMBER_id = MEMBER.MEMBER_id)) AS POSTCOUNT " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ? ";

  // 회원 비밀번호 확인.안승준
  private static final String SELECTONE_CHECKPW = "SELECT MEMBER_pw " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ?";

  private static final String SELECTONE_OAUTH2SIGNIN = "SELECT MEMBER_id, " +
      "MEMBER_grade " +
      "FROM MEMBER " +
      "WHERE MEMBER_id = ?";

  // 회원가입.안승준
  private static final String INSERT_SINGUP = "INSERT INTO MEMBER ( MEMBER_id, " +
      "MEMBER_pw, " +
      "MEMBER_name, " +
      "MEMBER_nickname, " +
      "MEMBER_ph, " +
      "MEMBER_birth, " +
      "MEMBER_gender, " +
      "MEMBER_introduction) " +
      "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

  // 비밀번호 변경.안승준
  private static final String UPDATE_CHANGEPW = "UPDATE MEMBER " +
      "SET MEMBER_pw = ? " +
      "WHERE MEMBER_id = ?";

  // 닉네임 변경.안승준
  private static final String UPDATE_CHANGENICKNAME = "UPDATE MEMBER " +
      "SET MEMBER_nickname = ? " +
      "WHERE MEMBER_id = ?";

  private static final String UPDATE_CHANGEPH = "UPDATE MEMBER " +
      "SET MEMBER_ph = ? " +
      "WHERE MEMBER_id = ? ";

  // 자기소개 변경.안승준
  private static final String UPDATE_CHANGEINTRODUCTION = "UPDATE MEMBER " +
      "SET MEMBER_introduction = ? " +
      "WHERE MEMBER_id = ?";

  // 회원 탈퇴.안승준
  private static final String UPDATE_DELETEACCOUNT = "UPDATE MEMBER " +
      "SET MEMBER_grade = 'delete' " +
      "WHERE MEMBER_id = ?";

  // 사용 안 할 예정.안승준
  private static final String DELETE = "";

  public List<MemberDTO> selectAll(MemberDTO memberDTO) {
    List<MemberDTO> result = null;
    System.out.println("MemberDAO(selectAll) In로그 = [" + memberDTO + "]");
    try {
      if (memberDTO.getSearchCondition().equals("levelRank")) {
        result = (List<MemberDTO>) jdbcTemplate.query(SELECTALL_LEVELRANK, new LevelRankRowMapper());
        System.out.println("MemberDAO(selectAll) Out로그 = [" + result + "]");
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    System.out.println("MemberDAO(selectAll) Error로그 = [" + memberDTO.getSearchCondition() + "]");
    return null;
  }

  public MemberDTO selectOne(MemberDTO memberDTO) {
    MemberDTO result = null;
    System.out.println("MemberDAO(selectOne) In로그 = [" + memberDTO + "]");
    try {
      if (memberDTO.getSearchCondition().equals("checkId")) {
        Object[] args = {memberDTO.getMemberId()};
        result = jdbcTemplate.queryForObject(SELECTONE_CHECKID, args, new CheckIdRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("checkPh")) {
        Object[] args = {memberDTO.getMemberId()};
        result = jdbcTemplate.queryForObject(SELECTONE_CHECKPH, args, new CheckPhRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("checkNickname")) {
        Object[] args = {memberDTO.getMemberNickname()};
        result = jdbcTemplate.queryForObject(SELECTONE_CHECKNICKNAME, args, new CheckNicknameRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("signIn")) {
        Object[] args = {memberDTO.getMemberId(), memberDTO.getMemberPw()};
        result = jdbcTemplate.queryForObject(SELECTONE_SIGNIN, args, new SignInRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("findId")) {
        Object[] args = {memberDTO.getMemberName(), memberDTO.getMemberPh()};
        result = jdbcTemplate.queryForObject(SELECTONE_FINDID, args, new FindIdRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("findPw")) {
        Object[] args = {memberDTO.getMemberId(), memberDTO.getMemberPh()};
        result = jdbcTemplate.queryForObject(SELECTONE_FINDPW, args, new FindPwRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("myPageSimple")) {
        Object[] args = {memberDTO.getMemberId()};
        result = jdbcTemplate.queryForObject(SELECTONE_MYPAGESIMPLE, args, new MyPageSimpleRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("myPage")) {
        Object[] args = {memberDTO.getMemberId()};
        result = jdbcTemplate.queryForObject(SELECTONE_MYPAGE, args, new MyPageRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("checkPw")) {
        Object[] args = {memberDTO.getMemberId()};
        result = jdbcTemplate.queryForObject(SELECTONE_CHECKPW, args, new CheckPwRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      } else if (memberDTO.getSearchCondition().equals("OAuth2SignIn")) {
        Object[] args = {memberDTO.getMemberId()};
        result = jdbcTemplate.queryForObject(SELECTONE_OAUTH2SIGNIN, args, new OAuth2SignInRowMapper());
        System.out.println("MemberDAO(selectOne) Out로그 = [" + result + "]");
        return result;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    System.out.println("MemberDAO(selectOne) Error로그 = [" + memberDTO.getSearchCondition() + "]");
    return null;
  }

  public boolean insert(MemberDTO memberDTO) {
    System.out.println("MemberDAO(insert) In로그 = [" + memberDTO + "]");
    if (memberDTO.getSearchCondition().equals("signUp")) {
      if (jdbcTemplate.update(INSERT_SINGUP, memberDTO.getMemberId(), memberDTO.getMemberPw(), memberDTO.getMemberName(), memberDTO.getMemberNickname(), memberDTO.getMemberPh(), memberDTO.getMemberBirth(), memberDTO.getMemberGender(), memberDTO.getMemberIntroduction()) <= 0) {
        return false;
      }
      return true;
    }
    System.out.println("MemberDAO(insert) Error로그 = [" + memberDTO.getSearchCondition() + "]");
    return false;
  }

  public boolean update(MemberDTO memberDTO) {
    System.out.println("MemberDAO(update) In로그 = [" + memberDTO + "]");
    if (memberDTO.getSearchCondition().equals("changePw")) {
      if (jdbcTemplate.update(UPDATE_CHANGEPW, memberDTO.getMemberPw(), memberDTO.getMemberId()) <= 0) {
        return false;
      }
      return true;
    } else if (memberDTO.getSearchCondition().equals("changeNickname")) {
      if (jdbcTemplate.update(UPDATE_CHANGENICKNAME, memberDTO.getMemberNickname(), memberDTO.getMemberId()) <= 0) {
        return false;
      }
      return true;
    } else if (memberDTO.getSearchCondition().equals("changePh")) {
      if (jdbcTemplate.update(UPDATE_CHANGEPH, memberDTO.getMemberPh(), memberDTO.getMemberId()) <= 0) {
        return false;
      }
      return true;
    } else if (memberDTO.getSearchCondition().equals("changeIntroduction")) {
      if (jdbcTemplate.update(UPDATE_CHANGEINTRODUCTION, memberDTO.getMemberIntroduction(), memberDTO.getMemberId()) <= 0) {
        return false;
      }
      return true;
    } else if (memberDTO.getSearchCondition().equals("deleteAccount")) {
      if (jdbcTemplate.update(UPDATE_DELETEACCOUNT, memberDTO.getMemberGrade(), memberDTO.getMemberId()) <= 0) {
        return false;
      }
      return true;
    }
    System.out.println("MemberDAO(update) Error로그 = [" + memberDTO.getSearchCondition() + "]");
    return false;
  }

  public boolean delete(MemberDTO memberDTO) {
    return false;
  }

}

// ===== SELECTALL =====

// 레벨 순위 출력 RowMapper.안승준
class LevelRankRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberNickname(rs.getString("MEMBER_nickname"));
    memberDTO.setCurrentLevel(rs.getString("CURRENT_level"));
    return memberDTO;
  }
}

// ===== SELECTONE =====

// 아이디 중복검사 RowMapper.안승준
class CheckIdRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberId(rs.getString("MEMBER_id"));
    return memberDTO;
  }
}

// 전화번호 중복검사 RowMapper.안승준
class CheckPhRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberPh(rs.getString("MEMBER_ph"));
    return memberDTO;
  }
}

// 닉네임 중복검사 RowMapper.안승준
class CheckNicknameRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberNickname(rs.getString("MEMBER_nickname"));
    return memberDTO;
  }
}

// 로그인 RowMapper.안승준
class SignInRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberId(rs.getString("MEMBER_id"));
    memberDTO.setMemberGrade(rs.getString("MEMBER_grade"));
    return memberDTO;
  }
}

// 아이디 찾기 RowMapper.안승준
class FindIdRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberId(rs.getString("MEMBER_id"));
    return memberDTO;
  }
}

// 비밀번호 찾기 RowMapper.안승준
class FindPwRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberPw(rs.getString("MEMBER_pw"));
    return memberDTO;
  }
}

// 메인페이지 회원 정보 출력.안승준
class MyPageSimpleRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberId(rs.getString("MEMBER_id"));
    memberDTO.setMemberName(rs.getString("MEMBER_name"));
    memberDTO.setMemberNickname(rs.getString("MEMBER_nickname"));
    memberDTO.setMemberIntroduction(rs.getString("MEMBER_introduction"));
    memberDTO.setMemberPh(rs.getString("MEMBER_ph"));
    memberDTO.setMemberGrade(rs.getString("MEMBER_grade"));
    memberDTO.setCurrentScore(rs.getString("CURRENT_score"));
    memberDTO.setCurrentLevel(rs.getString("CURRENT_level"));
    memberDTO.setCurrentExp(rs.getString("CURRENT_exp"));
    memberDTO.setCurrentNextExp(rs.getString("CURRENT_nextexp"));
    memberDTO.setProfileImgName(rs.getString("PROFILEIMG_name"));
    memberDTO.setPostCount(rs.getString("POSTCOUNT"));
    return memberDTO;
  }
}

// 회원 정보 상세 출력.안승준
class MyPageRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberId(rs.getString("MEMBER_id"));
    memberDTO.setMemberPw(rs.getString("MEMBER_pw"));
    memberDTO.setMemberName(rs.getString("MEMBER_name"));
    memberDTO.setMemberNickname(rs.getString("MEMBER_nickname"));
    memberDTO.setMemberPh(rs.getString("MEMBER_ph"));
    memberDTO.setMemberBirth(rs.getString("MEMBER_birth"));
    memberDTO.setMemberGender(rs.getString("MEMBER_gender"));
    memberDTO.setMemberIntroduction(rs.getString("MEMBER_introduction"));
    memberDTO.setMemberGrade(rs.getString("MEMBER_grade"));
    memberDTO.setCurrentScore(rs.getString("CURRENT_score"));
    memberDTO.setCurrentLevel(rs.getString("CURRENT_level"));
    memberDTO.setCurrentExp(rs.getString("CURRENT_exp"));
    memberDTO.setCurrentNextExp(rs.getString("CURRENT_nextexp"));
    memberDTO.setProfileImgName(rs.getString("PROFILEIMG_name"));
    memberDTO.setPostCount(rs.getString("POSTCOUNT"));
    return memberDTO;
  }
}

// 회원 비밀번호 확인 RowMapper.안승준
class CheckPwRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberPw(rs.getString("MEMBER_pw"));
    return memberDTO;
  }
}

class OAuth2SignInRowMapper implements RowMapper<MemberDTO> {
  @Override
  public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberId(rs.getString("MEMBER_id"));
    memberDTO.setMemberGrade(rs.getString("MEMBER_grade"));
    return memberDTO;
  }
}