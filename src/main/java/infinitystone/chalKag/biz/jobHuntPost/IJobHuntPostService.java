package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;

public interface IJobHuntPostService {

	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO);
	
	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean insert(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean update(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean delete(JobHuntPostDTO jobHuntPostDTO);
}
