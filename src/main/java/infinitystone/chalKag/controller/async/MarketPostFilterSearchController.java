package infinitystone.chalKag.controller.async;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MarketPostFilterSearchController {
	
	
	@Autowired
	private Gson gson;

	@Autowired
	private MarketPostService marketPostService;
	
	@RequestMapping("/marketPostFilterSearch")
	public @ResponseBody String filterSearchController(MarketPostDTO marketPostDTO) {
		// 필터링된 데이터를 가져오는 서비스 호출
    return gson.toJson(marketPostService.selectAll(marketPostDTO));
	}
}
