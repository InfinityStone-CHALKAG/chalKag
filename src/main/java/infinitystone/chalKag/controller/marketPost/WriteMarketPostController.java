package infinitystone.chalKag.controller.marketPost;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import jakarta.servlet.http.HttpSession;

@Controller
public class WriteMarketPostController {

  @Autowired
  private MarketPostService marketPostService;

  @Autowired
  private PostImgService postImgService;

  @RequestMapping(value = "/writeMarketPost", method = RequestMethod.GET)
  public String writeMarketPostPage() {
    return "writeMarketPost";
  }

  @RequestMapping(value = "/writeMarketPost", method = RequestMethod.POST)
  public String writeMarketPost(MarketPostDTO marketPostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam("file") MultipartFile[] files) {

	  marketPostDTO.setMemberId((String) session.getAttribute("member"));

	    System.out.println("WriteMarketPostController In Log = [" + marketPostDTO + "]");
	    System.out.println("WriteMarketPostController In Log = [" + postImgDTO + "]");

	    String uploadDir = this.getClass().getResource("").getPath();
	    System.out.println("WriteMarketPostController Log01 = [" + uploadDir + "]");

	    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
	    System.out.println("WriteMarketPostController Log02 = [" + uploadDir + "]");

	    for (MultipartFile file : files) {
	      if (file != null && !file.isEmpty()) {
	        String originalFilename = file.getOriginalFilename();
	        String extension = FilenameUtils.getExtension(originalFilename);
	        String newFilename = UUID.randomUUID().toString() + "." + extension;
	        String filePath = uploadDir + File.separator + newFilename;
	        File newFile = new File(filePath);
	        try {
	          file.transferTo(newFile);
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	        postImgDTO.setPostImgName(newFilename);
	      }
	    }

	    // Add the user-written post
	    if (!marketPostService.insert(marketPostDTO)) {
	      System.out.println("WriteMarketPostController insertion of post failed");
	    }

	    if (!postImgService.insert(postImgDTO)) {
	      System.out.println("WriteMarketPostController insertion of image failed");
	    }

	    System.out.println("WriteMarketPostController Out Log");

	    return "redirect:marketPostList";
  }
}