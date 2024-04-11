package infinitystone.chalKag.controller.headHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import infinitystone.chalKag.biz.recommend.RecommendDTO;
import infinitystone.chalKag.biz.recommend.RecommendService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HeadHuntPostSingleController {
	// "/headHuntPostSingle" 요청이 들어오면 해당 요청을 처리하기 위해 headHuntPostList 메서드가 호출되며,
	// 호출된 메서드로 서치 조건에 따라 글 목록을 조회한 후 JSON 형식으로 변환하여 모델에 저장
	// 저장한 데이터는 "headHuntPost/headHuntPostSingle" 뷰로 반환	
	
	@Autowired
	private HeadHuntPostService headHuntPostService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostImgService postImgService;
	
	@Autowired
	private RecommendService recommendService;
	
	@RequestMapping("/headHuntPostSingle")
	public String headHuntPostList(HttpSession session, Model model, HeadHuntPostDTO headHuntPostDTO, CommentDTO commentDTO, PostImgDTO postImgDTO, RecommendDTO recommendDTO) {

		// headHuntPostDTO에 있는 정보로 게시글 내용 불러오기
		headHuntPostDTO.setSearchCondition("headHuntPostSingle");
		headHuntPostDTO = headHuntPostService.selectOne(headHuntPostDTO);
		commentDTO.setPostId(headHuntPostDTO.getHeadHuntPostId());
		postImgDTO.setSearchCondition("headHuntPostSingleImg");
		postImgDTO.setPostId(headHuntPostDTO.getHeadHuntPostId());
		
		recommendDTO.setPostId(headHuntPostDTO.getHeadHuntPostId());
		
		recommendDTO.setMemberId((String)session.getAttribute("member"));
	
		
		recommendDTO = recommendService.selectOne(recommendDTO);
		
		
		
		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		List<CommentDTO> commentList = commentService.selectAll(commentDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("headHuntPostSingle",headHuntPostDTO);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postImgList", postImgList);
		model.addAttribute("recommendInfo", recommendDTO);

		return "headHuntPost/headHuntPostSingle";
	}
	
}
