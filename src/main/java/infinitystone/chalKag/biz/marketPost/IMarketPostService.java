package infinitystone.chalKag.biz.marketPost;

import java.util.List;

// 필터검색 시 컨트롤러에서 사용할 서비스
public interface IMarketPostService {
	
public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO);
	
	public MarketPostDTO selectOne(MarketPostDTO marketPostDTO);
	
	public boolean insert(MarketPostDTO marketPostDTO);
	
	public boolean update(MarketPostDTO marketPostDTO);
	
	public boolean delete(MarketPostDTO marketPostDTO);
}
