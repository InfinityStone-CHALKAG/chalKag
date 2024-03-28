package infinitystone.chalKag.controller.headHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;

@Controller
public class UpdateHeadHuntPostController {
	
	@Autowired
	private HeadHuntPostService headHuntPostService;
	
	@Autowired
	private PostImgService postImgService;
	
	
	@RequestMapping(value = "/updateHeadHuntPost", method = RequestMethod.GET)
	public String updateHeadHuntPostPage(HeadHuntPostDTO headHuntPostDTO) {
		return "updateHeadHuntPost";
	}
	
	@RequestMapping(value = "/updateHeadHuntPost", method = RequestMethod.POST)
	public String updateHeadHuntPost(HeadHuntPostDTO headHuntPostDTO, PostImgDTO postImgDTO) {
		// 사용자의 글을 수정
		postImgDTO.setPostId(headHuntPostDTO.getHeadHuntPostId());
		postImgDTO.setSearchCondition("postImgDeleteAll");
		postImgService.delete(postImgDTO);
		
	
		
		
		return "updateHeadHuntPost";
	}
}
