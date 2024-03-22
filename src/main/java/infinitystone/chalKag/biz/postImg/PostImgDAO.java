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

	private static final String SELECTALL_HEADHUNTPOSTIMG = "SELECT POSTIMG.POSTIMG_name, HEADHUNTPOST.HEADHUNTPOST_id "
			+ "FROM POSTIMG "
			+ "LEFT JOIN HEADHUNTPOST ON POSTIMG.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	private static final String SELECTALL_JOBHUNTPOSTIMG = "SELECT POSTIMG.POSTIMG_name, JOBHUNTPOST.JOBHUNTPOST_id "
			+ "FROM POSTIMG "
			+ "LEFT JOIN JOBHUNTPOST ON POSTIMG.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	private static final String SELECTALL_FREEPOSTIMG = "SELECT POSTIMG.POSTIMG_name, FREEHUNTPOST.FREEHUNTPOST_id "
			+ "FROM POSTIMG "
			+ "LEFT JOIN FREEHUNTPOST ON POSTIMG.POST_id = FREEHUNTPOST.FREEHUNTPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
	private static final String SELECTALL_MARKETPOSTIMG = "SELECT POSTIMG.POSTIMG_name, MARKETPOST.MARKETPOST_id "
			+ "FROM POSTIMG "
			+ "LEFT JOIN MARKETPOST ON POSTIMG.POST_id = MARKETPOST.MARKETPOST_id "
			+ "ORDER BY POSTIMG.POSTIMG_id DESC "
			+ "LIMIT 1";
	
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
			if(postImgDTO.getSearchCondition().equals("headHuntPostImgList")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_HEADHUNTPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(headHuntPostImgSellectAll) 로그 = [" + result + "]");
				
			}else if(postImgDTO.getSearchCondition().equals("jobHuntPostImgList")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_JOBHUNTPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(jobHuntPostImgSellectAll) 로그 = [" + result + "]");
			}else if(postImgDTO.getSearchCondition().equals("freePostImgList")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_FREEPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(freePostImgSellectAll) 로그 = [" + result + "]");
			}else if(postImgDTO.getSearchCondition().equals("marketPostImgList")) {
				result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL_MARKETPOSTIMG, new PostImgRowMapper());
				System.out.println("PostImgDAO(marektPostImgSellectAll) 로그 = [" + result + "]");
			}else {
				// 체크
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
