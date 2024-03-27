package infinitystone.chalKag.controller.admin;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.admin.AdminDTO;
import infinitystone.chalKag.biz.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SignInCountByYearMonthDateController {

  @Autowired
  private AdminService adminService;

  @RequestMapping("/signInCountByYearMonthDate")
  public @ResponseBody String signInCountByYearMonthDate(AdminDTO adminDTO, Gson gson, Model model) {

    adminDTO.setSearchCondition("signInCountByYearMonthDate");
    System.out.println("SignInCountByYearMonthDateController In로그 = [" + adminDTO + "]");

    String signInCountByYearMonthDateResult = gson.toJson(adminService.signInCountByYearMonthDate(adminDTO));

    return signInCountByYearMonthDateResult;
  }

}
