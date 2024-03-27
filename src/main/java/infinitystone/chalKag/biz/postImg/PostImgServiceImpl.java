package infinitystone.chalKag.biz.postImg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postImgService")
public class PostImgServiceImpl implements PostImgService{

	
	@Autowired
	private PostImgDAO postImgDAO;
	
	@Override
	public List<PostImgDTO> selectAll(PostImgDTO postImgDTO) {
		// TODO Auto-generated method stub
		return postImgDAO.selectAll(postImgDTO);
	}

	@Override
	public PostImgDTO selectOne(PostImgDTO postImgDTO) {
		// TODO Auto-generated method stub
		return postImgDAO.selectOne(postImgDTO);
	}

	@Override
	public boolean insert(PostImgDTO postImgDTO) {
		// TODO Auto-generated method stub
		return postImgDAO.insert(postImgDTO);
	}

	@Override
	public boolean update(PostImgDTO postImgDTO) {
		// TODO Auto-generated method stub
		return postImgDAO.update(postImgDTO);
	}

	@Override
	public boolean delete(PostImgDTO postImgDTO) {
		// TODO Auto-generated method stub
		return postImgDAO.delete(postImgDTO);
	}

}
