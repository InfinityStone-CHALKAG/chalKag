package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostDAO;
import infinitystone.chalKag.biz.headHuntPost.HeadHuntPostService;

@Service("jobHuntPostService") // @Service : 구직 게시판의 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class JobHuntPostServiceImpl implements JobHuntPostService { // 구직 게시판의 비즈니스 로직을 수행할 ServiceImpl 클래스
	  
	@Autowired // @Autowired : JobHuntPostDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
	private JobHuntPostDAO jobHuntPostDAO;
		
	// JobHuntPostService 인터페이스의 메서드를 구현
	// DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환
	@Override
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
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
