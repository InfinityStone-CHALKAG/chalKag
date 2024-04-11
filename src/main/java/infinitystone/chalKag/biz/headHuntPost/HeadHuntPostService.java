package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

public interface HeadHuntPostService { // 구인 게시판의 컨트롤러에서 사용할 서비스 (구인 게시판과 관련된 비즈니스 로직을 정의하는 인터페이스)
	
	List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO);
	
	HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO);
	
	boolean insert(HeadHuntPostDTO headHuntPostDTO);
	
	boolean update(HeadHuntPostDTO headHuntPostDTO);
	
	boolean delete(HeadHuntPostDTO headHuntPostDTO);
	
}

