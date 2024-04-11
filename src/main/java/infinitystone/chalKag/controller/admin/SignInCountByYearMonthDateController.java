package infinitystone.chalKag.controller.admin;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.admin.AdminDTO;
import infinitystone.chalKag.biz.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignInCountByYearMonthDateController {

  @Autowired
  private AdminService adminService;
// 월별 로그인 회수
  @RequestMapping("/signInCountByYearMonthDate")
  public @ResponseBody String signInCountByYearMonthDate(AdminDTO adminDTO, Gson gson) {
// 검색 상태 설정
    adminDTO.setSearchCondition("signInCountByYearMonthDate");
    System.out.println("SignInCountByYearMonthDateController In로그 = [" + adminDTO + "]");
// 월별 로그인 회수 정보를 JSON 형식의 문자열로 변환한 뒤 View 에 반환
    String signInCountByYearMonthDateResult = gson.toJson(adminService.signInCountByYearMonthDate(adminDTO));

    return signInCountByYearMonthDateResult;
  }

}
