package infinitystone.chalKag.biz.timeOut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("timeOutService")
public class TimeOutServiceImpl implements TimeOutService{

	@Autowired
	private TimeOutDAO timeOutDAO;
	
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
