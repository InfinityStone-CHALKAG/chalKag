package infinitystone.chalKag.controller.reply;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.reply.ReplyDTO;
import infinitystone.chalKag.biz.reply.ReplyService;

@Controller
public class WriteReplyController {

	@Autowired
	private ReplyService replyService;

	@RequestMapping (value="/writeReply", method=RequestMethod.POST)
	public @ResponseBody ReplyDTO writeReply(ReplyDTO replyDTO, HttpSession session){
		System.out.println("[WriteReplyController] Input 로그");
		replyDTO.setMemberId((String)session.getAttribute("member"));

		boolean replyInsertResult= replyService.insert(replyDTO);

		if(replyInsertResult) {
			System.out.println("[WriteReplyController] 답글 작성 성공");
			return replyDTO;
		}
		System.out.println("[WriteReplyController] 답글 작성 실패");
		return null;
	}

}
