package infinitystone.chalKag.controller.freePost;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FreePostSingleController {
	
	@Autowired
	private FreePostService freePostService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/freePostSingle")
	public String freePostList(Model model, FreePostDTO freePostDTO, CommentDTO commentDTO) {

		// freePostDTO에 있는 정보로 게시글 내용 불러오기
		
		freePostDTO = freePostService.selectOne(freePostDTO);
		commentDTO.setPostId(freePostDTO.getFreePostId());
		
		List<CommentDTO> commentDatas = commentService.selectAll(commentDTO);
		model.addAttribute("freePostSingle",freePostDTO);
		model.addAttribute("commentList", commentDatas);
		
		return "freePostSingle";
	}
	
}
