package infinitystone.chalKag.biz.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("replyDAO")
public class ReplyDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String SELECTALL = "SELECT  "
  		+ "    REPLY.REPLY_id, "
  		+ "    REPLY.MEMBER_id,  "
  		+ "    MEMBER.MEMBER_nickname,  "
  		+ "    REPLY.REPLY_content,  "
  		+ "    REPLY.REPLY_date,  "
  		+ "    REPLY.COMMENT_id, "
  		+ "    ( "
  		+ "        SELECT   "
  		+ "        PROFILEIMG.PROFILEIMG_name  "
  		+ "        FROM   "
  		+ "        PROFILEIMG  "
  		+ "        WHERE   "
  		+ "        PROFILEIMG.MEMBER_id = REPLY.MEMBER_id  "
  		+ "        ORDER BY   "
  		+ "        PROFILEIMG.PROFILEIMG_id DESC  "
  		+ "        LIMIT 1  "
  		+ "    ) AS PROFILEIMG_name  "
  		+ "FROM  "
  		+ "    REPLY "
  		+ "INNER JOIN  "
  		+ "    MEMBER ON REPLY.MEMBER_id = MEMBER.MEMBER_id "
  		+ "WHERE  "
  		+ "    REPLY.COMMENT_id = ?";

  private static final String SELECTONE = "";

  private static final String INSERT = "INSERT INTO REPLY (COMMENT_id, " +
      "MEMBER_id, " +
      "REPLY_content) " +
      "VALUES (?,?,?)";

  private static final String UPDATE = "UPDATE REPLY " +
      "SET REPLY_content = ? " +
      "WHERE REPLY_id = ?";

  private static final String DELETE = "DELETE " +
      "FROM REPLY " +
      "WHERE REPLY_id = ?";

  public List<ReplyDTO> selectAll(ReplyDTO replyDTO) {
	  List<ReplyDTO> result = null;
	  Object[] args = {replyDTO.getCommentId()};
    try {
    	result=  (List<ReplyDTO>) jdbcTemplate.query(SELECTALL, args,new ReplyRowMapper());
      return result; 
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ReplyDTO selectOne(ReplyDTO replyDTO) {
    return null;
  }

  public boolean insert(ReplyDTO replyDTO) {
	  int result = jdbcTemplate.update(INSERT,replyDTO.getCommentId(),replyDTO.getMemberId(),replyDTO.getReplyContent());
    if (result <= 0) {
      return false;
    }
    return true;
  }

  public boolean update(ReplyDTO replyDTO) {
    if (jdbcTemplate.update(UPDATE,replyDTO.getReplyContent(),replyDTO.getReplyId()) <= 0) {
      return false;
    }
    return true;
  }

  public boolean delete(ReplyDTO replyDTO) {
    if (jdbcTemplate.update(DELETE,replyDTO.getReplyId()) <= 0) {
      return false;
    }
    return true;
  }

}

class ReplyRowMapper implements RowMapper<ReplyDTO> {
  @Override
  public ReplyDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    ReplyDTO replyDTO = new ReplyDTO();
    replyDTO.setReplyId(rs.getString("REPLY_id"));
    replyDTO.setCommentId(rs.getString("COMMENT_id"));
    replyDTO.setMemberId(rs.getString("MEMBER_id"));
    replyDTO.setMemberNickname(rs.getString("MEMBER_nickname"));
    replyDTO.setProfileImgName(rs.getString("PROFILEIMG_name"));
    replyDTO.setReplyContent(rs.getString("REPLY_content"));
    replyDTO.setReplyDate(rs.getString("REPLY_date"));
    return replyDTO;
  }
}
