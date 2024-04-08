package infinitystone.chalKag.controller.headHuntPost;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UpdateHeadHuntPostController {

  @Autowired
  private HeadHuntPostService headHuntPostService;

  @Autowired
  private PostImgService postImgService;


  @RequestMapping(value = "/updateHeadHuntPost", method = RequestMethod.GET)
  public String updateHeadHuntPostPage(HeadHuntPostDTO headHuntPostDTO, Model model) {

    headHuntPostDTO.setSearchCondition("headHuntPostSingle");
    headHuntPostDTO = headHuntPostService.selectOne(headHuntPostDTO);
    System.out.println(headHuntPostDTO.getHeadHuntPostId());
    model.addAttribute("updateHeadHuntPost", headHuntPostDTO);
    return "headHuntPost/updateHeadHuntPost";
  }

  @RequestMapping(value = "/updateHeadHuntPost", method = RequestMethod.POST)
  public String updateHeadHuntPost(HeadHuntPostDTO headHuntPostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam(value = "file", required = false) MultipartFile[] files) {
    // 사용자의 글을 수정
    headHuntPostDTO.setMemberId((String) session.getAttribute("member"));

    System.out.println("UpdateHeadHuntPostController In Log = [" + headHuntPostDTO + "]");
    System.out.println("UpdateHeadHuntPostController In Log = [" + postImgDTO + "]");

    String uploadDir = this.getClass().getResource("").getPath();
    System.out.println("UpdateHeadHuntPostController Log01 = [" + uploadDir + "]");

    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
    System.out.println("UpdateHeadHuntPostController Log02 = [" + uploadDir + "]");

    headHuntPostDTO.setSearchCondition("headHuntPostUpdate");

    // Update the user-written post
    if (!headHuntPostService.update(headHuntPostDTO)) {
      System.out.println("UpdateHeadHuntPostController Update of post failed");
    }
    postImgDTO.setPostId(headHuntPostDTO.getHeadHuntPostId());

    if (!postImgService.delete(postImgDTO)) {
      System.out.println("UpdateHeadHuntPostController Delete of post images failed");
    }

    for (MultipartFile file : files) {
      if (file != null && !file.isEmpty()) {
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFilename = UUID.randomUUID().toString() + "." + extension;
        String filePath = uploadDir + File.separator + newFilename;
        File newFile = new File(filePath);
        postImgDTO.setPostImgName(newFilename);
        if (!postImgService.insert(postImgDTO)) {
          System.out.println("UpdateHeadHuntPostController insertion of image failed");
        }
        try {
          file.transferTo(newFile);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    System.out.println("UpdateHeadHuntPostController Out Log");


    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "redirect:headHuntPostList";
    }

    return "redirect:headHuntPostList";


  }
}
