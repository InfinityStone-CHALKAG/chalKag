package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;

public interface JobHuntPostService { // 구인 게시판의 컨트롤러에서 사용할 서비스 (구인 게시판과 관련된 비즈니스 로직을 정의하는 인터페이스)
	
	List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO);
	
	JobHuntPostDTO selectOne(JobHuntPostDTO jobHuntPostDTO);
	
	boolean insert(JobHuntPostDTO jobHuntPostDTO);
	
	boolean update(JobHuntPostDTO jobHuntPostDTO);
	
	boolean delete(JobHuntPostDTO jobHuntPostDTO);
}
