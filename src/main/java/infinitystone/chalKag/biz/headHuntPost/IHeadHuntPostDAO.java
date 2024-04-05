package infinitystone.chalKag.biz.headHuntPost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IHeadHuntPostDAO { // 구인 게시판 필터 검색.xml을 연결시켜주는 인터페이스
	
	List<HeadHuntPostDTO> selectAll(Map<String, Object> map);

}
