package infinitystone.chalKag.controller.headHuntPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeadHuntPostListController {

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @RequestMapping("/headHuntPostList")
  public String headHuntPostList(Model model, HeadHuntPostDTO headHuntPostDTO, Gson gson) {

    System.out.println("HeadHuntPostListController In로그");

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
