package infinitystone.chalKag.controller.async;

import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FreePostImgUpload {
  @Autowired
  private PostImgService postImgService;

  @RequestMapping("/freePostImgUpload")
  public @ResponseBody boolean freePostImgUpload(PostImgDTO postImgDTO){



    return false;
  }

}
