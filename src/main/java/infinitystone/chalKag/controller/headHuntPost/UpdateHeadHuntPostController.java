package infinitystone.chalKag.controller.headHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;

@Controller
public class UpdateHeadHuntPostController {
	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@RequestMapping(value = "/UpdateHeadHuntPost", method = RequestMethod.POST)
	public String writeHeadHuntPost(HeadHuntPostDTO headHuntPostDTO) {
		// 사용자의 글을 수정
		if(!headHuntPostService.delete(headHuntPostDTO)) {
			System.out.println("[UpdateHeadHuntPostController] 수정 실패");
		}
		System.out.println("[UpdateHeadHuntPostController] 수정 성공");
		return "headHuntPostSingle";
	}
}
