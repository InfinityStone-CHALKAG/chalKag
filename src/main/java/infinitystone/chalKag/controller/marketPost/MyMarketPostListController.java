package infinitystone.chalKag.controller.marketPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyMarketPostListController {
	@Autowired
	private MarketPostService marketPostService;

	@RequestMapping("/myMarketPostList")
	public String myMarketPostList(HttpSession session, MarketPostDTO marketPostDTO, Model model, Gson gson) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		marketPostDTO.setMemberId((String)session.getAttribute("member"));
		marketPostDTO.setSearchCondition("marketPostMemberList");
		String marketPostDatas = gson.toJson(marketPostService.selectAll(marketPostDTO));

		model.addAttribute("marketPostList", marketPostDatas);


		return "myPost/myMarketPostList";
	}
}
