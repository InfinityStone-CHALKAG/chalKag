package infinitystone.chalKag.biz.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("recommendDAO")
public class RecommendDAO { // 게시글 좋아요 DAO
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

//----------------------------------------------------------------- 메인 페이지 SELECTALL -----------------------------------------------------------------

	// 메인 페이지 - 추천순 게시글 목록 출력 (게시판 통합. 8개 출력).전미지  
	private static final String SELECTALL_RECOMMENDBEST = "SELECT "
			  // 게시글의 데이터 중 가져올 정보를 선택하는 쿼리끝
			+ " SELECT "
			+ "		CASE " // 게시글에 해당하는 카테고리 선택
			+ "			WHEN HEADHUNTPOST.HEADHUNTPOST_id IS NOT NULL THEN 'HEADHUNTPOST' "
			+ "       	WHEN JOBHUNTPOST.JOBHUNTPOST_id IS NOT NULL THEN 'JOBHUNTPOST' "
			+ "       	WHEN FREEPOST.FREEPOST_id IS NOT NULL THEN 'FREEPOST' "
			+ "        	WHEN MARKETPOST.MARKETPOST_id IS NOT NULL THEN 'MARKETPOST' "
			+ "        	ELSE 'Unknown' " // 어떤 카테고리에도 해당하지 않는 경우 'Unknown'으로 설정
			+ "		END AS post_category, "  // 게시글의 카테고리를 선택
			+ "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_id, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_id, "
	        + "			FREEPOST.FREEPOST_id, "
	        + "			MARKETPOST.MARKETPOST_id, "
	        + "			'Unknown' "
	        + "		) AS post_id, " // 각 게시글의 아이디를 선택
	        + "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_title, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_title, "
	        + "			FREEPOST.FREEPOST_title, "
	        + "			MARKETPOST.MARKETPOST_title, "
	        + "			'Unknown' "
	        + "		) AS post_title, " // 각 게시글의 제목을 선택
	        + "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_date, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_date, "
	        + "			FREEPOST.FREEPOST_date, "
	        + "			MARKETPOST.MARKETPOST_date, "
	        + "			'Unknown' "
	        + "		) AS post_date, " // 각 게시글의 날짜를 선택
	        + "		COUNT (RECOMMEND.POST_id) AS post_recommendcnt, " // 각 게시글의 좋아요 수를 합산
	        + "		COALESCE( " // 대표 이미지 설정 쿼리
	        + "			( " // 게시글의 대표 이미지 지정
	        + "				SELECT "
	        + "					POSTIMG.POSTIMG_name "  // 게시글 이미지 선택
	        + "				FROM "
	        + "					POSTIMG " // 게시글 이미지 테이블
	        + "				WHERE "
	        + "					POSTIMG.POST_id = COALESCE ("
	        + "										HEADHUNTPOST.HEADHUNTPOST_id, "
	        + "										JOBHUNTPOST.JOBHUNTPOST_id, "
	        + "										FREEPOST.FREEPOST_id, "
	        + "										MARKETPOST.MARKETPOST_id) " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
	        + "				ORDER BY "
	        + "					POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
	        + "				LIMIT 1 " // 이미지를 1개만 가져오도록 설정
	        + "        ), "
	        + "        'Unknown' " // 대표 이마지가 없을 경우 'Unknown'으로 설정
	        + "    ) AS POSTIMG_name " // 대표 이미지의 이름
	          // 게시글의 데이터 중 가져올 정보를 선택하는 쿼리 끝
	          // 게시글과 좋아요 정보를 JOIN하는 쿼리 시작
	        + "FROM " 
	        + "		RECOMMEND " // 좋아요 테이블
	        + "LEFT JOIN " // 각 게시글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "		HEADHUNTPOST ON RECOMMEND.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
	        + "LEFT JOIN " 
	        + "		JOBHUNTPOST ON RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " 
	        + "LEFT JOIN "
	        + "		FREEPOST ON RECOMMEND.POST_id = FREEPOST.FREEPOST_id " 
	        + "LEFT JOIN "
	        + "		MARKETPOST ON RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id "
	          // 게시글과 좋아요 정보를 JOIN하는 쿼리 끝
			+ "GROUP BY "
			+ "		RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
			+ "		CASE "
			+ "			WHEN HEADHUNTPOST.HEADHUNTPOST_id IS NOT NULL THEN 'HEADHUNTPOST' "
			+ "			WHEN JOBHUNTPOST.JOBHUNTPOST_id IS NOT NULL THEN 'JOBHUNTPOST' "
			+ "			WHEN FREEPOST.FREEPOST_id IS NOT NULL THEN 'FREEPOST' "
			+ "			WHEN MARKETPOST.MARKETPOST_id IS NOT NULL THEN 'MARKETPOST' "
			+ "			ELSE 'Unknown' "
			+ "		END, "
			+ "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_id, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_id, "
	        + "			FREEPOST.FREEPOST_id, "
	        + "			MARKETPOST.MARKETPOST_id, "
	        + "			'Unknown' "
	        + "		) ," 
			+ "		COALESCE ( "
			+ "			HEADHUNTPOST.HEADHUNTPOST_title, "
			+ "			JOBHUNTPOST.JOBHUNTPOST_title, "
			+ "			FREEPOST.FREEPOST_title, "
			+ "			MARKETPOST.MARKETPOST_title, "
			+ "			'Unknown' "
			+ "		), "
			+ "		COALESCE ( "
			+ "			HEADHUNTPOST.HEADHUNTPOST_date, "
			+ "			JOBHUNTPOST.JOBHUNTPOST_date,"
			+ "			FREEPOST.FREEPOST_date, "
			+ "			MARKETPOST.MARKETPOST_date, "
			+ "			'Unknown' "
			+ "		), "
			+ "ORDER BY "
			+ "		post_recommendcnt DESC DESC " // 좋아요 수를 기준으로 내림차순 정렬 
			+ "LIMIT 8 "; // 글을 8개만 가져오도록 설정
	
//----------------------------------------------------------------- 마이 페이지 SELECTALL --------------------------------------
	 
	// 회원 페이지 - 특정 회원이 좋아요한 구인글 목록 출력 (구인글만 출력).전미지	
	private static final String SELECTALL_HEADHUNTPOSTRECOMMEND = "SELECT "
	        + "		'HeadHuntPost' AS POST_category, " // 게시판 카테고리 설정
	        + "    	HEADHUNTPOST.HEADHUNTPOST_id, " 
	        + "    	HEADHUNTPOST.HEADHUNTPOST_title, " 
	        + "    	HEADHUNTPOST.HEADHUNTPOST_content, " 
	        + "    	HEADHUNTPOST.HEADHUNTPOST_date, "
	        + "    	HEADHUNTPOST.HEADHUNTPOST_viewcnt, " 
	        + "		( " // 게시글의 좋아요 수를 합산
	        + "		SELECT "
	        + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
	        + "		FROM "
	        + "            RECOMMEND " // 좋아요 테이블에서 가져옴
	        + "		WHERE "
	        + "            RECOMMEND.POST_id = HEADHUNTPOST.HEADHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
	        + "		) AS RECOMMEND_cnt, " // 좋아요 수
			+ "		( " // 대표 이미지 설정
	        + "			SELECT "
	        + "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
	        + "			FROM "
	        + "				POSTIMG " // 게시글 이미지 테이블
	        + "			WHERE "
	        + "				POSTIMG.POST_id = HEADHUNTPOST.HEADHUNTPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
	        + "			ORDER BY "
	        + "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
	        + "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
	        + "	 	) AS POSTIMG_name " // 대표 이미지의 이름	        
	        + "FROM "
	        + "		RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 구인글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    	HEADHUNTPOST ON RECOMMEND.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    	RECOMMEND.MEMBER_id = ? " 
			+ "ORDER BY "
			+ "		POST_id DESC"; // 게시글 아이디를 기준으로 내림차순 정렬
	
	
	// 회원 페이지 - 특정 회원이 좋아요한 구직글 목록 출력 (구직글만 출력).전미지	
	private static final String SELECTALL_JOBHUNTPOSTRECOMMEND = "SELECT "
	        + "		'JOBHUNTPOST' AS post_category, " // 게시판 카테고리 설정
	        + "    	JOBHUNTPOST.JOBHUNTPOST_id, " 
	        + "    	JOBHUNTPOST.JOBHUNTPOST_title, " 
	        + "    	JOBHUNTPOST.JOBHUNTPOST_content, " 
	        + "    	JOBHUNTPOST.JOBHUNTPOST_date, "
	        + "    	JOBHUNTPOST.JOBHUNTPOST_viewcnt, " 
	        + "		( " // 게시글의 좋아요 수를 합산
	        + "		SELECT "
	        + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
	        + "		FROM "
	        + "            RECOMMEND " // 좋아요 테이블에서 가져옴
	        + "		WHERE "
	        + "            RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
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
	        + "	 	) AS POSTIMG_name " // 대표 이미지의 이름	        
	        + "FROM "
	        + "		RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 구직글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    	JOBHUNTPOST ON RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    	RECOMMEND.MEMBER_id = ? " 
			+ "ORDER BY "
			+ "		POST_id DESC ";  // 게시글 아이디를 기준으로 내림차순 정렬
	
	
	// 회원 페이지 - 특정 회원이 좋아요한 자유글 목록 출력 (자유글만 출력).전미지	
	private static final String SELECTALL_FREEPOSTRECOMMEND = "SELECT "
	        + "    	'FREEPOST' AS post_category, " // 카테고리를 자유글로 지정
	        + "    	FREEPOST.FREEPOST_id, " 
	        + "    	FREEPOST.FREEPOST_title, " 
	        + "    	FREEPOST.FREEPOST_content, " 
	        + "    	FREEPOST.FREEPOST_date, "
	        + "    	FREEPOST.FREEPOST_viewcnt, " 
	        + "		( " // 게시글의 좋아요 수를 합산
	        + "		SELECT "
	        + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
	        + "		FROM "
	        + "            RECOMMEND " // 좋아요 테이블에서 가져옴
	        + "		WHERE "
	        + "            RECOMMEND.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
	        + "		) AS RECOMMEND_cnt, " // 좋아요 수
	        + "		( " // 대표 이미지 설정
	        + "			SELECT "
	        + "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
	        + "			FROM "
	        + "				POSTIMG " // 게시글 이미지 테이블
	        + "			WHERE "
	        + "				POSTIMG.POST_id = FREEPOST.FREEPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
	        + "			ORDER BY "
	        + "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
	        + "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
	        + "	 	) AS POSTIMG_name " // 대표 이미지의 이름	       
	        + "FROM "
	        + "		RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 자유글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "    	FREEPOST ON RECOMMEND.POST_id = FREEPOST.FREEPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "    	RECOMMEND.MEMBER_id = ? " 
			+ "ORDER BY "	
			+ "		POST_id DESC ";  // 게시글 아이디를 기준으로 내림차순 정렬
	
	
	// 회원 페이지 - 특정 회원이 좋아요한 장터글 목록 출력 (장터글만 출력).전미지	
	private static final String SELECTALL_MARKETPOSTRECOMMEND = "SELECT "
	        + "    	'MARKETPOST' AS post_category, " // 카테고리를 장터글로 지정
	        + "    	MARKETPOST.MARKETPOST_id, " 
	        + "    	MARKETPOST.MARKETPOST_title, " 
	        + "    	MARKETPOST.MARKETPOST_content, " 
	        + "    	MARKETPOST.MARKETPOST_date, "
	        + "    	MARKETPOST.MARKETPOST_viewcnt, " 
	        + "		( " // 게시글의 좋아요 수를 합산
	        + "		SELECT "
	        + "            COUNT(*) " // 해당 게시글에 대한 좋아요 수를 COUNT 함수를 사용해 합산
	        + "		FROM "
	        + "            RECOMMEND " // 좋아요 테이블에서 가져옴
	        + "		WHERE "
	        + "            RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 좋아요 테이블의 게시글 아이디가 동일한 것을 선택
	        + "		) AS RECOMMEND_cnt, " // 좋아요 수
			+ "		( " // 대표 이미지 설정
	        + "			SELECT "
	        + "				POSTIMG.POSTIMG_name " // 게시글 이미지를 선택
	        + "			FROM "
	        + "				POSTIMG " // 게시글 이미지 테이블
	        + "			WHERE "
	        + "				POSTIMG.POST_id = MARKETPOST.MARKETPOST_id " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
	        + "			ORDER BY "
	        + "				POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
	        + "			LIMIT 1 " // 이미지를 1개만 가져오도록 설정
	        + "	 	) AS POSTIMG_name " // 대표 이미지의 이름	       
	        + "FROM "
	        + "		RECOMMEND " // 좋아요 테이블에서 데이터를 가져옴
	        + "LEFT JOIN " // 장터글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "		MARKETPOST ON RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id "
	        + "WHERE " // 회원이 좋아요한 게시글 선택 (조회할 멤버 아이디 입력)
	        + "		RECOMMEND.MEMBER_id = ? " 
	        + "GROUP BY "
	        + "		RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
			+ "ORDER BY "
			+ "		POST_id DESC ";  // 게시글 아이디를 기준으로 내림차순 정렬
	
	
	// 회원 페이지 - 특정 회원이 좋아요한 게시글 목록 출력 (카테고리 모두 출력).전미지  
	private static final String SELECTALL_RECOMMENDTOTALCATEGORY = "SELECT "
			  // 게시글의 데이터 중 가져올 정보를 선택하는 쿼리끝
			+ " SELECT "
			+ "		CASE " // 게시글에 해당하는 카테고리 선택
			+ "			WHEN HEADHUNTPOST.HEADHUNTPOST_id IS NOT NULL THEN 'HEADHUNTPOST' "
			+ "       	WHEN JOBHUNTPOST.JOBHUNTPOST_id IS NOT NULL THEN 'JOBHUNTPOST' "
			+ "       	WHEN FREEPOST.FREEPOST_id IS NOT NULL THEN 'FREEPOST' "
			+ "        	WHEN MARKETPOST.MARKETPOST_id IS NOT NULL THEN 'MARKETPOST' "
			+ "        	ELSE 'Unknown' " // 어떤 카테고리에도 해당하지 않는 경우 'Unknown'으로 설정
			+ "		END AS post_category, "  // 게시글의 카테고리를 선택
			+ "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_id, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_id, "
	        + "			FREEPOST.FREEPOST_id, "
	        + "			MARKETPOST.MARKETPOST_id, "
	        + "			'Unknown' " 
	        + "		) AS post_id, " // 각 게시글의 아이디를 선택
	        + "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_title, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_title, "
	        + "			FREEPOST.FREEPOST_title, "
	        + "			MARKETPOST.MARKETPOST_title, "
	        + "			'Unknown' "
	        + "		) AS post_title, " // 각 게시글의 제목을 선택
	        + "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_content, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_content, "
	        + "			FREEPOST.FREEPOST_content, "
	        + "			MARKETPOST.MARKETPOST_content, "
	        + "			'Unknown' "
	        + "		) AS post_content, " // 각 게시글의 내용을 선택
	        + "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_date, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_date, "
	        + "			FREEPOST.FREEPOST_date, "
	        + "			MARKETPOST.MARKETPOST_date, "
	        + "			'Unknown' "
	        + "		) AS post_date, " // 각 게시글의 날짜를 선택
	        + "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_viewcnt, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_viewcnt, "
	        + "			FREEPOST.FREEPOST_viewcnt, "
	        + "			MARKETPOST.MARKETPOST_viewcnt, "
	        + "			'Unknown' "
	        + "		) AS post_viewcnt, " // 각 게시글의 조회수를 선택
	        + "		COUNT (RECOMMEND.POST_id) AS post_recommendcnt, " // 각 게시글의 좋아요 수를 합산
	        + "		COALESCE( " // 대표 이미지 설정 쿼리
	        + "			( " // 게시글의 대표 이미지 지정
	        + "				SELECT "
	        + "					POSTIMG.POSTIMG_name "  // 게시글 이미지 선택
	        + "				FROM "
	        + "					POSTIMG " // 게시글 이미지 테이블
	        + "				WHERE "
	        + "					POSTIMG.POST_id = COALESCE ("
	        + "										HEADHUNTPOST.HEADHUNTPOST_id, "
	        + "										JOBHUNTPOST.JOBHUNTPOST_id, "
	        + "										FREEPOST.FREEPOST_id, "
	        + "										MARKETPOST.MARKETPOST_id) " // 게시글 아이디와 이미지 테이블의 게시글 아이디가 동일한 것을 선택
	        + "				ORDER BY "
	        + "					POSTIMG.POSTIMG_id ASC " // 이미지 아이디를 기준으로 오름차순 정렬
	        + "				LIMIT 1 " // 이미지를 1개만 가져오도록 설정
	        + "        ), "
	        + "        'Unknown' " // 대표 이마지가 없을 경우 'Unknown'으로 설정
	        + "    ) AS POSTIMG_name " // 대표 이미지의 이름
	          // 게시글의 데이터 중 가져올 정보를 선택하는 쿼리 끝
	          // 게시글과 좋아요 정보를 JOIN하는 쿼리 시작
	        + "FROM " 
	        + "		RECOMMEND " // 좋아요 테이블
	        + "LEFT JOIN " // 각 게시글 테이블과 좋아요 테이블을 LEFT JOIN하여 게시글 정보를 가져옴
	        + "		HEADHUNTPOST ON RECOMMEND.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
	        + "LEFT JOIN " 
	        + "		JOBHUNTPOST ON RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " 
	        + "LEFT JOIN "
	        + "		FREEPOST ON RECOMMEND.POST_id = FREEPOST.FREEPOST_id " 
	        + "LEFT JOIN "
	        + "		MARKETPOST ON RECOMMEND.POST_id = MARKETPOST.MARKETPOST_id "
	          // 게시글과 좋아요 정보를 JOIN하는 쿼리 끝
	        + "WHERE "
	        + "		RECOMMEND.MEMBER_id = ? " // 특정 회원이 좋아요를 누른 게시글을 조회하기 위한 조건 
			+ "GROUP BY "
			+ "		RECOMMEND.POST_id, " // 게시글 아이디를 기준으로 결과를 그룹화
			+ "		CASE "
			+ "			WHEN HEADHUNTPOST.HEADHUNTPOST_id IS NOT NULL THEN 'HEADHUNTPOST' "
			+ "			WHEN JOBHUNTPOST.JOBHUNTPOST_id IS NOT NULL THEN 'JOBHUNTPOST' "
			+ "			WHEN FREEPOST.FREEPOST_id IS NOT NULL THEN 'FREEPOST' "
			+ "			WHEN MARKETPOST.MARKETPOST_id IS NOT NULL THEN 'MARKETPOST' "
			+ "			ELSE 'Unknown' "
			+ "		END, "
			+ "    	COALESCE ( "
	        + "			HEADHUNTPOST.HEADHUNTPOST_id, "
	        + "			JOBHUNTPOST.JOBHUNTPOST_id, "
	        + "			FREEPOST.FREEPOST_id, "
	        + "			MARKETPOST.MARKETPOST_id, "
	        + "			'Unknown' "
	        + "		), "
			+ "		COALESCE ( "
			+ "			HEADHUNTPOST.HEADHUNTPOST_title, "
			+ "			JOBHUNTPOST.JOBHUNTPOST_title, "
			+ "			FREEPOST.FREEPOST_title, "
			+ "			MARKETPOST.MARKETPOST_title, "
			+ "			'Unknown' "
			+ "		), "
			+ "		COALESCE ( "
			+ "			HEADHUNTPOST.HEADHUNTPOST_content, "
			+ "			JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "			FREEPOST.FREEPOST_content, "
			+ "			MARKETPOST.MARKETPOST_content, "
			+ "			'Unknown' "
			+ "		), "
			+ "		COALESCE ( "
			+ "			HEADHUNTPOST.HEADHUNTPOST_date, "
			+ "			JOBHUNTPOST.JOBHUNTPOST_date,"
			+ "			FREEPOST.FREEPOST_date, "
			+ "			MARKETPOST.MARKETPOST_date, "
			+ "			'Unknown' "
			+ "		), "
			+ "		COALESCE ( "
			+ "			HEADHUNTPOST.HEADHUNTPOST_viewcnt, "
			+ "			JOBHUNTPOST.JOBHUNTPOST_viewcnt,"
			+ "			FREEPOST.FREEPOST_viewcnt, "
			+ "			MARKETPOST.MARKETPOST_viewcnt, "
			+ "			'Unknown' "
			+ "		) "
			+ "ORDER BY "
			+ "		post_date DESC "; // 게시글 작성일을 기준으로 내림차순 정렬 
	
	
	// 글 상세 페이지 - 좋아요 클릭 시 색 변경.전미지
	private static final String SELECTALL_RECOMMEDONOFF = "SELECT "
			+ "		POST_id, "
			+ "		MEMBER_id "
			+ "FROM "
			+ "		RECOMMEND "
			+ "WHERE "
			+ "		POST_id = ? AND MEMBER_id";
	// 사용한 테이블 : 좋아요 테이블
	// 사용한 컬럼 (출력 내용) : 게시글 아이디

// ---------------------------------------------------------------------- SELECTONE ----------------------------------------------------------------------
	
	// 좋아요 중복검사 (좋아요를 눌렀는지 안눌렀는지 확인).전미지
	private static final String SELECTONE_RECOMMEND = "SELECT "                                                                                                                                                                              
			+ "		POST_id, "
			+ "		MEMBER_id "
			+ "FROM "
			+ "		RECOMMEND "
			+ "WHERE "
			+ "		POST_id = ? AND MEMBER_id = ? ";
	// 사용한 테이블 : 좋아요 테이블
	// 사용한 컬럼 (출력 내용) : 게시글 아이디, 회원 아이디

	
	// 좋아요 추가
	private static final String INSERT_RECOMMEND = "INSERT INTO RECOMMEND ( " 
			+ "POST_id, "
			+ "MEMBER_id) "
			+ "VALUES (?, ?) ";
	// 사용한 테이블 : 좋아요 테이블
	// 사용한 컬럼 (작성 내용) : 게시글 아이디, 회원 아이디
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
			// 메인 페이지 - 좋아요 수가 높은 게시글 목록 출력
			if (recommendDTO.getSearchCondition().equals("recommendBestListRecommend")) {
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_RECOMMENDBEST, new RecommendBestRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 회원 페이지 - 특정 회원이 좋아요한 구인글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("headHuntPostRecommendList")) {
				Object[] args = { recommendDTO.getMemberId() };
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_HEADHUNTPOSTRECOMMEND,args, new HeadHuntPostRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 회원 페이지 - 특정 회원이 좋아요한 구직글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("jobHuntPostRecommendList")) {
				Object[] args = { recommendDTO.getMemberId() };
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_JOBHUNTPOSTRECOMMEND,args, new JobHuntPostRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 회원 페이지 - 특정 회원이 좋아요한 자유글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("freePostRecommendList")) {
				Object[] args = { recommendDTO.getMemberId() };
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_FREEPOSTRECOMMEND,args, new FreePostRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 회원 페이지 - 특정 회원이 좋아요한 장터글 목록 출력
			else if (recommendDTO.getSearchCondition().equals("marketPostRecommendList")) {
				Object[] args = { recommendDTO.getMemberId() };
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTRECOMMEND, args,new MarketPostRecommendRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 회원 페이지 - 특정 회원이 좋아요한 게시글 목록 출력 (카테고리 모두 출력)
			else if (recommendDTO.getSearchCondition().equals("recommendTotalCateogoryList")) {
				Object[] args = { recommendDTO.getMemberId()};
				result = (List<RecommendDTO>) jdbcTemplate.query(SELECTALL_RECOMMENDTOTALCATEGORY, args,new RecommendTotalCategoryRowMapper());
				System.out.println("RecommendDAO(selectAll) Out로그 = [" + result + "]");
				return result;
			}
			// 글 상세 페이지 - 좋아요 클릭 시 색 변경
			else if (recommendDTO.getSearchCondition().equals("recommendOnOff")) {
				Object[] args = { recommendDTO.getPostId(), recommendDTO.getMemberId() };
				result = (List<RecommendDTO>) jdbcTemplate.query( SELECTALL_RECOMMEDONOFF, args,new RecommendOnOffRowMapper()); 
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
		result = jdbcTemplate.update(INSERT_RECOMMEND, recommendDTO.getPostId(), recommendDTO.getMemberId());
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
		result = jdbcTemplate.update(DELETE_RECOMMEND, recommendDTO.getPostId(), recommendDTO.getMemberId());
		if (result <= 0) {
			System.out.println("RecommendDAO(delete) Out로그 = [" + result + "]");
			return false; // 좋아요 삭제 실패 시 false 반환
		}
		return true; // 좋아요 삭제 성공 시 true 반환
	}
}

// ===== SELECTALL =====

// 매인 페이지 - 추천순 게시글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class RecommendBestRecommendRowMapper implements RowMapper<RecommendDTO> {
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드

		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostCategory(rs.getString("post_category")); 			// 게시글 카테고리
		recommendDTO.setPostId(rs.getString("post_id")); 						// 게시글 아이디
		recommendDTO.setPostTitle(rs.getString("post_title")); 					// 게시글 제목
		recommendDTO.setPostDate(rs.getString("post_date")); 					// 게시글 작성일
		recommendDTO.setPostRecommendCnt(rs.getString("post_recommendcnt"));	// 게시글의 좋아요 수
		recommendDTO.setPostImgName(rs.getString("post_imgname")); 				// 게시글 대표 이미지
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

/// 회원 페이지 - 특정 회원이 좋아요한 구인글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스 - 수정버전2
class HeadHuntPostRecommendRowMapper implements RowMapper<RecommendDTO> {
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드

		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostCategory(rs.getString("post_category")); 		// 게시글 카테고리
		recommendDTO.setPostId(rs.getString("HEADHUNTPOST_id")); 			// 게시글 아이디
		recommendDTO.setPostTitle(rs.getString("HEADHUNTPOST_title")); 		// 게시글 제목
		recommendDTO.setPostContent(rs.getString("HEADHUNTPOST_content")); 	// 게시글 내용
		recommendDTO.setPostDate(rs.getString("HEADHUNTPOST_date")); 		// 게시글 작성일
		recommendDTO.setPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt")); 	// 게시글 조회수
		recommendDTO.setPostRecommendCnt(rs.getString("RECOMMEND_cnt")); 	// 게시글의 좋아요 수
		recommendDTO.setPostImgName(rs.getString("POSTIMG_name")); 			// 게시글 대표 이미지
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}


// 회원 페이지 - 특정 회원이 좋아요한 구직글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지 - 수정버전1
class JobHuntPostRecommendRowMapper implements RowMapper<RecommendDTO> {
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드

		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostCategory(rs.getString("post_category")); 		// 게시글 카테고리
		recommendDTO.setPostId(rs.getString("JOBHUNTPOST_id")); 			// 게시글 아이디
		recommendDTO.setPostTitle(rs.getString("JOBHUNTPOST_title")); 		// 게시글 제목
		recommendDTO.setPostContent(rs.getString("JOBHUNTPOST_content")); 	// 게시글 내용
		recommendDTO.setPostDate(rs.getString("JOBHUNTPOST_date")); 		// 게시글 작성일
		recommendDTO.setPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt"));	// 게시글 조회수
		recommendDTO.setPostRecommendCnt(rs.getString("RECOMMEND_cnt")); 	// 게시글의 좋아요 수
		recommendDTO.setPostImgName(rs.getString("POSTIMG_name")); 			// 게시글 대표 이미지
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// 회원 페이지 - 특정 회원이 좋아요한 자유글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지 - 수정버전1
class FreePostRecommendRowMapper implements RowMapper<RecommendDTO> {
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드

		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostCategory(rs.getString("post_category")); 		// 게시글 카테고리
		recommendDTO.setPostId(rs.getString("FREEPOST_id"));				// 게시글 아이디
		recommendDTO.setPostTitle(rs.getString("FREEPOST_title")); 			// 게시글 제목
		recommendDTO.setPostContent(rs.getString("FREEPOST_content"));		// 게시글 내용
		recommendDTO.setPostDate(rs.getString("FREEPOST_date")); 			// 게시글 작성일
		recommendDTO.setPostViewcnt(rs.getString("FREEPOST_viewcnt")); 		// 게시글 조회수
		recommendDTO.setPostRecommendCnt(rs.getString("RECOMMEND_cnt")); 	// 게시글의 좋아요 수
		recommendDTO.setPostImgName(rs.getString("POSTIMG_name")); 			// 게시글 대표 이미지
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// 회원 페이지 - 특정 회원이 좋아요한 장터글 목록 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class MarketPostRecommendRowMapper implements RowMapper<RecommendDTO> {
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드

		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostCategory(rs.getString("post_category")); 		// 게시글 카테고리
		recommendDTO.setPostId(rs.getString("MARKETPOST_id"));				// 게시글 아이디
		recommendDTO.setPostTitle(rs.getString("MARKETPOST_title")); 			// 게시글 제목
		recommendDTO.setPostContent(rs.getString("MARKETPOST_content"));		// 게시글 내용
		recommendDTO.setPostDate(rs.getString("MARKETPOST_date")); 			// 게시글 작성일
		recommendDTO.setPostViewcnt(rs.getString("MARKETPOST_viewcnt")); 		// 게시글 조회수
		recommendDTO.setPostRecommendCnt(rs.getString("RECOMMEND_cnt")); 	// 게시글의 좋아요 수
		recommendDTO.setPostImgName(rs.getString("POSTIMG_name")); 			// 게시글 대표 이미지
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// 회원 페이지 - 특정 회원이 좋아요한 게시글 카테고리별 출력 시 필요한 데이터를 저장할 RowMapper 클래스.전미지
class  RecommendTotalCategoryRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostCategory(rs.getString("post_category"));			// 게시글 카테고리
		recommendDTO.setPostId(rs.getString("post_id"));						// 게시글 아이디	
		recommendDTO.setPostTitle(rs.getString("post_title"));					// 게시글 제목
		recommendDTO.setPostContent(rs.getString("post_content"));				// 게시글 내용
		recommendDTO.setPostDate(rs.getString("post_date"));					// 게시글 작성일
		recommendDTO.setPostViewcnt(rs.getString("post_viewcnt"));				// 게시글 조회수
		recommendDTO.setPostRecommendCnt(rs.getString("post_recommendcnt"));	// 게시글의 좋아요 수
		recommendDTO.setPostImgName(rs.getString("post_imgname"));				// 게시글 대표 이미지
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}

// 글 상세 페이지- 좋아요 클릭 시 색 변경에 대한 데이터를 저장할 RowMapper 클래스.전미지
class  RecommendOnOffRowMapper implements RowMapper<RecommendDTO>{
	@Override // mapRow 메서드 오버라이드
	public RecommendDTO mapRow(ResultSet rs,int rowNum) throws SQLException{
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 매핑(저장)하는 메서드
		
		RecommendDTO recommendDTO = new RecommendDTO(); // 새로운 RecommendDTO 객체 생성	
		// ResultSet에 저장된 데이터를 RecommendDTO 객체에 저장
		recommendDTO.setPostId(rs.getString("post_id")); 				// 게시글 아이디	
		recommendDTO.setMemberId(rs.getString("MEMBER_id")); 			// 회원 아이디
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
		recommendDTO.setMemberId(rs.getString("MEMBER_id")); 	// 회원 아이디	
		return recommendDTO; // recommendDTO에 저장된 데이터들을 반환
	}
}