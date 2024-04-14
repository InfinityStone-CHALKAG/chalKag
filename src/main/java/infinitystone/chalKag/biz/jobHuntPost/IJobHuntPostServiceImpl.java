package infinitystone.chalKag.biz.jobHuntPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iJobHuntPostService") // @Service : 구인 게시판의 필터검색 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class IJobHuntPostServiceImpl implements IJobHuntPostService{  // 구인 게시판의 필터검색 비즈니스 로직을 수행할 ServiceImpl 클래스

	@Autowired // @Autowired : 구인 게시판의 필터검색 .xml과 이어진 IHeadHuntPostDAO를 의존 주입
	private IJobHuntPostDAO iJobHuntPostDAO;
	
	// 필터검색 시 뷰에서 받은 값을 넣어서 출력
	@Override
	public List<JobHuntPostDTO> selectAll(JobHuntPostDTO jobHuntPostDTO) {
		// 필터링을 위한 매개변수를 Map으로 생성
		Map<String, Object> map = new HashMap<String,Object>();
		System.out.println("IMPL " + jobHuntPostDTO);
		
		// DTO 객체에서 필터링에 필요한 정보들을 추출하여 Map에 담음
		map.put("jobHuntPostId", jobHuntPostDTO.getJobHuntPostId());				// 구직글 아이디
		map.put("jobHuntPostTitle", jobHuntPostDTO.getJobHuntPostTitle());			// 제목
		map.put("jobHuntPostContent", jobHuntPostDTO.getJobHuntPostContent());		// 내용
		map.put("jobHuntPostRole", jobHuntPostDTO.getJobHuntPostRole());			// 작업 지역
		map.put("jobHuntPostRegion", jobHuntPostDTO.getJobHuntPostRegion());		// 작업 지역
		map.put("jobHuntPostConcept", jobHuntPostDTO.getJobHuntPostConcept());		// 촬영 컨셉
		
		map.put("jobHuntPostPay", jobHuntPostDTO.getJobHuntPostPay());				// 작업 페이
		map.put("minPay", jobHuntPostDTO.getMinPay());								// 작업 페이 최소 금액
		map.put("maxPay", jobHuntPostDTO.getMaxPay());								// 작업 페이 최대 금액
		
		map.put("jobHuntPostWorkDate", jobHuntPostDTO.getJobHuntPostWorkDate());	// 작업 날짜
		map.put("startWorkDate", jobHuntPostDTO.getStartWorkDate());				// 작업 시작일
		map.put("endWorkDate", jobHuntPostDTO.getEndWorkDate());					// 작업 마감일
		map.put("titleAndContents", jobHuntPostDTO.getTitleAndContents());			// 제목 및 내용 검색
		
		map.put("fromday", jobHuntPostDTO.getFromday());							// 작성일 검색 - 범위 시작			
		map.put("today", jobHuntPostDTO.getToday());								// 작성일 검색 - 범위 끝
		
		map.put("sortOrder", jobHuntPostDTO.getSortOrder());						// 정렬
		
		map.put("searchField", jobHuntPostDTO.getSearchField());					// 검색 키워드
		map.put("searchInput", jobHuntPostDTO.getSearchInput());					// 검색어
		
		map.put("memberId", jobHuntPostDTO.getMemberId());							// 회원 아이디
		
		System.out.println("MAP" + map);
		System.out.println("iJobHuntPostDAO.selectAll(map) :" + iJobHuntPostDAO.selectAll(map));// 검색 조건

		return iJobHuntPostDAO.selectAll(map);  // 조회된 구직 게시글 리스트를 반환
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
