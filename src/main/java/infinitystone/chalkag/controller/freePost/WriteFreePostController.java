package infinitystone.chalkag.controller.freePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalkag.biz.freePost.FreePostDTO;
import infinitystone.chalkag.biz.freePost.FreePostService;

@Controller
public class WriteFreePostController {
	@Autowired
	private FreePostService freePostService;
	
	@RequestMapping(value = "/writeFreePost", method = RequestMethod.POST)
	public String writeFreePost(FreePostDTO freePostDTO) {
		// 사용자가 작성한 글을 추가
		if(!freePostService.insert(freePostDTO)) {
			System.out.println("[WriteFreePostController] 작성 실패");
		}
		System.out.println("[WriteFreePostController] 작성 성공");
		return "freePostSingle";
	}
}
