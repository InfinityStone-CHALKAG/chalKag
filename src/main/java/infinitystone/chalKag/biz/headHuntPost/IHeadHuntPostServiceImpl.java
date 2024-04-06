package infinitystone.chalKag.biz.headHuntPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iHeadHuntPostService")
public class IHeadHuntPostServiceImpl implements IHeadHuntPostService { 

	// 구인 게시판 필터 검색.xml과 이어진 DAO 의존 주입
	@Autowired
	private IHeadHuntPostDAO iHeadHuntPostDAO;
	
	// 필터 검색 시 뷰에서 받은 값을 넣어서 출력
	@Override
	public List<HeadHuntPostDTO> selectAll(HeadHuntPostDTO headHuntPostDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("headHuntPostId", headHuntPostDTO.getHeadHuntPostId());				// 구인글 아이디
		map.put("headHuntPostTitle", headHuntPostDTO.getHeadHuntPostTitle());		// 제목
		map.put("headHuntPostContent", headHuntPostDTO.getHeadHuntPostContent());	// 내용
		map.put("headHuntPostRole", headHuntPostDTO.getHeadHuntPostRole());			// 작업 직업
		map.put("headHuntPostRegion", headHuntPostDTO.getHeadHuntPostRegion());		// 작업 지역
		map.put("headHuntPostConcept", headHuntPostDTO.getHeadHuntPostConcept());	// 촬영 컨셉
		
		map.put("headHuntPostPay", headHuntPostDTO.getHeadHuntPostPay());			// 작업 페이	
		map.put("minPay", headHuntPostDTO.getMinPay());								// 작업 페이 최소 금액
		map.put("maxPay", headHuntPostDTO.getMaxPay());								// 작업 페이 최대 금액
		
		map.put("headHuntPostWorkDate", headHuntPostDTO.getHeadHuntPostWorkDate()); // 작업 날짜
		map.put("startWorkDate", headHuntPostDTO.getStartWorkDate());				// 작업 시작일
		map.put("endWorkDate", headHuntPostDTO.getEndWorkDate());					// 작업 마감일
		map.put("titleAndContents", headHuntPostDTO.getTitleAndContents());			// 제목 및 내용 검색
		
		map.put("fromday", headHuntPostDTO.getFromday());							// 작성일 검색 - 범위 시작
		map.put("today", headHuntPostDTO.getToday());								// 작업일 검색 - 범위 끝
		
		map.put("sortOrder", headHuntPostDTO.getSortOrder());						// 정렬
		
		map.put("searchField", headHuntPostDTO.getSearchField());					// 검색 키워드
		map.put("searchInput", headHuntPostDTO.getSearchInput());					// 검색 조건
		return iHeadHuntPostDAO.selectAll(map);
	}
	
	@Override
	public HeadHuntPostDTO selectOne(HeadHuntPostDTO headHuntPostDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(HeadHuntPostDTO headHuntPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(HeadHuntPostDTO headHuntPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(HeadHuntPostDTO headHuntPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
