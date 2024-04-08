package infinitystone.chalKag.controller.jobHuntPost;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
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
public class UpdateJobHuntPostController {

	@Autowired
	private JobHuntPostService jobHuntPostService;

	@Autowired
	private PostImgService postImgService;


	@RequestMapping(value = "/updateJobHuntPost", method = RequestMethod.GET)
	public String updateJobHuntPostPage(JobHuntPostDTO jobHuntPostDTO, Model model) {

		jobHuntPostDTO.setSearchCondition("jobHuntPostSingle");
		jobHuntPostDTO = jobHuntPostService.selectOne(jobHuntPostDTO);
		System.out.println(jobHuntPostDTO.getJobHuntPostId());
		model.addAttribute("updateJobHuntPost", jobHuntPostDTO);
		return "jobHuntPost/updateJobHuntPost";
	}

	@RequestMapping(value = "/updateJobHuntPost", method = RequestMethod.POST)
	public String updateJobHuntPost(JobHuntPostDTO jobHuntPostDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam(value = "file", required = false) MultipartFile[] files) {
		// 사용자의 글을 수정
		jobHuntPostDTO.setMemberId((String) session.getAttribute("member"));

		System.out.println("UpdateJobHuntPostController In Log = [" + jobHuntPostDTO + "]");
		System.out.println("UpdateJobHuntPostController In Log = [" + postImgDTO + "]");

		String uploadDir = this.getClass().getResource("").getPath();
		System.out.println("UpdateJobHuntPostController Log01 = [" + uploadDir + "]");

		uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
		System.out.println("UpdateJobHuntPostController Log02 = [" + uploadDir + "]");

		jobHuntPostDTO.setSearchCondition("jobHuntPostUpdate");

		// Update the user-written post
		if (!jobHuntPostService.update(jobHuntPostDTO)) {
			System.out.println("UpdateJobHuntPostController Update of post failed");
		}
		postImgDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());

		if (!postImgService.delete(postImgDTO)) {
			System.out.println("UpdateJobHuntPostController Delete of post images failed");
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
					System.out.println("UpdateJobHuntPostController insertion of image failed");
				}
				try {
					file.transferTo(newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("UpdateJobHuntPostController Out Log");


		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:jobHuntPostList";
		}

		return "redirect:jobHuntPostList";


	}
}
