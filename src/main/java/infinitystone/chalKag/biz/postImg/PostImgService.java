package infinitystone.chalKag.biz.postImg;

import java.util.List;

public interface PostImgService {
	public List<PostImgDTO> selectAll(PostImgDTO postImgDTO);

	public PostImgDTO selectOne(PostImgDTO postImgDTO);

	public boolean insert(PostImgDTO postImgDTO);

	public boolean update(PostImgDTO postImgDTO);

	public boolean delete(PostImgDTO postImgDTO);
}
