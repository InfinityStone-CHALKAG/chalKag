package infinitystone.chalKag.biz.signinlog;

import lombok.Data;

@Data
public class SignInLogDTO {

  private String signInLogId;
  private String memberId;
  private String signInLogDate;
  private String year;
  private String month;
  private String week;
  private String yearlySignIn;
  private String monthlySignIn;
  private String weeklySignIn;
  private String dailySignIn;

}
