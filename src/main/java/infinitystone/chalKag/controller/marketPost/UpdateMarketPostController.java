package infinitystone.chalKag.controller.marketPost;

import infinitystone.chalKag.biz.marketPost.MarketPostDTO;
import infinitystone.chalKag.biz.marketPost.MarketPostService;
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
import java.util.List;
import java.util.UUID;

@Controller
public class UpdateMarketPostController {

	@Autowired
	private MarketPostService marketPostService;

	@Autowired
	private PostImgService postImgService;


	@RequestMapping(value = "/updateMarketPost", method = RequestMethod.GET)
	public String updateMarketPostPage(MarketPostDTO marketPostDTO, Model model, PostImgDTO postImgDTO) {

		marketPostDTO.setSearchCondition("marketPostSingle");
		marketPostDTO = marketPostService.selectOne(marketPostDTO);
		postImgDTO.setSearchCondition("marketPostSingleImg");
		postImgDTO.setPostId(marketPostDTO.getMarketPostId());

		List<PostImgDTO> postImgList = postImgService.selectAll(postImgDTO);
		System.out.println("postImgList : " + postImgList);
		model.addAttribute("marketPostSingle",marketPostDTO);
		model.addAttribute("postImgList", postImgList);


		return "marketPost/updateMarketPost";
	}

	@RequestMapping(value = "/updateMarketPost", method = RequestMethod.POST)
	public String updateMarketPost(MarketPostDTO marketPostDTO, PostImgDTO postImgDTO, HttpSession session,  @RequestParam(value = "file", required = false) MultipartFile[] files) {
		// 사용자의 글을 수정
		marketPostDTO.setMemberId((String) session.getAttribute("member"));

		System.out.println("UpdateMarketPostController In Log = [" + marketPostDTO + "]");
		System.out.println("UpdateMarketPostController In Log = [" + postImgDTO + "]");

		String uploadDir = this.getClass().getResource("").getPath();
		System.out.println("UpdateMarketPostController Log01 = [" + uploadDir + "]");

		uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) + "chalKag/src/main/resources/static/postImg";
		System.out.println("UpdateMarketPostController Log02 = [" + uploadDir + "]");

		// Update the user-written post
		if (!marketPostService.update(marketPostDTO)) {
			System.out.println("UpdateMarketPostController Update of post failed");
		}
		postImgDTO.setPostId(marketPostDTO.getMarketPostId());

		if(!postImgService.update(postImgDTO)){
			System.out.println("UpdateMarketPostController Update of post images failed");
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
					System.out.println("UpdateMarketPostController insertion of image failed");
				}
				try {
					file.transferTo(newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("UpdateMarketPostController Out Log");



		return "redirect:marketPost/marketPostSingle";
	}
}
