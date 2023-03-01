<%@page import="dto.Lat_Lnt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>
<%@ page import="dto.Wifi_Info"%>
<%@ page import="dto.BookMark_Hdr"%>

<%
	request.setCharacterEncoding("UTF-8");
	String str_Mgr_No = request.getParameter("MGR_NO");
	Lat_Lnt.LAT = Double.parseDouble(request.getParameter("Lat"));
	Lat_Lnt.LNT = Double.parseDouble(request.getParameter("Lnt"));
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">


<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>

	<nav id="href">
		<ul>
			<li><a href="Main_Page.jsp">홈</a></li>
			<li>|</li>
			<li><a href="History_List.jsp">위치 히스토리 목록</a></li>
			<li>|</li>
			<li><a href="Save_Page.jsp">Open API 와이파이 정보 가져오기</a></li>
			<li>|</li>
			<li><a href="Bookmark_List.jsp">북마크 보기</a></li>
			<li>|</li>
			<li><a href="Bookmark_Group_List.jsp">북마크 그룹 관리</a></li>
		</ul>
	</nav>
	<%
		DBConnection dbc = new DBConnection();
		ArrayList<BookMark_Hdr> arr_Bm = dbc.bookmarkgroup_Select("OrderSeq", "");
	%>
	<form id="form" action="Bookmark_DB.jsp" method="POST">
		<select name="nm_Option" style="height:22px;">
			<option value = "" selected>북마크 그룹 이름 선택</option>
	<%
		for(BookMark_Hdr item : arr_Bm) {
	%>
			<option value = "<%= item.BM_HDR_ID%>"><%= item.BM_HDR_NAME%></option>
	<%
		}
	%>
		</select>
        <button name="DBConnection" type="submit" value="Insert">북마크 추가하기</button>
        <input name="nm_Mgr_No" type="hidden" value="<%= str_Mgr_No%>">
	</form>
	
	<%
		ArrayList<Wifi_Info> arr_Wifi = dbc.wifi_Select(str_Mgr_No);
		for(Wifi_Info item : arr_Wifi) {
	%>
	<table id="table_height_style">
 		<tr>
 			<th>거리(Km)</th>
 			<td><%= item.KM%></td>
 		</tr>
 		<tr>
   			<th>관리번호</th>
   			<td><%= item.X_SWIFI_MGR_NO%></td>
 		</tr>
 		<tr>
   			<th>자치구</th>
   			<td><%= item.X_SWIFI_WRDOFC%></td>
 		</tr>
 		<tr>
   			<th>와이파이명</th>
   			<td><%= item.X_SWIFI_MAIN_NM%></td>
 		</tr>
 		<tr>
   			<th>도로명주소</th>
   			<td><%= item.X_SWIFI_ADRES1%></td>
 		</tr>
 		<tr>
   			<th>상세주소</th>
   			<td><%= item.X_SWIFI_ADRES2%></td>
 		</tr>
 		<tr>
   			<th>설치위치(층)</th>
   			<td><%= item.X_SWIFI_INSTL_FLOOR%></td>
 		</tr>
 		<tr>
   			<th>설치유형</th>
   			<td><%= item.X_SWIFI_INSTL_TY%></td>
 		</tr>
 		<tr>
   			<th>설치기관</th>
   			<td><%= item.X_SWIFI_INSTL_MBY%></td>
 		</tr>
 		<tr>
   			<th>서비스구분</th>
   			<td><%= item.X_SWIFI_SVC_SE%></td>
 		</tr>
 		<tr>
   			<th>망종류</th>
   			<td><%= item.X_SWIFI_CMCWR%></td>
 		</tr>
 		<tr>
   			<th>설치년도</th>
   			<td><%= item.X_SWIFI_CNSTC_YEAR%></td>
 		</tr>
 		<tr>
   			<th>실내외구분</th>
   			<td><%= item.X_SWIFI_INOUT_DOOR%></td>
 		</tr>
 		<tr>
   			<th>WIFI접속환경</th>
   			<td><%= item.X_SWIFI_REMARS3%></td>
 		</tr>
 		<tr>
   			<th>X좌표</th>
   			<td><%= item.LNT%></td>
 		</tr>
 		<tr>
   			<th>Y좌표</th>
   			<td><%= item.LAT%></td>
 		</tr>
 		<tr>
   			<th>작업일자</th>
   			<td><%= item.WORK_DTTM%></td>
 		</tr>
	</table>
	<%
		}
	%>
</body>
</html>