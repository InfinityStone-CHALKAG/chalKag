package infinitystone.chalKag.biz.reply;

import java.util.List;

public interface ReplyService {

  public List<ReplyDTO> selectAll(ReplyDTO replyDTO);

  public ReplyDTO selecOne(ReplyDTO replyDTO);

  public boolean inset(ReplyDTO replyDTO);

  public boolean update(ReplyDTO replyDTO);

  public boolean delete(ReplyDTO replyDTO);

}
