package infinitystone.chalKag.controller.jobHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;

@Controller
public class JobHuntPostListController {

  @Autowired
  private JobHuntPostService jobHuntPostService;

  @RequestMapping("/jobHuntPostList")
  public String jobHuntPostList(Model model, JobHuntPostDTO jobHuntPostDTO) {

    System.out.println("JobHuntPostListController In로그");

    jobHuntPostDTO.setSearchCondition("selectAllJobHuntPost");

    List<JobHuntPostDTO> result = jobHuntPostService.selectAll(jobHuntPostDTO);

    model.addAttribute("jobHuntPostList", result);

    System.out.println("jobHuntPostListController Out로그 = [" + result + "]");

    return "jobHuntPostList";
  }

}
