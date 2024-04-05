package infinitystone.chalKag.biz.marketPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IMarketPostService")
public class IMarketPostServiceImpl implements IMarketPostService{

	// .xml과 이어진 DAO 의존 주입
	@Autowired
	private IMarketPostDAO iMarketPostDAO;

	@Override
	public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO) {

		Map<String, Object> map = new HashMap<String, Object>();
		// 필터검색 시 뷰에서 받은 값을 넣어서 출력
		// 판매글만 출력
		if(marketPostDTO.getSearchCondition().equals("marketPostSellList")) {
			map.put("marketPostId", marketPostDTO.getMarketPostId());
			map.put("marketPostTitle", marketPostDTO.getMarketPostTitle());
			map.put("marketPostContent", marketPostDTO.getMarketPostContent());
			map.put("titleAndContents", marketPostDTO.getTitleAndContents());
			map.put("marketPostCompany", marketPostDTO.getMarketPostCompany());
			map.put("marketPostStatus", marketPostDTO.getMarketPostStatus());
			map.put("marketPostCategory", marketPostDTO.getMarketPostCategory());
			map.put("minPrice", marketPostDTO.getMinPrice());
			map.put("maxPrice", marketPostDTO.getMaxPrice());
		}
		// 삽니다 글만 출력
		else if(marketPostDTO.getSearchCondition().equals("marketPostBuyList")) {
			map.put("marketPostId", marketPostDTO.getMarketPostId());
			map.put("marketPostTitle", marketPostDTO.getMarketPostTitle());
			map.put("marketPostContent", marketPostDTO.getMarketPostContent());
			map.put("titleAndContents", marketPostDTO.getTitleAndContents());
			map.put("marketPostCompany", marketPostDTO.getMarketPostCompany());
			map.put("marketPostStatus", marketPostDTO.getMarketPostStatus());
			map.put("marketPostCategory", marketPostDTO.getMarketPostCategory());
			map.put("minPrice", marketPostDTO.getMinPrice());
			map.put("maxPrice", marketPostDTO.getMaxPrice());
		}
		
		// 무료나눔 글 출력
		else if(marketPostDTO.getSearchCondition().equals("marketPostFreecycleList")) {
			map.put("marketPostId", marketPostDTO.getMarketPostId());
			map.put("marketPostTitle", marketPostDTO.getMarketPostTitle());
			map.put("marketPostContent", marketPostDTO.getMarketPostContent());
			map.put("titleAndContents", marketPostDTO.getTitleAndContents());
			map.put("marketPostCompany", marketPostDTO.getMarketPostCompany());
			map.put("marketPostStatus", marketPostDTO.getMarketPostStatus());
			map.put("marketPostCategory", marketPostDTO.getMarketPostCategory());
			map.put("minPrice", marketPostDTO.getMinPrice());
			map.put("maxPrice", marketPostDTO.getMaxPrice());
		}
		
		
		return iMarketPostDAO.selectAll(map);
	}

	@Override
	public MarketPostDTO selectOne(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return false;
	}
}
