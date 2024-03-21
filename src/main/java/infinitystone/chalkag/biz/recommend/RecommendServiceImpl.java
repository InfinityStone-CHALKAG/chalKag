package infinitystone.chalkag.biz.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService{

	@Autowired
	private RecommendDAO recommendDAO;
	
	@Override
	public List<RecommendDTO> selectAll(RecommendDTO recommendDTO) {
		return recommendDAO.selectAll(recommendDTO);
	}

	@Override
	public RecommendDTO selectOne(RecommendDTO recommendDTO) {
		return recommendDAO.selectOne(recommendDTO);
	}

	@Override
	public boolean insert(RecommendDTO recommendDTO) {
		return recommendDAO.insert(recommendDTO);
	}

	@Override
	public boolean update(RecommendDTO recommendDTO) {
		return recommendDAO.update(recommendDTO);
	}

	@Override
	public boolean delete(RecommendDTO recommendDTO) {
		return recommendDAO.delete(recommendDTO);
	}

}
