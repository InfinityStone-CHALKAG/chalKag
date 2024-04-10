package infinitystone.chalKag.biz.timeOut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("timeOutService") // @Service : 회원 정지와 관련된 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class TimeOutServiceImpl implements TimeOutService{ // 회원 정지와 관련된 비즈니스 로직을 수행할 ServiceImpl 클래스

	@Autowired // @Autowired : TimeOutDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
	private TimeOutDAO timeOutDAO;
	
	// TimeOutService 인터페이스의 메서드를 구현
	// DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환	
	@Override
	public List<TimeOutDTO> selectAll(TimeOutDTO timeOutDTO) {
		return timeOutDAO.selectAll(timeOutDTO);
	}

	@Override
	public TimeOutDTO selectOne(TimeOutDTO timeOutDTO) {
		return timeOutDAO.selectOne(timeOutDTO);
	}

	@Override
	public boolean insert(TimeOutDTO timeOutDTO) {
		return timeOutDAO.insert(timeOutDTO);
	}

	@Override
	public boolean update(TimeOutDTO timeOutDTO) {
		return timeOutDAO.insert(timeOutDTO);
	}
	
	@Override
	public boolean delete(TimeOutDTO timeOutDTO) {
		return timeOutDAO.delete(timeOutDTO);
	}

}
