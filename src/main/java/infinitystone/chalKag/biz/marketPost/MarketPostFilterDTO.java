package infinitystone.chalKag.biz.marketPost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MarketPostFilterDTO {
	// 제조사 리스트
	private List<String> companyList = new ArrayList<>();
	
	// 제품 종류 리스트
	private List<String> categoryList = new ArrayList<>();
	
	// 날짜에 따른 리스트
	private List<String> dateList = new ArrayList<>();
	// 게시판 이동시 검색할 가격 초기값을 설정할 변수
		private int date;
		private int minPrice;
		private int maxPrice;
		private String priceSort;
		
		private String searchField;
		private String searchInput;
		
		
		// 어떤 카테고리에 있는지 저장하기 위한 변수
		private String category;
		
		// 정렬 데이터가 저장될 변수
		private Map<String, String> orderColumnDirection = new HashMap<>();
}
