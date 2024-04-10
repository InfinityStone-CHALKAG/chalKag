package infinitystone.chalKag.biz.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("recommendService") // @Service : 좋아요 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class RecommendServiceImpl implements RecommendService{ // 좋아요 정보 관련된 비즈니스 로직을 수행할 ServiceImpl 클래스

	@Autowired // @Autowired : RecommendDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
	private RecommendDAO recommendDAO;
	
	// RecommendService 인터페이스의 메서드를 구현
	// DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환
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
