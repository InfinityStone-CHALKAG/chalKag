package infinitystone.chalKag.biz.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reportService") // @Service : 신고글과 관련된 비즈니스 로직을 담당하는 서비스 클래스임을 명시(코드의 모듈화와 유지보수성↑)
public class ReportServiceImpl implements ReportService{ // 신고글과 관련된 비즈니스 로직을 수행할 ServiceImpl 클래스

	@Autowired // @Autowired : ReportDAO 타입의 객체를 자동으로 주입받아 사용하기 위한 어노테이션
	private ReportDAO reportDAO;
	
	// ReportService 인터페이스의 메서드를 구현
	// DAO를 통해 구인글에 대한 비즈니스로직 수행 후 반환	
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
