package infinitystone.chalKag.biz.freePost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IFreePostService")
public class IFreePostServiceImpl implements IFreePostService{
	
	@Autowired
	private IFreePostDAO iFreePostDAO;
	
	@Override
	public List<FreePostDTO> selectAll(FreePostDTO freePostDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("freePostId", freePostDTO.getFreePostId());
		map.put("freePostTitle", freePostDTO.getFreePostTitle());
		map.put("freePostContent", freePostDTO.getFreePostContent());
		map.put("titleAndContents", freePostDTO.getTitleAndContents());


		return iFreePostDAO.selectAll(map);
	}

	@Override
	public FreePostDTO selectOne(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return false;
	}


}
