package infinitystone.chalKag.biz.jobHuntPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("jobHuntPostDAO")
public class JobHuntPostDAO { // 구직 게시판 DAO

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// ----------------------------------------------------------------- 메인 페이지 SELECTALL -----------------------------------------------------------------

	// 프리미엄 회원이 작성한 구직글 출력 (최신글 2개만 출력).전미지
	private static final String SELECTALL_JOBHUNTPOSTPREMIUM = "SELECT "
			+ "DISTINCT " // 중복 제거 함수
			+ "		'JobHuntPost' AS POST_category, " // 게시판 카테고리 설정
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, " 
			+ "		JOBHUNTPOST.MEMBER_id, " 
			+ "		MEMBER.MEMBER_nickname, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_title, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_date, " 
			+ "		( " // 대표 이미지 설정
			+ "			SELECT " 
			+ "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			+ "			FROM " 
			+ "				POSTIMG " // 게시글 이미지 테이블
			+ "			WHERE " 
			+ "				POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			+ "			ORDER BY " 
			+ "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			+ "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			+ "		) AS POSTIMG_name " // 대표 이미지의 이름
			+ "FROM " 
			+ "		JOBHUNTPOST " // 구직글 테이블
			+ "INNER JOIN " 
			+ "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			+ "LEFT JOIN " 
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			+ "WHERE " 
			+ "		MEMBER.MEMBER_grade = 'PREMIUM' " 
			+ "ORDER BY "
			+ "		JOBHUNTPOST.JOBHUNTPOST_date DESC " // 작성일을 기준으로 내림차순 정렬
			+ "LIMIT 2 "; // 글을 2개만 가져오도록 설정
	// 사용한 테이블 : 구직글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	// 사용한 컬럼 : 카테고리, 구직글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 작성일, 대표 이미지(게시글 이미지 테이블)
	// 쿼리문 설명 :
	// INNER JOIN을 사용해 구직글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 구직글 테이블과 좋아요 테이블을 연결
	// 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

	// 주간 추천순 구직글 목록 출력 (2개만 출력).전미지
	private static final String SELECTALL_JOBHUNTPOSTWEEKLYBEST = "SELECT " 
			+ "DISTINCT " // 중복 제거 함수
			+ "		'JobHuntPost' AS POST_category, " // 게시판 카테고리 설정
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, " 
			+ "		JOBHUNTPOST.MEMBER_id, " 
			+ "		MEMBER.MEMBER_nickname, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_title, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_date, " 
			+ "		( " // 특정 구직글에 대해 로그인한 회원이 좋아요를 눌렀는지 여부를 확인
			+ " 		SELECT " 
			+ "				CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " // 좋아요를 눌렀으면 TRUE, 아니면 FALSE를 반환
			+ "			FROM "
			+ "				RECOMMEND " // 좋아요 테이블
			+ "			WHERE "
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 구직글에 좋아요가 눌렸는지 확인 후
			+ "				AND RECOMMEND.MEMBER_id = ? " // 현재 로그인한 회원의 아이디와 일치하는 좋아요만 선택
			+ "		) AS myRecommend, " // 로그인한 회원이 좋아요를 눌렀는지 중복 체크
			+ "		( " // 게시글의 좋아요 수를 합산
			+ "			SELECT " 
			+ "				COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			+ "			FROM " 
			+ "				RECOMMEND " // 좋아요 테이블에서 가져옴
			+ "			WHERE " 
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			+ "		) AS RECOMMEND_cnt, " // 좋아요 수
			+ "		( " // 대표 이미지 설정
			+ "			SELECT " 
			+ "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			+ "			FROM " 
			+ "				POSTIMG " // 게시글 이미지 테이블
			+ "			WHERE " 
			+ "				POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			+ "			ORDER BY "																			
			+ "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			+ "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			+ "		) AS POSTIMG_name " // 대표 이미지의 이름
			+ "FROM " 
			+ "		JOBHUNTPOST " // 구직글 테이블
			+ "INNER JOIN  " 
			+ "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			+ "LEFT JOIN " 
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			+ "WHERE " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_date >= DATE_SUB(NOW(), INTERVAL 1 WEEK) " // 최근 1주일간 작성된 글만 선택
			+ "ORDER BY " 
			+ "		RECOMMEND_cnt DESC, " // (1) 좋아요 수가 많은 순으로 1차 정렬 후
			+ "		JOBHUNTPOST.JOBHUNTPOST_date DESC " // (2) 최근 작성일 순으로 2차 정렬
			+ "LIMIT 2 ";
	// 사용한 테이블 : 구직글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	// 사용한 컬럼 : 카테고리, 구직글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 중복 체크(좋아요 테이블), 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	// 쿼리문 설명 :
	// INNER JOIN을 사용해 구직글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 구직글 테이블과 좋아요 테이블을 연결
	// 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	// 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

// ----------------------------------------------------------------- 구직글 페이지 SELECTALL -----------------------------------------------------------------

	// 구직글 목록 출력.전미지
	private static final String SELECTALL_JOBHUNTPOST = "SELECT " 
			+ "DISTINCT " // 중복 제거 함수
			+ "		'JobHuntPost' AS POST_category, " // 게시판 카테고리 설정
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, " 
			+ "		JOBHUNTPOST.MEMBER_id, " 
			+ "		MEMBER.MEMBER_nickname, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_title, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_date, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_viewcnt, " 
			+ "		( " // 특정 구직글에 대해 로그인한 회원이 좋아요를 눌렀는지 여부를 확인
			+ " 		SELECT " 
			+ "				CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " // 좋아요를 눌렀으면 TRUE, 아니면 FALSE를 반환
			+ "			FROM "
			+ "				RECOMMEND " // 좋아요 테이블
			+ "			WHERE "
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 구직글에 좋아요가 눌렸는지 확인 후
			+ "				AND RECOMMEND.MEMBER_id = ? " // 현재 로그인한 회원의 아이디와 일치하는 좋아요만 선택
			+ "		) AS myRecommend, " // 로그인한 회원이 좋아요를 눌렀는지 중복 체크
			+ "		( " // 게시글의 좋아요 수를 합산
			+ "			SELECT " 
			+ " 			COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			+ "			FROM " 
			+ "				RECOMMEND " // 좋아요 테이블에서 가져옴
			+ "			WHERE " 
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			+ "		) AS RECOMMEND_cnt, " // 좋아요 수
			+ "		( " // 대표 이미지 설정
			+ "			SELECT " 
			+ "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			+ "			FROM " 
			+ "				POSTIMG " // 게시글 이미지 테이블
			+ "			WHERE " 
			+ "				POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			+ "			ORDER BY " 
			+ "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			+ "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			+ "		) AS POSTIMG_name " // 대표 이미지의 이름
			+ "FROM " 
			+ "		JOBHUNTPOST " // 구직글 테이블
			+ "INNER JOIN " 
			+ "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			+ "LEFT JOIN " 
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			+ "ORDER BY " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_id DESC "; // 게시글 아이디를 기준으로 내림차순 정렬
	// 사용한 테이블 : 구직글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	// 사용한 컬럼 : 카테고리, 구직글 아이디, 회원 아아디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 조회수, 좋아요 중복 체크(좋아요 테이블), 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	// 쿼리문 설명 :
	// INNER JOIN을 사용해 구직글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 구직글 테이블과 좋아요 테이블을 연결
	// 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 RECOMMEND_cnt"라는 별칭으로 반환
	// 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

// ----------------------------------------------------------------- 회원 페이지 SELECTALL -----------------------------------------------------------------

	// 특정 회원이 작성한 구직글 목록 출력.전미지
	private static final String SELECTALL_JOBHUNTPOSTMEMBER = "SELECT " 
			+ "DISTINCT " // 중복 제거 함수
			+ "		'JobHuntPost' AS POST_category, " // 게시판 카테고리 설정
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, " 
			+ "		MEMBER.MEMBER_nickname, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_title, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_content, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_date, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_viewcnt, " 
			+ "		( " // 특정 구직글에 대해 로그인한 회원이 좋아요를 눌렀는지 여부를 확인
			+ " 		SELECT " 
			+ "				CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " // 좋아요를 눌렀으면 TRUE, 아니면 FALSE를 반환
			+ "			FROM "
			+ "				RECOMMEND " // 좋아요 테이블
			+ "			WHERE "
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 구직글에 좋아요가 눌렸는지 확인 후
			+ "				AND RECOMMEND.MEMBER_id = ? " // 현재 로그인한 회원의 아이디와 일치하는 좋아요만 선택
			+ "		) AS myRecommend, " // 로그인한 회원이 좋아요를 눌렀는지 중복 체크
			+ "		( " // 게시글의 좋아요 수를 합산
			+ "			SELECT " 
			+ " 			COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			+ "			FROM " 
			+ "				RECOMMEND " // 좋아요 테이블에서 가져옴
			+ "			WHERE " 
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			+ "		) AS RECOMMEND_cnt, " // 좋아요 수
			+ "		( " // 대표 이미지 설정
			+ "			SELECT " 
			+ "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			+ "			FROM " 
			+ "				POSTIMG " // 게시글 이미지 테이블
			+ "			WHERE " 
			+ "				POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			+ "			ORDER BY " 
			+ "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			+ "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			+ "		) AS POSTIMG_name " // 대표 이미지의 이름
			+ "FROM "
			+ "		JOBHUNTPOST " // 구직글 테이블
			+ "INNER JOIN " 
			+ "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			+ "LEFT JOIN " 
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			+ "WHERE " 
			+ "		MEMBER.MEMBER_id = ? " // 조회할 회원의 아이디
			+ "ORDER BY " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_id DESC "; // 게시글 아이디를 기준으로 내림차순 정렬
	// 사용한 테이블 : 구직글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	// 사용한 컬럼 : 카테고리, 구직글 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 조회수, 좋아요 중복 체크(좋아요 테이블), 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	// 쿼리문 설명 :
	// INNER JOIN을 사용해 구직글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 구직글 테이블과 좋아요 테이블을  연결
	// 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	// 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

// ---------------------------------------------------------------------- SELECTONE ----------------------------------------------------------------------

	// 게시글 아이디 최대값 가져오는 쿼리문
	private static final String SELECTONE_MAXPOSTID = "SELECT MAX(JOBHUNTPOST_id) FROM JOBHUNTPOST";

	// 메인 페이지 - 최신 게시글 1개 출력.전미지
	private static final String SELECTONE_JOBHUNTPOSTRECENT = "SELECT " 
			+ "DISTINCT " // 중복 제거 함수
			+ "		'JobHuntPost' AS POST_category, " // 게시판 카테고리 설정
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, " 
			+ "		JOBHUNTPOST.MEMBER_id, " 
			+ "		MEMBER.MEMBER_nickname, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_title, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_date, " 
			+ "		( " // 특정 구직글에 대해 로그인한 회원이 좋아요를 눌렀는지 여부를 확인
			+ " 		SELECT " 
			+ "				CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " // 좋아요를 눌렀으면 TRUE, 아니면 FALSE를 반환
			+ "			FROM "
			+ "				RECOMMEND " // 좋아요 테이블
			+ "			WHERE "
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 구직글에 좋아요가 눌렸는지 확인 후
			+ "				AND RECOMMEND.MEMBER_id = ? " // 현재 로그인한 회원의 아이디와 일치하는 좋아요만 선택
			+ "		) AS myRecommend, " // 로그인한 회원이 좋아요를 눌렀는지 중복 체크
			+ "		( " // 게시글의 좋아요 수를 합산
			+ "			SELECT " 
			+ " 			COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			+ "			FROM " 
			+ "				RECOMMEND " // 좋아요 테이블에서 가져옴
			+ "			WHERE " 
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			+ "		) AS RECOMMEND_cnt, " // 좋아요 수
			+ "		( " // 대표 이미지 설정
			+ "			SELECT " 
			+ "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			+ "			FROM " 
			+ "				POSTIMG " // 게시글 이미지 테이블
			+ "			WHERE " 
			+ "				POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			+ "			ORDER BY " 
			+ "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			+ "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			+ "		) AS POSTIMG_name " // 대표 이미지의 이름
			+ "FROM " 
			+ "		JOBHUNTPOST " // 구직글 테이블
			+ "INNER JOIN  " 
			+ "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			+ "LEFT JOIN " 
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			+ "ORDER BY " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_date DESC " // 작성일을 기준으로 내림차순 정렬
			+ "LIMIT 1 "; // 글을 1개만 가져오도록 설정
	// 사용한 테이블 : 구직글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	// 사용한 컬럼 : 카테고리, 게시글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 중복 체크(좋아요 테이블), 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	// 쿼리문 설명 :
	// INNER JOIN을 사용해 구직글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 구직글 테이블과 좋아요 테이블을 연결
	// 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	// 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

	// 구직글 페이지 - 구직글 상세 출력.전미지
	private static final String SELECTONE_JOBHUNTPOST = "SELECT " 
			+ "DISTINCT " // 중복 제거 함수
			+ "		'JobHuntPost' AS POST_category, " // 게시판의 카테고리 설정 "
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, " 
			+ "		JOBHUNTPOST.MEMBER_id, " 
			+ "		MEMBER.MEMBER_nickname, "
			+ "		MEMBER.MEMBER_introduction, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_title, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_content, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_role, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_region, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_pay, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_workDate, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_concept, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_date, " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_viewcnt, " 
			+ "		( " // 회원 프로필로 보여줄 이미지 설정
			+ "			SELECT " 
			+ "				PROFILEIMG.PROFILEIMG_name " // 프로필 이미지를 선택
			+ "			FROM  " 
			+ "				PROFILEIMG " // 프로필 이미지 테이블
			+ "			WHERE  " 
			+ "				PROFILEIMG.MEMBER_id = JOBHUNTPOST.MEMBER_id " // 회원 아이디와 프로필 이미지 테이블의 회원  아이디가 동일한 것을 선택
			+ "			ORDER BY  "
			+ "				PROFILEIMG.PROFILEIMG_id DESC " // 프로필 이미지 아이디를 기준으로 내림차순 정렬
			+ "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			+ "		) AS PROFILEIMG_name, " // 프로필 이미지의 이름
			+ "		( " // 게시글의 좋아요 수를 합산
			+ "			SELECT " 
			+ "				COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			+ "			FROM " 
			+ "				RECOMMEND " // 좋아요 테이블에서 가져옴
			+ "			WHERE " 
			+ "				RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			+ "		) AS RECOMMEND_cnt " // 좋아요 수
			+ "FROM " 
			+ "		JOBHUNTPOST " // 구직글 테이블
			+ "INNER JOIN  "
			+ "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			+ "LEFT JOIN  " 
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			+ "WHERE  " 
			+ "		JOBHUNTPOST.JOBHUNTPOST_id = ? ";
	// 사용한 테이블 : 구직글 테이블, 회원 테이블, 좋아요 테이블, 프로필 이미지 테이블
	// 사용한 컬럼 (출력 내용) : 카테고리, 구직글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 회원 자기소개(회원 테이블),
	// 제목, 내용, 직업, 작업 지역, 작업 페이, 작업 날짜, 촬영 컨셉, 작성일, 조회수, 좋아요 수(좋아요 테이블), 프로필 이미지(프로필 이미지 테이블)
	// 쿼리문 설명 :
	// INNER JOIN을 사용해 구직글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 구직글 테이블과 좋아요 테이블을 연결
	// 프로필 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 프로필 이미지 중 회원 프로필로 보여줄 이미지를 설정하고, 그 결과를 "PROFILEIMG_name"라는 별칭으로 반환
	// 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환

// --------------------------------------------------------------- INSERT, UPDATE, DELETE ---------------------------------------------------------------

	// 구직글 작성.전미지
	private static final String INSERT_JOBHUNTPOST = "INSERT INTO JOBHUNTPOST ( " 
			+ "MEMBER_id, "
			+ "JOBHUNTPOST_title, " 
			+ "JOBHUNTPOST_content, " 
			+ "JOBHUNTPOST_role, " 
			+ "JOBHUNTPOST_region, "
			+ "JOBHUNTPOST_pay, " 
			+ "JOBHUNTPOST_workDate, " 
			+ "JOBHUNTPOST_concept, " 
			+ "JOBHUNTPOST_viewcnt "
			+ ")VALUES(?,?,?,?,?,?,?,?,0) ";
	// 사용한 테이블 : 구직글 테이블
	// 사용한 컬럼 (작성 내용) : 회원 아이디(회원 테이블), 제목, 내용, 작성 시간, 직업, 작업 지역, 작업 페이, 작업 날짜, 촬영 컨셉, 조회수
	// 구직글 아이디는 테이블 생성시 AUTO_INCREMENT를 사용해 2001번부터 자동 증감하게 설정

	// 구직글 조회수 증가.전미지
	private static final String UPDATE_VIEWCNT = "UPDATE JOBHUNTPOST " 
			+ "SET "
			+ "		JOBHUNTPOST_viewcnt = (JOBHUNTPOST_viewcnt+1) " 
			+ "WHERE " 
			+ "		JOBHUNTPOST_id = ? ";
	// 사용한 테이블 : 구직글 테이블
	// 사용한 컬럼 (조회수 업데이트) : 구직글 조회수

	// 구직글 수정.전미지
	private static final String UPDATE_JOBHUNTPOST = "UPDATE JOBHUNTPOST " 
			+ "SET " 
			+ "		JOBHUNTPOST_title = ?, "
			+ "		JOBHUNTPOST_content = ?, " 
			+ "		JOBHUNTPOST_role = ?, " 
			+ "		JOBHUNTPOST_region = ?, "
			+ "		JOBHUNTPOST_pay = ?, " 
			+ "		JOBHUNTPOST_workDate = ?, " 
			+ "		JOBHUNTPOST_concept = ? " 
			+ "WHERE "
			+ "		JOBHUNTPOST_id = ? ";
	// 사용한 테이블 : 구직글 테이블
	// 사용한 컬럼 (수정 내용) : 제목, 내용, 직업, 작업 지역, 작업 페이, 작업 날짜, 촬영 컨셉

	// 구직글 삭제.전미지
	private static final String DELETE_JOBHUNTPOST = "DELETE " 
			+ "FROM " 
			+ "      JOBHUNTPOST " 
			+ "WHERE "
			+ "      JOBHUNTPOST_id = ? ";

	// 구직글 목록 출력
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		List<JobHuntPostDTO> result = null;
		// 검색 조건에 해당될 경우 jdbcTemplate을 사용하여 SELECTALL 쿼리 실행 후 결과를 RowMapper로 매핑하여 반환
		System.out.println("JobHuntPostDAO(selectAll) In로그 = [" + jobHuntPostDTO + "]");
		try {
			// 메인 페이지 - 프리미엄 회원 게시글 목록 출력
			if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostPremiumList")) {
				result = jdbcTemplate.query(SELECTALL_JOBHUNTPOSTPREMIUM, new JobHuntPostPremiumRowMapper());
				System.out.println("JobHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 메인 페이지 - 주간 추천순 게시글 목록 출력
			else if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostWeeklyBestList")) {
				Object[] args = { jobHuntPostDTO.getMemberId() };
				result = jdbcTemplate.query(SELECTALL_JOBHUNTPOSTWEEKLYBEST, args, new JobHuntPostWeeklyBestRowMapper());
				System.out.println("JobHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 구직글 페이지 - 구직글 전체 출력
			else if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostList")) {
				Object[] args = { jobHuntPostDTO.getMemberId() };
				result = jdbcTemplate.query(SELECTALL_JOBHUNTPOST, args, new JobHuntPostRowMapper());
				System.out.println("JobHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 회원 페이지 - 특정 회원이 작성한 구직글 전체 출력
			else if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostMemberList")) {
				Object[] args = { jobHuntPostDTO.getMemberId(), jobHuntPostDTO.getMemberId() };
				result = jdbcTemplate.query(SELECTALL_JOBHUNTPOSTMEMBER, args, new JobHuntPostMemberRowMapper());
				System.out.println("JobHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 예외 발생 시 null 반환
		}
		System.out.println("JobHuntPostDAO(selectAll) Error로그 = [" + jobHuntPostDTO.getSearchCondition() + "]");
		return null; // 글 목록 출력 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	}

	// 구직글 상세 출력
	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO) {
		JobHuntPostDTO result = null;
		System.out.println("JobHuntPostDAO(selectOne) In로그 = [" + jobHuntPostDTO + "]");
		try {
			// 게시글 이미지 저장 시 필요한 게시글 아이디의 최대값 가져오기
			if (jobHuntPostDTO.getSearchCondition().equals("maxPostId")) { // 게시글 작성 시 이미지에 들어갈 PostId 추가
				Object[] args = { jobHuntPostDTO.getJobHuntPostId() };
				result = jdbcTemplate.queryForObject(SELECTONE_MAXPOSTID, new SelectOneMaxPostIdRowMapper());
				System.out.println("JobHuntPostDAO(selectOne) Out로그 = [" + result + "]");
				return result;
			}
			// 최신 게시글 출력
			else if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostRecentPostSingle")) {
				Object[] args = { jobHuntPostDTO.getMemberId() };
				// SELECTONE_JOBHUNTPOSTRECNT 쿼리를 실행해 데이터베이스에 구직글 데이터를 불러옴
				result = jdbcTemplate.queryForObject(SELECTONE_JOBHUNTPOSTRECENT, args, new SelectOneJobHuntPostRecentRowMapper());
				System.out.println("JobHuntPostDAO(selectOne) Out로그 = [" + result + "]");
				return result;
			}
			// 게시글 상세 보기
			else if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostSingle")) {
				// 조회수 증가
				jobHuntPostDTO.setSearchCondition("jobHuntPostViewcntUpdate");
				update(jobHuntPostDTO);

				// SELECTONE_JOBHUNTPOSTSINGLE 쿼리를 실행해 데이터베이스에 구직글 데이터를 불러옴
				Object[] args = { jobHuntPostDTO.getJobHuntPostId() };
				result = jdbcTemplate.queryForObject(SELECTONE_JOBHUNTPOST, args, new SelectOneJobHuntPostRowMapper());
				System.out.println("JobHuntPostDAO(selectOne) Out로그 = [" + result + "]");
				return result;
			}

		} catch (Exception e) { // 예외 발생 시
			e.printStackTrace(); // 예외 내용 출력
			return null; // 예외 발생 시 null 반환
		}
		System.out.println("JobHuntPostDAO(selectOne) Error로그 = [" + jobHuntPostDTO.getSearchCondition() + "]");
		return null; // 글 상세 출력 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	}

	// 구직글 작성
	public boolean insert(JobHuntPostDTO jobHuntPostDTO) {
		int result = 0;
		System.out.println("JobHuntPostDAO(insert) In로그 = [" + jobHuntPostDTO + "]");
		// INSERT_JOBHUNTPOST 쿼리를 실행해 데이터베이스에 구직글 데이터를 저장
		result = jdbcTemplate.update(INSERT_JOBHUNTPOST, jobHuntPostDTO.getMemberId(),jobHuntPostDTO.getJobHuntPostTitle(), 
				jobHuntPostDTO.getJobHuntPostContent(), jobHuntPostDTO.getJobHuntPostRole(), jobHuntPostDTO.getJobHuntPostRegion(),
				jobHuntPostDTO.getJobHuntPostPay(), jobHuntPostDTO.getJobHuntPostWorkDate(), jobHuntPostDTO.getJobHuntPostConcept());
		if (result <= 0) {
			System.out.println("JobHuntPostDAO(insert) Out로그 = [" + result + "]");
			return false; // 글 작성 실패 시 false 반환
		}
		return true; // 글 작성 성공 시 true 반환
	}

	// 구직글 조회수 증가 및 구직글 수정
	public boolean update(JobHuntPostDTO jobHuntPostDTO) {
		int result = 0;
		System.out.println("JobHuntPostDAO(update) In로그 = [" + jobHuntPostDTO + "]");
		// 구직글 조회수 증가
		if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostViewcntUpdate")) {
			// UPDATE_VIEWCNT 쿼리를 실행해 데이터베이스에서 구직글 조회수를 증가
			result = jdbcTemplate.update(UPDATE_VIEWCNT, jobHuntPostDTO.getJobHuntPostId());
			if (result <= 0) {
				System.out.println("JobHuntPostDAO(update) Out로그 = [" + result + "]");
				return false; // 구직글 조회수 증가 실패 시 false 반환
			}
			return true; // 조회수 증가 성공 시 true 반환
		}
		// 구직글 수정
		else if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostUpdate")) {
			// UPDATE_JOBHUNTPOST 쿼리를 실행해 데이터베이스에서 구직글 정보를 수정
			result = jdbcTemplate.update(UPDATE_JOBHUNTPOST, jobHuntPostDTO.getJobHuntPostTitle(), jobHuntPostDTO.getJobHuntPostContent(), 
					jobHuntPostDTO.getJobHuntPostRole(),jobHuntPostDTO.getJobHuntPostRegion(), jobHuntPostDTO.getJobHuntPostPay(),
					jobHuntPostDTO.getJobHuntPostWorkDate(), jobHuntPostDTO.getJobHuntPostConcept(), jobHuntPostDTO.getJobHuntPostId());
			if (result <= 0) {
				System.out.println("JobHuntPostDAO(update) Out로그 = [" + result + "]");
				return false; // 구직글 수정 실패 시 false 반환
			}
			return true; // 구직글 수정 성공 시 true 반환
		}
		System.out.println("JobHuntPostDAO(update) Error 로그 = [" + jobHuntPostDTO.getSearchCondition() + "]");
		return false; // 업데이트 조건에 해당되지 않는다면 false 반환
	}

	// 구직글 삭제
	public boolean delete(JobHuntPostDTO jobHuntPostDTO) {
		int result = 0;
		System.out.println("JobHuntPostDAO(delete) In로그 = [" + jobHuntPostDTO + "]");
		// DELETE_JOBHUNTPOST 쿼리를 실행해 데이터베이스에서 구직글 데이터를 삭제
		result = jdbcTemplate.update(DELETE_JOBHUNTPOST, jobHuntPostDTO.getJobHuntPostId());
		if (result <= 0) {
			System.out.println("JobHuntPostDAO(delete) Out로그 = [" + result + "]");
			return false; // 구직글 삭제 실패 시 false 반환
		}
		return true; // 구직글 삭제 성공 시 true 반환
	}

}

//---------------------------------------------------------------------- SELECTALL ----------------------------------------------------------------------

// 메인 페이지 - 프리미엄 회원이 작성한 구직글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class JobHuntPostPremiumRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		jobHuntPostDTO.setPostCategory(rs.getString("POST_category")); 			// 카테고리
		jobHuntPostDTO.setJobHuntPostId(rs.getString("JOBHUNTPOST_id")); 		// 구직글 아이디
		jobHuntPostDTO.setMemberId(rs.getString("MEMBER_id"));					// 회원 아이디
		jobHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 		// 회원 닉네임
		jobHuntPostDTO.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title")); 	// 제목
		jobHuntPostDTO.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date")); 	// 작성일
		jobHuntPostDTO.setPostImgName(rs.getString("POSTIMG_name")); 			// 대표 이미지
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환
	}
}

// 메인 페이지 - 주간 추천순 구직글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class JobHuntPostWeeklyBestRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		jobHuntPostDTO.setPostCategory(rs.getString("POST_category")); 				// 카테고리
		jobHuntPostDTO.setJobHuntPostId(rs.getString("JOBHUNTPOST_id")); 			// 구직글 아이디
		jobHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); 						// 회원 아이디
		jobHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 			// 회원 닉네임
		jobHuntPostDTO.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title")); 		// 제목
		jobHuntPostDTO.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content")); 	// 내용
		jobHuntPostDTO.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date")); 		// 구직글 작성일
		jobHuntPostDTO.setMyRecommend(rs.getInt("myRecommend")); 					// 좋아요 로그인한 회원이 좋아요를 눌렀는지 체크
		jobHuntPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt"));					// 좋아요 수
		jobHuntPostDTO.setPostImgName(rs.getString("POSTIMG_name")); 				// 대표 이미지
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환
	}
}

// 구직글 페이지 - 구직글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class JobHuntPostRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		jobHuntPostDTO.setPostCategory(rs.getString("POST_category")); 				// 카테고리
		jobHuntPostDTO.setJobHuntPostId(rs.getString("JOBHUNTPOST_id")); 			// 구직글 아이디
		jobHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); 						// 회원 아이디
		jobHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 			// 회원 닉네임
		jobHuntPostDTO.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title")); 		// 제목
		jobHuntPostDTO.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content")); 	// 내용
		jobHuntPostDTO.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date")); 		// 작성일
		jobHuntPostDTO.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt")); 	// 조회수
		jobHuntPostDTO.setMyRecommend(rs.getInt("myRecommend")); 					// 좋아요 로그인한 회원이 좋아요를 눌렀는지 체크
		jobHuntPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 				// 좋아요 수
		jobHuntPostDTO.setPostImgName(rs.getString("POSTIMG_name")); 				// 대표 이미지
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환
	}
}

// 회원 페이지 - 특정 회원이 작성한 구직글 목록 출력 RowMapper 클래스.전미지
class JobHuntPostMemberRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		jobHuntPostDTO.setPostCategory(rs.getString("POST_category"));				// 카테고리
		jobHuntPostDTO.setJobHuntPostId(rs.getString("JOBHUNTPOST_id")); 			// 구직글 아이디
		jobHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));		 	// 회원 닉네임
		jobHuntPostDTO.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title")); 		// 제목
		jobHuntPostDTO.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content")); 	// 내용
		jobHuntPostDTO.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date")); 		// 작성일
		jobHuntPostDTO.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt")); 	// 구직글 조회수
		jobHuntPostDTO.setMyRecommend(rs.getInt("myRecommend")); 					// 좋아요 로그인한 회원이 좋아요를 눌렀는지 체크
		jobHuntPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 				// 좋아요 수
		jobHuntPostDTO.setPostImgName(rs.getString("POSTIMG_name")); 				// 대표 이미지
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환
	}
}

// ---------------------------------------------------------------------- SELECTONE ----------------------------------------------------------------------

// 게시글 이미지 저장 시 필요한 게시글 아이디의 최대값을 저장할 RowMapper 클래스.전미지
class SelectOneMaxPostIdRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		System.out.println("JobHuntPostDAO (SelectOneMaxPostIdRowMapper) In로그 = [" + jobHuntPostDTO + "]");
		jobHuntPostDTO.setJobHuntPostId(rs.getString("MAX(JOBHUNTPOST_id)")); 							// 제일 최근 추가된 게시글 아이디
		System.out.println("JobHuntPostDAO (SelectOneMaxPostIdRowMapper) Out로그 = [" + jobHuntPostDTO + "]");
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환
	}
}

// 메인 페이지 - 최신 게시글 목록 출력 RowMapper 클래스.전미지
class SelectOneJobHuntPostRecentRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		jobHuntPostDTO.setPostCategory(rs.getString("POST_category")); 					// 카테고리
		jobHuntPostDTO.setJobHuntPostId(rs.getString("JOBHUNTPOST_id")); 				// 구직글 아이디
		jobHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); 							// 회원 아이디
		jobHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 				// 회원 닉네임
		jobHuntPostDTO.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title")); 			// 제목
		jobHuntPostDTO.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content")); 		// 내용
		jobHuntPostDTO.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date"));			// 작성일
		jobHuntPostDTO.setMyRecommend(rs.getInt("myRecommend")); 						// 좋아요 로그인한 회원이 좋아요를 눌렀는지 체크
		jobHuntPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		jobHuntPostDTO.setPostImgName(rs.getString("POSTIMG_name")); 					// 대표 이미지
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환	
	}
}

// 구직글 페이지 - 구직글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneJobHuntPostRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 매핑(저장)하는 메서드

		JobHuntPostDTO jobHuntPostDTO = new JobHuntPostDTO(); // 새로운 JobHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 JobHuntPostDTO 객체에 저장
		jobHuntPostDTO.setPostCategory(rs.getString("POST_category")); 					// 카테고리
		jobHuntPostDTO.setJobHuntPostId(rs.getString("JOBHUNTPOST_id")); 				// 구직글 아이디
		jobHuntPostDTO.setMemberId(rs.getString("MEMBER_id")); 							// 회원 아이디
		jobHuntPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 				// 회원 닉네임
		jobHuntPostDTO.setMemberIntroduction(rs.getString("MEMBER_introduction")); 		// 회원 자기소개
		jobHuntPostDTO.setProfileImgName(rs.getString("PROFILEIMG_name")); 				// 회원 프로필
		jobHuntPostDTO.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title")); 			// 제목
		jobHuntPostDTO.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content")); 		// 내용
		jobHuntPostDTO.setJobHuntPostRole(rs.getString("JOBHUNTPOST_role")); 			// 직업 ( 모델 / 사진작가 )
		jobHuntPostDTO.setJobHuntPostRegion(rs.getString("JOBHUNTPOST_region")); 		// 작업 지역
		jobHuntPostDTO.setJobHuntPostPay(rs.getInt("JOBHUNTPOST_pay")); 				// 작업 페이
		jobHuntPostDTO.setJobHuntPostWorkDate(rs.getString("JOBHUNTPOST_workDate")); 	// 작업 날짜
		jobHuntPostDTO.setJobHuntPostConcept(rs.getString("JOBHUNTPOST_concept"));		// 촬영 컨셉
		jobHuntPostDTO.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date"));			// 작성일
		jobHuntPostDTO.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt"));		// 조회수
		jobHuntPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		return jobHuntPostDTO; // jobHuntPostDTO에 저장된 데이터들을 반환
	}
}

