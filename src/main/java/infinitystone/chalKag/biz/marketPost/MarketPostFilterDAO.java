package infinitystone.chalKag.biz.marketPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("marketPostFilterDAO")
public class MarketPostFilterDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 날짜 검색
	private String DATESQL="";

	// 가격검색
	private String PRICESQL = "";

	// 제조사 검색
	private String COMPANYLIST = "";

	// 제품 종류 검색
	private String PRODUCTCATEGORYLIST = "";

	// 검색어 검색
	private String USERSEARCHSQL = "";

	// 정렬
	private String ORDERSQL = "";
	
	// 카테고리 선택
	private String CATEGORYSQL = "";
	
	private String SELECTALL = "";

	public List<MarketPostDTO> selectAll(MarketPostFilterDTO marketPostFilterDTO) {

		CATEGORYSQL = marketPostFilterDTO.getCategory();
		
		if (marketPostFilterDTO.getSearchField() != null && marketPostFilterDTO.getSearchInput() != null) {

			// 사용자의 검색 입력에서 띄어쓰기 제거 및 소문자 변환
			String searchInput = marketPostFilterDTO.getSearchInput().replace(" ", "").toLowerCase();
			if (marketPostFilterDTO.getSearchField().equals("view에서 표시할 제목 value")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(JOBHUNTPOST_title), ' ', '') LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (marketPostFilterDTO.getSearchField().equals("view에서 표시할 내용 value")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(JOBHUNTPOST_content), ' ', '') LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (marketPostFilterDTO.getSearchField().equals("view에서 표시할 작성자 value")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(MEMBER_id), ' ', '') LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (marketPostFilterDTO.getSearchField().equals("view에서 표시할 제목과 내용 value")) {
				USERSEARCHSQL = "AND (REPLACE(LOWER(JOBHUNTPOST_title), ' ', '') LIKE '%'||'" + searchInput
						+ "'||'%' OR REPLACE(LOWER(JOBHUNTPOST_content), ' ', '') LIKE '%'||'" + searchInput
						+ "'||'%')";
			}

		}
		
		// 날짜 검색
		if(marketPostFilterDTO.getSearchField().equals("view에서 표시할 제목 value")) {
			DATESQL = "AND DATE(POST_date) >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)";
		}

		// 가격 검색
		if (marketPostFilterDTO.getMaxPrice() != 0) {
			PRICESQL = "AND PRICE BETWEEN " + marketPostFilterDTO.getMinPrice() + " AND "
					+ marketPostFilterDTO.getMaxPrice();
		}

		// 제조사 검색
		if (marketPostFilterDTO.getCompanyList().size() > 0) {
			StringBuilder postComanyBuilder = new StringBuilder();
			List<String> companyDatas = marketPostFilterDTO.getCompanyList();

			for (int i = 0; i < marketPostFilterDTO.getCompanyList().size(); i++) {
				postComanyBuilder.append("\'" + companyDatas.get(i) + "\'");
				postComanyBuilder.append(",");
			}
			COMPANYLIST = "AND MARKETPOST_company IN (" + postComanyBuilder.toString() + ")";

		}

		// 제품 종류 검색
		if (marketPostFilterDTO.getCategoryList().size() > 0) {
			StringBuilder postCategoryBuilder = new StringBuilder();
			List<String> categoryDatas = marketPostFilterDTO.getCategoryList();

			for (int i = 0; i < marketPostFilterDTO.getCategoryList().size(); i++) {
				String category = categoryDatas.get(i);
				postCategoryBuilder.append("\'" + category.toLowerCase() + "\',");
				postCategoryBuilder.append("\'" + category.toUpperCase() + "\',");
				
				if (i + 1 < marketPostFilterDTO.getCategoryList().size()) {
					postCategoryBuilder.append(",");
				}
				PRODUCTCATEGORYLIST = "AND MARKETPOST_category IN (" + postCategoryBuilder.toString() + ")";
			}
		}
		if(!CATEGORYSQL.equals("")) {						// 전체 데이터 수정하기
			SELECTALL = "SELECT" + "SORT_DATA.*"+"MEMBER.MEMBER_id, "+"FROM ("+ "SELECT"
			+	"FILTER_DATA.*," +"COALESCE(RECOMMEND.RECOMMEND_cnt,0) AS RECOMMEND FROM ("
						+"SELECT"
			+"MARKETPOST_id, MEMBER_id, MARKETPOST_date, MARKETPOST_price, MARKETPOST_category, MARKETPOST_company,MARKET_status,MARKET_title,"
			+"MARKETPOST_content, MARKETPOST_viewcnt"+"FROM MARKETPOST"+"WHERE CATEGORY ='"	
			+ COMPANYLIST+"' "+ PRICESQL
			+ " "+ PRODUCTCATEGORYLIST+"ORDER BY MARKETPOST_id ASC) " 
			+ "FILTER_DATA "+"LEFT JOIN ("+"SELECT "+ "MARKETPOST_id,COUNT(MARKETPOST_id) AS RECOMMEND_cnt "
			+ "FROM RECOMMEND "+"GROUP BY MARKETPOST_id"
			+ ") RECOMMEND_cnt ON FILTER_DATA.MARKETPOST_id = RECOMMEND_cnt.MARKETPOST_id ORDER BY MARKETPOST_id DESC"
			+ ") SORT_DATA "+"JOIN MEMBER ON MEMBER.MEMBER_id = SORT_DATA.MEMBER_id"+ USERSEARCHSQL + " " + ORDERSQL;
		}
		return (List<MarketPostDTO>) jdbcTemplate.query(SELECTALL, new FilterRowMapper());
	}
}

class FilterRowMapper implements RowMapper<MarketPostDTO> {

	@Override
	public MarketPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		MarketPostDTO data = new MarketPostDTO();

		data.setMarketPostId(rs.getString("MARKETPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMarketPostDate(rs.getString("MARKET_date"));
		data.setMarketPostPrice(rs.getString("MARKETPOST_price"));
		data.setMarketPostCategory(rs.getString("MARKETPOST_category"));
		data.setMarketPostCompany(rs.getString("MARKETPOST_company"));
		data.setMarketPostStatus(rs.getString("MARKETPOST_status"));
		data.setMarketPostTitle(rs.getString("MARKETPOST_title"));
		data.setMarketPostContent(rs.getString("MARKETPOST_content"));
		data.setMarketPostViewcnt(rs.getString("MARKETPOST_viewcnt"));

		return null;
	}

}
