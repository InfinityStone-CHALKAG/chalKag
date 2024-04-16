package infinitystone.chalKag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.jobHuntPost.IJobHuntPostService;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import jakarta.servlet.http.HttpSession;

@Controller
public class JobHuntPostFilterSearchController {
	
	
	@Autowired
	private Gson gson;

	@Autowired
	private IJobHuntPostService iJobHuntPostService;
	
	@RequestMapping("/jobHuntPostFilterSearch")
	public @ResponseBody String filterSearchController(HttpSession session, JobHuntPostDTO jobHuntPostDTO) {
		// 필터링된 데이터를 가져오는 서비스 호출
		
		String memberId = (String)session.getAttribute("member");
	    if (memberId == null) {
	    	memberId = null;
		}
	    
	    jobHuntPostDTO.setMemberId(memberId);
		
		return gson.toJson(iJobHuntPostService.selectAll(jobHuntPostDTO));
	}
}
