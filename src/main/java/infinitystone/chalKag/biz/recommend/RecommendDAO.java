package infinitystone.chalKag.biz.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("recommendDAO")
public class RecommendDAO { // 게시글 좋아요 DAO
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 오라클 쿼리문 (NVL 사용)
//	private static final String SELECTONE = "SELECT POST_ID, MEMBER_ID FROM RECOMMEND WHERE POST_ID = ? AND MEMBER_ID = ? ";
//	private static final String INSERT = "INSERT INTO RECOMMEND (RECOMMENDNUM, POST_ID, MEMBER_ID) "
//			+ "VALUES ((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND), ?, ?) ";
//	private static final String DELETE = "DELETE FROM RECOMMEND WHERE POST_ID = ? AND MEMBER_ID = ?";
	
	// MySQL 쿼리문 (AUTO_INCREMENT 함수를 사용해 RECOMMEND_id를 자동 증감시킴)	
	// 특정 회원이 좋아요한 구인글 목록 출력 (구인글만 출력).전미지	
	private static final String SELECTALL_HEADHUNTPOSTRECOMMEND = "SELECT "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 선택
	        + "    'HEADHUNTPOST' AS post_category, " // 카테고리를 구인글로 선택
	        + "    COALESCE(HEADHUNTPOST.HEADHUNTPOST_id, 'Unknown') AS post_id, " // 게시글 아이디를 선택
	        + "    COALESCE(HEADHUNTPOST.HEADHUNTPOST_title, 'Unknown') AS post_title, " // 게시글 제목을 선택
	        + "    COALESCE(HEADHUNTPOST.HEADHUNTPOST_date, 'Unknown') AS post_date, " // 게시글 날짜를 선택
	        + "    COALESCE(HEADHUNTPOST.HEADHUNTPOST_viewcnt, 'Unknown') AS post_viewcnt, " // 게시글 조회수를 선택
	        + "    COUNT(RECOMMEND.POST_id) AS post_recommendcnt " // 게시글의 좋아요 수를 합산
	        + "FROM "
	        + "    RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 구인글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    HEADHUNTPOST ON RECOMMEND.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    RECOMMEND.MEMBER_id = ? " 
	        + "GROUP BY "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
	        + "    post_id, " // 게시글의 아이디를 기준으로 결과를 그룹화
	        + "    post_title, " // 게시글의 제목을 기준으로 결과를 그룹화
	        + "    post_date, " // 게시글의 날짜를 기준으로 결과를 그룹화
	        + "    post_viewcnt " // 게시글의 조회수를 기준으로 결과를 그룹화
			+ "ORDER BY "
			+ "		POST_id DESC ";
	
	// 특정 회원이 좋아요한 구직글 목록 출력 (구직글만 출력).전미지	
	private static final String SELECTALL_JOBHUNTPOSTRECOMMEND = "SELECT "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 선택
	        + "    'JOBHUNTPOST' AS post_category, " // 카테고리를 구직글로 지정
	        + "    COALESCE(JOBHUNTPOST.JOBHUNTPOST_id, 'Unknown') AS post_id, " // 게시글 아이디를 선택
	        + "    COALESCE(JOBHUNTPOST.JOBHUNTPOST_title, 'Unknown') AS post_title, " // 게시글 제목을 선택
	        + "    COALESCE(JOBHUNTPOST.JOBHUNTPOST_date, 'Unknown') AS post_date, " // 게시글 날짜를 선택
	        + "    COALESCE(JOBHUNTPOST.JOBHUNTPOST_viewcnt, 'Unknown') AS post_viewcnt, " // 게시글 조회수를 선택
	        + "    COUNT(RECOMMEND.POST_id) AS post_recommendcnt " // 게시글의 좋아요 수를 합산
	        + "FROM "
	        + "    RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 구직글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    JOBHUNTPOST ON RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    RECOMMEND.MEMBER_id = ? " 
	        + "GROUP BY "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
	        + "    post_id, " // 게시글의 아이디를 기준으로 결과를 그룹화
	        + "    post_title, " // 게시글의 제목을 기준으로 결과를 그룹화
	        + "    post_date, " // 게시글의 날짜를 기준으로 결과를 그룹화
	        + "    post_viewcnt " // 게시글의 조회수를 기준으로 결과를 그룹화
			+ "ORDER BY "
			+ "		POST_id DESC ";
	
	// 특정 회원이 좋아요한 자유글 목록 출력 (자유글만 출력).전미지	
	private static final String SELECTALL_FREEPOSTRECOMMEND = "SELECT "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 선택
	        + "    'FREEPOST' AS post_category, " // 카테고리를 자유글로 지정
	        + "    COALESCE(FREEPOST.FREEPOST_id, 'Unknown') AS post_id, " // 게시글 아이디를 선택
	        + "    COALESCE(FREEPOST.FREEPOST_title, 'Unknown') AS post_title, " // 게시글 제목을 선택
	        + "    COALESCE(FREEPOST.FREEPOST_date, 'Unknown') AS post_date, " // 게시글 날짜를 선택
	        + "    COALESCE(FREEPOST.FREEPOST_viewcnt, 'Unknown') AS post_viewcnt, " // 게시글 조회수를 선택
	        + "    COUNT(RECOMMEND.POST_id) AS post_recommendcnt " // 게시글의 좋아요 수를 합산
	        + "FROM "
	        + "    RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 자유글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    FREEPOST ON RECOMMEND.POST_id = FREEPOST.FREEPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    RECOMMEND.MEMBER_id = ? " 
	        + "GROUP BY "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
	        + "    post_id, " // 게시글의 아이디를 기준으로 결과를 그룹화
	        + "    post_title, " // 게시글의 제목을 기준으로 결과를 그룹화
	        + "    post_date, " // 게시글의 날짜를 기준으로 결과를 그룹화
	        + "    post_viewcnt " // 게시글의 조회수를 기준으로 결과를 그룹화
			+ "ORDER BY "	
			+ "		POST_id DESC ";
	
	// 특정 회원이 좋아요한 장터글 목록 출력 (장터글만 출력).전미지	
	private static final String SELECTALL_MARKETPOSTRECOMMEND = "SELECT "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 선택
	        + "    'MARKETPOST' AS post_category, " // 카테고리를 장터글로 지정
	        + "    COALESCE(MARKETPOST.MARKETPOST_id, 'Unknown') AS post_id, " // 게시글 아이디를 선택
	        + "    COALESCE(MARKETPOST.MARKETPOST_title, 'Unknown') AS post_title, " // 게시글 제목을 선택
	        + "    COALESCE(MARKETPOST.MARKETPOST_date, 'Unknown') AS post_date, " // 게시글 날짜를 선택
	        + "    COALESCE(MARKETPOST.MARKETPOST_viewcnt, 'Unknown') AS post_viewcnt, " // 게시글 조회수를 선택
	        + "    COUNT(RECOMMEND.POST_id) AS post_recommendcnt " // 게시글의 좋아요 수를 합산
	        + "FROM "
	        + "    RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 장터글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    MARKETPOST ON RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    RECOMMEND.MEMBER_id = ? " 
	        + "GROUP BY "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
	        + "    post_id, " // 게시글의 아이디를 기준으로 결과를 그룹화
	        + "    post_title, " // 게시글의 제목을 기준으로 결과를 그룹화
	        + "    post_date, " // 게시글의 날짜를 기준으로 결과를 그룹화
	        + "    post_viewcnt " // 게시글의 조회수를 기준으로 결과를 그룹화
			+ "ORDER BY "
			+ "		POST_id DESC ";
	
	// 특정 회원이 좋아요한 게시글 목록 출력 (카테고리별 출력).전미지  
	private static final String SELECTALL_RECOMMEND2 = "SELECT "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 선택
	        + "    CASE " // 게시글에 해당하는 카테고리 선택
	        + "        WHEN HEADHUNTPOST.HEADHUNTPOST_id IS NOT NULL THEN 'HEADHUNTPOST' " 
	        + "        WHEN JOBHUNTPOST.JOBHUNTPOST_id IS NOT NULL THEN 'JOBHUNTPOST' " 
	        + "        WHEN FREEPOST.FREEPOST_id IS NOT NULL THEN 'FREEPOST' " 
	        + "        WHEN MARKETPOST.MARKETPOST_id IS NOT NULL THEN 'MARKETPOST' " 
	        + "        ELSE 'Unknown' " // 어떤 카테고리에도 해당하지 않는 경우 'Unknown'으로 설정
	        + "    END AS post_category, " // 게시글의 카테고리를 선택
	        + "    COALESCE (HEADHUNTPOST.HEADHUNTPOST_id, JOBHUNTPOST.JOBHUNTPOST_id,"
	        + "					FREEPOST.FREEPOST_id, MARKETPOST.MARKETPOST_id, 'Unknown') AS post_id, " // 각 게시글의 아이디를 선택
	        + "    COALESCE (HEADHUNTPOST.HEADHUNTPOST_title, JOBHUNTPOST.JOBHUNTPOST_title,"
	        + "					FREEPOST.FREEPOST_title, MARKETPOST.MARKETPOST_title, 'Unknown') AS post_title, " // 각 게시글의 제목을 선택
	        + "    COALESCE (HEADHUNTPOST.HEADHUNTPOST_date, JOBHUNTPOST.JOBHUNTPOST_date,"
	        + "					FREEPOST.FREEPOST_date, MARKETPOST.MARKETPOST_date, 'Unknown') AS post_date, " // 각 게시글의 날짜를 선택
	        + "    COALESCE (HEADHUNTPOST.HEADHUNTPOST_viewcnt, JOBHUNTPOST.JOBHUNTPOST_viewcnt,"
	        + "					FREEPOST.FREEPOST_viewcnt, MARKETPOST.MARKETPOST_viewcnt, 'Unknown') AS post_viewcnt, " //각 게시글의 조회수를 선택
	        + "    COUNT (RECOMMEND.POST_id) AS post_recommendcnt " // 각 게시글의 좋아요 수를 합산
	        + "FROM " 
	        + "    RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 각 게시글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    HEADHUNTPOST ON RECOMMEND.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
	        + "LEFT JOIN " 
	        + "    JOBHUNTPOST ON RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " 
	        + "LEFT JOIN "
	        + "    FREEPOST ON RECOMMEND.POST_id = FREEPOST.FREEPOST_id " 
	        + "LEFT JOIN "
	        + "    MARKETPOST ON RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id " 
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    RECOMMEND.MEMBER_id = ? " 
	        + "GROUP BY "
	        + "    RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
	        + "    post_category, " // 게시글의 카테고리를 기준으로 결과를 그룹화
	        + "    post_id, " // 게시글의 아이디를 기준으로 결과를 그룹화
	        + "    post_title, " // 게시글의 제목을 기준으로 결과를 그룹화
	        + "    post_date, " // 게시글의 날짜를 기준으로 결과를 그룹화
	        + "    post_viewcnt " // 게시글의 조회수를 기준으로 결과를 그룹화
			+ "ORDER BY "
			+ "		POST_id DESC ";
	
	// 좋아요 중복검사 (좋아요를 눌렀는지 안눌렀는지 확인).전미지
	private static final String SELECTONE_RECOMMEND = "SELECT "
			+ "		POST_id, "
			+ "		MEMBER_id "
			+ "FROM "
			+ "		RECOMMEND "
			+ "WHERE "
			+ "		POST_id = ? AND MEMBER_id = ? ";
	// 사용한 테이블 : 좋아요 테이블
	// 사용한 컬럼 (출력 내용) :
	// 게시글 아이디, 회원 아이디
	
	// 좋아요 추가
	private static final String INSERT_RECOMMEND = "INSERT INTO RECOMMEND ( " 
			+ "POST_id, "
			+ "MEMBER_id) "
			+ "VALUES (?, ?) ";
	// 사용한 테이블 : 좋아요 테이블
	// 사용한 컬럼 (작성 내용) :
	// 게시글 아이디, 회원 아이디
	// 게시글 좋아요 아이디 테이블 생성시 AUTO_INCREMENT를 사용해 0번부터 자동 증감하게 설정
	
	// 사용 안 할 예정.전미지
	private static final String UPDATE = " ";
	
	// 좋아요 삭제.전미지
	private static final String DELETE_RECOMMEND = "DELETE "
			+ "FROM " 
			+ "		RECOMMEND "
			+ "WHERE "
			+ "		POST_id = ? AND MEMBER_id = ? ";
	
	
	// 좋아요한 게시글 전체 출력
	public List<RecommendDTO> selectAll(RecommendDTO recommendDTO) {
		List<RecommendDTO> result = null;
		System.out.println("RecommendDAO(selectAll) In로그 = [" + recommendDTO + "]");
		// 검색 조건에 해당될 경우 jdbcTemplate을 사용하여 SELECTALL 쿼리 실행 후 결과를 RowMapper로 매핑하여 반환
		try {
			// 특정 회원이 좋아요한 구인글 목록 출력
			if (recommendDTO.getSearchCondition().equals("headHuntPostListRecommend")) {
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_HEADHUNTPOSTRECOMMEND, new HeadHuntPostListRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 특정 회원이 좋아요한 구직글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("jobHuntPostListRecommend")) {
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_JOBHUNTPOSTRECOMMEND, new JobHuntPostListRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 특정 회원이 좋아요한 자유글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("freePostListRecommen")) {
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_FREEPOSTRECOMMEND, new FreePostListRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 특정 회원이 좋아요한 장터글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("marketPostListRecommend")) {
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTRECOMMEND, new MarketPostListRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 예외 발생 시 null 반환
		}
		System.out.println("RecommendDAO(selectAll) Error로그 = [" + recommendDTO.getSearchCondition() + "]");
		return null; // 좋아요 상세 출력 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	}
 
	// 좋아요 중복검사(좋아요를 눌렀는지 안눌렀는지 확인)
	public RecommendDTO selectOne(RecommendDTO recommendDTO) {
		RecommendDTO result = null;
		System.out.println("RecommendDAO(selectOne) In로그 = [" + recommendDTO + "]");
		// 검색 조건에 해당될 경우 jdbcTemplate을 사용하여 SELECTONE 쿼리 실행 후 결과를 RowMapper로 매핑하여 반환
		try {
			Object[] args = {recommendDTO.getPostId(), recommendDTO.getMemberId()};
			result = jdbcTemplate.queryForObject(SELECTONE_RECOMMEND, args, new SelectOneRecommendRowMapper());
			System.out.println("RecommendDAO(selectOne) Out로그 = [" + result + "]");
			return result;
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 예외 발생 시 null 반환
		}
	}

	// 게시글 좋아요 추가
	public boolean insert(RecommendDTO recommendDTO) {
		int result = 0;
		System.out.println("RecommendDAO(insert) In로그 = [" + recommendDTO + "]");
		// INSERT_RECOMMEND 쿼리를 실행하여 데이터베이스에서 게시글 좋아요 데이터를 삭제
		result = jdbcTemplate.update(INSERT_RECOMMEND);
		if (result <= 0) {
			System.out.println("RecommendDAO(insert) Out로그 = [" + result + "]");
			return false; // 좋아요 추가 실패 시 false 반환
		}
		return true; // 좋아요 추가 성공 시 true 반환
	}
	
	// 사용 안 할 예정
	public boolean update(RecommendDTO recommendDTO) {
		return false;
	}

	// 게시글 좋아요 삭제
	public boolean delete(RecommendDTO recommendDTO) {
		int result = 0;
		System.out.println("RecommendDAO(delete) In로그 = [" + recommendDTO + "]");
		// DELETE_RECOMMEND 쿼리를 실행하여 데이터베이스에서 게시글 좋아요 데이터를 삭제
		result = jdbcTemplate.update(DELETE_RECOMMEND);
		if (result <= 0) {
			System.out.println("RecommendDAO(delete) Out로그 = [" + result + "]");
			return false; // 좋아요 삭제 실패 시 false 반환
		}
		return true; // 좋아요 삭제 성공 시 true 반환
	}
}

// ===== SELECTALL =====

// 특정 회원이 좋아요한 구인글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class HeadHuntPostListRecommendRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostId(rs.getString("post_id"));			// 게시글 아이디	
		recommendDTO.setPostId(rs.getString("post_title"));			// 게시글 제목
		recommendDTO.setPostId(rs.getString("post_date"));			// 게시글 작성일
		recommendDTO.setPostId(rs.getString("post_viewcnt"));		// 게시글 조회수
		recommendDTO.setPostId(rs.getString("post_recommendcnt"));	// 게시글의 좋아요 수
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환		
	}
}

// 특정 회원이 좋아요한 구직글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class JobHuntPostListRecommendRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostId(rs.getString("post_id"));			// 게시글 아이디	
		recommendDTO.setPostId(rs.getString("post_title"));			// 게시글 제목
		recommendDTO.setPostId(rs.getString("post_date"));			// 게시글 작성일
		recommendDTO.setPostId(rs.getString("post_viewcnt"));		// 게시글 조회수
		recommendDTO.setPostId(rs.getString("post_recommendcnt"));	// 게시글의 좋아요 수
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// 특정 회원이 좋아요한 자유글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class FreePostListRecommendRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostId(rs.getString("post_id"));			// 게시글 아이디	
		recommendDTO.setPostId(rs.getString("post_title"));			// 게시글 제목
		recommendDTO.setPostId(rs.getString("post_date"));			// 게시글 작성일
		recommendDTO.setPostId(rs.getString("post_viewcnt"));		// 게시글 조회수
		recommendDTO.setPostId(rs.getString("post_recommendcnt"));	// 게시글의 좋아요 수
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// 특정 회원이 좋아요한 장터글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class MarketPostListRecommendRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostId(rs.getString("post_id"));			// 게시글 아이디	
		recommendDTO.setPostId(rs.getString("post_title"));			// 게시글 제목
		recommendDTO.setPostId(rs.getString("post_date"));			// 게시글 작성일
		recommendDTO.setPostId(rs.getString("post_viewcnt"));		// 게시글 조회수
		recommendDTO.setPostId(rs.getString("post_recommendcnt"));	// 게시글의 좋아요 수
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// ===== SELECTONE =====

// 좋아요 중복검사(좋아요를 눌렀는지 안눌렀는지 확인) 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneRecommendRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostId(rs.getString("POST_id"));		// 게시글 아이디	
		recommendDTO.setMemberId(rs.getString("MEMBER_Id")); 	// 회원 아이디	
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}