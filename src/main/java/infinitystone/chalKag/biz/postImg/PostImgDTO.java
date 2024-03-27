package infinitystone.chalKag.biz.postImg;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostImgDTO {
	private String postImgId;
	private String postId;
	private String postImgName;
	
	private List<Image> postImages = new ArrayList<>();		// 여러 이미지 파일 업로드 

	private String searchCondition;
}
