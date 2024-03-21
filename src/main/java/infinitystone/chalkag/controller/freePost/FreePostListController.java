package infinitystone.chalkag.controller.freePost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalkag.biz.freePost.FreePostDTO;
import infinitystone.chalkag.biz.freePost.FreePostService;

@Controller
public class FreePostListController {
	
	@Autowired
	private FreePostService freePostService;
	
	@RequestMapping("/freePostList")
	public String freePostList(Model model, FreePostDTO freePostDTO) {

		System.out.println("FreePostListController In로그");
		
		List<FreePostDTO> result = freePostService.selectAll(freePostDTO);
		model.addAttribute("freePostList",result);
		
		return "freePostList";
	}
	
}
