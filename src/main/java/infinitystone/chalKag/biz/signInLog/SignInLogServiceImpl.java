package infinitystone.chalKag.biz.signInLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("signInLogService")
public class SignInLogServiceImpl implements SignInLogService {

  @Autowired
  private SignInLogDAO signInLogDAO;

  @Override
  public List<SignInLogDTO> selectAll(SignInLogDTO signInLogDTO) {
    return signInLogDAO.selectAll(signInLogDTO);
  }

  @Override
  public SignInLogDTO selectOne(SignInLogDTO signInLogDTO) {
    return signInLogDAO.selectOne(signInLogDTO);
  }

  @Override
  public boolean insert(SignInLogDTO signInLogDTO) {
    return signInLogDAO.insert(signInLogDTO);
  }

  @Override
  public boolean update(SignInLogDTO signInLogDTO) {
    return signInLogDAO.update(signInLogDTO);
  }

  @Override
  public boolean delete(SignInLogDTO signInLogDTO) {
    return signInLogDAO.delete(signInLogDTO);
  }
}
