package infinitystone.chalKag.biz.reply;

import java.util.List;

public interface ReplyService { // 답글 관련 컨트롤러에서 사용할 서비스 (답글과 관련된 비즈니스 로직을 정의하는 인터페이스)

  public List<ReplyDTO> selectAll(ReplyDTO replyDTO);

  public ReplyDTO selecOne(ReplyDTO replyDTO);

  public boolean insert(ReplyDTO replyDTO);

  public boolean update(ReplyDTO replyDTO);

  public boolean delete(ReplyDTO replyDTO);

}
