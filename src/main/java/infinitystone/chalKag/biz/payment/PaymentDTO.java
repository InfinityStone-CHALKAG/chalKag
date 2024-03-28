package infinitystone.chalKag.biz.payment;

import lombok.Data;

@Data
public class PaymentDTO {

  private String memberId;
  private String orderId;
  private String amount;

}
