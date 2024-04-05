package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

public interface HeadHuntPostService { // 구인 게시판 Service
	
	List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO);
	
	HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO);
	
	boolean insert(HeadHuntPostDTO headHuntPostDTO);
	
	boolean update(HeadHuntPostDTO headHuntPostDTO);
	
	boolean delete(HeadHuntPostDTO headHuntPostDTO);
	
}

