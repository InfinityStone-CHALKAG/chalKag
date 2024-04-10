package infinitystone.chalKag.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService") // @Service : 회원 정보의 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class MemberServiceImpl implements MemberService { // 회원 정보와 관련된 비즈니스 로직을 수행할 ServiceImpl 클래스

  @Autowired // @Autowired : MemberDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
  private MemberDAO memberDAO;

  // MemberService 인터페이스의 메서드를 구현
  // DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환
  @Override
  public List<MemberDTO> selectAll(MemberDTO memberDTO) {
    return memberDAO.selectAll(memberDTO);
  }

  @Override
  public MemberDTO selectOne(MemberDTO memberDTO) {
    return memberDAO.selectOne(memberDTO);
  }

  @Override
  public boolean insert(MemberDTO memberDTO) {
    return memberDAO.insert(memberDTO);
  }

  @Override
  public boolean update(MemberDTO memberDTO) {
    return memberDAO.update(memberDTO);
  }

  @Override
  public boolean delete(MemberDTO memberDTO) {
    return memberDAO.delete(memberDTO);
  }

}
