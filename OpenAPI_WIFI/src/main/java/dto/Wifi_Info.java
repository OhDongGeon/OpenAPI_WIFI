package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wifi_Info {
	public String X_SWIFI_MGR_NO; 		// 관리번호
	public String X_SWIFI_WRDOFC; 		// 자치구
	public String X_SWIFI_MAIN_NM; 		// 와이파이명
	public String X_SWIFI_ADRES1; 		// 도로명주소
	public String X_SWIFI_ADRES2; 		// 상세주소
	public String X_SWIFI_INSTL_FLOOR; 	// 설치위치(층)
	public String X_SWIFI_INSTL_TY; 	// 설치유형
	public String X_SWIFI_INSTL_MBY; 	// 설치기관
	public String X_SWIFI_SVC_SE; 		// 서비스구분
	public String X_SWIFI_CMCWR; 		// 망종류
	public String X_SWIFI_CNSTC_YEAR; 	// 설치년도
	public String X_SWIFI_INOUT_DOOR; 	// 실내외구분
	public String X_SWIFI_REMARS3; 		// 와이파이접속정보
	public Double LAT; 					// y좌표
	public Double LNT; 					// x좌표
	public String WORK_DTTM; 			// 작업일자
	public String KM; 					// 거리(KM)
}
