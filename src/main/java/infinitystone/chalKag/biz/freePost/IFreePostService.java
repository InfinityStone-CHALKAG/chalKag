package infinitystone.chalKag.biz.freePost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IFreePostService {
	
	public List<FreePostDTO> selectAll(FreePostDTO freePostDTO);
	public FreePostDTO selectOne(FreePostDTO freePostDTO);
	public boolean insert(FreePostDTO freePostDTO);
	public boolean update(FreePostDTO freePostDTO);
	public boolean delete(FreePostDTO freePostDTO);
	
}
