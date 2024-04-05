package infinitystone.chalKag.biz.jobHuntPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iJobHuntPostService")
public class IJobHuntPostServiceImpl implements IJobHuntPostService{

	// .xml과 이어진 DAO 의존 주입
	@Autowired
	private IJobHuntPostDAO iJobHuntPostDAO;
	
	// 필터검색 시 뷰에서 받은 값을 넣어서 출력
	@Override
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("jobHuntPostId", jobHuntPostDTO.getJobHuntPostId());
		map.put("jobHuntPostTitle", jobHuntPostDTO.getJobHuntPostTitle());
		map.put("jobHuntPostContent", jobHuntPostDTO.getJobHuntPostContent());
		map.put("jobHuntPostRole", jobHuntPostDTO.getJobHuntPostRole());
		map.put("jobHuntPostRegion", jobHuntPostDTO.getJobHuntPostRegion());
		map.put("jobHuntPostPay", jobHuntPostDTO.getJobHuntPostPay());
		map.put("minPay", jobHuntPostDTO.getMinPay());
		map.put("maxPay", jobHuntPostDTO.getMaxPay());
		
		// 작업일 
		map.put("jobHuntPostWorkDate", jobHuntPostDTO.getJobHuntPostWorkDate());
		map.put("startWorkDate", jobHuntPostDTO.getStartWorkDate());
		map.put("endWorkDate", jobHuntPostDTO.getEndWorkDate());
		
		map.put("titleAndContents", jobHuntPostDTO.getTitleAndContents());
		
		
		map.put("fromday", jobHuntPostDTO.getFromday());
		map.put("today", jobHuntPostDTO.getToday());
		
		map.put("sortOrder", jobHuntPostDTO.getSortOrder());
		
		map.put("searchField", jobHuntPostDTO.getSearchField());
		map.put("searchInput", jobHuntPostDTO.getSearchInput());
		
		map.put("jobHuntPostConcept", jobHuntPostDTO.getJobHuntPostConcept());

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
