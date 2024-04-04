package infinitystone.chalKag.biz.marketPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("marketPostService")
public class MarketPostServiceImpl implements MarketPostService{

	@Autowired
	private MarketPostDAO marketPostDAO;
	
	@Autowired
	private IMarketPostDAO iMarketPostDAO;
	
	@Override
	public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
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
		return marketPostDAO.selectOne(marketPostDTO);
	}

	@Override
	public boolean insert(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return marketPostDAO.insert(marketPostDTO);
	}

	@Override
	public boolean update(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return marketPostDAO.update(marketPostDTO);
	}

	@Override
	public boolean delete(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return marketPostDAO.delete(marketPostDTO);
	}

}
