package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;

public interface IJobHuntPostService { // 구직 게시판의 필터 검색 시 컨트롤러에서 사용할 서비스 (필터검색과 관련된 비즈니스 로직을 정의하는 인터페이스)

	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO);
	
	public JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean insert(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean update(JobHuntPostDTO jobHuntPostDTO);
	
	public boolean delete(JobHuntPostDTO jobHuntPostDTO);
}
