package infinitystone.chalKag.controller.common;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.profileImg.ProfileImgDTO;
import infinitystone.chalKag.biz.profileImg.ProfileImgService;
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
public class ChangeInformationController {

  @Autowired
  public MemberService memberService;

  @Autowired
  public ProfileImgService profileImgService;

  @RequestMapping(value = "changeInformation", method = RequestMethod.GET)
  public String changeInformationPage(Model model, MemberDTO memberDTO, ProfileImgDTO profileImgDTO, HttpSession session) {
    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("myPage");

    model.addAttribute("memberInfo", memberService.selectOne(memberDTO));

    return "changeInformation";
  }

  @RequestMapping(value = "/changeInformation", method = RequestMethod.POST)
  public String changeInformation(MemberDTO memberDTO, ProfileImgDTO profileImgDTO, @RequestParam("file") MultipartFile file, HttpSession session) {

    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("changeIntroduction");
    profileImgDTO.setMemberId(memberDTO.getMemberId());

    System.out.println("ChangeInformationController In로그 = [" + memberDTO + "]");

    if (!file.isEmpty()) {

      String uploadDir = this.getClass().getResource("").getPath();
      System.out.println("ChangeInformationController 로그01 = [" + uploadDir + "]");

      uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalKag")) +
          "chalKag/src/main/resources/static/profileImg"; // 윈도우 경로
      System.out.println("SignUpController 로그02 = [" + uploadDir + "]");
      // uploadDir = uploadDir.substring(0, uploadDir.indexOf("/WEB-INF")) + "/memberProfileImages"; // 맥북 경로

      String originalFilename = file.getOriginalFilename();            // 파일명을 저장하는 변수
      String extension = FilenameUtils.getExtension(originalFilename);    // 확장자 저장 변수
      String newFilename = UUID.randomUUID().toString() + "." + extension;  // 새 파일명 및 확장자 저장 변수
      String filePath = uploadDir + File.separator + newFilename;        // 위의 내용을 모두 통합하여 저장하는 변수
      File newFile = new File(filePath);
      try {
        file.transferTo(newFile); // 지정한 위치에 파일 데이터를 저장합니다
      } catch (IOException e) {
        e.printStackTrace();
      }
      profileImgDTO.setProfileImgName(newFilename);

      if (!profileImgService.insert(profileImgDTO)) {
        System.out.println("ChangeInformationController Out로그");
        return "redirect:error";
      }

    }

    if (!memberService.update(memberDTO)) {
      System.out.println("ChangeInformationController Out로그");
      return "redirect:error";
    }

    System.out.println("ChangeInformationController Out로그");

    try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "redirect:myPage";
	}
    
    return "redirect:myPage";
  }

}
