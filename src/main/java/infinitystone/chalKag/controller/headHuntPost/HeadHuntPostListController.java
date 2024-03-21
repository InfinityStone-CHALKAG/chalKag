package infinitystone.chalKag.controller.headHuntPost;

import java.util.List;

import infinitystone.chalKag.biz.postImg.PostImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;

@Controller
public class HeadHuntPostListController {

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @Autowired
  private PostImgService postImgService;

  @RequestMapping("/headHuntPostList")
  public String headHuntPostList(Model model, HeadHuntPostDTO headHuntPostDTO) {

    System.out.println("HeadHuntPostListController In로그");

    headHuntPostDTO.setSearchCondition("selectAllHeadHuntPost");

    List<HeadHuntPostDTO> result = headHuntPostService.selectAll(headHuntPostDTO);

    model.addAttribute("headHuntPostList", result);

    System.out.println("HeadHuntPostListController Out로그 = [" + result + "]");

    return "headHuntPostList";
  }

}
