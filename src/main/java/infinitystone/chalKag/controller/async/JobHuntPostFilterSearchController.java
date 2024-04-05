package infinitystone.chalKag.controller.async;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.jobHuntPost.IJobHuntPostDAO;
import infinitystone.chalKag.biz.jobHuntPost.IJobHuntPostService;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobHuntPostFilterSearchController {
	
	
	@Autowired
	private Gson gson;

	@Autowired
	private IJobHuntPostService iJobHuntPostService;
	
	@RequestMapping("/jobHuntPostFilterSearch")
	public @ResponseBody String filterSearchController(JobHuntPostDTO jobHuntPostDTO) {
		// 필터링된 데이터를 가져오는 서비스 호출
    return gson.toJson(iJobHuntPostService.selectAll(jobHuntPostDTO));
	}
}
