package infinitystone.chalKag.biz.timeOut;

import java.util.List;

public interface TimeOutService { // 회원 정지와 관련 컨트롤러에서 사용할 서비스 (회원 정지와 관련된 비즈니스 로직을 정의하는 인터페이스)
	
	List<TimeOutDTO> selectAll(TimeOutDTO timeOutDTO);
	
	TimeOutDTO selectOne(TimeOutDTO timeOutDTO);
	
	boolean insert(TimeOutDTO timeOutDTO);
	
	boolean update(TimeOutDTO timeOutDTO);
	
	boolean delete(TimeOutDTO timeOutDTO);
}
