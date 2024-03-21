package infinitystone.chalKag.controller.freePost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyFreePostListController {
	@Autowired
	private FreePostService freePostService;
	
	@RequestMapping("/myFreePostList")
	public String myFreePostList(HttpSession session, FreePostDTO freePostDTO, Model model) {
		// 현재 세션에 있는 로그인 정보를 가지고 와서 해당 유저가 쓴 글을 전부 출력
		freePostDTO.setMemberId((String)session.getAttribute("member"));
		freePostDTO.setSearchCondition("myFreePostList");
		List<FreePostDTO> freePostDatas = freePostService.selectAll(freePostDTO);
		
		model.addAttribute("myFreePostList", freePostDatas);
		
		
		return "myFreePostList";
	}
}
