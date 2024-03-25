package infinitystone.chalKag.biz.postImg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("postImgDAO")
public class PostImgDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
								// 해당 카테고리 전체 출력 (이미지만)
	private static final String SELECTALL_HEADHUNTPOSTIMG = "SELECT POSTIMG.POSTIMG_name, HEADHUNTPOST.HEADHUNTPOST_id "
			+ "FROM POSTIMG "
			// inner or left?
			+ "INNER JOIN HEADHUNTPOST ON POSTIMG.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	private static final String SELECTALL_JOBHUNTPOSTIMG = "SELECT POSTIMG.POSTIMG_name, JOBHUNTPOST.JOBHUNTPOST_id "
			+ "FROM POSTIMG "
			+ "INNER JOIN JOBHUNTPOST ON POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	private static final String SELECTALL_FREEPOSTIMG = "SELECT POSTIMG.POSTIMG_name, FREEPOST.FREEPOST_id "
			+ "FROM POSTIMG "
			+ "INNER JOIN FREEPOST ON POSTIMG.POST_id = FREEPOST.FREEPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	private static final String SELECTALL_MARKETPOSTIMG = "SELECT POSTIMG.POSTIMG_name, MARKETPOST.MARKETPOST_id "
			+ "FROM POSTIMG "
			+ "INNER JOIN MARKETPOST ON POSTIMG.POST_id = MARKETPOST.MARKETPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	// --------------------------------------------------------------------------------------------------------------------
	// 프리미엄 회원 작성글 이미지 불러오기
	private static final String SELECTALL_PREMIUMHEADHUNTPOSTIMG = "SELECT POSTIMG.POSTIMG_name, HEADHUNTPOST.HEADHUNTPOST_id "
			+ "FROM POSTIMG "
			+ "INNER JOIN HEADHUNTPOST ON POSTIMG.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
			+ "INNER JOIN MEMBER ON MEMBER.MEMBER_id = HEADHUNTPOST.MEMBER_id "
			+ "WHERE MEMBER.MEMBER_grade = 'PREMIUM' "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	private static final String SELECTALL_PREMIUMJOBHUNTPOSTIMG = "SELECT POSTIMG.POSTIMG_name, JOBHUNTPOST.JOBHUNTPOST_id "
			+ " FROM POSTIMG "
			+ "	LEFT JOIN JOBHUNTPOST ON POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
			+ "	INNER JOIN MEMBER ON MEMBER.MEMBER_id = JOBHUNTPOST.MEMBER_id "
			+ "	WHERE MEMBER.MEMBER_grade = 'PREMIUM' "
			+ "	ORDER BY POSTIMG.POSTIMG_id DESC "
			+ " LIMIT 1 ";
	private static final String SELECTALL_PREMIUMFREEPOSTIMG = "SELECT POSTIMG.POSTIMG_name, FREEPOST.FREEPOST_id "
			+ " FROM POSTIMG "
			+ "	INNER JOIN FREEPOST ON POSTIMG.POST_id = FREEPOST.FREEOST_id "
			+ "	INNER JOIN MEMBER ON MEMBER.MEMBER_id = FREEPOST.MEMBER_id "
			+ "	WHERE MEMBER.MEMBER_grade = 'PREMIUM' "
			+ "	ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "	LIMIT 1";
	private static final String SELECTALL_PREMIUMMARKETPOSTIMG = "SELECT POSTIMG.POSTIMG_name, MARKETPOST.MARKETPOST_id "
			+ "	FROM POSTIMG "
			+ "	INNER JOIN MARKETPOST ON POSTIMG.POST_id = MARKETPOST.MARKETPOST_id "
			+ "	INNER JOIN MEMBER ON MEMBER.MEMBER_id = MARKETPOST.MEMBER_id "
			+ "	WHERE MEMBER.MEMBER_grade = 'PREMIUM' "
			+ "	ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "	LIMIT 1";
	
	
	private static final String SELECTONE = "";
	private static final String INSERT = "INSERT INTO POSTIMG (POST_id, POSTIMG_name) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE POSTIMG"
										+ "SET"
										+ "POSTIMG_name = ?"
										+ "WHERE"
										+ "	POST_id =? ";
	private static final String DELETE = "DELETE FROM POSTIMG WHERE POST_id = ? AND POSTIMG_name = ? ";

	public List<PostImgDTO> selectAll(PostImgDTO postImgDTO) {
		List<PostImgDTO> result = null;
		try {
					// 구직글 전체 불러오기 이미지
			if(postImgDTO.getSearchCondition().equals("headHuntPostListImg")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_HEADHUNTPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(headHuntPostImgSellectAll) 로그 = [" + result + "]");
				
			}
					// 구인글 전체 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("jobHuntPostListImg")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_JOBHUNTPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(jobHuntPostImgSellectAll) 로그 = [" + result + "]");
			}
					// 자유글 전체 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("freePostListImg")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_FREEPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(freePostImgSellectAll) 로그 = [" + result + "]");
			}
					// 장터글 전체 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("marketPostListImg")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(marektPostImgSellectAll) 로그 = [" + result + "]");
	 		}
					// 프리미엄 회원 구인글 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("headHuntPostPremiumListImg")){
	 			result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_PREMIUMHEADHUNTPOSTIMG, new PostImgRowMapper());
	 			System.out.println("PostImgDAO(headHuntPostPremiumImgSellectAll) 로그 = [" + result + "]");
			}
			
					// 프리미엄 회원 구직글 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("jobHuntPostPremiumListImg")){
	 			result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_PREMIUMJOBHUNTPOSTIMG, new PostImgRowMapper());
	 			System.out.println("PostImgDAO(jobHuntPostPremiumImgSellectAll) 로그 = [" + result + "]");
			}
					// 프리미엄 회원 자유글 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("freePostPremiumListImg")){
	 			result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_PREMIUMFREEPOSTIMG, new PostImgRowMapper());
	 			System.out.println("PostImgDAO(freePostPremiumImgSellectAll) 로그 = [" + result + "]");
			}
					// 프리미엄 회원 장터글 불러오기 이미지
			else if(postImgDTO.getSearchCondition().equals("marketPostPremiumListImg")){
	 			result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_PREMIUMMARKETPOSTIMG, new PostImgRowMapper());
	 			System.out.println("PostImgDAO(marketPostPremiumImgSellectAll) 로그 = [" + result + "]");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public PostImgDTO selectOne(PostImgDTO postImgDTO) {

		return null;
	}

	public boolean insert(PostImgDTO postImgDTO) {
		int result = jdbcTemplate.update(INSERT,postImgDTO.getPostImgId(),postImgDTO.getPostImgName());
		System.out.println("PostImgDAO(insert) Out로그 = [" + result + "]");
		if (result <= 0) {
			return false;
		}

		return true;
	}

	public boolean update(PostImgDTO postImgDTO) {
		int result = jdbcTemplate.update(UPDATE,postImgDTO.getPostImgName(),postImgDTO.getPostImgId());
		if (result <= 0) {
			return false;
		}

		return true;
	}

	public boolean delete(PostImgDTO postImgDTO) {
		int result = jdbcTemplate.update(DELETE, postImgDTO.getPostImgId(),postImgDTO.getPostImgName());
		if (result <= 0) {
			return false;
		}

		return true;
	}

}

class PostImgRowMapper implements RowMapper<PostImgDTO> {

	@Override
	public PostImgDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		PostImgDTO data = new PostImgDTO();

		data.setPostImgId(rs.getString("POSTIMG_id"));
		data.setPostImgName(rs.getString("POSTIMG_name"));

		return data;
	}

}
