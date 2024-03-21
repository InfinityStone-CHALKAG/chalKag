package infinitystone.chalKag.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String start() {
		System.out.println("[로그] Index 페이지 이동");
		return "main";
	}
}
