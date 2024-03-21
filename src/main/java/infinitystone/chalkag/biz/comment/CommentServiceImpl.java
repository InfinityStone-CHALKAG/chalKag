package infinitystone.chalkag.biz.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public List<CommentDTO> selectAll(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return commentDAO.selectAll(commentDTO);
	}

	@Override
	public CommentDTO selectOne(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return commentDAO.selectOne(commentDTO);
	}

	@Override
	public boolean insert(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return commentDAO.insert(commentDTO);
	}

	@Override
	public boolean update(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return commentDAO.update(commentDTO);
	}

	@Override
	public boolean delete(CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		return commentDAO.delete(commentDTO);
	}

}
