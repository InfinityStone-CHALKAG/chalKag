package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IJobHuntPostDAO {
	
	List<JobHuntPostDTO> selectAll(Map<String, Object> map);
	
}
