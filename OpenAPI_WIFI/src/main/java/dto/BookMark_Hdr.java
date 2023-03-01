package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMark_Hdr {
	public int BM_HDR_ID; 		// 북마크 그룹 아이디
	public String BM_HDR_NAME; 	// 북마크 그룹 이름
	public int BM_HDR_SEQ; 		// 북마크 그룹 순서
	public String BM_HDR_IDT; 	// 북마크 그룹 저장일시
	public String BM_HDR_UDT; 	// 북마크 그룹 수정일시
}
