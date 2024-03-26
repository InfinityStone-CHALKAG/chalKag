package infinitystone.chalKag.controller.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.reply.ReplyDTO;
import infinitystone.chalKag.biz.reply.ReplyService;

@Controller
public class DeleteReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/deleteReply", method=RequestMethod.POST)
	public @ResponseBody int deleteReply(ReplyDTO replyDTO) {
		
		boolean replyDeleteResult = replyService.delete(replyDTO);
		
		if(replyDeleteResult) {
			System.out.println("[DeleteReplyController] 댓글 삭제 성공");
			return 1;
		}
		System.out.println("[DeleteReplyController] 댓글 삭제 실패");
		return 0;
	}
	
	
}
