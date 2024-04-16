package infinitystone.chalKag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.IFreePostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class FreePostFilterSearchController {
	
	
	@Autowired
	private Gson gson;

	@Autowired
	private IFreePostService iFreePostService;
	
	@RequestMapping("/freePostFilterSearch")
	public @ResponseBody String filterSearchController(HttpSession session, FreePostDTO freePostDTO) {
		// 필터링된 데이터를 가져오는 서비스 호출
//		freePostDTO.setMemberId((String)session.getAttribute("member"));
		
		String memberId = (String)session.getAttribute("member");
	    if (memberId == null) {
	    	memberId = null;
		}
	    freePostDTO.setMemberId(memberId);
	    
		System.out.println("ctrl >> "+freePostDTO);
    return gson.toJson(iFreePostService.selectAll(freePostDTO));
	}
}
