package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;

// 필터검색 시 컨트롤러에서 사용할 서비스 
public interface IJobHuntPostService {

	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO);
	
	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean insert(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean update(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean delete(JobHuntPostDTO jobHuntPostDTO);
}
