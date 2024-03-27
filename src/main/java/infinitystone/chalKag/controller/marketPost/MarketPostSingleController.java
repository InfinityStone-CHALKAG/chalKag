package infinitystone.chalKag.controller.marketPost;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;

import java.util.List;

@Controller
public class MarketPostSingleController {
	
	@Autowired
	private MarketPostService marketPostService;

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/marketPostSingle")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO, CommentDTO commentDTO) {

		// marketPostDTO에 있는 정보로 게시글 내용 불러오기

		marketPostDTO = marketPostService.selectOne(marketPostDTO);
		commentDTO.setPostId(marketPostDTO.getMarketPostId());

		List<CommentDTO> commentDatas = commentService.selectAll(commentDTO);
		model.addAttribute("marketPostSingle",marketPostDTO);
		model.addAttribute("commentList", commentDatas);

		return "marketPostSingle";
	}
	
}
