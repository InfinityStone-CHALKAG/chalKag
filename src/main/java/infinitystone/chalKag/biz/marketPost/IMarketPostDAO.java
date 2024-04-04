package infinitystone.chalKag.biz.marketPost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMarketPostDAO {
	
	List<MarketPostDTO> selecAll(Map<String, Object>map);
}
