package infinitystone.chalKag.controller.comment;

import infinitystone.chalKag.biz.member.MemberDTO;
import infinitystone.chalKag.biz.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import infinitystone.chalKag.biz.comment.CommentDTO;
import infinitystone.chalKag.biz.comment.CommentService;

@Controller
public class WriteCommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  MemberService memberService;

  @RequestMapping(value = "/writeComment", method = RequestMethod.POST)
  public @ResponseBody CommentDTO writeComment(CommentDTO commentDTO, MemberDTO memberDTO, HttpSession session) {
    System.out.println("[WriteCommentController] Input 로그");
    commentDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setMemberId((String) session.getAttribute("member"));
    memberDTO.setSearchCondition("writeCommentExp");
    boolean commentInsertResult = commentService.insert(commentDTO);

    if (commentInsertResult) {
      memberService.update(memberDTO);
      System.out.println("[WriteCommentController] 댓글 작성 성공");
      return commentDTO;
    }
    System.out.println("[WriteCommentController] 댓글 작성 실패");
    return null;
  }

}
