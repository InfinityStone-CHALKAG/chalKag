package infinitystone.chalKag.biz.jobHuntPost;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class JobHuntPostFilterDTO {
	
	// 지역 필터 검색
//	private List<String> regionList = new ArrayList<>();
	
	// 컨셉 필터 검색
//	private List<String> conceptList = new ArrayList<>();
	
	// 역할 필터 검색
//	private List<String> roleList = new ArrayList<>();
	
	// 작업 날짜 필터 검색
//	private List<String> workDateList = new ArrayList<>();
	
	// 작성 날짜에 따른 리스트
//	private List<String> dateList = new ArrayList<>();
	
	
	// 역할
	private String role;
	
	// 지역
	private String region;
	

	// 게시판 이동시 검색할 가격 초기값을 설정할 변수
	private int minPrice;
	private int maxPrice;
	private String priceSort;
	
	
	// 작업 날짜, 시작 끝
	private int startWorkDate;
	private int endWorkDate;

	// 검색 필드
	private String searchField;
	private String searchInput;
	
	private String id;
	
	// 어떤 카테고리에 있는지 저장하기 위한 변수
	private String category;


	// 정렬 데이터가 저장될 변수
	private Map<String, String> orderColumnDirection = new HashMap<>();

	private int rownum;
}
