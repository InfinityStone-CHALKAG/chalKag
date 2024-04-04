package infinitystone.chalKag.controller.async;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FreePostFilterSearchController {
	
	
	@Autowired
	private Gson gson;

	@Autowired
	private FreePostService freePostService;
	
	@RequestMapping("/freePostFilterSearch")
	public @ResponseBody String filterSearchController(FreePostDTO freePostDTO) {
		// 필터링된 데이터를 가져오는 서비스 호출
    return gson.toJson(freePostService.selectAll(freePostDTO));
	}
}
