package infinitystone.chalKag.controller.admin;

import com.google.gson.Gson;
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
  public String adminMain(AdminDTO adminDTO, Gson gson, Model model) {
    System.out.println("AdminMainController In로그");

    adminDTO.setSearchCondition("signUpCountByAgeGroup");

    String signUpCountByAgeGroupResult = gson.toJson(adminService.signUpCountByAgeGroup(adminDTO));

    model.addAttribute("signUpCountByAgeGroup", signUpCountByAgeGroupResult);

    adminDTO.setSearchCondition("signUpCountByGenderGroup");

    String signUpCountByGenderGroupResult = gson.toJson(adminService.signUpCountByGenderGroup(adminDTO));

    model.addAttribute("signUpCountByGenderGroup", signUpCountByGenderGroupResult);

    return "admin/adminMain";
  }
}
