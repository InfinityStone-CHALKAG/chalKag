package infinitystone.chalKag.controller.jobHuntPost;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
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
  public String writeJobHuntPost(JobHuntPostDTO jobHuntPostDTO, PostImgDTO postImgDTO, @RequestParam("file") MultipartFile[] files) {

    System.out.println("WriteJobHuntPostController In로그 = [" + jobHuntPostDTO + "]");
    System.out.println("WriteJobHuntPostController In로그 = [" + postImgDTO + "]");

    String uploadDir = this.getClass().getResource("").getPath();
    System.out.println("WriteJobHuntPostController 로그01 = [" + uploadDir + "]");

    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalkag")) + "chalKag/src/main/resources/static/postImg";
    System.out.println("WriteJobHuntPostController 로그02 = [" + uploadDir + "]");

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

    // 사용자가 작성한 글을 추가
    if (!jobHuntPostService.insert(jobHuntPostDTO)) {
      System.out.println("WriteJobHuntPostController 작성 실패");
    }

    if (!postImgService.insert(postImgDTO)) {
      System.out.println("WriteJobHuntPostController 사진 추가 실패");
    }

    System.out.println("WriteJobHuntPostController Out로그");

    return "jobHuntPostList";
  }
}
