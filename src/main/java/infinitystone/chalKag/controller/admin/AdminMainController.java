package infinitystone.chalKag.controller.admin;

import infinitystone.chalKag.biz.admin.AdminDTO;
import infinitystone.chalKag.biz.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {

  @Autowired
  private AdminService adminService;

  @RequestMapping("/adminMain")
  public String adminMain(AdminDTO adminDTO, Model model) {
    System.out.println("AdminMainController In로그");

    adminDTO.setSearchCondition("signUpCountByAgeGroup");

    model.addAttribute("signUpCountByAgeGroup", adminService.selectAll(adminDTO));

    adminDTO.setSearchCondition("signUpCountByGenderGroup");

    model.addAttribute("signUpCountByGenderGroup", adminService.selectOne(adminDTO));

    return "admin/adminMain";
  }
}
