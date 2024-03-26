package infinitystone.chalKag.biz.headHuntPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("HEADHUNTPOSTfilterDAO")
public class HeadHuntPostFilterDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 날짜 검색 쿼리문
	private String DATESQL = "";
	
	// 가격검색 쿼리문
	private String PRICESQL = "";

	// 지역 검색
	private String REGIONLIST = "";

	// 컨셉 검색
	private String CONCEPTLIST = "";
	
	// 작업날짜 쿼리문
	private String WORKDATESQL ="";
	
	private String CATEGORY= "";
	
	private String ORDERSQL = "";
	
	private String SELECTALL = "";

	//검색어 검색
	private String USERSEARCHSQL = "";

	public List<HeadHuntPostDTO> selectAll(HeadHuntPostFilterDTO filterDTO) {

		CATEGORY = filterDTO.getCategory();
		
		// 사용자가 입력하는 검색 결과에 따른 쿼리문
		if (filterDTO.getSearchField() != null && filterDTO.getSearchInput() != null) {

			String searchInput = filterDTO.getSearchInput().replace(" ", "").toLowerCase();
			if (filterDTO.getSearchField().equals("view에서 표시할 제목 value")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(HEADHUNTPOST_title), ' ', '') LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (filterDTO.getSearchField().equals("view에서 표시할 내용 value")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(HEADHUNTPOST_content), ' ', '') LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (filterDTO.getSearchField().equals("view에서 표시할 작성자 value")) {
				USERSEARCHSQL = "AND REPLACE(LOWER(MEMBER_id), ' ', '') LIKE '%'||'" + searchInput + "'||'%'";
			}
			if (filterDTO.getSearchField().equals("view에서 표시할 제목과 내용 value")) {
				USERSEARCHSQL = "AND (REPLACE(LOWER(HEADHUNTPOST_title), ' ', '') LIKE '%'||'" + searchInput
						+ "'||'%' OR REPLACE(LOWER(HEADHUNTPOST_content), ' ', '') LIKE '%'||'" + searchInput + "'||'%')";
			}
		}
		
		// 작성 날짜 검색
		
		
		// 직업 검색
		if(filterDTO.getRoleList().size()> 0) {
			StringBuilder postRoleBuilder = new StringBuilder();
			List<String> roleDatas = filterDTO.getRoleList();
			
			for(int i =0; i< filterDTO.getRoleList().size(); i++) {
				String role = roleDatas.get(i);
				postRoleBuilder.append("\'"+role.toLowerCase()+"\',");
				postRoleBuilder.append("\'"+role.toUpperCase()+"\',");
				
				if(i+1<filterDTO.getRoleList().size()) {
					
				}
			}
		}
		
		// 지역 검색
		if (filterDTO.getRegionList().size() > 0) {
			StringBuilder postRegionBuilder = new StringBuilder();
			List<String> regionDatas = filterDTO.getRegionList();
			
			for (int i = 0; i < filterDTO.getRegionList().size(); i++) {
				String workRegion = regionDatas.get(i);
				postRegionBuilder.append("\'" + workRegion.toLowerCase() + "\',");
				postRegionBuilder.append("\'" + workRegion.toUpperCase() + "\',");
				
				if (i + 1 < filterDTO.getRegionList().size()) {
					postRegionBuilder.append(",");
				}
				REGIONLIST = "AND HEADHUNTPOSTREGION IN (" + postRegionBuilder.toString() + ")";
			}
		}

		// 가격 검색
		if (filterDTO.getMaxPrice() != 0) {
			PRICESQL = "AND PRICE BETWEEN " + filterDTO.getMinPrice() + " AND " + filterDTO.getMaxPrice();
			
		}
		
		// 작업 날짜 검색
		

		// 컨셉 검색
		if (filterDTO.getConceptList().size() > 0) {
			StringBuilder postConceptBuilder = new StringBuilder();
			List<String> companyDatas = filterDTO.getConceptList();

			for (int i = 0; i < filterDTO.getConceptList().size(); i++) {
				postConceptBuilder.append("\'" + companyDatas.get(i) + "\'");
				if (i + 1 < filterDTO.getConceptList().size()) {
					postConceptBuilder.append(",");
				}
				CONCEPTLIST = "AND HEADHUNTPOST_concept IN (" + postConceptBuilder.toString() + ")";
			}
		}


		if(!CATEGORY.equals("")) {
			SELECTALL = "SELECT	"
					+"SORT_DATA.* "
					+"MEMBER.MEMBER_id "
					+"FROM ( "
					+"SELECT "
					+"FILTER_DATA.* "
					+"COALESCE(RECOMMEND.RECOMMEND_cnt,0) AS RECOMMED FROM ( "
					+ "SELECT "
					+ "		HEADHUNTPOST_id, MEMBER_id, HEADHUNTPOST_date, HEADHUNTPOST_role, "
					+ " 	HEADHUNTPOST_region, HEADHUNTPOST_pay,HEADHUNTPOST_workDate,"
					+ " 	HEADHUNTPOST_concept, HEADHUNTPOST_title, HEADHUNTPOST_content, HEADHUNTPOST_viewcnt"
					+ "FROM"
					+ "		HEADHUNTPOST"
					+ "WHERE CATEGORY='"
					+ PRICESQL +"' "+REGIONLIST
					+ " "+ CONCEPTLIST +"ORDER BY JOBHUNT_id ASC) "
					+ "FILTER_DATA "+"LEFT JOIN ("+"SELECT "+"HEADHUNTPOST_id,COUNT(HEADHUNTPOST_id) AS RECOMMEND_cnt "
					+ "FROM RECOMMEND "+"GROUP BY HEADHUNTPOST_id"
					+ ") RECOMMEND_cnt ON FILTER_DATA.HEADHUNTPOST_id = RECOMMEND_cnt.HEADHUNTPOST_id ORDER BY HEADHUNTPOST_id DESC"
					+ ") SORT_DATA "+"JOIN MEMBER ON MEMBER.MEMBER_id = SORT_DATA.MEMBER_id"+USERSEARCHSQL+" "+ ORDERSQL;
					;
		}
		return (List<HeadHuntPostDTO>) jdbcTemplate.query(SELECTALL, new FilterRowMapper());
	}
}

class FilterRowMapper implements RowMapper<HeadHuntPostDTO> {

	@Override
	public HeadHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		HeadHuntPostDTO data = new HeadHuntPostDTO();

		data.setHeadHuntPostId(rs.getString("HEADHUNTPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setHeadHuntPostDate(rs.getString("HEADHUNTPOST_date"));
		data.setHeadHuntPostRole(rs.getString("HEADHUNTPOST_role"));
		data.setHeadHuntPostRegion(rs.getString("HEADHUNTPOST_region"));
		data.setHeadHuntPostPay(rs.getInt("HEADHUNTPOST_pay"));
		data.setHeadHuntPostConcept(rs.getString("HEADHUNTPOST_workDate"));
		data.setHeadHuntPostConcept(rs.getString("HEADHUNTPOST_concept"));
		data.setHeadHuntPostTitle(rs.getString("HEADHUNTPOST_title"));
		data.setHeadHuntPostContent(rs.getString("HEADHUNTPOST_content"));
		data.setHeadHuntPostViewcnt(rs.getString("HEADHUNTPOST_viewcnt"));
		return data;
	}

}
