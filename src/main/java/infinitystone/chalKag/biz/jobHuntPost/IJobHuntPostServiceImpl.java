package infinitystone.chalKag.biz.jobHuntPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IJobHuntPostService")
public class IJobHuntPostServiceImpl implements IJobHuntPostService{

	@Autowired
	private IJobHuntPostDAO iJobHuntPostDAO;
	
	@Override
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		
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
		return iJobHuntPostDAO.selectAll(map);
	}

	@Override
	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
