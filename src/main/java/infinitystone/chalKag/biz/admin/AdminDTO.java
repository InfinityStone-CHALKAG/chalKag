package infinitystone.chalKag.biz.admin;

import lombok.Data;

@Data
public class AdminDTO {

  private String ageGroup;
  private String maleGroup;
  private String femaleGroup;
  private String year;
  private String month;
  private String date;
  private String dayOfWeek;
  private String signUpCount;
  private String signInCount;


  private String searchCondition;

}
