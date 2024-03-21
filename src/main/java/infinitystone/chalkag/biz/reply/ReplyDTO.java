package infinitystone.chalkag.biz.reply;

import lombok.Data;

@Data
public class ReplyDTO {

  private String replyId;
  private String commentId;
  private String memberId;
  private String memberNickname;
  private String replyContent;
  private String replyDate;

}
