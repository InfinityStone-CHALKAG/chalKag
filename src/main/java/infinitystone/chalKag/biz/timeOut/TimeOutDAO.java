package infinitystone.chalKag.biz.timeOut;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("timeOutDAO")
public class TimeOutDAO { 

	@Autowired
	private JdbcTemplate jdbcTemplate;


	private static final String SELECTALL_TIMEOUT = "";
	
	//정지당한 회원이 이미 있는지 체크
	private static final String SELECTONE_TIMEOUT = "SELECT "
			+ "		MEMBER_id "		
			+ "FROM "
			+ "		TIMEOUT "
			+ "WHERE "
			+ "		MEMBER_id = ? ";
	private static final String INSERT_TIMEOUT = "INSERT INTO TIMEOUT ( "
			+ "MEMBER_id, "
			+ "TIMEOUT_duration) "
			+ "VALUES(?,?)";	
	
	private static final String UPDATE_TIMEOUT = " ";
	
	private static final String DELETE_TIMEOUT = "DELETE "
			+ "FROM "
			+ "		TIMEOUT "
			+ "WHERE "
			+ "		MEMBER_id = ? ";
	
	
	public List<TimeOutDTO> selectAll(TimeOutDTO timeOutDTO) {
		List<TimeOutDTO> result = null;
	
			return null; 
		}
	
	
	public TimeOutDTO selectOne(TimeOutDTO timeOutDTO) {
		TimeOutDTO result = null;
		System.out.println("TimeOutDAO(selectOne) In로그 = [" +timeOutDTO + "]");
		// SELECTONE_REPORT 쿼리를 실행해 데이터베이스에 신고글 데이터를 불러옴 		
		try {
			Object[] args= {timeOutDTO.getMemberId()};
			result = jdbcTemplate.queryForObject(SELECTONE_TIMEOUT, args, new SelectOneTimeOutRowMapper());
			System.out.println("ReportDAO(selectAll) Out로그 = [" + result + "]");
			return result;			
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 에외 발생 시 null 반환
		}
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
		int result = jdbcTemplate.update(DELETE_TIMEOUT,timeOutDTO.getMemberId());
		if (result <= 0) {
			return false;
		}
		return true;
	}




	// 신고글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
	class SelectAllTimeOutRowMapper implements RowMapper<TimeOutDTO> {
		@Override // mapRow 메서드 오버라이드
		public TimeOutDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return null;
		}
			
	}

//===== SELECTONE =====

class SelectOneTimeOutRowMapper implements RowMapper<TimeOutDTO> {
	@Override // mapRow 메서드 오버라이드
	public TimeOutDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 ReportDTO 객체에 저장

		TimeOutDTO timeOutDTO = new TimeOutDTO(); // 새로운 ReportDTO 객체 생성
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
		timeOutDTO.setMemberId(rs.getString("MEMBER_id")); 					// 회원 아이디
		return timeOutDTO; // reportDTO에 저장된 데이터들을 반환
		
	}
}



}