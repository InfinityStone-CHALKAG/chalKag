package infinitystone.chalKag.biz.headHuntPost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class HeadHuntPostFilterDTO {
	
	// 지역 필터 검색
	private List<String> regionList = new ArrayList<>();
	
	// 컨셉 필터 검색
	private List<String> conceptList = new ArrayList<>();
	
	// 역할 필터 검색
	private List<String> roleList = new ArrayList<>();
	
	// 작업 날짜 필터 검색
	private List<String> workDateList = new ArrayList<>();
	
	// 작성 날짜에 따른 리스트
	private List<String> dateList = new ArrayList<>();

	// 게시판 이동시 검색할 가격 초기값을 설정할 변수
	private int minPrice;
	private int maxPrice;
	private String priceSort;

	private String searchField;
	private String searchInput;
	
	private String category;

	private String id;

	// 정렬 데이터가 저장될 변수
	private Map<String, String> orderColumnDirection = new HashMap<>();

	private int rownum;
}
