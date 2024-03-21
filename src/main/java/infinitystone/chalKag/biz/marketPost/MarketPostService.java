package infinitystone.chalKag.biz.marketPost;

import java.util.List;

public interface MarketPostService {
	public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO);

	public MarketPostDTO selectOne(MarketPostDTO marketPostDTO);

	public boolean insert(MarketPostDTO marketPostDTO);

	public boolean update(MarketPostDTO marketPostDTO);

	public boolean delete(MarketPostDTO marketPostDTO);
}
