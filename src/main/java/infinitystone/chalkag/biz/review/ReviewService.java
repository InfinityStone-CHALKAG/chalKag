package infinitystone.chalkag.biz.review;


import java.util.List;

public interface ReviewService {

  public List<ReviewDTO> selectAll(ReviewDTO reviewDTO);

  public ReviewDTO selectOne(ReviewDTO reviewDTO);

  public boolean insert(ReviewDTO reviewDTO);

  public boolean update(ReviewDTO reviewDTO);

  public boolean delete(ReviewDTO reviewDTO);

}
