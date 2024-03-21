package infinitystone.chalKag.biz.comment;

import java.util.List;

public interface CommentService {
	List<CommentDTO> selectAll(CommentDTO commentDTO);
	CommentDTO selectOne(CommentDTO commentDTO);
	
	boolean insert(CommentDTO commentDTO);
	boolean update(CommentDTO commentDTO);
	boolean delete(CommentDTO commentDTO);
	
}
