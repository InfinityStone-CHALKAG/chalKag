package infinitystone.chalKag.biz.jobHuntPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("jobHuntPostDAO")
public class JobHuntPostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECTALL = "SELECT " 
			+ "	JOBHUNTPOST.JOBHUNTPOST_id, "
			+ "	JOBHUNTPOST.JOBHUNTPOST_title," 
			+ "	JOBHUNTPOST.JOBHUNTPOST_content,"
			+ "	JOBHUNTPOST.JOBHUNTPOST_date,"
			+ " CASE "
			+ "        WHEN TIMESTAMPDIFF(MINUTE, JOBHUNTPOST.JOBHUNTPOST_date, NOW()) < 60 THEN CONCAT(TIMESTAMPDIFF(MINUTE, JOBHUNTPOST.JOBHUNTPOST_date, NOW()), ' 분 전') "
			+ "        WHEN TIMESTAMPDIFF(HOUR, JOBHUNTPOST.JOBHUNTPOST_date, NOW()) < 24 THEN CONCAT(TIMESTAMPDIFF(HOUR, JOBHUNTPOST.JOBHUNTPOST_date, NOW()), ' 시간 전') "
			+ "        ELSE CONCAT(TIMESTAMPDIFF(DAY, JOBHUNTPOST.JOBHUNTPOST_date, NOW()), ' 일 전') "
			+ "    END AS COMMENT_date," 
			+ "	JOBHUNTPOST.MEMBER_id,"
			+ " MEMBER.MEMBER_nickname," 	
			+ "	JOBHUNTPOST.JOBHUNTPOST_viewcnt,"
			+ "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt," 
			+ "	FROM" 
			+ "		JOBHUNTPOST" 
			+ " INNER JOIN "
		    + "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.member_id "
			+ "	LEFT JOIN "
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " 
			+ "	GROUP BY"
			+ "		JOBHUNTPOST.JOBHUNTPOST_id ";
	
	// 메인페이지 프리미엄 회원글 출력
	private static final String JOBHUNT_SELECTALL_PREMIUM= "SELECT"
			+ "    JOBHUNTPOST.JOBHUNTPOST_title,"
			+ "	   MEMBER.MEMBER_grade , "
			+ "    POSTIMG.POSTIMG_name "
			+ "FROM  "
			+ "    JOBHUNTPOST "
			+ "LEFT JOIN  "
			+ "    POSTIMG ON JOBHUNTPOST.JOBHUNTPOST_id = POSTIMG.POST_id  "
			+ "INNER JOIN "
			+ "    MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
			+ "WHERE  "
			+ "    MEMBER.MEMBER_grade = 'premium'";

	
	
	private static final String SELECTONE = "SELECT" 
			+ " JOBHUNTPOST.JOBHUNTPOST_id, "
			+ "	JOBHUNTPOST.JOBHUNTPOST_title, " 
			+ "	JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "	JOBHUNTPOST.JOBHUNTPOST_date, "
			+ " CASE "
			+ "        WHEN TIMESTAMPDIFF(MINUTE, JOBHUNTPOST.JOBHUNTPOST_date, NOW()) < 60 THEN CONCAT(TIMESTAMPDIFF(MINUTE, JOBHUNTPOST.JOBHUNTPOST_date, NOW()), ' 분 전') "
			+ "        WHEN TIMESTAMPDIFF(HOUR, JOBHUNTPOST.JOBHUNTPOST_date, NOW()) < 24 THEN CONCAT(TIMESTAMPDIFF(HOUR, JOBHUNTPOST.JOBHUNTPOST_date, NOW()), ' 시간 전') "
			+ "        ELSE CONCAT(TIMESTAMPDIFF(DAY, JOBHUNTPOST.JOBHUNTPOST_date, NOW()), ' 일 전') "
			+ "    END AS COMMENT_date," 
			+ "	JOBHUNTPOST.MEMBER_id, "
			+ " MEMBER.MEMBER_nickname, " 
			+ "	JOBHUNTPOST.JOBHUNTPOST_viewcnt, "
			+ "	COUNT(RECOMMEND.POST_id) AS RECOMMEND_cnt,"
			+ " PROFILEIMG.PROFILEIMG_name AS PROFILEIMG_name"
			+ "	FROM "
			+ "		JOBHUNTPOST " 
			+ " INNER JOIN "
		    + "		MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.member_id "
			+ "	LEFT JOIN "
			+ "		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id"
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON JOBHUNTPOST.MEMBER_id = PROFILEIMG.MEMBER_id "
			+ " WHERE "
			+ "		JOBHUNTPOST.JOBHUNTPOST_id = ? " 
			+ "	GROUP BY "
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, "
			+ " 	PROFILEIMG.PROFILEIMG_name";
	
	
	

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
			if (jobHuntPostDTO.getSearchCondition().equals("selectAllJobHuntPost")) {
				result = (List<JobHuntPostDTO>) jdbcTemplate.query(SELECTALL, new JobHuntPostSellecAllRowMapper());
				System.out.println("JobHuntPostDAO(selectAll) 로그 = [" + result + "]");
				return result;
			}
			else if(jobHuntPostDTO.getSearchCondition().equals("selectAllJobHuntPremiumPost")) {
				result = (List<JobHuntPostDTO>) jdbcTemplate.query(JOBHUNT_SELECTALL_PREMIUM, new JobHuntPostPremiumSellectAllRowMapper());
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
			if (jobHuntPostDTO.getSearchCondition().equals("selectOneJobHuntPost")) {
				result = jdbcTemplate.queryForObject(SELECTONE, args, new JobHuntPostSellectOneRowMapper());
				System.out.println("JobHuntPostDAO(selectOne) 로그 = [" + result + "]");
				return result;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public boolean insert(JobHuntPostDTO jobHuntPostDTO) {
		int result = jdbcTemplate.update(INSERT,jobHuntPostDTO.getMemberId(),jobHuntPostDTO.getJobHuntPostRole(),jobHuntPostDTO.getJobHuntPostRegion(),jobHuntPostDTO.getJobHuntPostPay(),jobHuntPostDTO.getJobHuntPostWorkdate(),jobHuntPostDTO.getJobHuntPostConcept(),jobHuntPostDTO.getJobHuntPostTitle(),jobHuntPostDTO.getJobHuntPostContent(),jobHuntPostDTO.getJobHuntPostViewcnt());
		if (result <= 0) {
			return false;
		}
		System.out.println("JobHuntPostDAO(insert) 로그 = [" + result + "]");
		return true;
	}

	public boolean update(JobHuntPostDTO jobHuntPostDTO) {
		int result = 0;
		if (jobHuntPostDTO.getSearchCondition().equals("jobHuntPostViewcntUpdate")) {
			result = jdbcTemplate.update(UPDATE_VIEWCNT,jobHuntPostDTO.getJobHuntPostViewcnt());
			System.out.println("JobHuntPostDAO(viewcntUpdate) 로그 = [" + result + "]");
		} else if(jobHuntPostDTO.getSearchCondition().equals("jobHuntPostUpdate")) {
			result = jdbcTemplate.update(UPDATE,jobHuntPostDTO.getJobHuntPostRole(),jobHuntPostDTO.getJobHuntPostRegion(),jobHuntPostDTO.getJobHuntPostPay(),jobHuntPostDTO.getJobHuntPostWorkdate(),jobHuntPostDTO.getJobHuntPostConcept(),jobHuntPostDTO.getJobHuntPostTitle(),jobHuntPostDTO.getJobHuntPostContent());
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

class JobHuntPostSellecAllRowMapper implements RowMapper<JobHuntPostDTO> {

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));
		data.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content"));
		data.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt"));

		return data;
	}
}

class JobHuntPostSellectOneRowMapper implements RowMapper<JobHuntPostDTO> {

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setJobHuntPostDate(rs.getString("JOBHUNTPOST_date"));
		data.setJobHuntPostRole(rs.getString("JOBHUNTPOST_role"));
		data.setJobHuntPostRegion(rs.getString("JOBHUNTPOST_region"));
		data.setJobHuntPostPay(rs.getString("JOBHUNTPOST_pay"));
		data.setJobHuntPostConcept(rs.getString("JOBHUNTPOST_workDate"));
		data.setJobHuntPostConcept(rs.getString("JOBHUNTPOST_concept"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));
		data.setJobHuntPostContent(rs.getString("JOBHUNTPOST_content"));
		data.setJobHuntPostViewcnt(rs.getString("JOBHUNTPOST_viewcnt"));

		return data;
	}
}

class JobHuntPostPremiumSellectAllRowMapper implements RowMapper<JobHuntPostDTO>{

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));

		return data;
	}
	
}