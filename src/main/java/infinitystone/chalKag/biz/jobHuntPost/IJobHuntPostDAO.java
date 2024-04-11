package infinitystone.chalKag.biz.jobHuntPost;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper // @Mapper : MyBatis와 연동하여 데이터베이스에 접근하는 DAO 인터페이스임을 명시
public interface IJobHuntPostDAO { // 구인 게시판의 필터 검색.xml 연결시켜주는 인터페이스
	
	// 필터 검색을 통해 구인 게시글을 조회하는 메서드 선언
	List<JobHuntPostDTO> selectAll(Map<String, Object> map);
	
}
