package infinitystone.chalKag.controller.marketPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import infinitystone.chalKag.biz.recommend.RecommendDTO;
import infinitystone.chalKag.biz.recommend.RecommendService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MarketPostSingleController {

	@Autowired
	private MarketPostService marketPostService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private PostImgService postImgService;
	
	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private JobHuntPostService jobHuntPostService;
	
	@Autowired
	private FreePostService freePostService;
	
	@Autowired
	private HeadHuntPostService headHuntPostService;


	@RequestMapping("/marketPostSingle")
	public String marketPostList(HttpSession session, Model model, MarketPostDTO marketPostDTO, CommentDTO commentDTO, PostImgDTO postImgDTO, RecommendDTO recommendDTO, JobHuntPostDTO jobHuntPostDTO, FreePostDTO freePostDTO, HeadHuntPostDTO headHuntPostDTO) {

		// marketPostDTO에 있는 정보로 게시글 내용 불러오기
		marketPostDTO.setSearchCondition("marketPostSingle");
		marketPostDTO = marketPostService.selectOne(marketPostDTO);
		commentDTO.setPostId(marketPostDTO.getMarketPostId());
		postImgDTO.setSearchCondition("marketPostSingleImg");
		postImgDTO.setPostId(marketPostDTO.getMarketPostId());
		
		recommendDTO.setPostId(marketPostDTO.getMarketPostId());
		
		recommendDTO.setMemberId((String)session.getAttribute("member"));
	
		recommendDTO = recommendService.selectOne(recommendDTO);

		MarketPostDTO latedto = new MarketPostDTO();
		latedto.setSearchCondition("marketPostRecentPostSingle");
		MarketPostDTO latestMarketPost = marketPostService.selectOne(latedto);
		
		// 최신 구인글
	    headHuntPostDTO.setSearchCondition("headHuntPostRecentPostSingle");
	    HeadHuntPostDTO latestHeadHuntPost  = headHuntPostService.selectOne(headHuntPostDTO);
		
	    // 최신 구직글
	    jobHuntPostDTO.setSearchCondition("jobHuntPostRecentPostSingle");
	    JobHuntPostDTO latestJobHuntPost = jobHuntPostService.selectOne(jobHuntPostDTO);
	
	   
	    // 최신 자유글
	    freePostDTO.setSearchCondition("freePostRecentPostSingle");
	    FreePostDTO latestFreePost = freePostService.selectOne(freePostDTO);

		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		List<CommentDTO> commentList = commentService.selectAll(commentDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("marketPostSingle",marketPostDTO);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postImgList", postImgList);
		
		model.addAttribute("recommendInfo", recommendDTO);
		model.addAttribute("latestMarketPost", latestMarketPost);
		model.addAttribute("latestHeadHuntPost", latestHeadHuntPost);
	    model.addAttribute("latestJobHuntPost", latestJobHuntPost);
	    model.addAttribute("latestFreePost", latestFreePost);


		return "marketPost/marketPostSingle";
	}

}
