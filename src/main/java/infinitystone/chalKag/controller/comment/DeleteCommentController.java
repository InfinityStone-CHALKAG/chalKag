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
	public @ResponseBody boolean deleteComment(CommentDTO commentDTO) {
		return commentService.delete(commentDTO);
	}
}
