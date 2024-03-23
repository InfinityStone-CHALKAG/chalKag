package infinitystone.chalKag.controller.marketPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class MarketPostListController {
	
	@Autowired
	private MarketPostService marketPostService;
	
	@Autowired
	private PostImgService postImgService;
	
	@RequestMapping("/marketPostList")
	public String marketPostList(Model model, MarketPostDTO marketPostDTO, Gson gson, PostImgDTO postImgDTO) {
		
		System.out.println("MarketPostListController In로그");

	    marketPostDTO.setSearchCondition("marketPostList");
	    postImgDTO.setSearchCondition("marketPostListImg");

	    String marketPostListResult = gson.toJson(marketPostService.selectAll(marketPostDTO));
	    String marketPostListImgResult = gson.toJson(postImgService.selectAll(postImgDTO));

	    model.addAttribute("marketPostList", marketPostListResult);
	    model.addAttribute("marketPostListImg", marketPostListImgResult);

	    System.out.println("MarketPostListController Out로그");

	    return "marketPostList";
	}
	
}
