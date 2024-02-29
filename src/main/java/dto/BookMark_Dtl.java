package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookMark_Dtl {	
	public int BM_DTL_ID; 			// 북마크 아이디
	public int BM_HDR_ID; 			// 북마크 그룹 아이디
	public String BM_HDR_NAME;		// 북마크 그룹명
	public String X_SWIFI_MGR_NO; 	// 관리번호
	public String X_SWIFI_MAIN_NM;	// 와이파명
	public Double BM_DTL_LAT;		// y좌표
	public Double BM_DTL_LNT;		// x좌표
	public String BM_DTL_IDT; 		// 북마크 저장일시
}