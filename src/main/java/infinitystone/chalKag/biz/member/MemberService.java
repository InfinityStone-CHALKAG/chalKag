package infinitystone.chalKag.biz.member;

import java.util.List;

public interface MemberService { // 회원 정보의 컨트롤러에서 사용할 서비스 (회원 정보와 관련된 비즈니스 로직을 정의하는 인터페이스)

	public List<MemberDTO> selectAll(MemberDTO memberDTO);

	public MemberDTO selectOne(MemberDTO memberDTO);

	public boolean insert(MemberDTO memberDTO);

	public boolean update(MemberDTO memberDTO);

	public boolean delete(MemberDTO memberDTO);

}
