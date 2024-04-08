package infinitystone.chalKag.controller.freePost;

import infinitystone.chalKag.biz.freePost.FreePostDTO;
import infinitystone.chalKag.biz.freePost.FreePostService;
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
public class UpdateFreePostController {

	@Autowired
	private FreePostService freePostService;

	@Autowired
	private PostImgService postImgService;


	@RequestMapping(value = "/updateFreePost", method = RequestMethod.GET)
	public String updateFreePostPage(FreePostDTO freePostDTO, Model model) {

		freePostDTO.setSearchCondition("freePostSingle");
		freePostDTO = freePostService.selectOne(freePostDTO);
		System.out.println(freePostDTO.getFreePostId());
		model.addAttribute("updateFreePost", freePostDTO);
		return "freePost/updateFreePost";
	}

	@RequestMapping(value = "/updateFreePost", method = RequestMethod.POST)
	public String updateFreePost(FreePostDTO freePostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam(value = "file", required = false) MultipartFile[] files) {
		// 사용자의 글을 수정
		freePostDTO.setMemberId((String) session.getAttribute("member"));

		System.out.println("UpdateFreePostController In Log = [" + freePostDTO + "]");
		System.out.println("UpdateFreePostController In Log = [" + postImgDTO + "]");

		String uploadDir = this.getClass().getResource("").getPath();
		System.out.println("UpdateFreePostController Log01 = [" + uploadDir + "]");

		uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
		System.out.println("UpdateFreePostController Log02 = [" + uploadDir + "]");

		freePostDTO.setSearchCondition("freePostUpdate");

		// Update the user-written post
		if (!freePostService.update(freePostDTO)) {
			System.out.println("UpdateFreePostController Update of post failed");
		}
		postImgDTO.setPostId(freePostDTO.getFreePostId());

		if (!postImgService.delete(postImgDTO)) {
			System.out.println("UpdateFreePostController Delete of post images failed");
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
					System.out.println("UpdateFreePostController insertion of image failed");
				}
				try {
					file.transferTo(newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("UpdateFreePostController Out Log");


		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:freePostList";
		}

		return "redirect:freePostList";


	}
}
