package infinitystone.chalKag.controller.headHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;

@Controller
public class DeleteHeadHuntPostController {
	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@RequestMapping(value = "/deleteHeadHuntPost", method = RequestMethod.GET)
	public String writeHeadHuntPost(HeadHuntPostDTO headHuntPostDTO) {
		// 사용자의 글을 삭제
		if(!headHuntPostService.delete(headHuntPostDTO)) {
			System.out.println("[DeleteHeadHuntPostController] 삭제 실패");
		}
		System.out.println("[DeleteHeadHuntPostController] 삭제 성공");
		return "headHuntPostList";
	}
}
