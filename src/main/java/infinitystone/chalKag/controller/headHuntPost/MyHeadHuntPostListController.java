package infinitystone.chalKag.controller.headHuntPost;

import java.util.List;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyHeadHuntPostListController {
	@Autowired
	private HeadHuntPostService headHuntPostService;

	@RequestMapping("/myHeadHuntPostList")
	public String myHeadHuntPostList(HttpSession session, HeadHuntPostDTO headHuntPostDTO, Model model, Gson gson) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		headHuntPostDTO.setMemberId((String)session.getAttribute("member"));
		headHuntPostDTO.setSearchCondition("headHuntPostMemberList");
		String headHuntPostDatas = gson.toJson(headHuntPostService.selectAll(headHuntPostDTO));

		model.addAttribute("HeadHuntPostList", headHuntPostDatas);


		return "myPost/myHeadHuntPostList";
	}
}
