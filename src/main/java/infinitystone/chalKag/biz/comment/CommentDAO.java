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
	private static final String SELECTALL_JOBHUNTPOST_COMMENT = "SELECT " 
			+ "    COMMENT.COMMENT_id, "
			+ "    COMMENT.POST_id," 
			+ "    COMMENT.MEMBER_id, "
			+ "    DATE_FORMAT(COMMENT.COMMENT_date, '%Y-%m-%d %H:%i') AS COMMENT_date, "
			+ "    COMMENT.COMMENT_content, " 
			+ "    PROFILEIMG.PROFILEIMG_name " 
			+ " FROM " 
			+ "    COMMENT " 
			+ " JOIN "
			+ "    JOBHUNTPOST ON COMMENT.POST_id = JOBHUNTPOST.JOBHUNTPOST_id " 
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON COMMENT.MEMBER_id = PROFILEIMG.MEMBER_id "
			+ " JOIN "
			+ "    MEMBER ON COMMENT.MEMBER_id = MEMBER.MEMBER_id " 
			+ " WHERE " 
			+ "    COMMENT.POST_id=? "
			+ " GROUP BY " 
			+ "		COMMENT.COMMENT_id, " 
			+ " 	PROFILEIMG.PROFILEIMG_name" 
			+ " ORDER BY "
			+ "    COMMENT.COMMENT_date DESC ";

	private static final String SELECTALL_HEADHUNTPOST_COMMENT = "SELECT  " 
			+ "    COMMENT.COMMENT_id, "
			+ "    COMMENT.POST_id, " 
			+ "    COMMENT.MEMBER_id, "
			+ "    DATE_FORMAT(COMMENT.COMMENT_date, '%Y-%m-%d %H:%i') AS COMMENT_date, "
			+ "    COMMENT.COMMENT_content, " 
			+ "    PROFILEIMG.PROFILEIMG_name " 
			+ " FROM " 
			+ "    COMMENT " 
			+ " JOIN "
			+ "    HEADHUNTPOST ON COMMENT.POST_id = HEADHUNTPOST.FREEPOST_id " 
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON COMMENT.MEMBER_id = PROFILEIMG.MEMBER_id " 
			+ " JOIN "
			+ "    MEMBER ON COMMENT.MEMBER_id = MEMBER.MEMBER_id " 
			+ " WHERE " 
			+ "    COMMENT.POST_id = ? "
			+ " GROUP BY " 
			+ "    COMMENT.COMMENT_id, " 
			+ "    PROFILEIMG.PROFILEIMG_name " 
			+ " ORDER BY "
			+ "    COMMENT.COMMENT_date DESC ";
	
	
	
	
	private static final String SELECTALL_FREEPOST_COMMENT = "SELECT  " 
			+ "    COMMENT.COMMENT_id, "
			+ "    COMMENT.POST_id, " 
			+ "    COMMENT.MEMBER_id, "
			+ "    DATE_FORMAT(COMMENT.COMMENT_date, '%Y-%m-%d %H:%i') AS COMMENT_date, "
			+ "    COMMENT.COMMENT_content, " 
			+ "    PROFILEIMG.PROFILEIMG_name " 
			+ " FROM " 
			+ "    COMMENT " 
			+ " JOIN "
			+ "    FREEPOST ON COMMENT.POST_id = FREEPOST.FREEPOST_id " 
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON COMMENT.MEMBER_id = PROFILEIMG.MEMBER_id "
			+ " JOIN "
			+ "    MEMBER ON COMMENT.MEMBER_id = MEMBER.MEMBER_id " 
			+ " WHERE " 
			+ "    COMMENT.POST_id = ? "
			+ " GROUP BY " 
			+ "    COMMENT.COMMENT_id, " 
			+ "    PROFILEIMG.PROFILEIMG_name " 
			+ " ORDER BY "
			+ "    COMMENT.COMMENT_date DESC ";

	private static final String SELECTALL_MARKETPOST_COMMENT = "SELECT  " 
			+ "    COMMENT.COMMENT_id, "
			+ "    COMMENT.POST_id, " 
			+ "    COMMENT.MEMBER_id, "
			+ "    DATE_FORMAT(COMMENT.COMMENT_date, '%Y-%m-%d %H:%i') AS COMMENT_date, "
			+ "    COMMENT.COMMENT_content, " 
			+ "    PROFILEIMG.PROFILEIMG_name "
			+ " FROM " 
			+ "    COMMENT " 
			+ " JOIN "
			+ "    FREEPOST ON COMMENT.POST_id = FREEPOST.FREEPOST_id " 
			+ " LEFT JOIN "
			+ "    PROFILEIMG ON COMMENT.MEMBER_id = PROFILEIMG.MEMBER_id " 
			+ " JOIN "
			+ "    MEMBER ON COMMENT.MEMBER_id = MEMBER.MEMBER_id " 
			+ " WHERE " 
			+ "    COMMENT.POST_id = ? "
			+ " GROUP BY " 
			+ "    COMMENT.COMMENT_id, " 
			+ "    PROFILEIMG.PROFILEIMG_name " 
			+ " ORDER BY "
			+ "    COMMENT.COMMENT_date DESC ";
	
	
	
	// 대댓글할 때 사용할 댓글 셀렉트원
	private static final String SELECTONE = "SELECT "
			+ "    COMMENT.COMMENT_id, "
			+ "    COMMENT.POST_id, "
			+ "    COMMENT.MEMBER_id, "
			+ "    DATE_FORMAT(COMMENT.COMMENT_date, '%Y-%m-%d %H:%i') AS COMMENT_date, "
			+ "    COMMENT.COMMENT_content, "
			+ "    PROFILEIMG.PROFILEIMG_name "
			+ "FROM "
			+ "    COMMENT "
			+ "LEFT JOIN "
			+ "    HEADHUNTPOST ON COMMENT.POST_id = HEADHUNTPOST.HEADHUNTPOST_id "
			+ "LEFT JOIN "
			+ "    JOBHUNTPOST ON COMMENT.POST_id = JOBHUNTPOST.JOBHUNTPOST_id "
			+ "LEFT JOIN "
			+ "    MARKETPOST ON COMMENT.POST_id = MARKETPOST.MARKETPOST_id "
			+ "LEFT JOIN "
			+ "    FREEPOST ON COMMENT.POST_id = FREEPOST.FREEPOST_id "
			+ "LEFT JOIN "
			+ "    PROFILEIMG ON COMMENT.MEMBER_id = PROFILEIMG.MEMBER_id "
			+ "JOIN "
			+ "    MEMBER ON COMMENT.MEMBER_id = MEMBER.MEMBER_id "
			+ "WHERE "
			+ "    COMMENT.POST_id = ? AND COMMENT.COMMENT_id = ? "
			+ "GROUP BY "
			+ "    COMMENT.COMMENT_id, "
			+ "    PROFILEIMG.PROFILEIMG_name "
			+ "ORDER BY "
			+ "    COMMENT.COMMENT_date DESC ";

	private static final String INSERT = "INSERT INTO COMMENT (POST_id, MEMBER_id, COMMENT_content)"
			+ "VALUES (?, ?, ?)";

	private static final String UPDATE = "UPDATE COMMENT SET COMMENTCONTENT = ? " + "WHERE COMMENTID = ?";

	private static final String DELETE = "DELETE FROM COMMENT WHERE COMMENTID = ?";

	public List<CommentDTO> selectAll(CommentDTO commentDTO) {
		List<CommentDTO> result = null;
		try {
			if (commentDTO.getSearchCondition().equals("jobHuntPostSelectAllComment")) {
				result = (List<CommentDTO>) jdbcTemplate.query(SELECTALL_JOBHUNTPOST_COMMENT,
						new CommentSellectAllRowMapper());
				System.out.println("commentDTO(jobHuntPostSelectAll) 로그 =" + "[" + result + "]");
				return result;
			} else if (commentDTO.getSearchCondition().equals("headHuntPostSelectAllComment")) {
				result = (List<CommentDTO>) jdbcTemplate.query(SELECTALL_HEADHUNTPOST_COMMENT,
						new CommentSellectAllRowMapper());
				System.out.println("commentDTO(freePostSelectAll) 로그 =" + "[" + result + "]");
				return result;
			} else if (commentDTO.getSearchCondition().equals("freePostSelectAllComment")) {
				result = (List<CommentDTO>) jdbcTemplate.query(SELECTALL_FREEPOST_COMMENT,
						new CommentSellectAllRowMapper());
				System.out.println("commentDTO(freePostSelectAll) 로그 =" + "[" + result + "]");
				return result;
			} else if (commentDTO.getSearchCondition().equals("marketPostSelectAllComment")) {
				result = (List<CommentDTO>) jdbcTemplate.query(SELECTALL_MARKETPOST_COMMENT,
						new CommentSellectAllRowMapper());
				System.out.println("commentDTO(marketPostSelectAll) 로그 =" + "[" + result + "]");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public CommentDTO selectOne(CommentDTO commentDTO) {

		CommentDTO result = null;
		Object[] args = { commentDTO.getCommentId() };
		try {
			result = jdbcTemplate.queryForObject(SELECTONE, args, new CommentSellectOneRowMapper());
			System.out.println("commentDTO(selectOne) 로그 =" + "[" + result + "]");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

class CommentSellectAllRowMapper implements RowMapper<CommentDTO> {

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

class CommentSellectOneRowMapper implements RowMapper<CommentDTO> {

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
