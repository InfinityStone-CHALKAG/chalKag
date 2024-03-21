package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jobHuntPostService")
public class JobHuntPostServiceImpl implements JobHuntPostService {
	
	
	@Autowired
	private JobHuntPostDAO jobHuntPostDAO;
	
	@Override
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		// TODO Auto-generated method stub
		return jobHuntPostDAO.selectAll(jobHuntPostDTO);
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
