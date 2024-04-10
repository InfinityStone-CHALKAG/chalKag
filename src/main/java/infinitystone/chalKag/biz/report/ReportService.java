package infinitystone.chalKag.biz.report;

import java.util.List;

public interface ReportService { // 신고글과 관련 컨트롤러에서 사용할 서비스 (신고글과 관련된 비즈니스 로직을 정의하는 인터페이스)
	
	List<ReportDTO> selectAll(ReportDTO reportDTO);
	
	ReportDTO selectOne(ReportDTO reportDTO);
	
	boolean insert(ReportDTO reportDTO);
	
	boolean update(ReportDTO reportDTO);
	
	boolean delete(ReportDTO reportDTO);
}
