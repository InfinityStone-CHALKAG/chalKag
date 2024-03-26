package infinitystone.chalKag.controller.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.reply.ReplyDTO;
import infinitystone.chalKag.biz.reply.ReplyService;

@Controller
public class UpdateReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/updateReply", method=RequestMethod.POST)
	public @ResponseBody int updateReply(ReplyDTO replyDTO) {
		
		boolean replyUpdateResult = replyService.delete(replyDTO);
		
		if(replyUpdateResult) {
			System.out.println("[DeleteReplyController] 댓글 수정 성공");
			return 1;
		}
		System.out.println("[DeleteReplyController] 댓글 수정 실패");
		return 0;
	}
	
	
	
}
