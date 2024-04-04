package infinitystone.chalKag.controller.reply;

import infinitystone.chalKag.biz.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.reply.ReplyDTO;
import infinitystone.chalKag.biz.reply.ReplyService;

import javax.xml.stream.events.Comment;

@Controller
public class UpdateReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/updateReply", method=RequestMethod.POST)
	public @ResponseBody ReplyDTO updateReply(ReplyDTO replyDTO) {
		
		boolean replyUpdateResult = replyService.update(replyDTO);
		
		if(replyUpdateResult) {
			System.out.println("[DeleteReplyController] 댓글 수정 성공");
			return replyDTO;
		}
		System.out.println("[DeleteReplyController] 댓글 수정 실패");
		return null;
	}
	
	
	
}
