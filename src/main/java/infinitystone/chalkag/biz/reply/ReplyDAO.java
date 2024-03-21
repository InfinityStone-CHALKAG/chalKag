package infinitystone.chalkag.biz.reply;

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

  private static final String SELECTALL = "SELET REPLY.REPLY_id," +
      "REPLY.MEMBER_id," +
      "MEMBER_nickname," +
      "REPLY.REPLY_content," +
      "REPLY.REPLY_date" +
      "FROM REPLY" +
      "INNER JOIN MEMBER ON REPLY.MEMBER_id = MEMBER.MEMBER_id" +
      "WHERE REPLY.COMMENT_id = ?";

  private static final String SELECTONE = "";

  private static final String INSERT = "INSERT INTO REPLY (COMMENT_id," +
      "MEMBER_id," +
      "REPLY_content)" +
      "VALUES (?,?,?)";

  private static final String UPDATE = "UPDATE REPLY" +
      "SET REPLY_content = ?" +
      "WHERE REPLY_id = ?";

  private static final String DELETE = "DELETE" +
      "FROM REPLY" +
      "WHERE REPLY_id = ?";

  public List<ReplyDTO> selectAll(ReplyDTO replyDTO) {
    try {
      return (List<ReplyDTO>) jdbcTemplate.queryForObject(SELECTALL, new ReplyRowMapper());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ReplyDTO selectOne(ReplyDTO replyDTO) {
    return null;
  }

  public boolean insert(ReplyDTO replyDTO) {
    if (jdbcTemplate.update(INSERT) <= 0) {
      return false;
    }
    return true;
  }

  public boolean update(ReplyDTO replyDTO) {
    if (jdbcTemplate.update(UPDATE) <= 0) {
      return false;
    }
    return true;
  }

  public boolean delete(ReplyDTO replyDTO) {
    if (jdbcTemplate.update(DELETE) <= 0) {
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
    replyDTO.setReplyContent(rs.getString("REPLY_content"));
    replyDTO.setReplyDate(rs.getString("REPLY_date"));
    return replyDTO;
  }
}
