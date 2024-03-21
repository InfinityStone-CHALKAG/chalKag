package infinitystone.chalKag.biz.report;

import java.util.List;

public interface ReportService {
	
	List<ReportDTO> selectAll(ReportDTO reportDTO);
	
	ReportDTO selectOne(ReportDTO reportDTO);
	
	boolean insert(ReportDTO reportDTO);
	
	boolean update(ReportDTO reportDTO);
	
	boolean delete(ReportDTO reportDTO);
}
