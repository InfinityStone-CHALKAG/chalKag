package infinitystone.chalKag.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminService {

  @Autowired
  private AdminDAO adminDAO;

  public List<AdminDTO> signUpCountByAgeGroup(AdminDTO adminDTO) {
    return adminDAO.selectAll(adminDTO);
  }

  public AdminDTO signUpCountByGenderGroup(AdminDTO adminDTO) {
    return adminDAO.selectOne(adminDTO);
  }

  public List<AdminDTO> signInCountByYearMonthDate(AdminDTO adminDTO) {
    return adminDAO.selectAll(adminDTO);
  }

  public List<AdminDTO> signInCountByDayOfWeek(AdminDTO adminDTO) {
    return adminDAO.selectAll(adminDTO);
  }


}
