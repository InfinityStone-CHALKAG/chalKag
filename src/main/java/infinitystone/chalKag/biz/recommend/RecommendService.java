package infinitystone.chalKag.biz.recommend;

import java.util.List;

public interface RecommendService { // 종아요 컨트롤러에서 사용할 서비스 (게시글의 좋아요와 관련된 비즈니스 로직을 정의하는 인터페이스)
	
	List<RecommendDTO> selectAll(RecommendDTO recommendDTO);
	
	RecommendDTO selectOne(RecommendDTO recommendDTO);
	
	boolean insert(RecommendDTO recommendDTO);
	
	boolean update(RecommendDTO recommendDTO);

	boolean delete(RecommendDTO rcommendDTO);

}
