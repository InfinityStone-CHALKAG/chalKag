package infinitystone.chalkag.biz.jobHuntPost;

import java.util.List;

public interface JobHuntPostService {
	List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO);
	JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO);
	
	boolean insert(JobHuntPostDTO jobHuntPostDTO);
	boolean update(JobHuntPostDTO jobHuntPostDTO);
	boolean delete(JobHuntPostDTO jobHuntPostDTO);
}
