package infinitystone.chalkag.biz.headHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("headHuntPostService")
public class HeadHuntPostServiceImpl implements HeadHuntPostService{

	@Autowired
	private HeadHuntPostDAO headHuntPostDAO;
	
	@Override
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) {
		return headHuntPostDAO.selectAll(headHuntPostDTO);
	}

	@Override
	public HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO) {
		return headHuntPostDAO.selectOne(headHuntPostDTO);
	}

	@Override
	public boolean insert(HeadHuntPostDTO headHuntPostDTO) {
		return headHuntPostDAO.insert(headHuntPostDTO);
	}

	@Override
	public boolean update(HeadHuntPostDTO headHuntPostDTO) {
		return headHuntPostDAO.update(headHuntPostDTO);
	}

	@Override
	public boolean delete(HeadHuntPostDTO headHuntPostDTO) {
		return  headHuntPostDAO.delete(headHuntPostDTO);
	}

}
