package infinitystone.chalkag.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {

  @RequestMapping("/adminMain")
  public String adminMain() {
    System.out.println("AdminMainController 로그");
    return "admin/adminMain";
  }
}
