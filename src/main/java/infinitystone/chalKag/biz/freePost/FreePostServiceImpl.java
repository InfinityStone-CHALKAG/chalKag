package infinitystone.chalKag.biz.freePost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("freePostService")
public class FreePostServiceImpl implements FreePostService{

	@Autowired
	private FreePostDAO freePostDAO;
	@Override
	public List<FreePostDTO> selectAll(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return freePostDAO.selectAll(freePostDTO);
	}

	@Override
	public FreePostDTO selectOne(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return freePostDAO.selectOne(freePostDTO);
	}

	@Override
	public boolean insert(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return freePostDAO.insert(freePostDTO);
	}

	@Override
	public boolean update(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return freePostDAO.update(freePostDTO);
	}

	@Override
	public boolean delete(FreePostDTO freePostDTO) {
		// TODO Auto-generated method stub
		return freePostDAO.delete(freePostDTO);
	}

}
