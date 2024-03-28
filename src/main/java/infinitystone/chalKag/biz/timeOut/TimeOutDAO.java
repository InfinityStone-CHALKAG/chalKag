package infinitystone.chalKag.biz.timeOut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("timeOutDAO")
public class TimeOutDAO { 

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final String SELECTALL_TIMEOUT = "";

	private static final String SELECTONE_TIMEOUT = "SELECT "
			+ "		MEMBER_id, "		
			+ "		TIMEOUT_startdate, "
			+ "		TIMEOUT_duration "
			+ "FROM "
			+ "		TIMEOUT "
			+ "WHERE "
			+ "		TIMEOUT_id = ? ";
	private static final String INSERT_TIMEOUT = "INSERT INTO TIMEOUT ( "
			+ "MEMBER_id, "
			+ "TIMEOUT_duration) "
			+ "VALUES(?,?)";	
	
	private static final String UPDATE_TIMEOUT = " ";
	
	private static final String DELETE_TIMEOUT = "";
	
	
	public List<TimeOutDTO> selectAll(TimeOutDTO timeOutDTO) {
		List<TimeOutDTO> result = null;
	
			return null; 
		}
	
	
	public TimeOutDTO selectOne(TimeOutDTO timeOutDTO) {
		TimeOutDTO result = null;
		
			return null; 
		}
	

	
	public boolean insert(TimeOutDTO timeOutDTO) {
		int result = 0;
		System.out.println("TimeOutDAO(insert) In로그 = [" + timeOutDTO + "]");
		result = jdbcTemplate.update(INSERT_TIMEOUT, timeOutDTO.getMemberId(), timeOutDTO.getTimeOutDuration());
		if (result <= 0) {
			System.out.println("TimeOutDAO(insert) Out로그 = [" + result + "]");
			return false; 
		}
		return true; 
	}
	
	public boolean update(TimeOutDTO timeOutDTO) {
		return false;
	}

	public boolean delete(TimeOutDTO timeOutDTO) {
	
		return true; 
	}




class SelectAllTimeOutRoMapper implements RowMapper<TimeOutDTO> {
	@Override // mapRow 메서드 오버라이드
	public TimeOutDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
		
	}
}

//===== SELECTONE =====

// 신고글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneTimeOutRowMapper implements RowMapper<TimeOutDTO> {
	@Override // mapRow 메서드 오버라이드
	public TimeOutDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}
		
	}
}