package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("headHuntPostService") // @Service : 구인 게시판의 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class HeadHuntPostServiceImpl implements HeadHuntPostService{ // 구인 게시판의 비즈니스 로직을 수행할 ServiceImpl 클래스

	@Autowired // @Autowired : HeadHuntPostDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
	private HeadHuntPostDAO headHuntPostDAO;
	
	// HeadHuntPostService 인터페이스의 메서드를 구현
	// DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환
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
