package infinitystone.chalKag.biz.freePost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iFreePostService")
public class IFreePostServiceImpl implements IFreePostService{
	
	// .xml과 이어진 DAO 의존 주입
	@Autowired
	private IFreePostDAO iFreePostDAO;
	
	// 필터검색 시 뷰에서 받은 값을 넣어서 출력
	@Override
	public List<FreePostDTO> selectAll(FreePostDTO freePostDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("memberId", freePostDTO.getMemberId());
		map.put("freePostId", freePostDTO.getFreePostId());
		map.put("freePostTitle", freePostDTO.getFreePostTitle());
		map.put("freePostContent", freePostDTO.getFreePostContent());
		map.put("titleAndContents", freePostDTO.getTitleAndContents());
		
		// 등록일 출력
		map.put("fromday", freePostDTO.getFromday());
		map.put("today", freePostDTO.getToday());
		
		// 정렬
		map.put("sortOrder", freePostDTO.getSortOrder());
		
		// 검색어 필터
		map.put("searchField", freePostDTO.getSearchField());
		map.put("searchInput", freePostDTO.getSearchInput());
		System.out.println(map);
		System.out.println("@#@!#!@#!@"+freePostDTO.getFromday());
		System.out.println("##@#@!#!@#!@"+freePostDTO.getToday());
		return iFreePostDAO.selectAll(map);
	}

	@Override
	public FreePostDTO selectOne(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return false;
	}


}
