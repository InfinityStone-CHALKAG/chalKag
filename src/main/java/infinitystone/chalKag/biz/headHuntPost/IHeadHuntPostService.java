package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;

public interface IHeadHuntPostService {
	
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) ;
	
	public HeadHuntPostDTO selectIbe(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean insert(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean update(HeadHuntPostDTO headHuntPostDTO);
	
	public boolean delete(HeadHuntPostDTO headHuntPostDTO);
}
