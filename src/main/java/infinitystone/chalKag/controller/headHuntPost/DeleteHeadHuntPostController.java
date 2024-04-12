package infinitystone.chalKag.controller.headHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;

@Controller
public class DeleteHeadHuntPostController {
	// "/deleteHeadHuntPost" URL에 대한 GET 요청을 writeHeadHuntPost 메소드로 매핑하고 구인글 삭제를 진행
	// 수행 결과에 따라 메시지를 출력  글 목록 페이지로 "headHuntPost/headHuntPostList" 뷰로 리다이렉트
	
	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@RequestMapping(value = "/deleteHeadHuntPost", method = RequestMethod.GET)
	public String writeHeadHuntPost(HeadHuntPostDTO headHuntPostDTO) {
		// 사용자의 글을 삭제
		if(!headHuntPostService.delete(headHuntPostDTO)) {
			System.out.println("[DeleteHeadHuntPostController] 삭제 실패");
		}
		System.out.println("[DeleteHeadHuntPostController] 삭제 성공");
		return "redirect:headHuntPostList";
	}
}
