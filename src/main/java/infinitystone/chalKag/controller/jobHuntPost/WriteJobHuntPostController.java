package infinitystone.chalKag.controller.jobHuntPost;

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

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import jakarta.servlet.http.HttpSession;

@Controller
public class WriteJobHuntPostController {

  @Autowired
  private JobHuntPostService jobHuntPostService;

  @Autowired
  private PostImgService postImgService;

  @RequestMapping(value = "/writeJobHuntPost", method = RequestMethod.GET)
  public String writeJobHuntPostPage() {
    return "writeJobHuntPost";
  }

  @RequestMapping(value = "/writeJobHuntPost", method = RequestMethod.POST)
  public String writeJobHuntPost(JobHuntPostDTO jobHuntPostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam("file") MultipartFile[] files) {

	  jobHuntPostDTO.setMemberId((String) session.getAttribute("member"));

	    System.out.println("WriteJobHuntPostController In Log = [" + jobHuntPostDTO + "]");
	    System.out.println("WriteJobHuntPostController In Log = [" + postImgDTO + "]");

	    String uploadDir = this.getClass().getResource("").getPath();
	    System.out.println("WriteJobHuntPostController Log01 = [" + uploadDir + "]");

	    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
	    System.out.println("WriteJobHuntPostController Log02 = [" + uploadDir + "]");

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
	    if (!jobHuntPostService.insert(jobHuntPostDTO)) {
	      System.out.println("WriteJobHuntPostController insertion of post failed");
	    }

	    if (!postImgService.insert(postImgDTO)) {
	      System.out.println("WriteJobHuntPostController insertion of image failed");
	    }

	    System.out.println("WriteJobHuntPostController Out Log");

	    return "redirect:jobHuntPostList";
  }
}
