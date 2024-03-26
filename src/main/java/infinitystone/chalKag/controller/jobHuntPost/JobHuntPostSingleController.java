package infinitystone.chalKag.controller.jobHuntPost;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;

import java.util.List;

@Controller
public class JobHuntPostSingleController {
	
	@Autowired
	private JobHuntPostService jobHuntPostService;

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/jobHuntPostSingle")
	public String jobHuntPostList(Model model, JobHuntPostDTO jobHuntPostDTO, CommentDTO commentDTO) {

		// jobHuntPostDTO에 있는 정보로 게시글 내용 불러오기

		jobHuntPostDTO = jobHuntPostService.selectOne(jobHuntPostDTO);
		commentDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());

		List<CommentDTO> commentDatas = commentService.selectAll(commentDTO);
		model.addAttribute("jobHuntPostSingle",jobHuntPostDTO);
		model.addAttribute("commentList", commentDatas);

		return "jobHuntPostSingle";
	}
	
}
