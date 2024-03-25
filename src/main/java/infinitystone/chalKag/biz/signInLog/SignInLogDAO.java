package infinitystone.chalKag.biz.signInLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("signInLogDAO")
public class SignInLogDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String SELECTALL = "";
  private static final String SELECTONE = "";
  private static final String INSERT = "INSERT INTO SIGNINLOG (MEMBER_id)" +
      "VALUES (?)";
  private static final String UPDATE = "";
  private static final String DELETE = "";

  public List<SignInLogDTO> selectAll(SignInLogDTO signInLogDTO) {
    return null;
  }

  public SignInLogDTO selectOne(SignInLogDTO signInLogDTO) {
    return null;
  }

  public boolean insert(SignInLogDTO signInLogDTO) {
    System.out.println("SignInLogDAO(insert) In로그=" + "[" + signInLogDTO + "]");
    if (jdbcTemplate.update(INSERT, signInLogDTO.getMemberId()) <= 0) {
      return false;
    }
    return true;
  }

  public boolean update(SignInLogDTO signInLogDTO) {
    return false;
  }

  public boolean delete(SignInLogDTO signInLogDTO) {
    return false;
  }

}
