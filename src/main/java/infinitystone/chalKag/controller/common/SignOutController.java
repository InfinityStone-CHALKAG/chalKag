package infinitystone.chalKag.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignOutController {
    
    @RequestMapping("/signOut")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }
}
