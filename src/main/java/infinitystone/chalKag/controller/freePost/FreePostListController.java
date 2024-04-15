package infinitystone.chalKag.controller.freePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class FreePostListController {

	@Autowired
	private FreePostService freePostService;

	@RequestMapping("/freePostList")
	public String freePostList(HttpSession session,Model model, FreePostDTO freePostDTO, Gson gson) {

		String memberId = (String)session.getAttribute("member");
	    if (memberId == null) {
	    	memberId = null;
		}
	    freePostDTO.setMemberId(memberId);

	    freePostDTO.setSearchCondition("freePostList");
	    String freePostListResult = gson.toJson(freePostService.selectAll(freePostDTO));
	    

	    model.addAttribute("freePostList", freePostListResult);

	    System.out.println("freePostListController Out로그");

		return "freePost/freePostList";
	}

}
