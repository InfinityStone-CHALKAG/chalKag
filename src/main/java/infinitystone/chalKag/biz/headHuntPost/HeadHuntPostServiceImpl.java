package infinitystone.chalKag.biz.headHuntPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("headHuntPostService")
public class HeadHuntPostServiceImpl implements HeadHuntPostService{

	@Autowired
	private HeadHuntPostDAO headHuntPostDAO;
	
	@Autowired
	private IHeadHuntPostDAO iHeadHuntPostDAO;
	
	@Override
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("headHuntPostId", headHuntPostDTO.getHeadHuntPostId());
		map.put("headHuntPostTitle", headHuntPostDTO.getHeadHuntPostTitle());
		map.put("headHuntPostContent", headHuntPostDTO.getHeadHuntPostContent());
		map.put("titleAndContents", headHuntPostDTO.getTitleAndContents());
		map.put("headHuntPostRole", headHuntPostDTO.getHeadHuntPostRole());
		map.put("headHuntPostRegion", headHuntPostDTO.getHeadHuntPostRegion());
		map.put("minPay", headHuntPostDTO.getMinPay());
		map.put("maxPay", headHuntPostDTO.getMaxPay());
		map.put("headHuntPostConcept", headHuntPostDTO.getHeadHuntPostConcept());
		map.put("startWorkDate", headHuntPostDTO.getStartWorkDate());
		map.put("endWorkDate", headHuntPostDTO.getEndWorkDate());
		return iHeadHuntPostDAO.selectAll(map);
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
