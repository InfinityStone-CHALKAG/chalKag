package infinitystone.chalKag.controller.common;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.profileImg.ProfileImgDTO;
import infinitystone.chalKag.biz.profileImg.ProfileImgService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SignUpController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private ProfileImgService profileImgService;

  // 멤버와 프로필이미지를 Command 객체로 활용.김성민
  // profileImgDTO에는 이미지 정보를 가지고 있음.김성민

  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public String signUpPage() {
    return "signUp";
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public String signUp(MemberDTO memberDTO, ProfileImgDTO profileImgDTO, @RequestParam("file") MultipartFile file) {

    System.out.println("SignUpController In로그 = [" + memberDTO + "]");
    System.out.println("SignUpController In로그 = [" + profileImgDTO + "]");

    memberDTO.setSearchCondition("signUp");

    String uploadDir = this.getClass().getResource("").getPath();
    System.out.println("SignUpController 로그01 = [" + uploadDir + "]");

// .metadata 앞까지 문자열잘라서 이미지가 저장되는 폴더인 memberProfileImages까지의 절대경로 부여
    uploadDir = uploadDir.substring(1, uploadDir.indexOf("chalkag")) +
        "chalkag/src/main/resources/static/profileImg"; // 윈도우 경로
    System.out.println("SignUpController 로그02 = [" + uploadDir + "]");
//		uploadDir = uploadDir.substring(0, uploadDir.indexOf("/WEB-INF")) + "/memberProfileImages"; // 맥북 경로

// 업로드 파일이 존재할 때
    if (file != null) {
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
    } else {
      // 만약 프로필 이미지를 등록하지 않았다면 기본 이미지를 추가.김성민
      profileImgDTO.setProfileImgName("default.png");
    }

    // 만약 닉네임을 입력하지 않았다면 닉네임을 ID로 설정.김성민
    if (memberDTO.getMemberNickname().trim() == null) {
      memberDTO.setMemberNickname(memberDTO.getMemberId());
    }

    // 프로필이미지 객체에 있는 memberId의 값을 멤버객체에 있는 memberId 값으로 설정.김성민
    profileImgDTO.setMemberId(memberDTO.getMemberId());

    if (!memberService.insert(memberDTO)) {
      System.out.println("[로그] Controller : 회원가입 실패");
      return "redirect:error";
    }

    // 프로필이미지 객체를 활용하여 프로필이미지를 추가.김성민
    if (!profileImgService.insert(profileImgDTO)) {
      System.out.println("[로그] Controller : 프로필 사진 추가 실패");
    }

    System.out.println("SignUpController Out로그");

    return "redirect:main";
  }
}

