package infinitystone.chalKag.biz.jobHuntPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDTO;

@Repository("jobHuntPostDAO")
public class JobHuntPostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECTALL_JOBHUNTPOST ="SELECT "
			+ "	'JobHuntPost' AS POST_category, " // 게시판 카테고리 설정
			+ " JOBHUNTPOST.JOBHUNTPOST_id, "
			+ " JOBHUNTPOST.MEMBER_id, "
			+ " 	(SELECT POSTIMG.POSTIMG_name "
			+ " FROM POSTIMG "
			+ " 	WHERE POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
			+ " 	ORDER BY POSTIMG.POSTIMG_id ASC "
			+ " 	LIMIT 1) AS POSTIMG_name, "
			+ " MEMBER.MEMBER_nickname, "
			+ " JOBHUNTPOST.JOBHUNTPOST_title, "
			+ " JOBHUNTPOST.JOBHUNTPOST_content, "
			+ " JOBHUNTPOST.JOBHUNTPOST_date, "
			+ " JOBHUNTPOST.JOBHUNTPOST_viewcnt, "
			+ " COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt "
			+ " FROM JOBHUNTPOST "
			+ " INNER JOIN MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
			+ " LEFT JOIN RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id "
			+ " GROUP BY JOBHUNTPOST.JOBHUNTPOST_id, "
			+ " 		MEMBER.MEMBER_nickname "
			+ " ORDER BY JOBHUNTPOST.JOBHUNTPOST_id DESC";
	
	// 메인페이지 프리미엄 회원글 출력(이미지 포함)
	private static final String SELECTALL_PREMIUMJOBHUNTPOST= "SELECT "
			+ "			    JOBHUNTPOST.JOBHUNTPOST_title, "
			+ "				   MEMBER.MEMBER_grade ,  "
			+ "			FROM   "
			+ "			    JOBHUNTPOST  "
			+ "			LEFT JOIN   "
			+ "			    POSTIMG ON JOBHUNTPOST.JOBHUNTPOST_id = POSTIMG.POST_id   "
			+ "			INNER JOIN  "
			+ "			    MEMBER ON MEMBER.MEMBER_id = JOBHUNTPOST.MEMBER_id "
			+ "			WHERE   "
			+ "			    MEMBER.MEMBER_grade = 'PREMIUM'"
			+ "			ORDER BY "
			+ "    			JOBHUNTPOST.JOBHUNTPOST_date DESC "
			+ "			LIMIT 2 ";

	
	private static final String SELECTONE_JOBHUNTPOST = "SELECT" 
			+ " 	JOBHUNTPOST.JOBHUNTPOST_id, "
			+ "		JOBHUNTPOST.MEMBER_id, "
			+ " 	MEMBER.MEMBER_nickname, "
			+ " 	PROFILEIMG.PROFILEIMG_name, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_role, "
		    + "		JOBHUNTPOST.JOBHUNTPOST_region, "
		    + "		JOBHUNTPOST.JOBHUNTPOST_pay, "
		    + "		JOBHUNTPOST.JOBHUNTPOST_workDate, "
		    + "		JOBHUNTPOST.JOBHUNTPOST_concept, "
		    + "		JOBHUNTPOST.JOBHUNTPOST_title, " 
		    + "		JOBHUNTPOST.JOBHUNTPOST_content, "
		    + "		JOBHUNTPOST.JOBHUNTPOST_date, "
			+ "		JOBHUNTPOST.JOBHUNTPOST_viewcnt, "
			+ "		COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt "
			+ "	FROM "
			+ "		JOBHUNTPOST " 
			+ " INNER JOIN "
		    + "		MEMBER "
			+ "	LEFT JOIN "
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id"
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON JOBHUNTPOST.MEMBER_id = PROFILEIMG.MEMBER_id "
			+ " WHERE "
			+ "		JOBHUNTPOST.JOBHUNTPOST_id = ? " 
			+ "	GROUP BY "
			+ "		JOBHUNTPOST.JOBHUNTPOST_id,"
			+ "		MEMBER.MEMBER_nickname, "
			+ " 	PROFILEIMG.PROFILEIMG_name";
	
	// 포스트아이디 최대값 가져오는 쿼리문
	private static final String SELECTONE_MAXPOSTID = "SELECT MAX(JOBHUNTPOST_id) FROM JOBHUNTPOST";
	

	private static final String INSERT = "INSERT "
			+ " INTO JOBHUNTPOST" 
			+ "(MEMBER_id," 
			+ "JOBHUNTPOST_role," 
			+ "JOBHUNTPOST_region," 
			+ "JOBHUNTPOST_pay," 
			+ "JOBHUNTPOST_workDate,"
			+ "JOBHUNTPOST_concept," 
			+ "JOBHUNTPOST_title," 
			+ "JOBHUNTPOST_content," 
			+ "JOBHUNTPOST_viewcnt) "
			+ "VALUES(?,?,?,?,?,?,?,?,0)";
	
	
	private static final String UPDATE = "UPDATE " 
			+ "JOBHUNTPOST SET JOBHUNTPOST_region=?,"
			+ " JOBHUNTPOST_pay=?,"
			+ " JOBHUNTPOST_workDate=?," 
			+ " JOBHUNTPOST_concept=?," 
			+ " JOBHUNTPOST_title=?,"
			+ " JOBHUNTPOST_content=?"
			+ " WHERE "
			+ " JOBHUNTPOST_id = ?";
	
	private static final String UPDATE_VIEWCNT = "UPDATE "
			+ " JOBHUNTPOST"
			+ " SET JOBHUNTPOST_viewcnt=(JOBHUNTPOST_viewcnt+1) " 
			+ " WHERE JOBHUNTPOST_id=?";
	
	
	private static final String DELETE = "DELETE FROM JOBHUNTPOST WHERE JOBHUNTPOST_id=? ";

	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		List<JobHuntPostDTO> result = null;

		try {
			if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostList")) {
				result = (List<JobHuntPostDTO>) jdbcTemplate.query(SELECTALL_JOBHUNTPOST, new JobHuntPostSelecAllRowMapper());
				System.out.println("JobHuntPostDAO(selectAll) 로그 = [" + result + "]");
				return result;
			}
			else if(jobHuntPostDTO.getSearchCondition().equals("jobHuntPostPremiumList")) {
				result = (List<JobHuntPostDTO>) jdbcTemplate.query(SELECTALL_PREMIUMJOBHUNTPOST, new JobHuntPostPremiumSelectAllRowMapper());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO) {
		JobHuntPostDTO result = null;
		Object[] args = { jobHuntPostDTO.getJobHuntPostId() };
		try {
			if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostSingle")) {
				result = jdbcTemplate.queryForObject(SELECTONE_JOBHUNTPOST, args, new JobHuntPostOneRowMapper());
				System.out.println("JobHuntPostDAO(selectOne) 로그 = [" + result + "]");
				return result;
			}
			else if(jobHuntPostDTO.getSearchCondition().equals("maxPostId")) {
				result = jdbcTemplate.queryForObject(SELECTONE_MAXPOSTID, new SelectOneMaxPostIdRowMapper());
				System.out.println("HeadHuntPostDAO(selectOne) Out로그 = [" + result + "]");
				return result;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public boolean insert(JobHuntPostDTO jobHuntPostDTO) {
		int result = jdbcTemplate.update(INSERT,jobHuntPostDTO.getMemberId(),jobHuntPostDTO.getJobHuntPostRole(),jobHuntPostDTO.getJobHuntPostRegion(),jobHuntPostDTO.getJobHuntPostPay(),jobHuntPostDTO.getJobHuntPostWorkDate(),jobHuntPostDTO.getJobHuntPostConcept(),jobHuntPostDTO.getJobHuntPostTitle(),jobHuntPostDTO.getJobHuntPostContent());
		if (result <= 0) {
			return false;
		}
		System.out.println("JobHuntPostDAO(insert) 로그 = [" + result + "]");
		return true;
	}

	public boolean update(JobHuntPostDTO jobHuntPostDTO) {
		int result = 0;
		if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostViewcntUpdate")) {
			result = jdbcTemplate.update(UPDATE_VIEWCNT,jobHuntPostDTO.getJobHuntPostId());
			System.out.println("JobHuntPostDAO(viewcntUpdate) 로그 = [" + result + "]");
		} else if(jobHuntPostDTO.getSearchCondition().equals("jobHuntPostUpdate")) {
			result = jdbcTemplate.update(UPDATE,jobHuntPostDTO.getJobHuntPostRole(),jobHuntPostDTO.getJobHuntPostRegion(),jobHuntPostDTO.getJobHuntPostPay(),jobHuntPostDTO.getJobHuntPostWorkDate(),jobHuntPostDTO.getJobHuntPostConcept(),jobHuntPostDTO.getJobHuntPostTitle(),jobHuntPostDTO.getJobHuntPostContent());
			System.out.println("JobHuntPostDAO(update) 로그 = [" + result + "]");
		}
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(JobHuntPostDTO jobHuntPostDTO) {
		int result = jdbcTemplate.update(DELETE,jobHuntPostDTO.getJobHuntPostId());
		if (result <= 0) {
			return false;
		}
		System.out.println("JobHuntPostDAO(delete) 로그 = [" + result + "]");
		return true;
	}
}

class JobHuntPostSelecAllRowMapper implements RowMapper<JobHuntPostDTO> {

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();
		
		data.setPostCategory(rs.getString("POST_category"));
		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));
		data.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content"));
		data.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date"));
		data.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt"));
		data.setRecommendCnt(rs.getString("RECOMMEND_cnt"));
		data.setPostImgName(rs.getString("POSTIMG_name"));

		return data;
	}
}

class JobHuntPostOneRowMapper implements RowMapper<JobHuntPostDTO> {

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date"));
		data.setJobHuntPostRole(rs.getString("JOBHUNTPOST_role"));
		data.setJobHuntPostRegion(rs.getString("JOBHUNTPOST_region"));
		data.setJobHuntPostPay(rs.getInt("JOBHUNTPOST_pay"));
		data.setJobHuntPostConcept(rs.getString("JOBHUNTPOST_workDate"));
		data.setJobHuntPostConcept(rs.getString("JOBHUNTPOST_concept"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));
		data.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content"));
		data.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt"));

		return data;
	}
}

class JobHuntPostPremiumSelectAllRowMapper implements RowMapper<JobHuntPostDTO>{

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));

		return data;
	}
	
}

class SelectOneMaxPostIdRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();
		data.setJobHuntPostId(rs.getString("MAX(JOBHUNTPOST_id)"));
		System.out.println("RowMapper OUT");
		return data;
	}
}