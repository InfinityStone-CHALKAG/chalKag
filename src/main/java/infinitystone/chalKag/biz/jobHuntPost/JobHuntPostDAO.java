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

	private static final String SELECTALL_JOBHUNTPOST ="SELECT "
			+ "		'JOBHuntPost' AS POST_category, " // 게시판 카테고리 설정 
			+ "		JOBHUNTPOST.JOBHUNTPOST_id, "
			+ "		JOBHUNTPOST.MEMBER_id, "
			+ "			( " 							// 게시글의 대표 이미지를 설정하는 서브 쿼리문 
			+ "				SELECT "
			+ "					POSTIMG.POSTIMG_name " 	// 구직글의 게시글 이미지를 선택 
			+ "				FROM  "
			+ "					POSTIMG " 				// 게시글 이미지 테이블에서 가져옴 
			+ "				WHERE  "
			+ "					POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 해당 구직글과 연관된 이미지 
			+ "				ORDER BY  "
			+ "					POSTIMG.POSTIMG_id ASC " 	// 게시글의 이미지 아이디 기준으로 오름차순 정렬 
			+ "				LIMIT 1 " 						// 최대 1개의 이미지만 가져오도록 설정 
			+ "			 ) AS POSTIMG_name, " 				// 가져온 대표 이미지의 이름을 "POSTIMG_name"로 칭함 
			+ "		    MEMBER.MEMBER_nickname, "
			+ "		    JOBHUNTPOST.JOBHUNTPOST_title, "
			+ "		    JOBHUNTPOST.JOBHUNTPOST_content, "
			+ "		    JOBHUNTPOST.JOBHUNTPOST_date, "
			+ "		    JOBHUNTPOST.JOBHUNTPOST_viewcnt, "
			+ "			 ( "
			+ "				SELECT "
			+ "		            COUNT(*) " 					// 해당 구직글에 대한 좋아요 수를 COUNT 함수를 사용해 합산 
			+ "		        FROM  "
			+ "		            RECOMMEND " 				// 좋아요 테이블에서 가져옴 
			+ "		        WHERE  "
			+ "		             RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " // 해당 구직글과 연관된 좋아요 수 
			+ "		    ) AS RECOMMEND_cnt " 				// 좋아요 수를 \"RECOMMEND_cnt\"로 칭함 "
			+ "			FROM  "
			+ "				JOBHUNTPOST "					 // 구직글 테이블에서 가져옴 "
			+ "		  	INNER JOIN  "
			+ "				MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id " // 회원 정보와 INNER JOIN 
			+ "		  	LEFT JOIN "
			+ "		  		RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id " // 좋아요 정보와 LEFT JOIN 
			+ "		  	GROUP BY "
			+ "		  		JOBHUNTPOST.JOBHUNTPOST_id, " 	// 구직글 아이디로 그룹화 
			+ "		  		MEMBER.MEMBER_nickname " 		// 회원 닉네임으로 그룹화 
			+ "		  	ORDER BY "
			+ "		 		JOBHUNTPOST.JOBHUNTPOST_id DESC";
	

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

	
	private static final String SELECTONE_JOBHUNTPOST = "SELECT "
			+ "			'JobHuntPost' AS POST_category,"    
			+ "		    JOBHUNTPOST.jobHUNTPOST_id, "
			+ "		    JOBHUNTPOST.MEMBER_id, "
			+ "		    MEMBER.MEMBER_nickname, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_title, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_content, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_role, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_region, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_pay,"
			+ "		    JOBHUNTPOST.jobHUNTPOST_workDate, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_concept, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_date, "
			+ "		    JOBHUNTPOST.jobHUNTPOST_viewcnt, "
			+ "		    (  "
			+ "		        SELECT "
			+ "		            PROFILEIMG.PROFILEIMG_name "
			+ "		        FROM "
			+ "		            PROFILEIMG "
			+ "		        WHERE "
			+ "		            PROFILEIMG.MEMBER_id = jobHUNTPOST.MEMBER_id "
			+ "		        ORDER BY "
			+ "		            PROFILEIMG.PROFILEIMG_id DESC "
			+ "		        LIMIT 1 "
			+ "		    ) AS PROFILEIMG_name, "
			+ "		    (  "
			+ "		        SELECT "
			+ "		            COUNT(*) "
			+ "		        FROM "
			+ "		            RECOMMEND "
			+ "		        WHERE "
			+ "		            RECOMMEND.POST_id = JOBHUNTPOST.JOBHUNTPOST_id  "
			+ "		    ) AS RECOMMEND_cnt  "
			+ "		FROM "
			+ "		    jobHUNTPOST "
			+ "		INNER JOIN "
			+ "		    MEMBER ON JOBHUNTPOST.MEMBER_id = MEMBER.MEMBER_id "
			+ "		LEFT JOIN "
			+ "		    RECOMMEND ON JOBHUNTPOST.JOBHUNTPOST_id = RECOMMEND.POST_id  "
			+ "		WHERE "
			+ "		    JOBHUNTPOST.JOBHUNTPOST_id = ? ";
	
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
				System.out.println("JobHuntPostDAO(selectOne) Out로그 = [" + result + "]");
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
// 전체보기 출력
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

// 상세보기 출력
class JobHuntPostOneRowMapper implements RowMapper<JobHuntPostDTO> {

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setPostCategory(rs.getString("POST_category"));
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
//		data.setPostImgName(rs.getString("POSTIMG_name"));
		
		return data;
	}
}

// 메인페이지 프리미엄 회원 전체 출력
class JobHuntPostPremiumSelectAllRowMapper implements RowMapper<JobHuntPostDTO>{

	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();

		data.setJobHuntPostId(rs.getString("JOBHUNTPOST_id"));
		data.setJobHuntPostTitle(rs.getString("JOBHUNTPOST_title"));

		return data;
	}
	
}

// 저장 시 최대값
class SelectOneMaxPostIdRowMapper implements RowMapper<JobHuntPostDTO> {
	@Override
	public JobHuntPostDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHuntPostDTO data = new JobHuntPostDTO();
		data.setJobHuntPostId(rs.getString("MAX(JOBHUNTPOST_id)"));
		System.out.println("RowMapper OUT");
		return data;
	}
}