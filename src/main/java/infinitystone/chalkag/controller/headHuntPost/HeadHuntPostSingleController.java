package infinitystone.chalkag.controller.headHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import infinitystone.chalkag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalkag.biz.headHuntPost.HeadHuntPostService;

@Controller
public class HeadHuntPostSingleController {
	
	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@RequestMapping("/headHuntPostSingle")
	public String headHuntPostList(Model model, HeadHuntPostDTO headHuntPostDTO) {

		// headHuntPostDTO에 있는 정보로 게시글 내용 불러오기
		
		headHuntPostDTO = headHuntPostService.selectOne(headHuntPostDTO);
		
		model.addAttribute("headHuntPostSingle",headHuntPostDTO);
		
		return "headHuntPostSingle";
	}
	
}
