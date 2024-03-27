package infinitystone.chalKag.controller.headHuntPost;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class WriteHeadHuntPostController {

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @Autowired
  private PostImgService postImgService;

  @RequestMapping(value = "/writeHeadHuntPost", method = RequestMethod.GET)
  public String writeHeadHuntPostPage() {
    return "writeHeadHuntPost";
  }

  @RequestMapping(value = "/writeHeadHuntPost", method = RequestMethod.POST)
  public String writeHeadHuntPost(HeadHuntPostDTO headHuntPostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam("file") MultipartFile[] files) {

    headHuntPostDTO.setMemberId((String) session.getAttribute("member"));

    System.out.println("WriteHeadHuntPostController In Log = [" + headHuntPostDTO + "]");
    System.out.println("WriteHeadHuntPostController In Log = [" + postImgDTO + "]");

    String uploadDir = this.getClass().getResource("").getPath();
    System.out.println("WriteHeadHuntPostController Log01 = [" + uploadDir + "]");

    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
    System.out.println("WriteHeadHuntPostController Log02 = [" + uploadDir + "]");

    // Add the user-written post
    if (!headHuntPostService.insert(headHuntPostDTO)) {
      System.out.println("WriteHeadHuntPostController insertion of post failed");
    }

    headHuntPostDTO.setSearchCondition("maxPostId");
    HeadHuntPostDTO maxPostIdDTO = headHuntPostService.selectOne(headHuntPostDTO);
    headHuntPostDTO.setHeadHuntPostId(maxPostIdDTO.getHeadHuntPostId());

    System.out.println(maxPostIdDTO.getHeadHuntPostId());

    postImgDTO.setPostId(headHuntPostDTO.getHeadHuntPostId());

    for (MultipartFile file : files) {
      if (file != null && !file.isEmpty()) {
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFilename = UUID.randomUUID().toString() + "." + extension;
        String filePath = uploadDir + File.separator + newFilename;
        File newFile = new File(filePath);
        postImgDTO.setPostImgName(newFilename);
        if (!postImgService.insert(postImgDTO)) {
          System.out.println("WriteHeadHuntPostController insertion of image failed");
        }
        try {
          file.transferTo(newFile);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    System.out.println("WriteHeadHuntPostController Out Log");

    return "redirect:headHuntPostList";
  }
}
