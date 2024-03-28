package infinitystone.chalKag.biz.timeOut;

import java.util.List;

public interface TimeOutService {
	
	List<TimeOutDTO> selectAll(TimeOutDTO timeOutDTO);
	
	TimeOutDTO selectOne(TimeOutDTO timeOutDTO);
	
	boolean insert(TimeOutDTO timeOutDTO);
	
	boolean update(TimeOutDTO timeOutDTO);
	
	boolean delete(TimeOutDTO timeOutDTO);
}
