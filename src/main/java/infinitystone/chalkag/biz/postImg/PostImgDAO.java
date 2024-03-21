package infinitystone.chalkag.biz.postImg;

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

	private static final String SELECTALL = "SELECT POSTIMG_name FROM POSTIMG WHERE POST_id = ? ";
	private static final String SELECTONE = "";
	private static final String INSERT = "INSERT INTO POSTIMG (POST_id, POSTIMG_name) VALUES (?, ?)";
	private static final String UPDATE = "";
	private static final String DELETE = "DELETE FROM POSTIMG WHERE POST_id = ? AND MEMBER_id = ? ";

	public List<PostImgDTO> selectAll(PostImgDTO postImgDTO) {
		List<PostImgDTO> result = null;
		try {
			result = (List<PostImgDTO>) jdbcTemplate.query(SELECTALL, new PostImgRowMapper());
			System.out.println("PostImgDAO(selectAll) 로그 = [" + result + "]");
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
		int result = jdbcTemplate.update(INSERT);
		System.out.println("PostImgDAO(insert) Out로그 = [" + result + "]");
		if (result <= 0) {
			return false;
		}

		return true;
	}

	public boolean update(PostImgDTO postImgDTO) {
		int result = jdbcTemplate.update(UPDATE);
		if (result <= 0) {
			return false;
		}

		return true;
	}

	public boolean delete(PostImgDTO postImgDTO) {
		int result = jdbcTemplate.update(DELETE);
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
