package infinitystone.chalKag.biz.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("paymentDAO")
public class PaymentDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String INSERT = "INSERT INTO PAYMENT ( MEMBER_id, ORDER_id, AMOUNT) " +
      "VALUES (?, ?, ?)";

  public boolean insert(PaymentDTO paymentDTO) {
    System.out.println("PaymentDAO(insert) In로그 = [" + paymentDTO + "]");
    if (jdbcTemplate.update(INSERT, paymentDTO.getMemberId(), paymentDTO.getOrderId(), paymentDTO.getAmount()) <= 0) {
      return false;
    }
    return true;
  }

}
