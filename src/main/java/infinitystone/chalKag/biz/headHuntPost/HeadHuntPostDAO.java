package infinitystone.chalKag.biz.headHuntPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("headHuntPostDAO")
public class HeadHuntPostDAO { // 구인 게시판 DAO

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  //구인글 전체 출력.전미지
  private static final String SELECTALL_HEADHUNTPOST = "SELECT " 
		  + "	'HeadHuntPost' AS POST_category, " // 게시판 카테고리 설정
		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
		  + "	HEADHUNTPOST.MEMBER_id, "
		  + "	MEMBER.MEMBER_nickname, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_title, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_content, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_date, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_viewcnt, "
		  + "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt " // 게시글의 좋아요 수 합산
		  + "FROM "
		  + "	HEADHUNTPOST "
		  + "INNER JOIN  "
		  + "	MEMBER ON HEADHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
		  + "LEFT JOIN "
		  + "	RECOMMEND ON HEADHUNTPOST.HEADHUNTPOST_id = RECOMMEND.POST_id "
		  + "GROUP BY "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
		  + "	MEMBER.MEMBER_nickname "
		  + "ORDER BY "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id DESC ";
  // 사용한 테이블 : 구인글 테이블, 회원 테이블, 좋아요 테이블
  // 사용한 컬럼 (출력 내용) :
  // 게시글 카테고리, 구인글 아이디, 회원 아이디, 회원 닉네임, 구인글 제목, 구인글 내용, 구인글 작성일, 구인글 조회수, 게시글의 좋아요 수
  // 게시글 이미지는 따로 받아옴

  // 특정 회원이 작성한 구인글 전체 출력.전미지
  private static final String SELECTALL_MEMBERHEADHUNTPOST = "SELECT " 
		  + "	'HeadHuntPost' AS POST_category, " // 게시판 카테고리 설정
		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
		  + "	HEADHUNTPOST.MEMBER_id, "
		  + "	MEMBER.MEMBER_nickname, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_title, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_content, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_date, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_viewcnt, "
		  + "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt " // 게시글의 좋아요 수 합산
		  + "FROM "
		  + "	HEADHUNTPOST "
		  + "INNER JOIN  "
		  + "	MEMBER ON HEADHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
		  + "LEFT JOIN "
		  + "	RECOMMEND ON HEADHUNTPOST.HEADHUNTPOST_id = RECOMMEND.POST_id "
		  + "WHERE "
		  + "	MEMBER.MEMBER_id = ? " 
		  + "GROUP BY "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
		  + "	MEMBER.MEMBER_nickname "
		  + "ORDER BY "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id DESC ";
  // 사용한 테이블 : 구인글 테이블, 회원 테이블, 좋아요 테이블
  // 사용한 컬럼 (출력 내용) :
  // 게시글 카테고리, 구인글 아이디, 회원 아이디, 회원 닉네임, 구인글 제목, 구인글 내용, 구인글 작성일, 구인글 조회수, 게시글의 좋아요 수
  // 게시글 이미지는 따로 받아옴
  
  // 프리미엄 회원이 작성한 구인글 출력 (최근에 작성된 글 2개만 불러오기).전미지
  private static final String SELECTALL_PREMIUMHEADHUNTPOST= "SELECT "
		  + "	'HeadHuntPost' AS POST_category, " // 게시판 카테고리 설정
		  + "    HEADHUNTPOST.HEADHUNTPOST_title, "
		  + "    HEADHUNTPOST.HEADHUNTPOST_date, "
		  + "    MEMBER.MEMBER_grade, "
		  + "    POSTIMG.POSTIMG_name "
		  + "FROM "
		  + "    HEADHUNTPOST "
		  + "LEFT JOIN "
		  + "    POSTIMG ON HEADHUNTPOST.HEADHUNTPOST_id = POSTIMG.POST_id "
		  + "INNER JOIN "
		  + "    MEMBER ON HEADHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
		  + "WHERE "
		  + "    MEMBER.MEMBER_grade = 'PREMIUM' "
		  + "ORDER BY "
		  + "    HEADHUNTPOST.HEADHUNTPOST_date DESC "
		  + "LIMIT 2 ";
  // 사용한 테이블 : 구인글 테이블, 회원 테이블
  // 사용한 컬럼 (출력 내용) :
  // 게시글 카테고리, 구인글 아이디, 회원 아이디, 회원 닉네임, 구인글 제목, 구인글 내용, 구인글 작성일, 구인글 조회수, 게시글의 좋아요 수, 몇 분 전 작성된 글인지
  // 게시글 이미지는 따로 받아옴

  
////구인글 전체 출력(게시판 카테고리와 몇 분 전 작성된 글인지도 출력).전미지
//private static final String SELECTALL_HEADHUNTPOST = "SELECT " 
//		  + "	'HeadHuntPost' AS POST_category, " // 게시판 카테고리 설정
//		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
//		  + "	HEADHUNTPOST.MEMBER_id, "
//		  + "	MEMBER.MEMBER_nickname, "
//		  + "	HEADHUNTPOST.HEADHUNTPOST_title, "
//		  + "	HEADHUNTPOST.HEADHUNTPOST_content, "
//		  + "	HEADHUNTPOST.HEADHUNTPOST_date, "
//		  + "	HEADHUNTPOST.HEADHUNTPOST_viewcnt, "
//		  + "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt, "
//		  + "	CASE " // 몇 분 전 작성된 글인지 체크
//		  + "		WHEN TIMESTAMPDIFF(MINUTE, HEADHUNTPOST.HEADHUNTPOST_date, NOW()) < 60 THEN CONCAT "
//		  + "			(TIMESTAMPDIFF(MINUTE, HEADHUNTPOST.HEADHUNTPOST_date, NOW()), ' 분 전') "
//		  + "		WHEN TIMESTAMPDIFF(HOUR, HEADHUNTPOST.HEADHUNTPOST_date, NOW()) < 24 THEN CONCAT "
//		  + "			(TIMESTAMPDIFF(HOUR, HEADHUNTPOST.HEADHUNTPOST_date, NOW()), ' 시간 전') "
//		  + "        ELSE CONCAT(TIMESTAMPDIFF(DAY, HEADHUNTPOST.HEADHUNTPOST_date, NOW()), ' 일 전') "
//		  + "	END AS HEADHUNTPOST_date " 
//		  + "FROM "
//		  + "    HEADHUNTPOST "
//		  + "INNER JOIN  "
//		  + "    MEMBER ON HEADHUNTPOST.MEMBER_id = MEMBER.MEMBER_id"
//		  + "LEFT JOIN "
//		  + "    RECOMMEND ON HEADHUNTPOST.HEADHUNTPOST_id = RECOMMEND.POST_id "
//		  + "GROUP BY "
//		  + "    HEADHUNTPOST.HEADHUNTPOST_id, "
//		  + "    MEMBER.MEMBER_nickname "
//		  + "ORDER BY "
//		  + "    HEADHUNTPOST.HEADHUNTPOST_id DESC ";
//// 사용한 테이블 : 구인글 테이블, 회원 테이블, 좋아요 테이블
//// 사용한 컬럼 (출력 내용) :
//// 게시글 카테고리, 구인글 아이디, 회원 아이디, 회원 닉네임, 구인글 제목, 구인글 내용, 구인글 작성일, 구인글 조회수, 게시글의 좋아요 수, 몇 분 전 작성된 글인지
//// 게시글 이미지는 따로 받아옴  

  
  // 구인글 상세 출력.전미지
  private static final String SELECTONE_HEADHUNTPOST = "SELECT "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
		  + "	HEADHUNTPOST.MEMBER_id, "
		  + "	MEMBER.MEMBER_nickname, "
		  + "	PROFILEIMG.PROFILEIMG_name, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_role, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_region, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_pay, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_workDate, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_concept, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_title, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_content, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_date, "
		  + "	HEADHUNTPOST.HEADHUNTPOST_viewcnt, "
		  + "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt " // 게시글의 좋아요 수 합산
		  + "FROM "
		  + "	HEADHUNTPOST "
		  + "INNER JOIN "
		  + "	MEMBER ON HEADHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
		  + "LEFT JOIN "
		  + "	PROFILEIMG ON HEADHUNTPOST.MEMBER_id = PROFILEIMG.MEMBER_id "
		  + "LEFT JOIN "
		  + "	RECOMMEND ON HEADHUNTPOST.HEADHUNTPOST_id = RECOMMEND.POST_id "
		  + "WHERE "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id = ? "
		  + "GROUP BY "
		  + "	HEADHUNTPOST.HEADHUNTPOST_id, "
		  + "	MEMBER.MEMBER_nickname, "
		  + "	PROFILEIMG.PROFILEIMG_name ";
  // 사용한 테이블 : 구인글 테이블, 회원 테이블, 프로필 이미지 테이블, 좋아요 테이블
  // 사용한 컬럼 (출력 내용) :
  // 구인글 아이디, 회원 아이디, 회원 닉네임 (회원 테이블), 프로필 이미지 네임 (프로필 이미지 테이블),
  // 구인글 직업 ( 모델 / 사진작가 ), 구인글 작업 지역, 구인글 작업 페이, 구인글 작업 날짜, 구인글 촬영 컨셉,
  // 구인글 제목, 구인글 내용, 구인글 작성일, 구인글 조회수, 게시글의 좋아요 수
  // INNER JOIN을 사용하여 HEADHUNTPOST 테이블과 MEMBER 테이블을 연결하고, 또 다른 LEFT JOIN을 사용하여 MEMBER 테이블과 PROFILEIMG 테이블을 연결

  // 구인글 작성.전미지
  private static final String INSERT_HEADHUNTPOST = "INSERT INTO HEADHUNTPOST ( "
		  + "MEMBER_id, "
		  + "HEADHUNTPOST_role, "
		  + "HEADHUNTPOST_region, "
		  + "HEADHUNTPOST_pay, "
		  + "HEADHUNTPOST_workDate, "
		  + "HEADHUNTPOST_concept, "
		  + "HEADHUNTPOST_title, "
		  + "HEADHUNTPOST_content, "
		  + "HEADHUNTPOST_viewcnt) "
		  + "VALUES(?,?,?,?,?,?,?,?,0) ";
  // 사용한 테이블 : 구인글 테이블, 회원 테이블
  // 사용한 컬럼 (작성 내용) :
  // 회원 아이디(회원 테이블), 구인글 작성 시간, 구인글 직업, 구인글 작업 지역, 구인글 작업 페이,
  // 구인글 작업 날짜, 구인글 촬영 컨셉, 구인글 제목, 구인글 내용, 구인글 조회수
  // 구인글 아이디는 테이블 생성시 AUTO_INCREMENT를 사용해 2001번부터 자동 증감하게 설정

  // 구인글 수정.전미지
  private static final String UPDATE_HEADHUNTPOST = "UPDATE HEADHUNTPOST "
		  + "SET "
		  + "	HEADHUNTPOST_role = ?, "
		  + "	HEADHUNTPOST_region = ?, "
		  + "	HEADHUNTPOST_pay = ?, "
		  + "	HEADHUNTPOST_workDate = ?, "
		  + "	HEADHUNTPOST_concept = ?, "
		  + "	HEADHUNTPOST_title = ?, "
		  + "	HEADHUNTPOST_content = ? "
		  + "WHERE "
		  + "	HEADHUNTPOST_id = ? ";
  // 사용한 테이블 : 구인글 테이블
  // 사용한 컬럼 (수정 내용) :
  // 구인글 직업, 구인글 작업 지역, 구인글 작업 페이, 구인글 작업 날짜, 구인글 촬영 컨셉, 구인글 제목, 구인글 내용

  // 구인글 조회수 증가.전미지
  private static final String UPDATE_VIEWCNT = "UPDATE HEADHUNTPOST "
		  + "SET "
		  + "	HEADHUNTPOST_viewcnt = (HEADHUNTPOST_viewcnt+1) "
		  + "WHERE "
		  + "	HEADHUNTPOST_id = ? ";
  // 사용한 테이블 : 구인글 테이블
  // 사용한 컬럼 (조회수 업데이트) :
  // 구인글 조회수

  // 구인글 삭제.전미지
  private static final String DELETE_HEADHUNTPOST = "DELETE "
		  + "FROM "
		  + "      HEADHUNTPOST "
		  + "WHERE "
		  + "      HEADHUNTPOST_id = ? ";

  
	// 구인글 목록 출력
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) {
		List<HeadHuntPostDTO> result = null;
		// 검색 조건에 해당될 경우 jdbcTemplate을 사용하여 SELECTALL 쿼리 실행 후 결과를 RowMapper로 매핑하여 반환
		System.out.println("HeadHuntPostDAO(selectAll) In로그 = [" + headHuntPostDTO + "]");
		try {
			// 구인글 전체 출력
			if (headHuntPostDTO.getSearchCondition().equals("headHuntPostList")) {
				result = jdbcTemplate.query(SELECTALL_HEADHUNTPOST, new HeadHuntPostListRowMapper());
				System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 특정 회원이 작성한 구인글 전체 출력
			else if (headHuntPostDTO.getSearchCondition().equals("HeadHuntPostMembertList")) {
				result = jdbcTemplate.query(SELECTALL_MEMBERHEADHUNTPOST, new HeadHuntPostMemberListRowMapper());
				System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 프리미엄 회원 게시글 목록 출력
			else if (headHuntPostDTO.getSearchCondition().equals("HeadHuntPostPremiumList")) {
				result = jdbcTemplate.query(SELECTALL_PREMIUMHEADHUNTPOST, new HeadHuntPostPremiumListRowMapper());
				System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
//			// 최신 게시글 목록 출력
//			else if (headHuntPostDTO.getSearchCondition().equals("HeadHuntPostLatestList")) {
//				result = jdbcTemplate.query(SELECTALL_LATESTHEADHUNTPOST, new HeadHuntPostLatestListRowMapper());
//				System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
//				return result;
//			}
//			// 추천 게시글 목록 출력
//			else if (headHuntPostDTO.getSearchCondition().equals("HeadHuntPostHotList")) {
//				result = jdbcTemplate.query(SELECTALL_HOTHEADHUNTPOST, new HeadHuntPostHotListRowMapper());
//				System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
//				return result;
//			}
//			// 주간 추천 게시글 목록 출력
//			else if (headHuntPostDTO.getSearchCondition().equals("HeadHuntPostBestOfTheWeekList")) {
//				result = jdbcTemplate.query(SELECTALL_BESTOFTHEWEEKHEADHUNTPOST,
//						new HeadHuntPostBestOfTheWeekListkRowMapper());
//				System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
//				return result;
//			}
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 예외 발생 시 null 반환
		}
		System.out.println("HeadHuntPostDAO(selectAll) Error로그 = [" + headHuntPostDTO.getSearchCondition() + "]");
		return null; // 글 상세 출력 검색 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	}

	// 구인글 상세 출력
	public HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO) {
		HeadHuntPostDTO result = null;
		System.out.println("HeadHuntPostDAO(selectOne) In로그 = [" + headHuntPostDTO + "]");
		try {
			Object[] args = { headHuntPostDTO.getHeadHuntPostId() };
			// SELECTONE_HEADHUNTPOST 쿼리를 실행해 데이터베이스에 구인글 데이터를 불러옴
			result = jdbcTemplate.queryForObject(SELECTONE_HEADHUNTPOST, args, new SelectOneHeadHuntPostRowMapper());
			System.out.println("HeadHuntPostDAO(selectOne) Out로그 = [" + result + "]");
			return result;
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 예외 발생 시 null 반환
		}
	}

	// 구인글 작성
	public boolean insert(HeadHuntPostDTO headHuntPostDTO) {
		int result = 0;
		System.out.println("HeadHuntPostDAO(insert) In로그 = [" + headHuntPostDTO + "]");
		// INSERT_HEADHUNTPOST 쿼리를 실행해 데이터베이스에 구인글 데이터를 저장
		result = jdbcTemplate.update(INSERT_HEADHUNTPOST, headHuntPostDTO.getMemberId(),
				headHuntPostDTO.getHeadHuntPostRole(), headHuntPostDTO.getHeadHuntPostRegion(),
				headHuntPostDTO.getHeadHuntPostPay(), headHuntPostDTO.getHeadHuntPostWorkDate(),
				headHuntPostDTO.getHeadHuntPostConcept(), headHuntPostDTO.getHeadHuntPostTitle(),
				headHuntPostDTO.getHeadHuntPostContent());
		if (result <= 0) {
			System.out.println("HeadHuntPostDAO(insert) Out로그 = [" + result + "]");
			return false; // 글 작성 실패 시 false 반환
		}
		return true; // 글 작성 성공 시 true 반환
	}

	// 구인글 조회수 증가 및 구인글 수정
	public boolean update(HeadHuntPostDTO headHuntPostDTO) {
		int result = 0;
		System.out.println("HeadHuntPostDAO(update) In로그 = [" + headHuntPostDTO + "]");
		// 구인글 조회수 증가
		if (headHuntPostDTO.getSearchCondition().equals("headHuntPostViewcntUpdate")) {
			// UPDATE_VIEWCNT 쿼리를 실행해 데이터베이스에서 구인글 조회수를 증가
			result = jdbcTemplate.update(UPDATE_VIEWCNT, headHuntPostDTO.getHeadHuntPostId());
			if (result <= 0) {
				System.out.println("HeadHuntPostDAO(update) Out로그 = [" + result + "]");
				return false; // 구인글 조회수 증가 실패 시 false 반환
			}
			return true; // 조회수 증가 성공 시 true 반환
		}
		// 구인글 수정
		else if (headHuntPostDTO.getSearchCondition().equals("headHuntPostUpdate")) {
			// UPDATE_HEADHUNTPOST 쿼리를 실행해 데이터베이스에서 구인글 정보를 수정
			result = jdbcTemplate.update(UPDATE_HEADHUNTPOST, headHuntPostDTO.getHeadHuntPostRole(),
					headHuntPostDTO.getHeadHuntPostRegion(), headHuntPostDTO.getHeadHuntPostPay(),
					headHuntPostDTO.getHeadHuntPostWorkDate(), headHuntPostDTO.getHeadHuntPostConcept(),
					headHuntPostDTO.getHeadHuntPostTitle(), headHuntPostDTO.getHeadHuntPostContent());
			if (result <= 0) {
				System.out.println("HeadHuntPostDAO(update) Out로그 = [" + result + "]");
				return false; // 구인글 수정 실패 시 false 반환
			}
			return true; // 구인글 수정 성공 시 true 반환
		}
		System.out.println("HeadHuntPostDAO(update) Error 로그 = [" + headHuntPostDTO.getSearchCondition() + "]");
		return false; // 업데이트 조건에 해당되지 않는다면 false 반환
	}

	// 구인글 삭제
	public boolean delete(HeadHuntPostDTO headHuntPostDTO) {
		int result = 0;
		System.out.println("HeadHuntPostDAO(delete) In로그 = [" + headHuntPostDTO + "]");
		// DELETE_HEADHUNTPOST 쿼리를 실행해 데이터베이스에서 구인글 데이터를 삭제
		result = jdbcTemplate.update(DELETE_HEADHUNTPOST, headHuntPostDTO.getHeadHuntPostId());
		if (result <= 0) {
			System.out.println("HeadHuntPostDAO(delete) Out로그 = [" + result + "]");
			return false; // 구인글 삭제 실패 시 false 반환
		}
		return true; // 구인글 삭제 성공 시 true 반환
	}

}

// ===== SELECTALL =====

// 구인글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class HeadHuntPostListRowMapper implements RowMapper<HeadHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드

		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
//      headHuntPostDTO.setPostCategory(rs.getString("POST_category"));            	// 게시글 카테고리
		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
		headHuntPostDTO.setHeadHuntPostDate(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
	}
}

//구인글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class HeadHuntPostMemberListRowMapper implements RowMapper<HeadHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드

		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
//   headHuntPostDTO.setPostCategory(rs.getString("POST_category"));            	// 게시글 카테고리
		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
		headHuntPostDTO.setHeadHuntPostDate(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
	}
}

//// 구인글 최신 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
//class HeadHuntPostLatestListRowMapper implements RowMapper<HeadHuntPostDTO> {
//	@Override // mapRow 메서드 오버라이드
//	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드
//
//		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
//		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
//		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
//		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
//		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
//		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
//		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
//		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
//		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
//		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
//		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
//	}
//}

// 프리미엄 회원이 작성한 구인글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class HeadHuntPostPremiumListRowMapper implements RowMapper<HeadHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드

		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
		headHuntPostDTO.setHeadHuntPostDate(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
	}
}

//// 구인글 추천글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
//class HeadHuntPostHotListRowMapper implements RowMapper<HeadHuntPostDTO> {
//	@Override // mapRow 메서드 오버라이드
//	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드
//
//		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
//		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
//		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
//		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
//		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
//		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
//		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
//		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
//		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
//		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
//		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
//	}
//}
//
//// 구인글 주간 추천글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
//class HeadHuntPostBestOfTheWeekListkRowMapper implements RowMapper<HeadHuntPostDTO> {
//	@Override // mapRow 메서드 오버라이드
//	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드
//
//		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
//		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
//		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
//		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
//		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
//		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
//		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
//		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
//		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
//		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
//		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
//	}
//}

// ===== SELECTONE =====

// 구인글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneHeadHuntPostRowMapper implements RowMapper<HeadHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드

		HeadHuntPostDTO headHuntPostDTO = new HeadHuntPostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
		headHuntPostDTO.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id")); // 구인글 아이디
		headHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); // 회원 아이디
		headHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); // 회원 닉네임
		headHuntPostDTO.setProfileImgName(rs.getString("PROFILEIMG_name")); // 회원 프로필
		headHuntPostDTO.setHeadHuntPostRole(rs.getString("HEADHUNTPOST_role")); // 구인글 직업 ( 모델 / 사진작가 )
		headHuntPostDTO.setHeadHuntPostRegion(rs.getString("HEADHUNTPOST_region")); // 구인글 작업 지역
		headHuntPostDTO.setHeadHuntPostPay(rs.getInt("HEADHUNTPOST_pay")); // 구인글 작업 페이
		headHuntPostDTO.setHeadHuntPostWorkDate(rs.getString("HEADHUNTPOST_workDate")); // 구인글 작업 날짜
		headHuntPostDTO.setHeadHuntPostConcept(rs.getString("HEADHUNTPOST_concept")); // 구인글 촬영 컨셉
		headHuntPostDTO.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title")); // 구인글 제목
		headHuntPostDTO.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content")); // 구인글 내용
		headHuntPostDTO.setHeadHuntPostDate(rs.getString("HEADHUNTPOST_date")); // 구인글 작성일
		headHuntPostDTO.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); // 구인글 조회수
		headHuntPostDTO.setRecommendCnt(rs.getString("RECOMMEND_cnt")); // 게시글의 좋아요 수
		return headHuntPostDTO; // headHuntPostDTO에 저장된 데이터들을 반환
	}
}