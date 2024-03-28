package infinitystone.chalKag.biz.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentService {

  @Autowired
  private PaymentDAO paymentDAO;

  public boolean insert(PaymentDTO paymentDTO) {
    return paymentDAO.insert(paymentDTO);
  }

}
