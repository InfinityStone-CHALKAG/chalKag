package infinitystone.chalKag.controller.reply;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.reply.ReplyDTO;
import infinitystone.chalKag.biz.reply.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyListController {

  @Autowired
  private ReplyService replyService;

  @Autowired
  private Gson gson;

  @RequestMapping(value="/replyList", method= RequestMethod.GET)
  public @ResponseBody String replyList(ReplyDTO replyDTO){
    System.out.println("ReplyListController Input 로그");
    String replyList = gson.toJson(replyService.selectAll(replyDTO));

    System.out.println("replyDTOList : "+replyList);
    System.out.println("ReplyListController Output 로그");

    return replyList;
  }
}
