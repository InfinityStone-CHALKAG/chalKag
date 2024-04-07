package infinitystone.chalKag.controller.recommend;

import infinitystone.chalKag.biz.recommend.RecommendDTO;
import infinitystone.chalKag.biz.recommend.RecommendService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecommendController {
  @Autowired
  private RecommendService recommendService;

  @RequestMapping("recommend")
  public @ResponseBody boolean recommend(RecommendDTO recommendDTO, HttpSession session) {

    recommendDTO.setMemberId((String) session.getAttribute("member"));
    if (recommendService.selectOne(recommendDTO) == null) {
      return recommendService.insert(recommendDTO);
    } else {
      return recommendService.delete(recommendDTO);
    }
  }
}
