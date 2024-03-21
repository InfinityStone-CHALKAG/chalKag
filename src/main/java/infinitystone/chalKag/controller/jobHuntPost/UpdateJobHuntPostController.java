package infinitystone.chalKag.controller.jobHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;

@Controller
public class UpdateJobHuntPostController {
	@Autowired
	private JobHuntPostService jobHuntPostService;
	
	@RequestMapping(value = "/UpdateJobHuntPost", method = RequestMethod.POST)
	public String writeJobHuntPost(JobHuntPostDTO jobHuntPostDTO) {
		// 사용자의 글을 수정
		if(!jobHuntPostService.delete(jobHuntPostDTO)) {
			System.out.println("[UpdateJobHuntPostController] 수정 실패");
		}
		System.out.println("[UpdateJobHuntPostController] 수정 성공");
		return "jobHuntPostSingle";
	}
}
