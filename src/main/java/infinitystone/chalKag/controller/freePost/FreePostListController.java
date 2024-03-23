package infinitystone.chalKag.controller.freePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class FreePostListController {
	
	@Autowired
	private FreePostService freePostService;
	
	@Autowired
	private PostImgService postImgService;
	
	@RequestMapping("/freePostList")
	public String freePostList(Model model, FreePostDTO freePostDTO, PostImgDTO postImgDTO, Gson gson) {

		System.out.println("FreePostListController In로그");

	    freePostDTO.setSearchCondition("freePostList");
	    postImgDTO.setSearchCondition("freePostListImg");

	    String freePostListResult = gson.toJson(freePostService.selectAll(freePostDTO));
	    String freePostListImgResult = gson.toJson(postImgService.selectAll(postImgDTO));

	    model.addAttribute("freePostList", freePostListResult);
	    model.addAttribute("freePostListImg", freePostListImgResult);

	    System.out.println("FreePostListController Out로그");

	    return "freePostList";
	}
	
}
