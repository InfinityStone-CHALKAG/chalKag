package infinitystone.chalKag.biz.freePost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//필터검색 시 컨트롤러에서 사용할 서비스 
public interface IFreePostService {
	
	public List<FreePostDTO> selectAll(FreePostDTO freePostDTO);
	public FreePostDTO selectOne(FreePostDTO freePostDTO);
	public boolean insert(FreePostDTO freePostDTO);
	public boolean update(FreePostDTO freePostDTO);
	public boolean delete(FreePostDTO freePostDTO);
	
}
