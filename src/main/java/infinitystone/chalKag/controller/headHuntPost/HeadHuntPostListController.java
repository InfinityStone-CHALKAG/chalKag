package infinitystone.chalKag.controller.headHuntPost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeadHuntPostListController {

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @Autowired
  private PostImgService postImgService;


  @RequestMapping("/headHuntPostList")
  public String headHuntPostList(Model model, HeadHuntPostDTO headHuntPostDTO, Gson gson, PostImgDTO postImgDTO) {

    System.out.println("HeadHuntPostListController In로그");

    headHuntPostDTO.setSearchCondition("headHuntPostList");
    postImgDTO.setSearchCondition("headHuntPostListImg");

    String headHuntPostListResult = gson.toJson(headHuntPostService.selectAll(headHuntPostDTO));
    String headHuntPostListImgResult = gson.toJson(postImgService.selectAll(postImgDTO));



    model.addAttribute("headHuntPostList", headHuntPostListResult);
    model.addAttribute("headHuntPostListImg", headHuntPostListImgResult);

    System.out.println("HeadHuntPostListController Out로그");

    return "headHuntPostList";
  }

}
