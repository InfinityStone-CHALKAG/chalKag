package infinitystone.chalKag.controller.marketPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MarketPostListController {

	@Autowired
	private MarketPostService marketPostService;

	@RequestMapping("/marketPostList")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO, Gson gson) {

		System.out.println("MarketPostListController In로그");

		// 거래 게시글의 거래 상태를 담는 변수 status
		String status = marketPostDTO.getMarketPostStatus();

		if(status.equals("sell")){
			marketPostDTO.setSearchCondition("marketPostSellList");
		} else if (status.equals("buy")) {
			marketPostDTO.setSearchCondition("marketPostBuyList");
		} else if (status.equals("freecycle")) {
			marketPostDTO.setSearchCondition("marketPostFreecycleList");
		} else {
			System.out.println("MarketPostListController Out로그 : marketPostDTO.SearchCondition is null");
			return null;
		}

		String marketPostList = gson.toJson(marketPostService.selectAll(marketPostDTO));
		model.addAttribute("marketPostList", marketPostList);

		System.out.println("MarketPostListController Out로그");

		return "marketPost/marketPostList";
	}

}
