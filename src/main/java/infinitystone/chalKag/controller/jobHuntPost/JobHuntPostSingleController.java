package infinitystone.chalKag.controller.jobHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class JobHuntPostSingleController {

	@Autowired
	private JobHuntPostService jobHuntPostService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private PostImgService postImgService;

	@RequestMapping("/jobHuntPostSingle")
	public String jobHuntPostList(Model model, JobHuntPostDTO jobHuntPostDTO, CommentDTO commentDTO, PostImgDTO postImgDTO) {

		// jobHuntPostDTO에 있는 정보로 게시글 내용 불러오기

		jobHuntPostDTO.setSearchCondition("jobHuntPostSingle");
		jobHuntPostDTO = jobHuntPostService.selectOne(jobHuntPostDTO);
		commentDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());
		postImgDTO.setSearchCondition("jobHuntPostSingleImg");
		postImgDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());

		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		List<CommentDTO> commentList = commentService.selectAll(commentDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("jobHuntPostSingle",jobHuntPostDTO);
		model.addAttribute("commentList", commentList);
		model.addAttribute("postImgList", postImgList);

		return "jobHuntPost/jobHuntPostSingle";
	}

}
