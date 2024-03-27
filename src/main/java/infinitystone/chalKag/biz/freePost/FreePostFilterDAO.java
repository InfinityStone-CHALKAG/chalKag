package infinitystone.chalKag.biz.freePost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("freePostFilterDAO")
public class FreePostFilterDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String SELECTALL = "";

	// 날짜 검색
	private String DATESQL = "";
	
	// 검색어 검색
	private String USERSEARCHSQL = "";
	
	private String ORDERSQL = "";
	
	private String CATEGORY = "";


	public List<FreePostDTO> selectAll(FreePostFilterDTO freePostFilterDTO) {

		// 검색어 검색
		if (freePostFilterDTO.getSearchField() != null && freePostFilterDTO.getSearchInput() != null) {
			String searchInput = freePostFilterDTO.getSearchInput().replace(" ", "").toLowerCase();
			if (freePostFilterDTO.getSearchField().equals("title")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(FREEPOST_title),' ','')LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (freePostFilterDTO.getSearchField().equals("contents")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(FREEPOST_title),' ','')LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (freePostFilterDTO.getSearchField().equals("write")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(FREEPOST_title),' ','')LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (freePostFilterDTO.getSearchField().equals("titleAndContents")) {
				USERSEARCHSQL = "AND (REPLACE(LOWER(FREEPOST_title), ' ', '') LIKE '%'||'" + searchInput
						+ "'||'%' OR REPLACE(LOWER(FREEPOST_content), ' ', '') LIKE '%'||'" + searchInput + "'||'%')";
			}
		}
		
		
		// 날짜 검색
		// 전체 출력
		
		// 오늘 작성한 글 목록 보기 누르면
		// 현재 날짜보다 -1 했을 때 날짜가 더 큰 글만 출력
		// 현재 날짜-7 = 		20240312 < 작성된 글 < 현재 날짜
		// 이걸 변수에 저장함
		
		
		// 현재 날짜보다 -7 했을 때 날짜가 더 큰 글만 출력
		// 현재 날짜-7 = 		20240312 < 작성된 글 < 현재 날짜
		// 이걸 변수에 저장함		

		
		// 현재 날짜보다 -30 했을 때 날짜가 더 큰 글만 출력
		// 현재 날짜-7 = 		20240312 < 작성된 글 < 현재 날짜
		// 이걸 변수에 저장함
		
		
		
		
		// 수정 전
//		if(!CATEGORY.equals("")) {
//			SELECTALL = "SELECT"+"SORT_DATA.*"+"MEMBER.MEMBER_id"+"FROM ("+"SELECT"
//		+ "FILTER_DATA.*"+"COALESCE(RECOMMEND.RECOMMEND_cnt,0) AS RECOMMEND FROM ("
//					+ "SELECT"
//		+" FREEPOST_id, MEMBER_id, FREEPOST_date, FREEPOST_title, FREEPOST_content, FREEPOST_viewcnt"
//					+"FROM FREEPOST"
//		+"WHERE CATEGORY ='"
//					+ USERSEARCHSQL+"ORDER BY FREEPOST_id ASC) "
//					+ "FILTER_DATA "+"LEFT JOIN (" + "SELECT "+"FREEPOST_id, COUNT(FREEPOST_id) AS RECOMMEND_cnt "
//					+ "FROM RECOMMEND "+"GROUP BY FREEPOST_id"
//					+ ") RECOMMEND_cnt ON FILTER_DATA.FREEPOST_id = RECOMMEND_cnt.FREEPOST_id ORDER BY FREEPOST_id DESC"
//					+" ) SORT_DATA "+"JOIN MEMBER ON MEMBER.MEMBER_id = SORT_DATA.MEMBER_id "+ORDERSQL;
//		}
		
		// 수정 후
		if (!CATEGORY.equals("")) {
		    SELECTALL = "SELECT SORT_DATA.*,"
		    		+ " MEMBER.MEMBER_id "
		    		+ "FROM "
		    		+ "(SELECT FILTER_DATA.*, COALESCE(RECOMMEND.RECOMMEND_cnt, 0) AS RECOMMEND "
		    		+ "FROM "
		    		+ "(SELECT FREEPOST_id, MEMBER_id, FREEPOST_date, FREEPOST_title, FREEPOST_content,"
		    		+ " FREEPOST_viewcnt FROM FREEPOST WHERE CATEGORY = '" 
		    		+ USERSEARCHSQL 
		    		+ "' ORDER BY FREEPOST_id ASC) "
		    		+ "FILTER_DATA "
		    		+ "LEFT JOIN (SELECT FREEPOST_id, COUNT(FREEPOST_id) AS RECOMMEND_cnt "
		    		+ "FROM "
		    		+ "RECOMMEND GROUP BY FREEPOST_id) "
		    		+ "RECOMMEND_cnt ON FILTER_DATA.FREEPOST_id = RECOMMEND_cnt.FREEPOST_id ORDER BY FREEPOST_id DESC)"
		    		+ " SORT_DATA JOIN MEMBER ON MEMBER.MEMBER_id = SORT_DATA.MEMBER_id " + ORDERSQL;
		}

		
		return (List<FreePostDTO>) jdbcTemplate.query(SELECTALL, new FilterRowMapper());

	}
}
class FilterRowMapper implements RowMapper<FreePostDTO>{

	@Override
	public FreePostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		FreePostDTO data = new FreePostDTO();
		
		data.setFreePostId(rs.getString("FRREPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setFreePostDate(rs.getString("FREEPOST_date"));
		data.setFreePostTitle(rs.getString("FREEPOST_title"));
		data.setFreePostContent(rs.getString("FREEPOST_content"));
		data.setFreePostViewcnt(rs.getString("FREEPOST_viewcnt"));
		
		return data;
	}
	
}
