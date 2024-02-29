package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class History_Info {
	public int HI_ID; 		// 히스토리 아이디
	public Double HI_LAT; 	// y좌표
	public Double HI_LNT; 	// x좌표
	public String HI_IDT; 	// 히스토리 저장일시
}
