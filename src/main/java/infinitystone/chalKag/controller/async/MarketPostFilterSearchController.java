package infinitystone.chalKag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.marketPost.IMarketPostService;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;

@Controller
public class MarketPostFilterSearchController {

	@Autowired
	private Gson gson;

	@Autowired
	private IMarketPostService iMarketPostService;

	@RequestMapping("/marketPostFilterSearch")
	public @ResponseBody String filterSearchController(MarketPostDTO marketPostDTO) {

		 String status = marketPostDTO.getMarketPostStatus();
		 System.out.println("filterSearchController 로그  status 값 : " + status);
		    
		    if (status != null) {
		        if (status.equals("Sell")) {
		            marketPostDTO.setSearchCondition("marketPostSellList");
		        } else if (status.equals("Buy")) {
		            marketPostDTO.setSearchCondition("marketPostBuyList");
		        } else if (status.equals("Freecycle")) {
		            marketPostDTO.setSearchCondition("marketPostFreecycleList");
		        } else {
		            System.out.println("MarketPostListController Out로그 : marketPostDTO.SearchCondition is null");
		            return null;
		        }
		    }
		    
		// 필터링된 데이터를 가져오는 서비스 호출
		return gson.toJson(iMarketPostService.selectAll(marketPostDTO));
	}
}
