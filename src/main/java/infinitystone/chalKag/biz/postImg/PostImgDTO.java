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
	private String postState;
	

	private String searchCondition;
}
