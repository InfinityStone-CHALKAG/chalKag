package infinitystone.chalKag.controller.jobHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class JobHuntPostListController {

  @Autowired
  private JobHuntPostService jobHuntPostService;
  

  @RequestMapping("/jobHuntPostList")
  public String jobHuntPostList(Model model, JobHuntPostDTO jobHuntPostDTO, Gson gson) {

	  System.out.println("JobHuntPostListController In로그");

	    jobHuntPostDTO.setSearchCondition("jobHuntPostList");

	    String jobHuntPostListResult = gson.toJson(jobHuntPostService.selectAll(jobHuntPostDTO));

	    model.addAttribute("jobHuntPostList", jobHuntPostListResult);

	    System.out.println("JobHuntPostListController Out로그");

	    return "jobHuntPostList";
  }

}
