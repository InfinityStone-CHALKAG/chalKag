package infinitystone.chalKag.biz.jobHuntPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobHuntPostService")
public class JobHuntPostServiceImpl implements JobHuntPostService {
	
	
	@Autowired
	private JobHuntPostDAO jobHuntPostDAO;
	
	@Autowired
	private IJobHuntPostDAO iJobHuntPostDAO;
	
	@Override
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobHuntPostId", jobHuntPostDTO.getJobHuntPostId());
		map.put("jobHuntPostTitle", jobHuntPostDTO.getJobHuntPostTitle());
		map.put("jobHuntPostContent", jobHuntPostDTO.getJobHuntPostContent());
		map.put("titleAndContents", jobHuntPostDTO.getTitleAndContents());
		map.put("jobHuntPostRole", jobHuntPostDTO.getJobHuntPostRole());
		map.put("jobHuntPostRegion", jobHuntPostDTO.getJobHuntPostRegion());
		map.put("jobHuntPostPay", jobHuntPostDTO.getJobHuntPostPay());
		map.put("minPay", jobHuntPostDTO.getMinPay());
		map.put("maxPay", jobHuntPostDTO.getMaxPay());
		map.put("jobHuntPostConcept", jobHuntPostDTO.getJobHuntPostConcept());

		// 작업일 
		map.put("jobHuntPostWorkDate", jobHuntPostDTO.getJobHuntPostWorkDate());


		map.put("startWorkDate", jobHuntPostDTO.getStartWorkDate());
		map.put("endWorkDate", jobHuntPostDTO.getEndWorkDate());
		// TODO Auto-generated method stub
		return iJobHuntPostDAO.selectAll(map);
	}

	@Override
	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return jobHuntPostDAO.selectOne(jobHuntPostDTO);
	}

	@Override
	public boolean insert(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		
		return jobHuntPostDAO.insert(jobHuntPostDTO);
	}

	@Override
	public boolean update(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return jobHuntPostDAO.update(jobHuntPostDTO);
	}

	@Override
	public boolean delete(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return jobHuntPostDAO.delete(jobHuntPostDTO);
	}

}
