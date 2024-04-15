package infinitystone.chalKag.controller.jobHuntPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.recommend.RecommendDTO;
import infinitystone.chalKag.biz.recommend.RecommendService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecommendJobHuntPostListController {
	@Autowired
	private RecommendService recommendService;

	@RequestMapping("/recommendJobHuntPostList")
	public String myJobHuntPostList(HttpSession session, RecommendDTO recommendDTO, Model model, Gson gson) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		recommendDTO.setMemberId((String)session.getAttribute("member"));
		recommendDTO.setSearchCondition("jobHuntPostRecommendList");
		String jobHuntPostDatas = gson.toJson(recommendService.selectAll(recommendDTO));

		model.addAttribute("jobHuntPostList", jobHuntPostDatas);


		return "recommendPost/recommendJobHuntPostList";
	}
}
