package infinitystone.chalKag.controller.marketPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class MarketPostSingleController {

	@Autowired
	private MarketPostService marketPostService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private PostImgService postImgService;

	@RequestMapping("/marketPostSingle")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO, CommentDTO commentDTO, PostImgDTO postImgDTO) {

		// marketPostDTO에 있는 정보로 게시글 내용 불러오기

		marketPostDTO.setSearchCondition("marketPostSingle");
		marketPostDTO = marketPostService.selectOne(marketPostDTO);
		commentDTO.setPostId(marketPostDTO.getMarketPostId());
		postImgDTO.setSearchCondition("marketPostSingleImg");
		postImgDTO.setPostId(marketPostDTO.getMarketPostId());

		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		List<CommentDTO> commentList = commentService.selectAll(commentDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("marketPostSingle",marketPostDTO);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postImgList", postImgList);

		return "marketPost/marketPostSingle";
	}

}
