package infinitystone.chalKag.controller.freePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;

@Controller
public class DeleteFreePostController {
	@Autowired
	private FreePostService freePostService;
	
	@RequestMapping(value = "/DeleteFreePost", method = RequestMethod.POST)
	public String writeFreePost(FreePostDTO freePostDTO) {
		// 사용자의 글을 삭제
		if(!freePostService.delete(freePostDTO)) {
			System.out.println("[DeleteFreePostController] 삭제 실패");
		}
		System.out.println("[DeleteFreePostController] 삭제 성공");
		return "freePostList";
	}
}
