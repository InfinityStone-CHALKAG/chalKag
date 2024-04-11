package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

public interface IHeadHuntPostService { // 구인 게시판의 필터 검색 시 컨트롤러에서 사용할 서비스 (필터검색과 관련된 비즈니스 로직을 정의하는 인터페이스)
	
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) ;
	
	public HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean insert(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean update(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean delete(HeadHuntPostDTO headHuntPostDTO);
}
