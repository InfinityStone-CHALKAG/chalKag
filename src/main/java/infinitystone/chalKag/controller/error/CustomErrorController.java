package infinitystone.chalKag.controller.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    // RequestDispatcher를 통해 에러 정보 가져오기
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());

      // 상태 코드에 따라 다른 처리 로직
      if (statusCode == 404) {
        return "error/404";
      } else if (statusCode == 403) {
        return "error/403";
      } else if (statusCode == 500) {
        return "error/500";
      } else if (statusCode == 503) {
        return "error/503";
      }
    }
    return "error";
  }
}
