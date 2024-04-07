package infinitystone.chalKag.controller.async;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeadHuntPostFilterSearchController {
	
	
	@Autowired
	private Gson gson;

	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@RequestMapping("/headHuntPostFilterSearch")
	public @ResponseBody String filterSearchController(HeadHuntPostDTO headHuntPostDTO) {
		// 필터링된 데이터를 가져오는 서비스 호출
    return gson.toJson(headHuntPostService.selectAll(headHuntPostDTO));
	}
}
