package infinitystone.chalKag.controller.marketPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyMarketPostListController {
	@Autowired
	private MarketPostService marketPostService;
	
	@RequestMapping("/myMarketPostList")
	public String myMarketPostList(HttpSession session, MarketPostDTO marketPostDTO, Model model) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		marketPostDTO.setMemberId((String)session.getAttribute("member"));
		marketPostDTO.setSearchCondition("myMarketPostList");
		List<MarketPostDTO> marketPostDatas = marketPostService.selectAll(marketPostDTO);
		
		model.addAttribute("myMarketPostList", marketPostDatas);
		
		
		return "myMarketPostList";
	}
}
