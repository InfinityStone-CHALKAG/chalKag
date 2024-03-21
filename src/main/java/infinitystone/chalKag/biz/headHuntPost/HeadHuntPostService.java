package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

public interface HeadHuntPostService {
	
	List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO);
	
	HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO);
	
	boolean insert(HeadHuntPostDTO headHuntPostDTO);
	
	boolean update(HeadHuntPostDTO headHuntPostDTO);
	
	boolean delete(HeadHuntPostDTO headHuntPostDTO);
	
}

