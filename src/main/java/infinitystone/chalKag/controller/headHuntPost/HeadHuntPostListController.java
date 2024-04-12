package infinitystone.chalKag.controller.headHuntPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeadHuntPostListController {
// "/headHuntPostList" 요청이 들어오면 해당 요청을 처리하기 위해 headHuntPostList 메서드가 호출되며,
// 호출된 메서드로 서치 조건에 따라 글 목록을 조회한 후 JSON 형식으로 변환하여 모델에 저장
// 저장한 데이터는 "headHuntPost/headHuntPostList" 뷰로 반환

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @RequestMapping("/headHuntPostList")
  public String headHuntPostList(HttpSession session, Model model, HeadHuntPostDTO headHuntPostDTO, Gson gson) {

    System.out.println("HeadHuntPostListController In로그");
    
    String memberId = (String)session.getAttribute("member");
    if (memberId == null) {
    	memberId = null;
	}
    headHuntPostDTO.setMemberId(memberId);

    headHuntPostDTO.setSearchCondition("headHuntPostList");
    String headHuntPostListResult = gson.toJson(headHuntPostService.selectAll(headHuntPostDTO));
    
    headHuntPostDTO.setSearchCondition("headHuntPostPremium1MonthList");
    String PremiumHeadHuntPostList = gson.toJson(headHuntPostService.selectAll(headHuntPostDTO));

    model.addAttribute("headHuntPostList", headHuntPostListResult);
    model.addAttribute("PremiumHeadHuntPostList", PremiumHeadHuntPostList);

    System.out.println("HeadHuntPostListController Out로그");

    return "headHuntPost/headHuntPostList";
  }

}
