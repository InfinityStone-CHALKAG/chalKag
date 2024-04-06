package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

public interface IHeadHuntPostService { // 구인 게시판 필터 검색 시 사용할 Service
	
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) ;
	
	public HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean insert(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean update(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean delete(HeadHuntPostDTO headHuntPostDTO);
}
