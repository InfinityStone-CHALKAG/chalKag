package infinitystone.chalkag.controller.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class FilterSearchController {
	
	
	@Autowired
	private Gson gson;
	
	@RequestMapping("/filterSearch")
	public @ResponseBody void filterSearchController(Model model,@RequestParam(required = false) String activeRegion, @RequestParam(required = false) String concept) {
		
		
	}
}
