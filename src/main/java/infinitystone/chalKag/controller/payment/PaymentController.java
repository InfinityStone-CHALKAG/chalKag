package infinitystone.chalKag.controller.payment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import infinitystone.chalKag.biz.payment.PaymentDTO;
import infinitystone.chalKag.biz.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private PaymentService paymentService;

  @RequestMapping(value = "payment", method = RequestMethod.GET)
  public String paymentPage(MemberDTO memberDTO, HttpSession session, Model model, Gson gson) {

    memberDTO.setMemberId(session.getAttribute("member").toString());
    memberDTO.setSearchCondition("myPage");

    System.out.println("PaymentController In로그 = [" + memberDTO + "]");

    String result = gson.toJson(memberService.selectOne(memberDTO));

    model.addAttribute("memberInfo", result);

    System.out.println("PaymentController Out로그 = [" + result + "]");

    return "payment/payment";
  }

  @RequestMapping(value = "payment", method = RequestMethod.POST)
  public @ResponseBody int payment(MemberDTO memberDTO, PaymentDTO paymentDTO, HttpSession session) {

    System.out.println("PaymentController In로그 = [" + paymentDTO + "]");

    if (!paymentService.insert(paymentDTO)) {
      return 0;
    }

    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("changeGrade");

    memberService.update(memberDTO);

    return 1;
  }
}
