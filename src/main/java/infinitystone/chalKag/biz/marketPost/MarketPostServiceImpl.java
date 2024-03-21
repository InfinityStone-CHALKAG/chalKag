package infinitystone.chalKag.biz.marketPost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("marketPostService")
public class MarketPostServiceImpl implements MarketPostService{

	@Autowired
	private MarketPostDAO marketPostDAO;
	
	@Override
	public List<MarketPostDTO> selectAll(MarketPostDTO marketPostDTO) {
		// TODO Auto-generated method stub
		return marketPostDAO.selectAll(marketPostDTO);
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
