package infinitystone.chalKag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;

@Controller
public class HeadHuntPostFilterSearchController {
	
	
	@Autowired
	private Gson gson;
	
	@RequestMapping("/headHuntPostfilterSearch")
	public @ResponseBody void filterSearchController(Model model, HeadHuntPostDTO headHuntPostDTO) {
		
		
		
		
		
		
	}
}
