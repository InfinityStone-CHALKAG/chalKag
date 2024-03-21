package infinitystone.chalkag.biz.member;

import lombok.Data;

@Data
public class MemberDTO {

  private String memberId;
  private String memberPw;
  private String memberName;
  private String memberNickname;
  private String memberPh;
  private String memberBirth;
  private String memberGender;
  private String memberIntroduction;
  private String memberGrade;
  private String currentScore;
  private String currentLevel;
  private String currentExp;
  private String currentNextExp;
  private String profileImgName;

  private String searchCondition;

}
