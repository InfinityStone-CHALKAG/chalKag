package infinitystone.chalKag.controller.jobHuntPost;

import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostDTO;
import infinitystone.chalKag.biz.jobHuntPost.JobHuntPostService;
import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
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
public class WriteJobHuntPostController {

	@Autowired
	private JobHuntPostService jobHuntPostService;

	@Autowired
	private PostImgService postImgService;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/writeJobHuntPost", method = RequestMethod.GET)
	public String writeJobHuntPostPage() {
		return "jobHuntPost/writeJobHuntPost";
	}

	@RequestMapping(value = "/writeJobHuntPost", method = RequestMethod.POST)
	public String writeJobHuntPost(JobHuntPostDTO jobHuntPostDTO, MemberDTO memberDTO, PostImgDTO postImgDTO, HttpSession session, @RequestParam("file") MultipartFile[] files) {

		jobHuntPostDTO.setMemberId((String) session.getAttribute("member"));

		System.out.println("WriteJobHuntPostController In Log = [" + jobHuntPostDTO + "]");
		System.out.println("WriteJobHuntPostController In Log = [" + postImgDTO + "]");

		String uploadDir = this.getClass().getResource("").getPath();
		System.out.println("WriteJobHuntPostController Log01 = [" + uploadDir + "]");

		uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
		System.out.println("WriteJobHuntPostController Log02 = [" + uploadDir + "]");

		// Add the user-written post
		if (!jobHuntPostService.insert(jobHuntPostDTO)) {
			System.out.println("WriteJobHuntPostController insertion of post failed");
		}

		jobHuntPostDTO.setSearchCondition("maxPostId");
		JobHuntPostDTO maxPostIdDTO = jobHuntPostService.selectOne(jobHuntPostDTO);
		jobHuntPostDTO.setJobHuntPostId(maxPostIdDTO.getJobHuntPostId());

		System.out.println(maxPostIdDTO.getJobHuntPostId());

		postImgDTO.setPostId(jobHuntPostDTO.getJobHuntPostId());

		for (MultipartFile file : files) {
			if (file != null && !file.isEmpty()) {
				String originalFilename = file.getOriginalFilename();
				String extension = FilenameUtils.getExtension(originalFilename);
				String newFilename = UUID.randomUUID().toString() + "." + extension;
				String filePath = uploadDir + File.separator + newFilename;
				File newFile = new File(filePath);
				postImgDTO.setPostImgName(newFilename);
				if (!postImgService.insert(postImgDTO)) {
					System.out.println("WriteJobHuntPostController insertion of image failed");
				}
				try {
					file.transferTo(newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		memberDTO.setSearchCondition("writePostExp");
		memberService.update(memberDTO);

		System.out.println("WriteJobHuntPostController Out Log");

		return "redirect:jobHuntPostList";
	}
}
