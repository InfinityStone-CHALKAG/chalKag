package infinitystone.chalKag.biz.freePost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IFreePostDAO {

	List<FreePostDTO> selectAll(Map<String, Object> map);
}
