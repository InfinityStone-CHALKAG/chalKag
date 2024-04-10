package infinitystone.chalKag.biz.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService") // @Service : 답글과 관련된 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class ReplyServiceImpl implements ReplyService { // 답글과 관련된 비즈니스 로직을 수행할 ServiceImpl 클래스

  @Autowired // @Autowired : ReplyDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
  private ReplyDAO replyDAO;

  // ReplyService 인터페이스의 메서드를 구현
  // DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환
  @Override
  public List<ReplyDTO> selectAll(ReplyDTO replyDTO) {
    return replyDAO.selectAll(replyDTO);
  }

  @Override
  public ReplyDTO selecOne(ReplyDTO replyDTO) {
    return replyDAO.selectOne(replyDTO);
  }

  @Override
  public boolean insert(ReplyDTO replyDTO) {
    return replyDAO.insert(replyDTO);
  }

  @Override
  public boolean update(ReplyDTO replyDTO) {
    return replyDAO.update(replyDTO);
  }

  @Override
  public boolean delete(ReplyDTO replyDTO) {
    return replyDAO.delete(replyDTO);
  }
}
