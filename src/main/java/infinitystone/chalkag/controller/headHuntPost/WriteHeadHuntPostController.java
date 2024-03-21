package infinitystone.chalkag.controller.headHuntPost;

import infinitystone.chalkag.biz.headHuntPost.HeadHuntPostDTO;
import infinitystone.chalkag.biz.headHuntPost.HeadHuntPostService;
import infinitystone.chalkag.biz.postImg.PostImgDTO;
import infinitystone.chalkag.biz.postImg.PostImgService;
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
	public String writeHeadHuntPost(HeadHuntPostDTO headHuntPostDTO, PostImgDTO postImgDTO, @RequestParam("file") MultipartFile[] files) {

		System.out.println("WriteHeadHuntPostController In Log = [" + headHuntPostDTO + "]");
		System.out.println("WriteHeadHuntPostController In Log = [" + postImgDTO + "]");

		String uploadDir = this.getClass().getResource("").getPath();
		System.out.println("WriteHeadHuntPostController Log01 = [" + uploadDir + "]");

		uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalkag")) + "chalkag/src/main/resources/static/postImg";
		System.out.println("WriteHeadHuntPostController Log02 = [" + uploadDir + "]");

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
		if (!headHuntPostService.insert(headHuntPostDTO)) {
			System.out.println("WriteHeadHuntPostController insertion of post failed");
		}

		if (!postImgService.insert(postImgDTO)) {
			System.out.println("WriteHeadHuntPostController insertion of image failed");
		}

		System.out.println("WriteHeadHuntPostController Out Log");

		return "headHuntPostList";
	}
}
