package infinitystone.chalKag.biz.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDAO reportDAO;
	
	@Override
	public List<ReportDTO> selectAll(ReportDTO reportDTO) {
		return reportDAO.selectAll(reportDTO);
	}

	@Override
	public ReportDTO selectOne(ReportDTO reportDTO) {
		return reportDAO.selectOne(reportDTO);
	}

	@Override
	public boolean insert(ReportDTO reportDTO) {
		return reportDAO.insert(reportDTO);
	}

	@Override
	public boolean update(ReportDTO reportDTO) {
		return reportDAO.update(reportDTO);
	}
	
	@Override
	public boolean delete(ReportDTO reportDTO) {
		return reportDAO.delete(reportDTO);
	}

}
