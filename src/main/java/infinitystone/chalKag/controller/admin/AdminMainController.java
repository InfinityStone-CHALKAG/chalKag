package infinitystone.chalKag.controller.admin;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.admin.AdminDTO;
import infinitystone.chalKag.biz.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AdminMainController {

  @Autowired
  private AdminService adminService;

  @RequestMapping("/adminMain")
  public String adminMain(AdminDTO adminDTO, Gson gson, Model model) {
    System.out.println("AdminMainController In로그");

    // 헤더 정보 출력
    adminDTO.setSearchCondition("adminHeader");

    model.addAttribute("adminHeader", adminService.signUpCountByGenderGroup(adminDTO));

    // 연령별 가입자 수
    adminDTO.setSearchCondition("signUpCountByAgeGroup");

    String signUpCountByAgeGroupResult = gson.toJson(adminService.signUpCountByAgeGroup(adminDTO));

    model.addAttribute("signUpCountByAgeGroup", signUpCountByAgeGroupResult);

    // 성별 별 가입 자 수
    adminDTO.setSearchCondition("signUpCountByGenderGroup");

    String signUpCountByGenderGroupResult = gson.toJson(adminService.signUpCountByGenderGroup(adminDTO));

    model.addAttribute("signUpCountByGenderGroup", signUpCountByGenderGroupResult);

    // 날짜 별 방문자 수
    adminDTO.setSearchCondition("signInCountByYearMonthDate");
    adminDTO.setYear(String.valueOf(LocalDate.now().getYear()));
    adminDTO.setMonth(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")));

    String signInCountByYearMonthDateResult = gson.toJson(adminService.signInCountByYearMonthDate(adminDTO));

    model.addAttribute("signInCountByYearMonthDate", signInCountByYearMonthDateResult);

    // 요일 별 누적 방문자 수
    adminDTO.setSearchCondition("signInCountByDayOfWeek");

    String signInCountByDayOfWeekResult = gson.toJson(adminService.signInCountByDayOfWeek(adminDTO));

    model.addAttribute("signInCountByDayOfWeek", signInCountByDayOfWeekResult);

    // 연간 회원 가입자 수
    adminDTO.setSearchCondition("signUpCountByYear");

    String signUpCountByYearResult = gson.toJson(adminService.signUpCountByYear(adminDTO));

    model.addAttribute("signUpCountByYear", signUpCountByYearResult);

    return "admin/adminMain";
  }
}
