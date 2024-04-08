package infinitystone.chalKag.controller.async;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.IHeadHuntPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeadHuntPostFilterSearchController {


  @Autowired
  private Gson gson;

  @Autowired
  private IHeadHuntPostService IheadHuntPostService;

  @RequestMapping("/headHuntPostFilterSearch")
  public @ResponseBody String filterSearchController(HeadHuntPostDTO headHuntPostDTO) {
    // 필터링된 데이터를 가져오는 서비스 호출
    System.out.println("HHP@@" + headHuntPostDTO);
    System.out.println("서비스로그~!!!" + IheadHuntPostService.selectAll(headHuntPostDTO));

    return gson.toJson(IheadHuntPostService.selectAll(headHuntPostDTO));
  }
}
