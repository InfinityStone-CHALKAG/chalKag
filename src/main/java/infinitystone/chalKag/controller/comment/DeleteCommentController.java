package infinitystone.chalKag.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;

@Controller
public class DeleteCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/deleteComment", method=RequestMethod.POST)
	public @ResponseBody int deleteComment(CommentDTO commentDTO) {
		
		boolean commentDeleteResult = commentService.delete(commentDTO);
		
		if(commentDeleteResult) {
			System.out.println("[DeleteCommentController] 댓글 삭제 성공");
			return 1;
		}
		System.out.println("[DeleteCommentController] 댓글 삭제 실패");
		return 0;
	}
	
	
}
