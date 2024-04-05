package infinitystone.chalKag.biz.freePost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


// .xml 과 이어주는 DAO
@Mapper
public interface IFreePostDAO {

	List<FreePostDTO> selectAll(Map<String, Object> map);
}
