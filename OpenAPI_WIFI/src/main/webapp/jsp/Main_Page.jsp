<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>   
<%@ page import="dto.Wifi_Info"%>   
<%@ page import="dto.Lat_Lnt"%>   

<%
	request.setCharacterEncoding("UTF-8");
	String str_Lat = request.getParameter("nm_Lat");
	String str_Lnt = request.getParameter("nm_Lnt");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/My_Location.js" type="text/javascript"></script>

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
	
	<form id="form" action="Main_Page.jsp" method="POST">
		<label>LNT: <input id="id_Lnt" name="nm_Lnt" type="text" value="<%= str_Lnt == null ? "" : str_Lnt%>"></label>,	
		<label>LAT: <input id="id_Lat" name="nm_Lat" type="text" value="<%= str_Lat == null ? "" : str_Lat%>"></label>	
		<button type="button" onclick="my_Location()">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 가져오기</button>
	</form>

	 
	<table id="table_width_style">
 		<tr>
 			<th>거리(Km)</th>
		    <th>관리번호</th>
		    <th>자치구</th>
		    <th>와이파이명</th>
		    <th>도로명주소</th>
		    <th>상세주소</th>
		    <th>설치위치(층)</th>
		    <th>설치유형</th>
		    <th>설치기관</th>
		    <th>서비스구분</th>
		    <th>망종류</th>
		    <th>설치년도</th>
		    <th>실내외구분</th>
		    <th>WIFI접속환경</th>
		    <th>X좌표</th>
		    <th>Y좌표</th>
		    <th>작업일자</th>
 		</tr>
 		<%
 			if((str_Lat == null ? "" : str_Lat) == "" || (str_Lnt == null ? "" : str_Lnt) == "") {
 		%>	
 		<tr>
   			<td colspan="17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
 		</tr>	
 		<%
 			} else {
 				DBConnection dbc = new DBConnection();
 				Double dou_Lat = Double.parseDouble(str_Lat);
 				Double dou_Lnt = Double.parseDouble(str_Lnt);
 				
 				// 위치 히스토리 저장
 				dbc.history_Info_Insert(dou_Lat, dou_Lnt);
 				// WIFI 정보 조회
 				ArrayList<Wifi_Info> arr = dbc.wifi_Select(dou_Lat, dou_Lnt);
 				for(Wifi_Info item : arr) {
 		%>
 		<tr>
   			<td><%= item.KM%></td>
   			<td><%= item.X_SWIFI_MGR_NO%></td>
   			<td><%= item.X_SWIFI_WRDOFC%></td>
   			<td><a href="Wifi_Info.jsp?MGR_NO=<%= item.X_SWIFI_MGR_NO%>&Lat=<%= dou_Lat%>&Lnt=<%= dou_Lnt%>"> <%= item.X_SWIFI_MAIN_NM%></a></td>
   			<td><%= item.X_SWIFI_ADRES1%></td>
   			<td><%= item.X_SWIFI_ADRES2%></td>
   			<td><%= item.X_SWIFI_INSTL_FLOOR%></td>
   			<td><%= item.X_SWIFI_INSTL_TY%></td>
   			<td><%= item.X_SWIFI_INSTL_MBY%></td>
   			<td><%= item.X_SWIFI_SVC_SE%></td>
   			<td><%= item.X_SWIFI_CMCWR%></td>
   			<td><%= item.X_SWIFI_CNSTC_YEAR%></td>
   			<td><%= item.X_SWIFI_INOUT_DOOR%></td>
   			<td><%= item.X_SWIFI_REMARS3%></td>
   			<td><%= item.LNT%></td>
   			<td><%= item.LAT%></td>
   			<td><%= item.WORK_DTTM%></td>
 		</tr>
 		<%	
 				}
 			}
 		%>	
	</table>
</body>
</html>