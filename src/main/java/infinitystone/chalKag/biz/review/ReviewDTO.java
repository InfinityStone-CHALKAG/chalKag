package infinitystone.chalKag.biz.review;

import lombok.Data;

@Data
public class ReviewDTO {

  private String reviewId;
  private String profileImgName;
  private String memberId;
  private String memberNickname;
  private String reviewPartner;
  private String reviewPartnerNickname;
  private String reviewDate;
  private String reviewScore;
  private String reviewContent;

  private int reviewStart;
  private int reviewCnt;
  private String reviewTotalCnt;
}
