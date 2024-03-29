package infinitystone.chalKag.controller.freePost;

import com.google.gson.Gson;
import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreePostListController {

	@Autowired
	private FreePostService freePostService;

	@RequestMapping("/freePostList")
	public String freePostList(Model model, FreePostDTO freePostDTO, Gson gson) {

		System.out.println("FreePostListController In로그");

		freePostDTO.setSearchCondition("freePostList");

		String freePostListResult = gson.toJson(freePostService.selectAll(freePostDTO));



		model.addAttribute("freePostList", freePostListResult);

		System.out.println("FreePostListController Out로그");

		return "freePost/freePostList";
	}

}
