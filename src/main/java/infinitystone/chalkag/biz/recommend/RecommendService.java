package infinitystone.chalkag.biz.recommend;

import java.util.List;

public interface RecommendService {
	
	List<RecommendDTO> selectAll(RecommendDTO recommendDTO);
	
	RecommendDTO selectOne(RecommendDTO recommendDTO);
	
	boolean insert(RecommendDTO recommendDTO);
	
	boolean update(RecommendDTO recommendDTO);

	boolean delete(RecommendDTO rcommendDTO);

}
