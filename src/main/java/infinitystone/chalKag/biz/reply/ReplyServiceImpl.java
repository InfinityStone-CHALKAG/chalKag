package infinitystone.chalKag.biz.reply;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

  private ReplyDAO replyDAO;

  @Override
  public List<ReplyDTO> selectAll(ReplyDTO replyDTO) {
    return replyDAO.selectAll(replyDTO);
  }

  @Override
  public ReplyDTO selecOne(ReplyDTO replyDTO) {
    return replyDAO.selectOne(replyDTO);
  }

  @Override
  public boolean inset(ReplyDTO replyDTO) {
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
