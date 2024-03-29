package infinitystone.chalKag.controller.freePost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class FreePostSingleController {

	@Autowired
	private FreePostService freePostService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private PostImgService postImgService;

	@RequestMapping("/freePostSingle")
	public String freePostList(Model model, FreePostDTO freePostDTO, CommentDTO commentDTO, PostImgDTO postImgDTO) {

		// freePostDTO에 있는 정보로 게시글 내용 불러오기

		freePostDTO.setSearchCondition("freePostSingle");
		freePostDTO = freePostService.selectOne(freePostDTO);
		commentDTO.setPostId(freePostDTO.getFreePostId());
		postImgDTO.setSearchCondition("freePostSingleImg");
		postImgDTO.setPostId(freePostDTO.getFreePostId());

		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		List<CommentDTO> commentList = commentService.selectAll(commentDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("freePostSingle",freePostDTO);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postImgList", postImgList);

		return "freePost/freePostSingle";
	}

}
