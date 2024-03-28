package infinitystone.chalKag.controller.marketPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketPostListController {

	@Autowired
	private MarketPostService marketPostService;

	@RequestMapping("/marketPostList")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO, Gson gson) {

		System.out.println("MarketPostListController In로그");

		marketPostDTO.setSearchCondition("marketPostList");

		String marketPostListResult = gson.toJson(marketPostService.selectAll(marketPostDTO));



		model.addAttribute("marketPostList", marketPostListResult);

		System.out.println("MarketPostListController Out로그");

		return "marketPostList";
	}

}
