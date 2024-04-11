package infinitystone.chalKag.biz.freePost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("freePostDAO")
public class FreePostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
// ----------------------------------------------------------------- 메인 페이지 SELECTALL -----------------------------------------------------------------

	 // 프리미엄 회원이 작성한 자유글 출력 (최신글 2개만 출력).전미지
	 private static final String SELECTALL_FREEPOSTPREMIUM = "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'FREEPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	FREEPOST.FREEPOST_id, "
			  + "	FREEPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	FREEPOST.FREEPOST_title, "
			  + "	FREEPOST.FREEPOST_content, "
			  + "	FREEPOST.FREEPOST_date, "
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	FREEPOST " // 자유글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "    MEMBER.MEMBER_grade = 'PREMIUM' "
			  + "ORDER BY "
			  + "   FREEPOST.FREEPOST_date DESC " // 작성일을 기준으로 내림차순 정렬
			  + "LIMIT 2 "; // 글을 2개만 가져오도록 설정
	 // 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	 // 사용한 컬럼 (출력 내용) : 카테고리, 자유글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 작성일, 대표 이미지(게시글 이미지 테이블)
	 // 쿼리문 설명 :
	 // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
	 // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
	

	 // 주간 추천순 자유글 목록 출력 (2개만 출력).전미지
	 private static final String SELECTALL_FREEPOSTWEEKLYBEST = "SELECT "
			  + "DISTINCT " // 중복 제거 함수	 
			  + "	'FREEPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	FREEPOST.FREEPOST_id, "
			  + "	FREEPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	FREEPOST.FREEPOST_title, "
			  + "	FREEPOST.FREEPOST_content, "
			  + "	FREEPOST.FREEPOST_date, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	FREEPOST " // 자유글 테이블
			  + "INNER JOIN  "
			  + "	MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "	FREEPOST.FREEPOST_date >= DATE_SUB(NOW(), INTERVAL 1 WEEK) "
			  + "GROUP BY "
			  + "	FREEPOST.FREEPOST_id "
			  + "ORDER BY "
			  + "	COUNT(RECOMMEND.POST_id) DESC, " // (1) 좋아요 수가 많은 순으로 정렬한 뒤
			  + "	FREEPOST.FREEPOST_date DESC " // (2) 다음 작성일이 최신인 순으로 정렬
			  + "LIMIT 2 ";
	 // 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	 // 사용한 컬럼 (출력 내용) : 카테고리, 자유글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	 // 쿼리문 설명 :
	 // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
	 // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	 // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환 
	
	 
// ----------------------------------------------------------------- 자유글 페이지 SELECTALL -----------------------------------------------------------------
	 
 // 프리미엄 회원이 작성한지 한 달 이내의 글 목록 출력.전미지
	 private static final String SELECTALL_FREEPOSTPREMIUM1MONTH = "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'FREEPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	FREEPOST.FREEPOST_id, "
			  + "	FREEPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	FREEPOST.FREEPOST_title, "
			  + "	FREEPOST.FREEPOST_content, "
			  + "	FREEPOST.FREEPOST_date, "
			  + "	FREEPOST.FREEPOST_viewcnt, "
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name, " // 대표 이미지의 이름
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt " // 좋아요 수
			  + "FROM "
			  + "	FREEPOST " // 자유글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "	MEMBER.MEMBER_grade = 'PREMIUM' " // 회원 등급이 'PREMIUM' 회원 이면서
			  + "	AND FREEPOST.FREEPOST_date >= DATE_SUB(NOW(), INTERVAL 1 MONTH) " // 작성일이 한 달 이내인 경우
			  + "ORDER BY "
			  + "   FREEPOST.FREEPOST_date DESC "; // 작성일을 기준으로 내림차순 정렬
	 // 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	 // 사용한 컬럼 (출력 내용) : 카테고리, 게시글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 수(좋아요 테이블), 대표 이미지 (게시글 이미지 테이블)
	 // 쿼리문 설명 :
	 // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
	 // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환	 
	
	// 자유글 목록 출력.전미지
	  private static final String SELECTALL_FREEPOST = "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'FREEPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	FREEPOST.FREEPOST_id, "
			  + "	FREEPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	FREEPOST.FREEPOST_title, "
			  + "	FREEPOST.FREEPOST_content, "
			  + "	FREEPOST.FREEPOST_date, "
			  + "	FREEPOST.FREEPOST_viewcnt, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	FREEPOST " // 자유글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "ORDER BY "
			  + "	FREEPOST.FREEPOST_id DESC "; // 게시글 아이디를 기준으로 내림차순 정렬
	  // 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	  // 사용한 컬럼 (출력 내용) : 카테고리, 자유글 아이디, 회원 아아디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 조회수, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	  // 쿼리문 설명 :
	  // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
	  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	  // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
	
	// ----------------------------------------------------------------- 회원 페이지 SELECTALL -----------------------------------------------------------------
	  
	  // 특정 회원이 작성한 자유글 목록 출력.전미지
	  private static final String SELECTALL_FREEPOSTMEMBER = "SELECT "
			  + "DISTINCT " // 중복 제거 함수		  
			  + "	'FREEPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	FREEPOST.FREEPOST_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	FREEPOST.FREEPOST_title, "
			  + "	FREEPOST.FREEPOST_content, "
			  + "	FREEPOST.FREEPOST_date, "
			  + "	FREEPOST.FREEPOST_viewcnt, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	FREEPOST " // 자유글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "	MEMBER.MEMBER_id = ? " // 조회할 회원의 아이디
			  + "ORDER BY "
			  + "	FREEPOST.FREEPOST_id DESC "; // 게시글 아이디를 기준으로 내림차순 정렬
	  // 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	  // 사용한 컬럼 (출력 내용) : 카테고리, 자유글 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 조회수, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	  // 쿼리문 설명 :
	  // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
	  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	  // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
 
	
		
	private static final String SELECTONE_MAXPOSTID = "SELECT MAX(FREEPOST_id) FROM FREEPOST";

	// selectOneByFreePostRecent
	
	// 메인 페이지 - 최신 게시글 1개 출력.전미지
	 private static final String SELECTONE_FREEPOSTRECENT= "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'FreePost' AS POST_category, " // 게시판 카테고리 설정
			  + "	FREEPOST.FREEPOST_id, "	
			  + "	FREEPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	FREEPOST.FREEPOST_title, "
			  + "	FREEPOST.FREEPOST_content, "
			  + "	FREEPOST.FREEPOST_date, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	FREEPOST " // 구인글 테이블
			  + "INNER JOIN  "
			  + "	MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "ORDER BY "
			  + "    FREEPOST.FREEPOST_date DESC " // 작성일을 기준으로 내림차순 정렬
			  + "LIMIT 1 "; // 글을 1개만 가져오도록 설정
  // 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
  // 사용한 컬럼 (출력 내용) : 카테고리, 게시글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
  // 쿼리문 설명 :
  // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
  // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
	
	// 게시글 상세보기 게시글 전체 보기 자유게시판, 게시판 이미지, 좋아요 테이블 조인문
	private static final String SELECTONE_FREEPOST = "SELECT  "
			+ "	'FreePost' AS POST_category, "
			+ "	FREEPOST.FREEPOST_id, "
			+ "	FREEPOST.MEMBER_id, "
			+ "	MEMBER.MEMBER_nickname, "
			+ " MEMBER.MEMBER_introduction , "
			+ "	FREEPOST.FREEPOST_title, "
			+ "	FREEPOST.FREEPOST_content, "
			+ "	FREEPOST.FREEPOST_date, "
			+ "	FREEPOST.FREEPOST_viewcnt, "
			+ "	( "
			+ "	SELECT "
			+ "		PROFILEIMG.PROFILEIMG_name "
			+ "	FROM "
			+ "		PROFILEIMG "
			+ "	WHERE "
			+ "		PROFILEIMG.MEMBER_id = FREEPOST.MEMBER_id "
			+ "	ORDER BY "
			+ "		PROFILEIMG.PROFILEIMG_id DESC "
			+ "	LIMIT 1 "
			+ "	) AS PROFILEIMG_name, "
			+ "	(  "
			+ "	SELECT "
			+ "		COUNT(*) "
			+ "	FROM  "
			+ "		RECOMMEND  "
			+ "	WHERE  "
			+ "		RECOMMEND.POST_id = FREEPOST.FREEPOST_id "
			+ "	) AS RECOMMEND_cnt "
			+ "	FROM  "
			+ "		FREEPOST "
			+ "	INNER JOIN "
			+ "		MEMBER ON FREEPOST.MEMBER_id = MEMBER.MEMBER_id "
			+ "	LEFT JOIN  "
			+ "		RECOMMEND ON FREEPOST.FREEPOST_id = RECOMMEND.POST_id "
			+ "	WHERE "
			+ "		FREEPOST.FREEPOST_id = ?";
	// 사용한 테이블 : 자유글 테이블, 회원 테이블, 좋아요 테이블, 프로필 이미지 테이블
	  // 사용한 컬럼 (출력 내용) : 카테고리, 자유글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 회원 자기소개(회원 테이블),
	  // 제목, 내용, 직업, 작업 지역, 작업 페이, 작업 날짜, 촬영 컨셉, 작성일, 조회수, 좋아요 수(좋아요 테이블), 프로필 이미지(프로필 이미지 테이블)
	  // 쿼리문 설명 :
	  // INNER JOIN을 사용해 자유글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 자유글 테이블과 좋아요 테이블을 연결
	  // 프로필 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 프로필 이미지 중 회원 프로필로 보여줄 이미지를 설정하고, 그 결과를 "PROFILEIMG_name"라는 별칭으로 반환
	  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	
	
	// --------------------------------------------------------------- INSERT, UPDATE, DELETE ---------------------------------------------------------------
	
	// 자유게시판 글 작성 게시판 이미지와 글 내용 인서트를 따로 받음
	private static final String INSERT = "INSERT INTO FREEPOST(MEMBER_id,FREEPOST_title,FREEPOST_content,FREEPOST_viewcnt) "
			+ "							VALUES(?,?,?,0)";
	private static final String UPDATE = "UPDATE FREEPOST SET FREEPOST_title=?, FREEPOST_content=? "
										+ "	WHERE "
										+ " FREEPOST_id = ? ";
	private static final String UPDATE_VIEWCNT = "UPDATE BOARD SET FREEPOST_viewcnt = (FREEPOST_viewcnt+1) WHERE FREEPOST_id=? ";
	private static final String DELETE = "DELETE FROM FREEPOST WHERE FREEPOST_id = ?";

	 // 자유글 목록 출력
	  public List<FreePostDTO> selectAll(FreePostDTO freePostDTO) {
		  List<FreePostDTO> result = null;
		  // 검색 조건에 해당될 경우 jdbcTemplate을 사용하여 SELECTALL 쿼리 실행 후 결과를 RowMapper로 매핑하여 반환
		  System.out.println("FreePostDAO(selectAll) In로그 = [" + freePostDTO + "]");
		  try {
			  // 메인 페이지 - 프리미엄 회원 게시글 목록 출력
			  if (freePostDTO.getSearchCondition().equals("freePostPremiumList")) {
				  result = jdbcTemplate.query(SELECTALL_FREEPOSTPREMIUM, new FreePostPremiumRowMapper());
				  System.out.println("FreePostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 메인 페이지 - 주간 추천순 게시글 목록 출력
			  else if (freePostDTO.getSearchCondition().equals("freePostWeeklyBestList")) {
				  result = jdbcTemplate.query(SELECTALL_FREEPOSTWEEKLYBEST, new FreePostWeeklyBestRowMapper());
				  System.out.println("FreePostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 자유글 페이지 - 프리미엄 회원이 작성한지 한 달 이내의 글 목록 출력
			  else if (freePostDTO.getSearchCondition().equals("freePostPremium1MonthList")) {
				  result = jdbcTemplate.query(SELECTALL_FREEPOSTPREMIUM1MONTH, new FreePostPremium1MonthRowMapper());
				  System.out.println("FreePostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }		  
			  // 자유글 페이지 - 자유글 전체 출력
			  else if (freePostDTO.getSearchCondition().equals("freePostList")) {
				  result = jdbcTemplate.query(SELECTALL_FREEPOST, new FreePostRowMapper());
				  System.out.println("FreePostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 회원 페이지 - 특정 회원이 작성한 자유글 전체 출력
			  else if (freePostDTO.getSearchCondition().equals("freePostMemberList")) {
				  Object[] args = { freePostDTO.getMemberId()};
				  result = jdbcTemplate.query(SELECTALL_FREEPOSTMEMBER, args, new FreePostMemberRowMapper());
				  System.out.println("HeadHuntPostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }
		  } catch (Exception e) { // 예외 발생 시
			  e.printStackTrace(); // 예외 내용 출력
			  return null; // 예외 발생 시 null 반환
		  }
		  System.out.println("FreePostDAO(selectAll) Error로그 = [" + freePostDTO.getSearchCondition() + "]");
		  return null; // 글 목록 출력 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	 }

	  // 자유글 상세 출력
	  public FreePostDTO selectOne(FreePostDTO freePostDTO) {
		  FreePostDTO result = null;
		  System.out.println("freePostDAO(selectOne) In로그 = [" + freePostDTO + "]");
		  try {
			  Object[] args = { freePostDTO.getFreePostId() };
			  // 게시글 이미지 저장 시 필요한 게시글 아이디의 최대값 가져오기
			  if (freePostDTO.getSearchCondition().equals("maxPostId")) { // 게시글 작성 시 이미지에 들어갈 PostId 추가
				  result = jdbcTemplate.queryForObject(SELECTONE_MAXPOSTID, new SelectOneMaxPostIdRowMapper());
				  System.out.println("FreePostDAO(selectOne) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 최신 게시글 출력
			  else if (freePostDTO.getSearchCondition().equals("freePostRecentPostSingle")) {
				  // SELECTONE_HEADHUNTPOST 쿼리를 실행해 데이터베이스에 자유글 데이터를 불러옴
				  result = jdbcTemplate.queryForObject(SELECTONE_FREEPOSTRECENT, new SelectOneFreePostRecentRowMapper());
				  System.out.println("FreePostDAO(selectOne) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 게시글 상세 보기 
			  else if (freePostDTO.getSearchCondition().equals("freePostSingle")) {
				  // SELECTONE_HEADHUNTPOSTSINGLE 쿼리를 실행해 데이터베이스에 자유글 데이터를 불러옴
				  result = jdbcTemplate.queryForObject(SELECTONE_FREEPOST, args, new SelectOneFreePostRowMapper());
				  System.out.println("FreePostDAO(selectOne) Out로그 = [" + result + "]");
				  return result;
			  }
			 
		  } catch (Exception e) { // 예외 발생 시
			  e.printStackTrace(); // 예외 내용 출력
			  return null; // 예외 발생 시 null 반환
		  }
		  System.out.println("HeadHuntPostDAO(selectOne) Error로그 = [" + freePostDTO.getSearchCondition() + "]");
		  return null; // 글 상세 출력 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	  }

	public boolean insert(FreePostDTO freePostDTO) {
		int result = 0;
		result = jdbcTemplate.update(INSERT,freePostDTO.getMemberId(),freePostDTO.getFreePostTitle(),freePostDTO.getFreePostContent());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean update(FreePostDTO freePostDTO) {
		int result = 0;								
		if (freePostDTO.getSearchCondition().equals("freePostViewcntUpdate")) {
			result = jdbcTemplate.update(UPDATE_VIEWCNT,freePostDTO.getFreePostId());
		} else if(freePostDTO.getSearchCondition().equals("freePostUpdate")) {
			result = jdbcTemplate.update(UPDATE,freePostDTO.getFreePostTitle(),freePostDTO.getFreePostContent(),freePostDTO.getFreePostId());
		}
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(FreePostDTO freePostDTO) {
		int result = jdbcTemplate.update(DELETE,freePostDTO.getFreePostId());
		if (result <= 0) {
			return false;
		}
		return true;
	}
}

// 		==== SELECTALL ===== 
//메인 페이지 - 프리미엄 회원이 작성한 자유글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class FreePostPremiumRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 HeadHuntPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 HeadHuntPostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 작성일
		freePostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}

//메인 페이지 - 주간 추천순 자유글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class FreePostWeeklyBestRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostContent(rs.getString("FREEPOST_content")); 	// 내용
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 자유글 작성일
		freePostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		freePostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}

//자유글 페이지 - 프리미엄 회원이 작성한지 한 달 이내의 글 필요한 데이터를 저장할 RowMapper 클래스.전미지
class FreePostPremium1MonthRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostContent(rs.getString("FREEPOST_content")); 	// 내용
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 작성일
		freePostDTO.setFreePostViewcnt(rs.getString("FREEPOST_viewcnt")); 	// 조회수
		freePostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		freePostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}

//자유글 페이지 - 자유글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class FreePostRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostContent(rs.getString("FREEPOST_content")); 	// 내용
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 작성일
		freePostDTO.setFreePostViewcnt(rs.getString("FREEPOST_viewcnt")); 	// 조회수
		freePostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		freePostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}

//회원 페이지 - 특정 회원이 작성한 자유글 목록 출력 RowMapper 클래스.전미지
class FreePostMemberRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostContent(rs.getString("FREEPOST_content")); 	// 내용
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 작성일
		freePostDTO.setFreePostViewcnt(rs.getString("freePOST_viewcnt")); 	// 자유글 조회수
		freePostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		freePostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}

//		=====SELECTONE ========
//게시글 이미지 저장 시 필요한 게시글 아이디의 최대값을 저장할 RowMapper 클래스.전미지
class SelectOneMaxPostIdRowMapper implements RowMapper<FreePostDTO> {
	@Override
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드
		
		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장	
		System.out.println("FreePostDAO (SelectOneMaxPostIdRowMapper) In로그 = [" + freePostDTO + "]");
		freePostDTO.setFreePostId(rs.getString("MAX(freePOST_id)")); // 제일 최근 추가된 게시글 아이디
		System.out.println("FreePostDAO (SelectOneMaxPostIdRowMapper) Out로그 = [" + freePostDTO + "]");
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환	
	}
}




class FreePostSelectOneRowMapper implements RowMapper<FreePostDTO> {

	@Override
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		FreePostDTO data = new FreePostDTO();

		data.setFreePostId(rs.getString("FREEPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setMemberIntroduction(rs.getString("MEMBER_introduction"));
		data.setProfileImgName(rs.getString("PROFILEIMG_name"));
		data.setFreePostDate(rs.getString("FREEPOST_date"));
		data.setFreePostTitle(rs.getString("FREEPOST_title"));
		data.setFreePostContent(rs.getString("FREEPOST_content"));
		data.setFreePostViewcnt(rs.getString("FREEPOST_viewcnt"));
		data.setRecommendCnt(rs.getInt("RECOMMEND_cnt"));
		
		return data;
	}

}

// 프리미엄 회원 출력
class FreePostPremiumSelectAllRowMapper implements RowMapper<FreePostDTO>{

	@Override
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		FreePostDTO data = new FreePostDTO();

		data.setFreePostId(rs.getString("FREEPOST_id"));
		data.setFreePostTitle(rs.getString("FREEPOST_title"));

		return data;
	}
	
}

//메인 페이지 - 최신 게시글 목록 출력 RowMapper 클래스.전미지
class SelectOneFreePostRecentRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberId(rs.getString("MEMBER_id")); 						// 회원 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 			// 회원 닉네임
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostContent(rs.getString("FREEPOST_content"));	// 내용
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 작성일
		freePostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		freePostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}

//자유글 페이지 - 자유글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneFreePostRowMapper implements RowMapper<FreePostDTO> {
	@Override // mapRow 메서드 오버라이드
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 매핑(저장)하는 메서드

		FreePostDTO freePostDTO = new FreePostDTO(); // 새로운 freePostDTO 객체 생성
		// ResultSet에 저장된 데이터를 freePostDTO 객체에 저장
		freePostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		freePostDTO.setFreePostId(rs.getString("FREEPOST_id")); 			// 자유글 아이디
		freePostDTO.setMemberId(rs.getString("MEMBER_id")); 						// 회원 아이디
		freePostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 			// 회원 닉네임
		freePostDTO.setMemberIntroduction(rs.getString("MEMBER_introduction"));		// 회원 자기소개
		freePostDTO.setProfileImgName(rs.getString("PROFILEIMG_name")); 			// 회원 프로필
		freePostDTO.setFreePostTitle(rs.getString("FREEPOST_title")); 		// 제목
		freePostDTO.setFreePostContent(rs.getString("FREEPOST_content")); 	// 내용
		freePostDTO.setFreePostDate(rs.getString("FREEPOST_date")); 		// 작성일
		freePostDTO.setFreePostViewcnt(rs.getString("FREEPOST_viewcnt")); 	// 조회수
		freePostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		return freePostDTO; // freePostDTO에 저장된 데이터들을 반환
	}
}