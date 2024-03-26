package infinitystone.chalKag.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;

@Controller
public class UpdateCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/updateComment", method=RequestMethod.POST)
	public @ResponseBody int updateComment(CommentDTO commentDTO) {
		
		boolean commentUpdateResult = commentService.delete(commentDTO);
		
		if(commentUpdateResult) {
			System.out.println("[DeleteCommentController] 댓글 수정 성공");
			return 1;
		}
		System.out.println("[DeleteCommentController] 댓글 수정 실패");
		return 0;
	}
	
	
	
}
