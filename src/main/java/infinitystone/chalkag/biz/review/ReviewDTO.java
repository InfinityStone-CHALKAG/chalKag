package infinitystone.chalkag.biz.review;

import lombok.Data;

@Data
public class ReviewDTO {

  private String reviewId;
  private String memberId;
  private String memberNickname;
  private String reviewPartner;
  private String reviewPartnerNickname;
  private String reviewDate;
  private String reviewScore;
  private String reviewContent;

}
