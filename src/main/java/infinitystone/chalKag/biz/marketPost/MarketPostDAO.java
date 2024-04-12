package infinitystone.chalKag.biz.marketPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("marketPostDAO")
public class MarketPostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

// ----------------------------------------------------------------- 메인 페이지 SELECTALL -----------------------------------------------------------------

	 // 프리미엄 회원이 작성한 장터글 출력 (최신글 2개만 출력).전미지
	 private static final String SELECTALL_MARKETPOSTPREMIUM = "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'MARKETPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	MARKETPOST.MARKETPOST_id, "
			  + "	MARKETPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	MARKETPOST.MARKETPOST_title, "
			  + "	MARKETPOST.MARKETPOST_content, "
			  + "	MARKETPOST.MARKETPOST_date, "
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	MARKETPOST " // 장터글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "    MEMBER.MEMBER_grade = 'PREMIUM' "
			  + "ORDER BY "
			  + "   MARKETPOST.MARKETPOST_date DESC " // 작성일을 기준으로 내림차순 정렬
			  + "LIMIT 2 "; // 글을 2개만 가져오도록 설정
	 // 사용한 테이블 : 장터글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	 // 사용한 컬럼 (출력 내용) : 카테고리, 장터글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 작성일, 대표 이미지(게시글 이미지 테이블)
	 // 쿼리문 설명 :
	 // INNER JOIN을 사용해 장터글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 장터글 테이블과 좋아요 테이블을 연결
	 // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
	 
	// 주간 추천순 장터글 목록 출력 (2개만 출력).전미지
	 private static final String SELECTALL_MARKETPOSTWEEKLYBEST = "SELECT "
			  + "DISTINCT " // 중복 제거 함수	 
			  + "	'MARKETPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	MARKETPOST.MARKETPOST_id, "
			  + "	MARKETPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	MARKETPOST.MARKETPOST_title, "
			  + "	MARKETPOST.MARKETPOST_content, "
			  + "	MARKETPOST.MARKETPOST_date, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	MARKETPOST " // 장터글 테이블
			  + "INNER JOIN  "
			  + "	MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "	MARKETPOST.MARKETPOST_date >= DATE_SUB(NOW(), INTERVAL 1 WEEK) "
			  + "GROUP BY "
			  + "	MARKETPOST.MARKETPOST_id "
			  + "ORDER BY "
			  + "	COUNT(RECOMMEND.POST_id) DESC, " // (1) 좋아요 수가 많은 순으로 정렬한 뒤
			  + "	MARKETPOST.MARKETPOST_date DESC " // (2) 다음 작성일이 최신인 순으로 정렬
			  + "LIMIT 2 ";
	 // 사용한 테이블 : 장터글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	 // 사용한 컬럼 (출력 내용) : 카테고리, 장터글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	 // 쿼리문 설명 :
	 // INNER JOIN을 사용해 장터글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 장터글 테이블과 좋아요 테이블을 연결
	 // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	 // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
	 
	// ----------------------------------------------------------------- 장터글 페이지 SELECTALL -----------------------------------------------------------------
	 
	 
	 
	 // 프리미엄 회원이 작성한지 한 달 이내의 글 목록 출력.전미지
	 private static final String SELECTALL_MARKETPOSTPREMIUM1MONTH = "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'MARKETPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	MARKETPOST.MARKETPOST_id, "
			  + "	MARKETPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	MARKETPOST.MARKETPOST_title, "
			  + "	MARKETPOST.MARKETPOST_content, "
			  + "	MARKETPOST.MARKETPOST_date, "
			  + "	MARKETPOST.MARKETPOST_viewcnt, "
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
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
			  + "            RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt " // 좋아요 수
			  + "FROM "
			  + "	MARKETPOST " // 장터글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "	MEMBER.MEMBER_grade = 'PREMIUM' " // 회원 등급이 'PREMIUM' 회원 이면서
			  + "	AND MARKETPOST.MARKETPOST_date >= DATE_SUB(NOW(), INTERVAL 1 MONTH) " // 작성일이 한 달 이내인 경우
			  + "ORDER BY "
			  + "   MARKETPOST.MARKETPOST_date DESC "; // 작성일을 기준으로 내림차순 정렬
	 // 사용한 테이블 : 장터글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	 // 사용한 컬럼 (출력 내용) : 카테고리, 게시글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 수(좋아요 테이블), 대표 이미지 (게시글 이미지 테이블)
	 // 쿼리문 설명 :
	 // INNER JOIN을 사용해 장터글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 장터글 테이블과 좋아요 테이블을 연결
	 // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환
	
	// 삽니다 글 전체 출력
	private static final String SELECTALL_MARKETPOSTSELLLIST = "SELECT   "
			+ "    MARKETPOST.MARKETPOST_id,   "
			+ "    MARKETPOST.MEMBER_id,   "
			+ "    ( "
			+ "        SELECT POSTIMG.POSTIMG_name   "
			+ "        FROM POSTIMG   "
			+ "        WHERE POSTIMG.POST_id = MARKETPOST.MARKETPOST_id   "
			+ "        ORDER BY POSTIMG.POSTIMG_id ASC   "
			+ "        LIMIT 1 "
			+ "    ) AS POSTIMG_name,   "
			+ "    MEMBER.MEMBER_nickname,   "
			+ "    MARKETPOST.MARKETPOST_title,   "
			+ "    MARKETPOST.MARKETPOST_content,   "
			+ "    MARKETPOST.MARKETPOST_date,   "
			+ "    MARKETPOST.MARKETPOST_viewcnt,   "
			+ "    ( "
			+ "		SELECT "
			+ "			COUNT(*)  "
			+ "		FROM  "
			+ "			RECOMMEND  "
			+ "		WHERE  "
			+ "			RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id "
			+ "	) AS RECOMMEND_cnt "
			+ "FROM   "
			+ "    MARKETPOST  "
			+ "INNER JOIN   "
			+ "    MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id   "
			+ "LEFT JOIN   "
			+ "    RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id   "
			+ "WHERE   "
			+ "    MARKETPOST.MARKETPOST_status = 'sell' "
			+ "GROUP BY  "
			+ "    MARKETPOST.MARKETPOST_id,  "
			+ "    MEMBER.MEMBER_nickname  "
			+ "ORDER BY  "
			+ "    MARKETPOST.MARKETPOST_id DESC";
	
	// 팝니다 글 전체 출력
	private static final String SELECTALL_MARKETPOSTBUYLIST="SELECT  "
			+ "    'MarketPost' AS POST_category,  "
			+ "    MARKETPOST.MARKETPOST_id,  "
			+ "    MARKETPOST.MEMBER_id,  "
			+ "    ( "
			+ "        SELECT POSTIMG.POSTIMG_name  "
			+ "        FROM POSTIMG  "
			+ "        WHERE POSTIMG.POST_id = MARKETPOST.MARKETPOST_id  "
			+ "        ORDER BY POSTIMG.POSTIMG_id ASC  "
			+ "        LIMIT 1 "
			+ "    ) AS POSTIMG_name,  "
			+ "    MEMBER.MEMBER_nickname,  "
			+ "    MARKETPOST.MARKETPOST_title,  "
			+ "    MARKETPOST.MARKETPOST_content,  "
			+ "    MARKETPOST.MARKETPOST_date,  "
			+ "    MARKETPOST.MARKETPOST_viewcnt,  "
			+ "    COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt  "
			+ "FROM  "
			+ "    MARKETPOST "
			+ "INNER JOIN  "
			+ "    MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id  "
			+ "LEFT JOIN  "
			+ "    RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id  "
			+ "WHERE  "
			+ "	MARKETPOST.MARKETPOST_status = 'buy'"
			+ "GROUP BY  "
			+ "    MARKETPOST.MARKETPOST_id,  "
			+ "    MEMBER.MEMBER_nickname  "
			+ "ORDER BY  "
			+ "    MARKETPOST.MARKETPOST_id DESC";
	
	// 무료나눔 글 전체 출력
	private static final String SELECTALL_MARKETPOSTFREECYCLELIST="SELECT  "
			+ "    'MarketPost' AS POST_category,  "
			+ "    MARKETPOST.MARKETPOST_id,  "
			+ "    MARKETPOST.MEMBER_id,  "
			+ "    ( "
			+ "        SELECT POSTIMG.POSTIMG_name  "
			+ "        FROM POSTIMG  "
			+ "        WHERE POSTIMG.POST_id = MARKETPOST.MARKETPOST_id  "
			+ "        ORDER BY POSTIMG.POSTIMG_id ASC  "
			+ "        LIMIT 1 "
			+ "    ) AS POSTIMG_name,  "
			+ "    MEMBER.MEMBER_nickname,  "
			+ "    MARKETPOST.MARKETPOST_title,  "
			+ "    MARKETPOST.MARKETPOST_content,  "
			+ "    MARKETPOST.MARKETPOST_date,  "
			+ "    MARKETPOST.MARKETPOST_viewcnt,  "
			+ "    COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt  "
			+ "FROM  "
			+ "    MARKETPOST "
			+ "INNER JOIN  "
			+ "    MEMBER MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id  "
			+ "LEFT JOIN  "
			+ "    RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id  "
			+ "WHERE  "
			+ "	MARKETPOST.MARKETPOST_status = 'freecycle' "
			+ "GROUP BY  "
			+ "    MARKETPOST.MARKETPOST_id,  "
			+ "    MEMBER.MEMBER_nickname  "
			+ "ORDER BY  "
			+ "    MARKETPOST.MARKETPOST_id DESC";
	
	// ----------------------------------------------------------------- 회원 페이지 SELECTALL -----------------------------------------------------------------
	  
	  // 특정 회원이 작성한 장터글 목록 출력.전미지
	  private static final String SELECTALL_MARKETPOSTMEMBER = "SELECT "
			  + "DISTINCT " // 중복 제거 함수		  
			  + "	'MARKETPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	MARKETPOST.MARKETPOST_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	MARKETPOST.MARKETPOST_title, "
			  + "	MARKETPOST.MARKETPOST_content, "
			  + "	MARKETPOST.MARKETPOST_date, "
			  + "	MARKETPOST.MARKETPOST_viewcnt, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	MARKETPOST " // 장터글 테이블
			  + "INNER JOIN "
			  + "	MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "WHERE "
			  + "	MEMBER.MEMBER_id = ? " // 조회할 회원의 아이디
			  + "ORDER BY "
			  + "	MARKETPOST.MARKETPOST_id DESC "; // 게시글 아이디를 기준으로 내림차순 정렬
	  // 사용한 테이블 : 장터글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	  // 사용한 컬럼 (출력 내용) : 카테고리, 장터글 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 조회수, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	  // 쿼리문 설명 :
	  // INNER JOIN을 사용해 장터글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 장터글 테이블과 좋아요 테이블을 연결
	  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	  // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

	// ---------------------------------------------------------------------- SELECTONE ----------------------------------------------------------------------

	private static final String SELECTONE_MAXPOSTID = "SELECT MAX(MARKETPOST_id) FROM MARKETPOST";
	
	 // 메인 페이지 - 최신 게시글 1개 출력.전미지
	  private static final String SELECTONE_MARKETPOSTRECENT= "SELECT "
			  + "DISTINCT " // 중복 제거 함수
			  + "	'MARKETPost' AS POST_category, " // 게시판 카테고리 설정
			  + "	MARKETPOST.MARKETPOST_id, "	
			  + "	MARKETPOST.MEMBER_id, "
			  + "	MEMBER.MEMBER_nickname, "
			  + "	MARKETPOST.MARKETPOST_title, "
			  + "	MARKETPOST.MARKETPOST_content, "
			  + "	MARKETPOST.MARKETPOST_date, "
			  + "	( " // 게시글의 좋아요 수를 합산
			  + "		SELECT "
			  + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
			  + "		FROM "
			  + "            RECOMMEND " // 좋아요 테이블에서 가져옴
			  + "		WHERE "
			  + "            RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
			  + "	) AS RECOMMEND_cnt, " // 좋아요 수
			  + "	( " // 대표 이미지 설정
			  + "		SELECT "
			  + "			POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
			  + "		FROM "
			  + "			POSTIMG " // 게시글 이미지 테이블
			  + "		WHERE "
			  + "			POSTIMG.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
			  + "		ORDER BY "
			  + "			POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
			  + "		LIMIT 1 " // 이미지를 1개만 가져오도록 설정
			  + "	 ) AS POSTIMG_name " // 대표 이미지의 이름
			  + "FROM "
			  + "	MARKETPOST " // 장터글 테이블
			  + "INNER JOIN  "
			  + "	MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN
			  + "LEFT JOIN "
			  + "	RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN
			  + "ORDER BY "
			  + "    MARKETPOST.MARKETPOST_date DESC " // 작성일을 기준으로 내림차순 정렬
			  + "LIMIT 1 "; // 글을 1개만 가져오도록 설정
	  // 사용한 테이블 : 장터글 테이블, 회원 테이블, 좋아요 테이블, 게시글 이미지 테이블
	  // 사용한 컬럼 (출력 내용) : 카테고리, 게시글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 제목, 내용, 작성일, 좋아요 수(좋아요 테이블), 대표 이미지(게시글 이미지 테이블)
	  // 쿼리문 설명 :
	  // INNER JOIN을 사용해 장터글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 장터글 테이블과 좋아요 테이블을 연결
	  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	  // 게시글 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 게시글 이미지 중 대표 이미지로 보여줄 이미지를 설정하고, 그 결과를 "POSTIMG_name"라는 별칭으로 반환

			
	private static final String SELECTONE_MARKETPOST = "SELECT "
			+ "	 	DISTINCT "
			+ "		'MarketPost' AS POST_category, " //  게시판의 카테고리 설정  
			+ "		 MARKETPOST.MARKETPOST_id, "
			+ "		 MARKETPOST.MEMBER_id, "
			+ "		 MEMBER.MEMBER_nickname, "
			+ " 	 MEMBER.MEMBER_introduction , "
			+ "		 MARKETPOST.MARKETPOST_price, "
			+ "	 	 MARKETPOST.MARKETPOST_category, "
			+ "		 MARKETPOST.MARKETPOST_company, "
			+ "	 	 MARKETPOST.MARKETPOST_status, "
			+ "		 MARKETPOST.MARKETPOST_title, "
			+ "		 MARKETPOST.MARKETPOST_content, "
			+ "		 MARKETPOST.MARKETPOST_date, "
			+ "		 MARKETPOST.MARKETPOST_viewcnt, "
			+ "		 ( "
			+ "		   SELECT "
			+ "		        PROFILEIMG.PROFILEIMG_name "
			+ "		    FROM "
			+ "		        PROFILEIMG "
			+ "      WHERE "
			+ "		     PROFILEIMG.MEMBER_id = MARKETPOST.MEMBER_id "
			+ "		 ORDER BY "
			+ "		     PROFILEIMG.PROFILEIMG_id DESC "
			+ "	     LIMIT 1 "
			+ "		     ) AS PROFILEIMG_name,"
			+ "		     (  "
			+ "		 SELECT "
			+ "		       COUNT(*) "
			+ "      FROM "
			+ "            RECOMMEND "
			+ "      WHERE "
			+ "		       RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id  "
			+ "		     ) AS RECOMMEND_cnt  "
			+ "		 FROM "
			+ "		     MARKETPOST "
			+ "		 INNER JOIN "
			+ "		     MEMBER ON MARKETPOST.MEMBER_id = MEMBER.MEMBER_id  "
			+ "		 LEFT JOIN "
			+ "		     RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id  "
			+ "		 WHERE   "
			+ "		     MARKETPOST.MARKETPOST_id = ? ";
	 // 사용한 테이블 : 장터글 테이블, 회원 테이블, 좋아요 테이블, 프로필 이미지 테이블
	  // 사용한 컬럼 (출력 내용) : 카테고리, 장터글 아이디, 회원 아이디, 회원 닉네임(회원 테이블), 회원 자기소개(회원 테이블),
	  // 제목, 내용, 직업, 작업 지역, 작업 페이, 작업 날짜, 촬영 컨셉, 작성일, 조회수, 좋아요 수(좋아요 테이블), 프로필 이미지(프로필 이미지 테이블)
	  // 쿼리문 설명 :
	  // INNER JOIN을 사용해 장터글 테이블과 회원 테이블을 연결하고, 또 다른 LEFT JOIN을 사용해 장터글 테이블과 좋아요 테이블을 연결
	  // 프로필 이미지는 테이블을 따로 나누었으며 서브 쿼리를 사용해 프로필 이미지 중 회원 프로필로 보여줄 이미지를 설정하고, 그 결과를 "PROFILEIMG_name"라는 별칭으로 반환
	  // 게시글의 좋아요는 테이블을 따로 나누었으며 COUNT() 함수를 사용해 게시글에 대한 좋아요 수를 합산하고, 그 결과를 "RECOMMEND_cnt"라는 별칭으로 반환
	  
	// --------------------------------------------------------------- INSERT, UPDATE, DELETE ---------------------------------------------------------------
	
	private static final String INSERT = "INSERT INTO MARKETPOST(MEMBER_id,MARKETPOST_price,MARKETPOST_category,MARKETPOST_company,MARKETPOST_status,MARKETPOST_title,MARKETPOST_content,MARKETPOST_viewcnt)"
			+ "							VALUES(?,?,?,?,?,?,?,0)";
	private static final String UPDATE = "UPDATE MARKETPOST SET MARKETPOST_PRICE = ? ,MARKETPOST_category = ? ,MARKETPOST_company = ? ,MARKETPOST_status = ? ,MARKETPOST_title = ? ,MARKETPOST_content = ? "
									+ "	 WHERE "
									+ "  MARKETPOST_id = ?";
	private static final String UPDATE_VIEWCNT = "UPDATE MARKETPOST SET MARKETPOST_viewcnt = (MARKETPOST_viewcnt+1) WHERE MARKETPOST_id=?";
	private static final String DELETE = "DELETE FROM MARKETPOST WHERE MARKETPOST_id=? ";

	
	// 		====== 전체 출력 ======   
	public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO) {
		List<MarketPostDTO> result = null;
		try {
			// 팝니다 글 상태 글 출력
			if (marketPostDTO.getSearchCondition().equals("marketPostSellList")) {
				result = (List<MarketPostDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTSELLLIST, new MarketPostSelectAllRowMapper());
				System.out.println("MarketPostDAO(SellselectAll) 로그 = [" + result + "]");
				return result;
			}
			// 삽니다 글 상태 글 출력
			else if (marketPostDTO.getSearchCondition().equals("marketPostBuyList")) {
				result = (List<MarketPostDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTBUYLIST, new MarketPostSelectAllRowMapper());
				System.out.println("MarketPostDAO(BuyselectAll) 로그 = [" + result + "]");
				return result;
			}
			// 무료나눔 글 상태 글 출력
			else if (marketPostDTO.getSearchCondition().equals("marketPostFreecycleList")) {
				result = (List<MarketPostDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTFREECYCLELIST, new MarketPostSelectAllRowMapper());
				System.out.println("MarketPostDAO(FreecycleSelectAll) 로그 = [" + result + "]");
				return result;
			}
			//  프리미엄 회원 글 출력
			else if (marketPostDTO.getSearchCondition().equals("marketPostPremiumList")) {
					  result = jdbcTemplate.query(SELECTALL_MARKETPOSTPREMIUM, new MarketPostPremiumRowMapper());
					  System.out.println("marketPostDAO(selectAll) Out로그 = [" + result + "]");
					  return result;
			}// 메인 페이지 - 주간 추천순 게시글 목록 출력
			  else if (marketPostDTO.getSearchCondition().equals("marketPostWeeklyBestList")) {
				  result = jdbcTemplate.query(SELECTALL_MARKETPOSTWEEKLYBEST, new MarketPostWeeklyBestRowMapper());
				  System.out.println("marketPostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }
			// 장터글 페이지 - 프리미엄 회원이 작성한지 한 달 이내의 글 목록 출력
			  else if (marketPostDTO.getSearchCondition().equals("marketPostPremium1MonthList")) {
				  result = jdbcTemplate.query(SELECTALL_MARKETPOSTPREMIUM1MONTH, new MarketPostPremium1MonthRowMapper());
				  System.out.println("marketPostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }	
			// 회원 페이지 - 특정 회원이 작성한 장터글 전체 출력
			  else if (marketPostDTO.getSearchCondition().equals("marketPostMemberList")) {
				  Object[] args = { marketPostDTO.getMemberId()};
				  result = jdbcTemplate.query(SELECTALL_MARKETPOSTMEMBER, args, new MarketPostMemberRowMapper());
				  System.out.println("marketPostDAO(selectAll) Out로그 = [" + result + "]");
				  return result;
			  }
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// 장터글 상세 출력
	  public MarketPostDTO selectOne(MarketPostDTO marketPostDTO) {
		  MarketPostDTO result = null;
		  System.out.println("marketPostDAO(selectOne) In로그 = [" + marketPostDTO + "]");
		  try {
			  Object[] args = { marketPostDTO.getMarketPostId() };
			  // 게시글 이미지 저장 시 필요한 게시글 아이디의 최대값 가져오기
			  if (marketPostDTO.getSearchCondition().equals("maxPostId")) { // 게시글 작성 시 이미지에 들어갈 PostId 추가
				  result = jdbcTemplate.queryForObject(SELECTONE_MAXPOSTID, new SelectOneMaxPostIdRowMapper());
				  System.out.println("marketPostDAO(selectOne) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 최신 게시글 출력
			  else if (marketPostDTO.getSearchCondition().equals("marketPostRecentPostSingle")) {
				  // SELECTONE_marketPOST 쿼리를 실행해 데이터베이스에 장터글 데이터를 불러옴
				  result = jdbcTemplate.queryForObject(SELECTONE_MARKETPOSTRECENT, new SelectOneMarketPostRecentRowMapper());
				  System.out.println("marketPostDAO(selectOne) Out로그 = [" + result + "]");
				  return result;
			  }
			  // 게시글 상세 보기 
			  else if (marketPostDTO.getSearchCondition().equals("marketPostSingle")) {
				  // SELECTONE_marketPOSTSINGLE 쿼리를 실행해 데이터베이스에 장터글 데이터를 불러옴
				  result = jdbcTemplate.queryForObject(SELECTONE_MARKETPOST, args, new SelectOneMarketPostRowMapper());
				  System.out.println("marketPostDAO(selectOne) Out로그 = [" + result + "]");
				  return result;
			  }
			 
		  } catch (Exception e) { // 예외 발생 시
			  e.printStackTrace(); // 예외 내용 출력
			  return null; // 예외 발생 시 null 반환
		  }
		  System.out.println("marketPostDAO(selectOne) Error로그 = [" + marketPostDTO.getSearchCondition() + "]");
		  return null; // 글 상세 출력 조건에 해당되지 않거나 처리되지 않은 경우 null 반환
	  }

	public boolean insert(MarketPostDTO marketPostDTO) {
		int result = jdbcTemplate.update(INSERT,marketPostDTO.getMemberId(),marketPostDTO.getMarketPostPrice(),marketPostDTO.getMarketPostCategory(),marketPostDTO.getMarketPostCompany(),marketPostDTO.getMarketPostStatus(),marketPostDTO.getMarketPostTitle(),marketPostDTO.getMarketPostContent());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean update(MarketPostDTO marketPostDTO) {
		int result = 0;
		if (marketPostDTO.getSearchCondition().equals("marketPostViewcntUpdate")) {
			result = jdbcTemplate.update(UPDATE_VIEWCNT,marketPostDTO.getMarketPostId());
		} else if(marketPostDTO.getSearchCondition().equals("marketPostUpdate")){
			result = jdbcTemplate.update(UPDATE,marketPostDTO.getMarketPostPrice(),marketPostDTO.getMarketPostCategory(),marketPostDTO.getMarketPostCompany(),marketPostDTO.getMarketPostStatus(),marketPostDTO.getMarketPostTitle(),marketPostDTO.getMarketPostContent(),marketPostDTO.getMarketPostId());
		}
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(MarketPostDTO marketPostDTO) {
		int result = jdbcTemplate.update(DELETE,marketPostDTO.getMarketPostId());
		if (result <= 0) {
			return false;
		}
		return true;
	}
}


// ====SELECTALL===== 

//메인 페이지 - 프리미엄 회원이 작성한 장터글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class MarketPostPremiumRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MARKETPOST_id")); 			// 장터글 아이디
		marketPostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostDate(rs.getString("MARKETPOST_date")); 		// 작성일
		marketPostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}

//메인 페이지 - 주간 추천순 장터글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class MarketPostWeeklyBestRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MARKETPOST_id")); 			// 장터글 아이디
		marketPostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostContent(rs.getString("MARKETPOST_content")); 	// 내용
		marketPostDTO.setMarketPostDate(rs.getString("MARKETPOST_date")); 		// 장터글 작성일
		marketPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		marketPostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}

//장터글 페이지 - 프리미엄 회원이 작성한지 한 달 이내의 글 필요한 데이터를 저장할 RowMapper 클래스.전미지
class MarketPostPremium1MonthRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MAREKTPOST_id")); 			// 장터글 아이디
		marketPostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostContent(rs.getString("MARKETPOST_content")); 	// 내용
		marketPostDTO.setMarketPostDate(rs.getString("MARKETPOST_date")); 		// 작성일
		marketPostDTO.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt")); 	// 조회수
		marketPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		marketPostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}

//장터글 페이지 - 장터글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class MarketPostRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MARKETPOST_id")); 			// 장터글 아이디
		marketPostDTO.setMemberId(rs.getString("MEMBER_id"));							// 회원 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostContent(rs.getString("MARKETPOST_content")); 	// 내용
		marketPostDTO.setMarketPostDate(rs.getString("marketPOST_date")); 		// 작성일
		marketPostDTO.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt")); 	// 조회수
		marketPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		marketPostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}

//회원 페이지 - 특정 회원이 작성한 장터글 목록 출력 RowMapper 클래스.전미지
class MarketPostMemberRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MARKETPOST_id")); 			// 장터글 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname"));				// 회원 닉네임
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostContent(rs.getString("MARKETPOST_content")); 	// 내용
		marketPostDTO.setMarketPostDate(rs.getString("MARKETPOST_date")); 		// 작성일
		marketPostDTO.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt")); 	// 장터글 조회수
		marketPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		marketPostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}

class MarketPostSelectAllRowMapper implements RowMapper<MarketPostDTO> {

	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		MarketPostDTO data = new MarketPostDTO();

//		data.setPostCategory(rs.getString("POST_category"));
		data.setMarketPostId(rs.getString("MARKETPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setMarketPostDate(rs.getString("MARKETPOST_date"));
		data.setMarketPostTitle(rs.getString("MARKETPOST_title"));
		data.setMarketPostContent(rs.getString("MARKETPOST_content"));
		data.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt"));
		data.setRecommendCnt(rs.getInt("RECOMMEND_cnt"));
		data.setPostImgName(rs.getString("POSTIMG_name"));

		return data;
	}

}

//  =====SELECTONE ========
class MarketPostSelectOneRowMapper implements RowMapper<MarketPostDTO> {

	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		MarketPostDTO data = new MarketPostDTO();

		data.setMarketPostId(rs.getString("MARKETPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setMemberIntroduction(rs.getString("MEMBER_introduction"));
		data.setProfileImgName(rs.getString("PROFILEIMG_name"));
		data.setMarketPostDate(rs.getString("MARKETPOST_date"));
		data.setMarketPostPrice(rs.getInt("MARKETPOST_price"));
		data.setMarketPostCategory(rs.getString("MARKETPOST_category"));
		data.setMarketPostCompany(rs.getString("MARKETPOST_company"));
		data.setMarketPostStatus(rs.getString("MARKETPOST_status"));
		data.setMarketPostTitle(rs.getString("MARKETPOST_title"));
		data.setMarketPostContent(rs.getString("MARKETPOST_content"));
		data.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt"));
		data.setRecommendCnt(rs.getInt("RECOMMEND_cnt"));
		
		return data;
	}

}

//---------------------------------------------------------------------- SELECTONE ----------------------------------------------------------------------

//게시글 이미지 저장 시 필요한 게시글 아이디의 최대값을 저장할 RowMapper 클래스.전미지
class SelectOneMaxPostIdRowMapper implements RowMapper<MarketPostDTO> {
	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드
		
		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장	
		System.out.println("marketPostDAO (SelectOneMaxPostIdRowMapper) In로그 = [" + marketPostDTO + "]");
		marketPostDTO.setMarketPostId(rs.getString("MAX(MARKETPOST_id)")); // 제일 최근 추가된 게시글 아이디
		System.out.println("marketPostDAO (SelectOneMaxPostIdRowMapper) Out로그 = [" + marketPostDTO + "]");
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환	
	}
}

//메인 페이지 - 최신 게시글 목록 출력 RowMapper 클래스.전미지
class SelectOneMarketPostRecentRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MARKETPOST_id")); 			// 장터글 아이디
		marketPostDTO.setMemberId(rs.getString("MEMBER_id")); 						// 회원 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 			// 회원 닉네임
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostContent(rs.getString("MARKETPOST_content"));	// 내용
		marketPostDTO.setMarketPostDate(rs.getString("MARKETPOST_date")); 		// 작성일
		marketPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		marketPostDTO.setPostImgName(rs.getString("POSTIMG_name"));					// 대표 이미지
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}

//장터글 페이지 - 장터글 상세 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class SelectOneMarketPostRowMapper implements RowMapper<MarketPostDTO> {
	@Override // mapRow 메서드 오버라이드
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 매핑(저장)하는 메서드

		MarketPostDTO marketPostDTO = new MarketPostDTO(); // 새로운 marketPostDTO 객체 생성
		// ResultSet에 저장된 데이터를 marketPostDTO 객체에 저장
		marketPostDTO.setPostCategory(rs.getString("POST_category"));            		// 카테고리
		marketPostDTO.setMarketPostId(rs.getString("MARKETPOST_id")); 					// 장터글 아이디
		marketPostDTO.setMemberId(rs.getString("MEMBER_id")); 							// 회원 아이디
		marketPostDTO.setMemberNickname(rs.getString("MEMBER_nickname")); 				// 회원 닉네임
		marketPostDTO.setMemberIntroduction(rs.getString("MEMBER_introduction"));		// 회원 자기소개
		marketPostDTO.setProfileImgName(rs.getString("PROFILEIMG_name")); 				// 회원 프로필
		marketPostDTO.setMarketPostPrice(rs.getInt("MARKETPOST_price")); 				// 가격
		marketPostDTO.setMarketPostCategory(rs.getString("MARKETPOST_category")); 		// 카메라 종류
		marketPostDTO.setMarketPostCompany(rs.getString("MARKETPOST_company")); 		// 제조 회사
		marketPostDTO.setMarketPostStatus(rs.getString("MARKETPOST_status")); 		// 상태
		marketPostDTO.setMarketPostTitle(rs.getString("MARKETPOST_title")); 		// 제목
		marketPostDTO.setMarketPostContent(rs.getString("MARKETPOST_content")); 	// 내용
		marketPostDTO.setMarketPostDate(rs.getString("MARKETPOST_date")); 		// 작성일
		marketPostDTO.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt")); 	// 조회수
		marketPostDTO.setRecommendCnt(rs.getInt("RECOMMEND_cnt")); 					// 좋아요 수
		return marketPostDTO; // marketPostDTO에 저장된 데이터들을 반환
	}
}
