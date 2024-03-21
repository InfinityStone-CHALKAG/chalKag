package infinitystone.chalkag.biz.freePost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class FreePostFilterDTO {

	// 날짜 검색
	private List<String> dateList = new ArrayList<>();
	
	private String searchField;
	private String searchInput;
	
	// 정렬 데이터가 저장될 변수
	private Map<String, String> orderColumnDirection = new HashMap<>();
}
