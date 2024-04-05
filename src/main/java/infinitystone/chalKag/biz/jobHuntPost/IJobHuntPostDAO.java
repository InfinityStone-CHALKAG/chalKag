package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


//.xml 을 이어주는 인터페이스
@Mapper
public interface IJobHuntPostDAO {
	
	List<JobHuntPostDTO> selectAll(Map<String, Object> map);
	
}
