package infinitystone.chalKag.controller.headHuntPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.recommend.RecommendDTO;
import infinitystone.chalKag.biz.recommend.RecommendService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecommendHeadHuntPostListController {
	// "/recommendHeadHuntPostList" 요청이 들어오면 해당 요청을 처리하기 위해 myHeadHuntPostList 메서드가 호출되며,
	// 호출된 메서드로 서치 조건에 따라 글 목록을 조회한 후 JSON 형식으로 변환하여 모델에 저장
	// 저장한 데이터는"recommendPost/recommendHeadHuntPostList" 뷰로 반환
	
	@Autowired
	private RecommendService recommendService;

	@RequestMapping("/recommendHeadHuntPostList")
	public String myHeadHuntPostList(HttpSession session, RecommendDTO recommendDTO, Model model, Gson gson) {
		
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		recommendDTO.setMemberId((String)session.getAttribute("member"));
		recommendDTO.setSearchCondition("headHuntPostRecommendList");
		String headHuntPostDatas = gson.toJson(recommendService.selectAll(recommendDTO));

		model.addAttribute("headHuntPostList", headHuntPostDatas);

		return "recommendPost/recommendHeadHuntPostList";
	}
}
