package infinitystone.chalkag.controller.jobHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalkag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalkag.biz.jobHuntPost.JobHuntPostService;

@Controller
public class JobHuntPostSingleController {
	
	@Autowired
	private JobHuntPostService jobHuntPostService;
	
	@RequestMapping("/jobHuntPostSingle")
	public String jobHuntPostList(Model model, JobHuntPostDTO jobHuntPostDTO) {

		// jobHuntPostDTO에 있는 정보로 게시글 내용 불러오기
		
		jobHuntPostDTO = jobHuntPostService.selectOne(jobHuntPostDTO);
		
		model.addAttribute("jobHuntPostSingle",jobHuntPostDTO);
		
		return "jobHuntPostSingle";
	}
	
}
