package infinitystone.chalKag.biz.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("commentDAO")
public class CommentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	// 댓글 전체 출력 댓글, 게시판,회원 조인 쿼리문
	private static final String SELECTALL = "SELECT "
			+ "    COMMENT.COMMENT_id, "
			+ "    COMMENT.POST_id, "
			+ "    COMMENT.MEMBER_id, "
			+ "    MEMBER.MEMBER_nickname, "
			+ "    COMMENT.COMMENT_date, "
			+ "    COMMENT.COMMENT_content, "
			+ "    ( "
			+ "        SELECT  "
			+ "        	PROFILEIMG.PROFILEIMG_name "
			+ "        FROM  "
			+ "        	PROFILEIMG "
			+ "        WHERE  "
			+ "        	PROFILEIMG.MEMBER_id = COMMENT.MEMBER_id "
			+ "        ORDER BY  "
			+ "        	PROFILEIMG.PROFILEIMG_id DESC "
			+ "        LIMIT 1 "
			+ "    ) AS PROFILEIMG_name "
			+ "FROM "
			+ "    COMMENT "
			+ "INNER JOIN "
			+ "    MEMBER ON COMMENT.MEMBER_id = MEMBER.MEMBER_id "
			+ "WHERE "
			+ "    COMMENT.POST_id = ?";

	
	// 대댓글할 때 사용할 댓글 셀렉트원
	private static final String SELECTONE = "";

	private static final String INSERT = "INSERT INTO COMMENT (POST_id, MEMBER_id, COMMENT_content)"
			+ "VALUES (?, ?, ?)";

	private static final String UPDATE = "UPDATE COMMENT SET COMMENT_content = ? " + "WHERE COMMENT_id = ?";

	private static final String DELETE = "DELETE FROM COMMENT WHERE COMMENT_id = ?";

	public List<CommentDTO> selectAll(CommentDTO commentDTO) {
		List<CommentDTO> result = null;
		Object[] args = { commentDTO.getPostId() };
		try {
				result = (List<CommentDTO>) jdbcTemplate.query(SELECTALL,args,new CommentSelectAllRowMapper());
				System.out.println("commentDTO(jobHuntPostSelectAll) 로그 =" + "[" + result + "]");
				return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CommentDTO selectOne(CommentDTO commentDTO) {

		return null;
		}

	public boolean insert(CommentDTO commentDTO) {
		System.out.println("commentDTO(insert) 로그 =" + "[" + commentDTO + "]");
		int result = jdbcTemplate.update(INSERT,commentDTO.getPostId(),commentDTO.getMemberId(),commentDTO.getCommentContent());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean update(CommentDTO commentDTO) {
		System.out.println("commentDTO(update) 로그 =" + "[" + commentDTO + "]");
		int result = jdbcTemplate.update(UPDATE,commentDTO.getCommentContent(),commentDTO.getCommentId());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(CommentDTO commentDTO) {
		System.out.println("commentDTO(delete) 로그 =" + "[" + commentDTO + "]");
		int result = jdbcTemplate.update(DELETE,commentDTO.getCommentId());
		if (result <= 0) {
			return false;
		}
		return true;
	}
}

class CommentSelectAllRowMapper implements RowMapper<CommentDTO> {

	@Override
	public CommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentDTO data = new CommentDTO();
		data.setPostId(rs.getString("POST_id"));
		data.setCommentId(rs.getString("COMMENT_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setProfileImgName(rs.getString("PROFILEIMG_name"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setCommentContent(rs.getString("COMMENT_content"));
		data.setCommentDate(rs.getString("COMMENT_date"));
		return data;
	}
}

class CommentSelectOneRowMapper implements RowMapper<CommentDTO> {

	@Override
	public CommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentDTO data = new CommentDTO();
		data.setPostId(rs.getString("POST_id"));
		data.setCommentId(rs.getString("COMMENT_id"));
		data.setMemberId(rs.getString("MEMBER_id"));
		data.setProfileImgName(rs.getString("PROFILEIMG_name"));
		data.setMemberNickname(rs.getString("MEMBER_nickname"));
		data.setCommentContent(rs.getString("COMMENT_content"));
		data.setCommentDate(rs.getString("COMMENT_date"));
		return data;
	}
}
