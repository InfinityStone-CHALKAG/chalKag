package infinitystone.chalkag.controller.jobHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalkag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalkag.biz.jobHuntPost.JobHuntPostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyJobHuntPostListController {
	@Autowired
	private JobHuntPostService jobHuntPostService;
	
	@RequestMapping("/myJobHuntPostList")
	public String myJobHuntPostList(HttpSession session, JobHuntPostDTO jobHuntPostDTO, Model model) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		jobHuntPostDTO.setMemberId((String)session.getAttribute("member"));
		jobHuntPostDTO.setSearchCondition("myJobHuntPostList");
		List<JobHuntPostDTO> jobHuntPostDatas = jobHuntPostService.selectAll(jobHuntPostDTO);
		
		model.addAttribute("myJobHuntPostList", jobHuntPostDatas);
		
		
		return "myJobHuntPostList";
	}
}
