package infinitystone.chalKag.controller.marketPost;

import infinitystone.chalKag.biz.postImg.PostImgDTO;
import infinitystone.chalKag.biz.postImg.PostImgService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
  public String writeMarketPost(MarketPostDTO marketPostDTO, PostImgDTO postImgDTO,
                                @RequestParam("file") MultipartFile[] files) {

    System.out.println("WriteMarketPostController In로그 = [" + marketPostDTO + "]");
    System.out.println("WriteMarketPostController In로그 = [" + postImgDTO + "]");

    String uploadDir = this.getClass().getResource("").getPath();
    System.out.println("WriteMarketPostController 로그01  = [ " + uploadDir + "]");

    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalkag")) + "chalkag/src/main/resources/static/postImg";
    System.out.println("WriteMarketPostController 로그02 = [" + uploadDir + "]");

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

    System.out.println("WriteMarketPostController Out로그");

    return "marketPostList";
  }
}