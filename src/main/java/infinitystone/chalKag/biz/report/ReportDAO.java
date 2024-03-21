package infinitystone.chalKag.biz.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("reportDAO")
public class ReportDAO { // 신고 DAO

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 신고글 전체 출력.전미지
	private static final String SELECTALL_REPORT = "SELECT "
			+ "		REPORT_id, "
			+ "		MEMBER_id, "		
			+ "		REPORT_suspector, "
			+ "		REPORT_date, "
			+ "		REPORT_content "
			+ "FROM "
			+ "		REPORT"
			+ "ORDER BY "
			+ "		REPORT_id DESC ";
	// 사용한 테이블 : 신고 테이블
	// 사용한 컬럼 (출력 내용) :
	// 신고글 아이디, 회원 아이디(회원 테이블), 피신고자 아이디, 신고글 작성 시간, 신고 내용
	
	// 신고글 상세 출력.전미지
	private static final String SELECTONE_REPORT = "SELECT "
			+ "		REPORT_id,"
			+ "		MEMBER_id, "		
			+ "		REPORT_suspector,"
			+ "		REPORT_date,"
			+ "		REPORT_content "
			+ "FROM "
			+ "		REPORT"
			+ "WHERE "
			+ "		REPORT_id = ? ";
	// 사용한 테이블 : 신고 테이블
	// 사용한 컬럼 (출력 내용) :
	// 신고글 아이디, 회원 아이디(회원 테이블), 피신고자 아이디, 신고글 작성 시간, 신고 내용

	// 신고글 작성. 전미지
	private static final String INSERT_REPORT = "INSERT INTO REPORT ( "
			+ "MEMBER_id, "
			+ "REPORT_suspector, "
			+ "REPORT_date, "
			+ "REPORT_content) "
			+ "VALUES(?,?,CURRENT_TIMESTAMP,?) ";	
	// 사용한 테이블 : 신고 테이블
	// 사용한 컬럼 (작성 내용) :
	// 회원 아이디(회원 테이블), 피신고자 아이디, 신고글 작성 시간, 신고 내용
	// 신고 아이디는 테이블 생성 시 AUTO_INCREMENT를 사용해 번호 부여

	// 사용 안 할 예정.전미지
	private static final String UPDATE = " ";
	
	// 신고글 삭제.전미지
	private static final String DELETE_REPORT = "DELETE "
			+ "FROM "
			+ "		REPORT "
			+ "WHERE "
			+ "		REPORT_id = ? ";
	
	
	// 신고글 전체 출력
	public List<ReportDTO> selectAll(ReportDTO reportDTO) {
		List<ReportDTO> result = null;
		System.out.println("ReportDAO(selectAll) In로그 = [" + reportDTO + "]");
		// 검색 조건에 해당될 경우 jdbcTemplate을 사용하여 SELECTALL 쿼리 실행 후 결과를 RowMapper로 매핑하여 반환
		try {
			result = jdbcTemplate.query(SELECTALL_REPORT, new SelectAllReportRoMapper());
			System.out.println("ReportDAO(selectAll) Out로그 = [" + result + "]");
			return result;
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 에외 발생 시 null 반환
		}
	}
	
	// 신고글 상세 출력
	public ReportDTO selectOne(ReportDTO reportDTO) {
		ReportDTO result = null;
		System.out.println("ReportDAO(selectOne) In로그 = [" +reportDTO + "]");
		// SELECTONE_REPORT 쿼리를 실행해 데이터베이스에 신고글 데이터를 불러옴 		
		try {
			Object[] args= {reportDTO.getReportId()};
			result = jdbcTemplate.queryForObject(SELECTONE_REPORT, args, new SelectOneReportRowMapper());
			System.out.println("ReportDAO(selectAll) Out로그 = [" + result + "]");
			return result;			
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 에외 발생 시 null 반환
		}
	}

	// 신고글 작성
	public boolean insert(ReportDTO reportDTO) {
		int result = 0;
		System.out.println("ReportDAO(insert) In로그 = [" + reportDTO + "]");
		// INSERT_REPORT 쿼리를 실행해 데이터베이스에 신고글 데이터를 저장
		result = jdbcTemplate.update(INSERT_REPORT);
		if (result <= 0) {
			System.out.println("ReportDAO(insert) Out로그 = [" + result + "]");
			return false; // 신고글 작성 실패 시 false 반환
		}
		return true; // 신고글 작성 성공 시 true 반환
	}
	
	// 사용 안 할 예정
	public boolean update(ReportDTO reportDTO) {
		return false;
	}

	// 신고글 작성
	public boolean delete(ReportDTO reportDTO) {
		int result = 0;
		System.out.println("ReportDAO(delete) In로그 = [" + reportDTO + "]");
		// DELETE_REPORT 쿼리를 실행해 데이터베이스에 신글 데이터를 삭제
		result = jdbcTemplate.update(DELETE_REPORT);
		if (result <= 0) {
			System.out.println("ReportDAO(delete) Out로그 = [" + result + "]");
			return false; // 신고글 작성 실패 시 false 반환
		}
		return true; // 신고글 작성 실패 시 true 반환
	}

}

//===== SELECTALL =====

// 신고글 전체 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectAllReportRoMapper implements RowMapper<ReportDTO> {
	@Override // mapRow 메서드 오버라이드
	public ReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 ReportDTO 객체에 저장

		ReportDTO reportDTO = new ReportDTO(); // 새로운 ReportDTO 객체 생성
		reportDTO.setReportId(rs.getString("REPORT_id")); 					// 신고 아이디
		reportDTO.setMemberId(rs.getString("MEMBER_id")); 					// 회원 아이디
		reportDTO.setReportSuspector(rs.getString("REPORT_suspector")); 	// 피신고자 아이디
		reportDTO.setReportDate(rs.getString("REPORT_date")); 				// 신고글 작성 시간
		reportDTO.setReportContent(rs.getString("REPORT_content")); 		// 신고 내용
		return reportDTO; // reportDTO에 저장된 데이터들을 반환
	}
}

//===== SELECTONE =====

// 신고글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneReportRowMapper implements RowMapper<ReportDTO> {
	@Override // mapRow 메서드 오버라이드
	public ReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 ReportDTO 객체에 저장

		ReportDTO reportDTO = new ReportDTO(); // 새로운 ReportDTO 객체 생성
		reportDTO.setReportId(rs.getString("REPORT_id")); 					// 신고 아이디
		reportDTO.setMemberId(rs.getString("MEMBER_id")); 					// 회원 아이디
		reportDTO.setReportSuspector(rs.getString("REPORT_suspector"));		// 피신고자 아이디
		reportDTO.setReportDate(rs.getString("REPORT_date")); 				// 신고글 작성 시간
		reportDTO.setReportContent(rs.getString("REPORT_content")); 		// 신고 내용
		return reportDTO; // reportDTO에 저장된 데이터들을 반환
	}
}