package infinitystone.chalKag.controller.freePost;

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

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import jakarta.servlet.http.HttpSession;

@Controller
public class WriteFreePostController {
	@Autowired
	private FreePostService freePostService;
	
	@Autowired
	private PostImgService postImgService;
	
	@RequestMapping(value = "/writeFreePost", method = RequestMethod.POST)
	public String writeFreePost(FreePostDTO freePostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam("file") MultipartFile[] files) {
		freePostDTO.setMemberId((String) session.getAttribute("member"));

	    System.out.println("WriteFreePostController In Log = [" + freePostDTO + "]");
	    System.out.println("WriteFreePostController In Log = [" + postImgDTO + "]");

	    String uploadDir = this.getClass().getResource("").getPath();
	    System.out.println("WriteFreePostController Log01 = [" + uploadDir + "]");

	    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
	    System.out.println("WriteFreePostController Log02 = [" + uploadDir + "]");

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
	    if (!freePostService.insert(freePostDTO)) {
	      System.out.println("WriteFreePostController insertion of post failed");
	    }

	    if (!postImgService.insert(postImgDTO)) {
	      System.out.println("WriteFreePostController insertion of image failed");
	    }

	    System.out.println("WriteFreePostController Out Log");

	    return "redirect:freePostList";
	}
}
