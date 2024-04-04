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
	public @ResponseBody boolean deleteReply(ReplyDTO replyDTO) {
		return replyService.delete(replyDTO);
	}
}
