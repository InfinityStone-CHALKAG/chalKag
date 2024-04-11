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
	// "/myHeadHuntPostList" 요청이 들어오면 해당 요청을 처리하기 위해 myHeadHuntPostList 메서드가 호출되며,
	// 호출된 메서드로 서치 조건에 따라 글 목록을 조회한 후 JSON 형식으로 변환하여 모델에 저장
	// 저장한 데이터는 "myPost/myHeadHuntPostList" 뷰로 반환
	
	@Autowired
	private HeadHuntPostService headHuntPostService;

	@RequestMapping("/myHeadHuntPostList")
	public String myHeadHuntPostList(HttpSession session, HeadHuntPostDTO headHuntPostDTO, Model model, Gson gson) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		headHuntPostDTO.setMemberId((String)session.getAttribute("member"));
		headHuntPostDTO.setSearchCondition("headHuntPostMemberList");
		
		String headHuntPostDatas = gson.toJson(headHuntPostService.selectAll(headHuntPostDTO));

		model.addAttribute("headHuntPostList", headHuntPostDatas);


		return "myPost/myHeadHuntPostList";
	}
}
