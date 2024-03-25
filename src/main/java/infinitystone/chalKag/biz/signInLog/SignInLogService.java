package infinitystone.chalKag.biz.signInLog;

import java.util.List;

public interface SignInLogService {

  public List<SignInLogDTO> selectAll(SignInLogDTO signInLogDTO);

  public SignInLogDTO selectOne(SignInLogDTO signInLogDTO);

  public boolean insert(SignInLogDTO signInLogDTO);

  public boolean update(SignInLogDTO signInLogDTO);

  public boolean delete(SignInLogDTO signInLogDTO);

}
