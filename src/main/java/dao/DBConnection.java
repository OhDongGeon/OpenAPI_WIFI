package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import dto.Wifi_Info;
import dto.History_Info;
import dto.Lat_Lnt;
import dto.BookMark_Hdr;
import dto.BookMark_Dtl;


public class DBConnection {
	
	private String mstr_Url = "jdbc:mariadb://127.0.0.1:3306/openapi_wifi";
	private String mstr_ID = "openapi";
	private String mstr_Pass = "zerobase";
	private String mstr_Driver = "org.mariadb.jdbc.Driver";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Statement stm;
	
	
	// WIFI 조회
	public ArrayList<Wifi_Info> wifi_Select(Double dou_Lat, Double dou_Lnt) {
		try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			stm = con.createStatement();
			
			Wifi_Info wifi_info;
			ArrayList<Wifi_Info> arr_Wifi_Info = new ArrayList<>(); 
			
			StringBuilder sb_Sql = new StringBuilder("	SELECT * ");
									   sb_Sql.append("		 , ROUND(6371 * ACOS(COS(RADIANS(?)) * COS(RADIANS(LNT)) ");
									   sb_Sql.append("					  * COS(RADIANS(LAT) - RADIANS(?)) ");
									   sb_Sql.append("					  + SIN(RADIANS(?)) * SIN(RADIANS(LNT))), 4) KM");
									   sb_Sql.append("	  FROM WIFI_INFO ");
									   sb_Sql.append("ORDER BY KM ");
									   sb_Sql.append("	 LIMIT 20");
			
									   
			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setDouble(1, dou_Lnt);
			pstmt.setDouble(2, dou_Lat);
			pstmt.setDouble(3, dou_Lnt);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				wifi_info = new Wifi_Info();
				wifi_info.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi_info.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi_info.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi_info.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi_info.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi_info.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				wifi_info.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi_info.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi_info.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi_info.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi_info.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi_info.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi_info.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi_info.setLAT(Double.parseDouble(rs.getString("LAT")));
				wifi_info.setLNT(Double.parseDouble(rs.getString("LNT")));
				wifi_info.setWORK_DTTM(rs.getString("WORK_DTTM"));
				wifi_info.setKM(rs.getString("KM"));
				
				arr_Wifi_Info.add(wifi_info);
			}
			
			con.close();
			return arr_Wifi_Info;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	// WIFI 상세 조회
	public ArrayList<Wifi_Info> wifi_Select(String str_Mgr_No) {
		try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			stm = con.createStatement();
			
			Wifi_Info wifi_info;
			ArrayList<Wifi_Info> arr_Wifi_Info = new ArrayList<>(); 

			StringBuilder sb_Sql = new StringBuilder("	SELECT * ");
			   						   sb_Sql.append("		 , ROUND(6371 * ACOS(COS(RADIANS(?)) * COS(RADIANS(LNT)) ");
			   						   sb_Sql.append("					  * COS(RADIANS(LAT) - RADIANS(?)) ");
			   						   sb_Sql.append("					  + SIN(RADIANS(?)) * SIN(RADIANS(LNT))), 4) KM");
									   sb_Sql.append("	  FROM WIFI_INFO ");
									   sb_Sql.append("	 WHERE X_SWIFI_MGR_NO = ?");
			   
			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setDouble(1, Lat_Lnt.LNT);
			pstmt.setDouble(2, Lat_Lnt.LAT);
			pstmt.setDouble(3, Lat_Lnt.LNT);
			pstmt.setString(4, str_Mgr_No);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				wifi_info = new Wifi_Info();
				wifi_info.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				wifi_info.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				wifi_info.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				wifi_info.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				wifi_info.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				wifi_info.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				wifi_info.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				wifi_info.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				wifi_info.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				wifi_info.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				wifi_info.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				wifi_info.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				wifi_info.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				wifi_info.setLAT(Double.parseDouble(rs.getString("LAT")));
				wifi_info.setLNT(Double.parseDouble(rs.getString("LNT")));
				wifi_info.setWORK_DTTM(rs.getString("WORK_DTTM"));
				wifi_info.setKM(rs.getString("KM"));
				
				arr_Wifi_Info.add(wifi_info);
			}
			
			con.close();
			return arr_Wifi_Info;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	// WIFI 저장 및 수정
    public int wifi_Save(JsonArray json_ArrRow) {
    	try {
    		int i_Save_Cnt = 0;
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);

			Gson gson = new Gson();
			for (JsonElement json_Element : json_ArrRow) {
				Wifi_Info wifi_info = gson.fromJson(json_Element, Wifi_Info.class);
				StringBuilder sb_Sql = new StringBuilder("INSERT INTO WIFI_INFO ");
				sb_Sql.append("VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				sb_Sql.append("ON DUPLICATE KEY UPDATE ");
				sb_Sql.append("X_SWIFI_WRDOFC = ?, X_SWIFI_MAIN_NM = ?, X_SWIFI_ADRES1 = ?, X_SWIFI_ADRES2 = ?, X_SWIFI_INSTL_FLOOR = ?, ");
				sb_Sql.append("X_SWIFI_INSTL_TY = ?, X_SWIFI_INSTL_MBY = ?, X_SWIFI_SVC_SE = ?, X_SWIFI_CMCWR = ?, X_SWIFI_CNSTC_YEAR = ?, ");
				sb_Sql.append("X_SWIFI_INOUT_DOOR = ?, X_SWIFI_REMARS3 = ?, LAT = ?, LNT = ?, WORK_DTTM = ?");
				
				
				pstmt = con.prepareStatement(sb_Sql.toString());
				pstmt.setString(1, wifi_info.getX_SWIFI_MGR_NO());
				pstmt.setString(2, wifi_info.getX_SWIFI_WRDOFC());
				pstmt.setString(3, wifi_info.getX_SWIFI_MAIN_NM());
				pstmt.setString(4, wifi_info.getX_SWIFI_ADRES1());
				pstmt.setString(5, wifi_info.getX_SWIFI_ADRES2());
				pstmt.setString(6, wifi_info.getX_SWIFI_INSTL_FLOOR());
				pstmt.setString(7, wifi_info.getX_SWIFI_INSTL_TY());
				pstmt.setString(8, wifi_info.getX_SWIFI_INSTL_MBY());
				pstmt.setString(9, wifi_info.getX_SWIFI_SVC_SE());
				pstmt.setString(10, wifi_info.getX_SWIFI_CMCWR());
				pstmt.setString(11, wifi_info.getX_SWIFI_CNSTC_YEAR());
				pstmt.setString(12, wifi_info.getX_SWIFI_INOUT_DOOR());
				pstmt.setString(13, wifi_info.getX_SWIFI_REMARS3());
				pstmt.setDouble(14, wifi_info.getLAT());
				pstmt.setDouble(15, wifi_info.getLNT());
				pstmt.setString(16, wifi_info.getWORK_DTTM());
				pstmt.setString(17, wifi_info.getX_SWIFI_WRDOFC());
				pstmt.setString(18, wifi_info.getX_SWIFI_MAIN_NM());
				pstmt.setString(19, wifi_info.getX_SWIFI_ADRES1());
				pstmt.setString(20, wifi_info.getX_SWIFI_ADRES2());
				pstmt.setString(21, wifi_info.getX_SWIFI_INSTL_FLOOR());
				pstmt.setString(22, wifi_info.getX_SWIFI_INSTL_TY());
				pstmt.setString(23, wifi_info.getX_SWIFI_INSTL_MBY());
				pstmt.setString(24, wifi_info.getX_SWIFI_SVC_SE());
				pstmt.setString(25, wifi_info.getX_SWIFI_CMCWR());
				pstmt.setString(26, wifi_info.getX_SWIFI_CNSTC_YEAR());
				pstmt.setString(27, wifi_info.getX_SWIFI_INOUT_DOOR());
				pstmt.setString(28, wifi_info.getX_SWIFI_REMARS3());
				pstmt.setDouble(29, wifi_info.getLAT());
				pstmt.setDouble(30, wifi_info.getLNT());
				pstmt.setString(31, wifi_info.getWORK_DTTM());
				pstmt.executeUpdate();
				i_Save_Cnt++;
			}
			con.close();
			return i_Save_Cnt;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    
    // 위치 히스토리 조회
    public ArrayList<History_Info> history_Info_Select() {
    	try {
    		Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			stm = con.createStatement();
			
			History_Info hi;
			ArrayList<History_Info> arr_Hi = new ArrayList<>(); 
			
			StringBuilder sb_Sql = new StringBuilder("	SELECT * ");
									   sb_Sql.append("	  FROM HISTORY_INFO ");
									   sb_Sql.append("ORDER BY HI_ID DESC");
									   
			rs = stm.executeQuery(sb_Sql.toString());
			while(rs.next()) {
				hi = new History_Info();
				hi.setHI_ID(Integer.parseInt(rs.getString("HI_ID")));
				hi.setHI_LAT(Double.parseDouble(rs.getString("HI_LAT")));
				hi.setHI_LNT(Double.parseDouble(rs.getString("HI_LNT")));
				hi.setHI_IDT(rs.getString("HI_IDT"));
				
				arr_Hi.add(hi);
			}
			
			con.close();
			return arr_Hi;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    
    
    // 위치 히스토리 저장
    public void history_Info_Insert(Double dou_Lat, Double dou_Lnt) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			
			StringBuilder sb_Sql = new StringBuilder("INSERT INTO HISTORY_INFO ");
									   sb_Sql.append("SELECT IFNULL(MAX(HI_ID), 0) + 1, ?, ?, NOW() ");
									   sb_Sql.append("  FROM HISTORY_INFO");
			

			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setDouble(1, dou_Lat);
			pstmt.setDouble(2, dou_Lnt);
			pstmt.executeUpdate();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
        }
    }
    
    
    
    // 위치 히스토리 삭제
    public int history_Info_Delete(String str_HI_Id) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			
			StringBuilder sb_Sql = new StringBuilder("	DELETE ");
									   sb_Sql.append("	  FROM HISTORY_INFO");
									   sb_Sql.append("   WHERE HI_ID = ?");
										  
			
			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setInt(1, Integer.parseInt(str_HI_Id));
			pstmt.executeUpdate();
			con.close();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    
    // 북마크 그룹 조회
    public ArrayList<BookMark_Hdr> bookmarkgroup_Select(String str_Chk, String str_BM_Id) {
    	try {
    		Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			stm = con.createStatement();
			
			BookMark_Hdr bm_Hdr;
			ArrayList<BookMark_Hdr> arr_bm_Hdr = new ArrayList<>(); 
			
			StringBuilder sb_Sql = new StringBuilder("	SELECT BM_HDR_ID, BM_HDR_NAME, BM_HDR_SEQ, BM_HDR_IDT, IFNULL(BM_HDR_UDT, '') BM_HDR_UDT ");
									   sb_Sql.append("	  FROM BOOKMARK_HDR ");
			if(str_Chk.equals("Equals")){
									   sb_Sql.append("	 WHERE BM_HDR_ID = ? ");
			}
			
			if(str_Chk.equals("OrderSeq")){
									   sb_Sql.append("ORDER BY BM_HDR_SEQ");
			} else {
									   sb_Sql.append("ORDER BY BM_HDR_ID");
			}
			
			if(str_Chk.equals("Equals")){
				pstmt = con.prepareStatement(sb_Sql.toString());
				pstmt.setInt(1, Integer.parseInt(str_BM_Id));
				
				rs = pstmt.executeQuery();
			} else {
				rs = stm.executeQuery(sb_Sql.toString());
			}   
			while(rs.next()) {
				bm_Hdr = new BookMark_Hdr();
				bm_Hdr.setBM_HDR_ID(Integer.parseInt(rs.getString("BM_HDR_ID")));
				bm_Hdr.setBM_HDR_NAME(rs.getString("BM_HDR_NAME"));
				bm_Hdr.setBM_HDR_SEQ(Integer.parseInt(rs.getString("BM_HDR_SEQ")));
				bm_Hdr.setBM_HDR_IDT(rs.getString("BM_HDR_IDT"));
				bm_Hdr.setBM_HDR_UDT(rs.getString("BM_HDR_UDT"));
				
				arr_bm_Hdr.add(bm_Hdr);
			}
			
			con.close();
			return arr_bm_Hdr;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    

    // 북마크 그룹 저장
    public int bookmarkgroup_Insert(String str_BM_Name, String str_BM_Seq) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			
			StringBuilder sb_Sql = new StringBuilder("INSERT INTO BOOKMARK_HDR ");
									   sb_Sql.append("SELECT IFNULL(MAX(BM_HDR_ID), 0) + 1, ?, ?, NOW(), NULL ");
									   sb_Sql.append("  FROM BOOKMARK_HDR");
			

			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setString(1, str_BM_Name);
			pstmt.setString(2, str_BM_Seq);
			pstmt.executeUpdate();
			con.close();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    
    // 북마크 그룹 수정
    public int bookmarkgroup_Update(String str_BM_Id, String str_BM_Name, String str_BM_Seq) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			
			StringBuilder sb_Sql = new StringBuilder("	UPDATE BOOKMARK_HDR ");
									   sb_Sql.append("	   SET BM_HDR_NAME 	= ? ");
									   sb_Sql.append("		 , BM_HDR_SEQ	= ? ");
									   sb_Sql.append("		 , BM_HDR_UDT	= NOW() ");
									   sb_Sql.append("   WHERE BM_HDR_ID 	= ?");
										  
									   
			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setString(1, str_BM_Name);
			pstmt.setString(2, str_BM_Seq);
			pstmt.setString(3, str_BM_Id);
			pstmt.executeUpdate();
			con.close();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    
    // 북마크 그룹 삭제
    public int bookmarkgroup_Delete(String str_BM_Id) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			String str_Chk = "";
			StringBuilder sb_Sql = new StringBuilder("IF EXISTS(SELECT BM_HDR_ID FROM BOOKMARK_DTL WHERE BM_HDR_ID  = ?) ");
									   sb_Sql.append("THEN" );
									   sb_Sql.append("	 SELECT 2 CHK; ");
									   sb_Sql.append("ELSE ");
									   sb_Sql.append("	 DELETE FROM BOOKMARK_HDR WHERE BM_HDR_ID  = ?; ");
									   sb_Sql.append("END IF");
			
			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setInt(1, Integer.parseInt(str_BM_Id));
			pstmt.setInt(2, Integer.parseInt(str_BM_Id));
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				str_Chk = rs.getString("CHK");
				con.close();
				return 2;
			}
			if(str_Chk.equals("")){
				pstmt.executeUpdate();
			}
			con.close();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    
    // 북마크 조회
    public ArrayList<BookMark_Dtl> bookmark_Select(String str_Chk, String str_BM_Id) {
    	try {
    		Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			stm = con.createStatement();
			
			BookMark_Dtl bm_Dtl;
			ArrayList<BookMark_Dtl> arr_Hi = new ArrayList<>(); 
			
			StringBuilder sb_Sql = new StringBuilder("		SELECT BD.BM_DTL_ID, BD.BM_DTL_IDT, BD.X_SWIFI_MGR_NO, BD.BM_DTL_LAT, BD.BM_DTL_LNT, BH.BM_HDR_NAME, WI.X_SWIFI_MAIN_NM ");
									   sb_Sql.append("		  FROM BOOKMARK_DTL		 BD ");
									   sb_Sql.append("	INNER JOIN BOOKMARK_HDR		 BH ");
									   sb_Sql.append("			ON BD.BM_HDR_ID		 = BH.BM_HDR_ID ");
									   sb_Sql.append("	INNER JOIN WIFI_INFO	 	 WI ");
									   sb_Sql.append("			ON BD.X_SWIFI_MGR_NO = WI.X_SWIFI_MGR_NO ");
			if(str_Chk.equals("Equals")){
									   sb_Sql.append("		 WHERE BD.BM_DTL_ID = ? ");
			}
									   sb_Sql.append("	  ORDER BY BD.BM_DTL_ID");
									   
									   
			if(str_Chk.equals("Equals")){
				pstmt = con.prepareStatement(sb_Sql.toString());
				pstmt.setInt(1, Integer.parseInt(str_BM_Id));
				rs = pstmt.executeQuery();
			} else {
				rs = stm.executeQuery(sb_Sql.toString());
			}
			while(rs.next()) {
				bm_Dtl = new BookMark_Dtl();
				bm_Dtl.setBM_DTL_ID(Integer.parseInt(rs.getString("BM_DTL_ID")));
				bm_Dtl.setBM_HDR_NAME(rs.getString("BM_HDR_NAME"));
				bm_Dtl.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				bm_Dtl.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				bm_Dtl.setBM_DTL_LAT(Double.parseDouble(rs.getString("BM_DTL_LAT")));
				bm_Dtl.setBM_DTL_LNT(Double.parseDouble(rs.getString("BM_DTL_LNT")));
				bm_Dtl.setBM_DTL_IDT(rs.getString("BM_DTL_IDT"));
				
				arr_Hi.add(bm_Dtl);
			}
			
			con.close();
			return arr_Hi;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    
    
    // 북마크 저장
    public int bookmark_Insert(String str_Option, String str_Mgr_No) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			
			StringBuilder sb_Sql = new StringBuilder("INSERT INTO BOOKMARK_DTL ");
									   sb_Sql.append("SELECT IFNULL(MAX(BM_DTL_ID), 0) + 1, ?, ?, ?, ?, NOW() ");
									   sb_Sql.append("  FROM BOOKMARK_DTL");
			

			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setString(1, str_Option);
			pstmt.setString(2, str_Mgr_No);
			pstmt.setDouble(3, Lat_Lnt.LAT);
			pstmt.setDouble(4, Lat_Lnt.LNT);
			pstmt.executeUpdate();
			con.close();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    
    // 북마크 삭제
    public int bookmark_Delete(String str_BM_Id) {
    	try {
			Class.forName(mstr_Driver);
			con = DriverManager.getConnection(mstr_Url, mstr_ID, mstr_Pass);
			
			StringBuilder sb_Sql = new StringBuilder("	DELETE ");
									   sb_Sql.append("	  FROM BOOKMARK_DTL");
									   sb_Sql.append("   WHERE BM_DTL_ID = ?");
										  
			
			pstmt = con.prepareStatement(sb_Sql.toString());
			pstmt.setInt(1, Integer.parseInt(str_BM_Id));
			pstmt.executeUpdate();
			con.close();
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
        }
    }
    
    
    public static void main(String[] args){
    }
}