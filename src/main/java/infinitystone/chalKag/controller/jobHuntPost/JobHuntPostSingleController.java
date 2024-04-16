package infinitystone.chalKag.controller.jobHuntPost;

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
public class JobHuntPostSingleController {

	@Autowired
	private JobHuntPostService jobHuntPostService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private PostImgService postImgService;
	
	
	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@Autowired
	private FreePostService freePostService;
	
	@Autowired
	private MarketPostService marketPostService;
	

	@RequestMapping("/jobHuntPostSingle")
	public String jobHuntPostList(HttpSession session, Model model, HeadHuntPostDTO headHuntPostDTO, CommentDTO commentDTO, PostImgDTO postImgDTO, RecommendDTO recommendDTO, MarketPostDTO marketPostDTO,JobHuntPostDTO jobHuntPostDTO, FreePostDTO freePostDTO) {

		// jobHuntPostDTO에 있는 정보로 게시글 내용 불러오기

		jobHuntPostDTO.setSearchCondition("jobHuntPostSingle");
		jobHuntPostDTO = jobHuntPostService.selectOne(jobHuntPostDTO);
		commentDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());
		postImgDTO.setSearchCondition("jobHuntPostSingleImg");
		postImgDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());
		
		recommendDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());
		
		recommendDTO.setMemberId((String)session.getAttribute("member"));
		
		recommendDTO = recommendService.selectOne(recommendDTO);

		JobHuntPostDTO latedto = new JobHuntPostDTO();
		latedto.setSearchCondition("jobHuntPostRecentPostSingle");
		
		JobHuntPostDTO latestJobHuntPost  = jobHuntPostService.selectOne(latedto);
		
	    // 최신 구인글
	    headHuntPostDTO.setSearchCondition("headHuntPostRecentPostSingle");
	    HeadHuntPostDTO latestHeadHuntPost = headHuntPostService.selectOne(headHuntPostDTO);
	    
	    // 최신 판매글
	    marketPostDTO.setSearchCondition("marketPostRecentPostSingle");
	    MarketPostDTO latestMarketPost = marketPostService.selectOne(marketPostDTO);
	  
	    // 최신 자유글
	    freePostDTO.setSearchCondition("freePostRecentPostSingle");
	    FreePostDTO latestFreePost = freePostService.selectOne(freePostDTO);
		

		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		List<CommentDTO> commentList = commentService.selectAll(commentDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("jobHuntPostSingle",jobHuntPostDTO);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postImgList", postImgList);
		model.addAttribute("recommendInfo", recommendDTO);
		model.addAttribute("latestHeadHuntPost", latestHeadHuntPost);
	    model.addAttribute("latestJobHuntPost", latestJobHuntPost);
	    model.addAttribute("latestMarketPost", latestMarketPost);
	    model.addAttribute("latestFreePost", latestFreePost);

		return "jobHuntPost/jobHuntPostSingle";
	}

}
