package infinitystone.chalkag.controller.freePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalkag.biz.freePost.FreePostDTO;
import infinitystone.chalkag.biz.freePost.FreePostService;

@Controller
public class UpdateFreePostController {
	@Autowired
	private FreePostService freePostService;
	
	@RequestMapping(value = "/UpdateFreePost", method = RequestMethod.POST)
	public String writeFreePost(FreePostDTO freePostDTO) {
		// 사용자의 글을 수정
		if(!freePostService.delete(freePostDTO)) {
			System.out.println("[UpdateFreePostController] 수정 실패");
		}
		System.out.println("[UpdateFreePostController] 수정 성공");
		return "freePostSingle";
	}
}
