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

	private static final String SELECTALL_MARKETPOST = "SELECT " 
			+ "	MARKETPOST.MARKETPOST_id, "
			+ "	MARKETPOST.MEMBER_id, " 
			+ " MEMBER.MEMBER_nickname, "
			+ " MARKETPOST.MARKETPOST_date,"
			+ " CASE "
			+ "        WHEN TIMESTAMPDIFF(MINUTE, MARKETPOST.MARKETPOST_date, NOW()) < 60 THEN CONCAT(TIMESTAMPDIFF(MINUTE, MARKETPOST.MARKETPOST_date, NOW()), ' 분 전') "
			+ "        WHEN TIMESTAMPDIFF(HOUR, MARKETPOST.MARKETPOST_date, NOW()) < 24 THEN CONCAT(TIMESTAMPDIFF(HOUR, MARKETPOST.MARKETPOST_date, NOW()), ' 시간 전') "
			+ "        ELSE CONCAT(TIMESTAMPDIFF(DAY, MARKETPOST.MARKETPOST_date, NOW()), ' 일 전') "
			+ "    END AS MARKETPOST_date," 
			+ " MARKETPOST.MARKETPOST_title, " 
			+ "	MARKETPOST.MARKETPOST_content, "
			+ "	MARKETPOST.MARKETPOST_viewcnt, " 
			+ "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt, "
			+ " FROM " 
			+ "		MARKETPOST "
			+ " INNER JOIN "
		    + "		MEMBER ON MARKETPOST.MEMBER_id = MEMBER.member_id "
			+ " LEFT JOIN " 
			+ "		RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id "
			+ " GROUP BY "
			+ "		MARKETPOST.MARKETPOST_id "
			+ "ORDER BY "
	        + "		MARKETPOST_id DESC ";

	private static final String SELECTONE_MARKETPOST = "SELECT " 
			+ "	MARKETPOST.MARKETPOST_id, "   
			+ "	MARKETPOST.MEMBER_id, "
			+ " MEMBER.MEMBER_nickname, " 
			+ "	MARKETPOST.MARKETPOST_date, "
			+ " CASE "
			+ "        WHEN TIMESTAMPDIFF(MINUTE, MARKETPOST.MARKETPOST_date, NOW()) < 60 THEN CONCAT(TIMESTAMPDIFF(MINUTE, MARKETPOST.MARKETPOST_date, NOW()), ' 분 전') "
			+ "        WHEN TIMESTAMPDIFF(HOUR, MARKETPOST.MARKETPOST_date, NOW()) < 24 THEN CONCAT(TIMESTAMPDIFF(HOUR, MARKETPOST.MARKETPOST_date, NOW()), ' 시간 전') "
			+ "        ELSE CONCAT(TIMESTAMPDIFF(DAY, MARKETPOST.MARKETPOST_date, NOW()), ' 일 전') "
			+ "    END AS MARKETPOST_date,"
			+ "	MARKETPOST.MARKETPOST_price, " 
			+ "	MARKETPOST.MARKETPOST_category, "
			+ "	MARKETPOST.MARKETPOST_company, " 
			+ "	MARKETPOST.MARKETPOST_status, "
			+ "	MARKETPOST.MARKETPOST_title, " 
			+ "	MARKETPOST.MARKETPOST_content, "
			+ "	MARKETPOST.MARKETPOST_viewcnt, " 
			+ "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt, "
			+ " GROUP_CONCAT(POSTIMG.POSTIMG_name) AS POSTIMG_name "
			+ " FROM " 
			+ "		MARKETPOST " 
			+ " INNER JOIN "
		    + "		MEMBER ON MARKETPOST.MEMBER_id = MEMBER.member_id "
			+ " LEFT JOIN "
			+ "		RECOMMEND ON MARKETPOST.MARKETPOST_id = RECOMMEND.POST_id "
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON MARKETPOST.MEMBER_id = PROFILEIMG.MEMBER_id "
			+ " WHERE"
			+ "	 	MARKETPOST.MARKETPOST_id =? " 
			+ " GROUP BY "
			+ "		MARKETPOST.MARKETPOST_id, "
			+ " 	PROFILEIMG.PROFILEIMG_name";
	
	private static final String INSERT = "INSERT INTO MARKETPOST(MEMBER_id,MARKET_price,MARKET_category,MARKET_company,MARKET_status,MARKET_title,MARKET_content,MARKET_viewcnt)"
			+ "							VALUES(?,?,?,?,?,?,?,0)";
	private static final String UPDATE = "UPDATE MARKETPOST SET MARKETPOST_PRICE = ? ,MARKETPOST_category = ? ,MARKETPOST_company = ? ,MARKETPOST_status = ? ,MARKETPOST_title = ? ,MARKETPOST_content = ? "
									+ "	 WHERE "
									+ "  MARKETPOST_id = ?";
	private static final String UPDATE_VIEWCNT = "UPDATE MARKETOPOST SET MARKETPOST_viewcnt = (MARKETPOST_viewcnt+1) WHERE MARKETPOST_id=?";
	private static final String DELETE = "DELETE FROM MARKETPOST WHERE MARKETPOST_id=? ";

	public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO) {
		List<MarketPostDTO> result = null;
		try {
			if (marketPostDTO.getSearchCondition().equals("marketPostList")) {
				result = (List<MarketPostDTO>) jdbcTemplate.query(SELECTALL_MARKETPOST, new MarketPostSellectAllRowMapper());
				System.out.println("MarketPostDAO(selectAll) 로그 = [" + result + "]");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public MarketPostDTO selectOne(MarketPostDTO marketPostDTO) {
		MarketPostDTO result = null;
		Object[] args = { marketPostDTO.getMarketPostId() };
		try {
			if(marketPostDTO.getSearchCondition().equals("marketPostSingle")) {
				result = jdbcTemplate.queryForObject(SELECTONE_MARKETPOST, args, new MarketPostSellectOneRowMapper());
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
			result = jdbcTemplate.update(UPDATE,marketPostDTO.getMarketPostPrice(),marketPostDTO.getMarketPostCategory(),marketPostDTO.getMarketPostCompany(),marketPostDTO.getMarketPostStatus(),marketPostDTO.getMarketPostTitle(),marketPostDTO.getMarketPostContent());
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

class MarketPostSellectAllRowMapper implements RowMapper<MarketPostDTO> {

	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		MarketPostDTO data = new MarketPostDTO();

		data.setMarketPostId(rs.getString("MARKETPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMarketPostTitle(rs.getString("MARKETPOST_title"));
		data.setMarketPostContent(rs.getString("MARKETPOST_content"));
		data.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt"));

		return data;
	}

}

class MarketPostSellectOneRowMapper implements RowMapper<MarketPostDTO> {

	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		MarketPostDTO data = new MarketPostDTO();

		data.setMarketPostId(rs.getString("MARKETPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setProfileImgName(rs.getString("PROFILEIMG_name"));
		data.setMarketPostDate(rs.getString("MARKETPOST_date"));
		data.setMarketPostPrice(rs.getString("MARKETPOST_price"));
		data.setMarketPostCategory(rs.getString("MARKETPOST_category"));
		data.setMarketPostCompany(rs.getString("MARKETPOST_company"));
		data.setMarketPostStatus(rs.getString("MARKETPOST_status"));
		data.setMarketPostTitle(rs.getString("MARKETPOST_title"));
		data.setMarketPostContent(rs.getString("MARKETPOST_content"));
		data.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt"));

		return data;
	}

}
