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
			+ "	MARKETPOST.MARKETPOST_status = 'freecycle "
			+ "GROUP BY  "
			+ "    MARKETPOST.MARKETPOST_id,  "
			+ "    MEMBER.MEMBER_nickname  "
			+ "ORDER BY  "
			+ "    MARKETPOST.MARKETPOST_id DESC";
	
	// 메인페이지 프리미엄 회원글 출력
	private static final String SELECTALL_PREMIUMMARKETPOST= "SELECT "
					+ "			    MARKETPOST.MARKETPOST_title, "
					+ "				   MEMBER.MEMBER_grade ,  "
					+ "			FROM   "
					+ "			    MARKETPOST  "
					+ "			LEFT JOIN   "
					+ "			    POSTIMG ON MARKETPOST.MARKETPOST_id = POSTIMG.POST_id   "
					+ "			INNER JOIN  "
					+ "			    MEMBER ON MEMBER.MEMBER_id = MARKETPOST.MEMBER_id "
					+ "			WHERE   "
					+ "			    MEMBER.MEMBER_grade = 'PREMIUM'"
					+ "			ORDER BY "
					+ "    			MARKETPOST.MARKETPOST_date DESC "
					+ "			LIMIT 2 ";

	private static final String SELECTONE_MAXPOSTID = "SELECT MAX(MARKETPOST_id) FROM MARKETPOST";
			
	private static final String SELECTONE_MARKETPOSTSINGLE = "SELECT "
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
	
	private static final String INSERT = "INSERT INTO MARKETPOST(MEMBER_id,MARKETPOST_price,MARKETPOST_category,MARKETPOST_company,MARKETPOST_status,MARKETPOST_title,MARKETPOST_content,MARKETPOST_viewcnt)"
			+ "							VALUES(?,?,?,?,?,?,?,0)";
	private static final String UPDATE = "UPDATE MARKETPOST SET MARKETPOST_PRICE = ? ,MARKETPOST_category = ? ,MARKETPOST_company = ? ,MARKETPOST_status = ? ,MARKETPOST_title = ? ,MARKETPOST_content = ? "
									+ "	 WHERE "
									+ "  MARKETPOST_id = ?";
	private static final String UPDATE_VIEWCNT = "UPDATE MARKETOPOST SET MARKETPOST_viewcnt = (MARKETPOST_viewcnt+1) WHERE MARKETPOST_id=?";
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
			else if(marketPostDTO.getSearchCondition().equals("marketPostPremiumList")) {
				result = (List<MarketPostDTO>) jdbcTemplate.query(SELECTALL_PREMIUMMARKETPOST, new MarketPostPremiumSelectAllRowMapper());
				System.out.println("MarketPostDAO(premiumSelectAll) 로그 = [" + result + "]");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// 		======= 상세 출력 ==========
	public MarketPostDTO selectOne(MarketPostDTO marketPostDTO) {
		MarketPostDTO result = null;
		Object[] args = { marketPostDTO.getMarketPostId() };
		try {
			if(marketPostDTO.getSearchCondition().equals("marketPostSingle")) {
				result = jdbcTemplate.queryForObject(SELECTONE_MARKETPOSTSINGLE, args, new MarketPostSelectOneRowMapper());
				return result;
			}else if(marketPostDTO.getSearchCondition().equals("maxPostId")) {
				result = jdbcTemplate.queryForObject(SELECTONE_MAXPOSTID, new SelectOneMaxPostIdRowMapper());
				return result;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
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

//====PREMIUM SELECTALL=====
class MarketPostPremiumSelectAllRowMapper implements RowMapper<MarketPostDTO>{

	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MarketPostDTO data = new MarketPostDTO();

		data.setMarketPostId(rs.getString("marketPost_id"));
		data.setMarketPostTitle(rs.getString("marketPost_title"));

		return data;
	}
	
}

// 저장 시 최대값
class SelectOneMaxPostIdRowMapper implements RowMapper<MarketPostDTO> {
	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MarketPostDTO data = new MarketPostDTO();
		data.setMarketPostId(rs.getString("MAX(MARKETPOST_id)"));
		System.out.println("RowMapper OUT");
		return data;
	}
}

