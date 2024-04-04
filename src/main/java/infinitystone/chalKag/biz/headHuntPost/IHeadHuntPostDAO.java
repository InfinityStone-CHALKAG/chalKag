package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHeadHuntPostDAO {
	
	List<HeadHuntPostDTO> selectAll(Map<String, Object> map);

}
