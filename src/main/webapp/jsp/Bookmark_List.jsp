<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>
<%@ page import="dto.BookMark_Dtl"%>
<%@ page import="dto.Lat_Lnt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">

<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 목록</h1>

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
	
	<table id="table_width_style">
 		<tr>
 			<th>ID</th>
		    <th>북마크 그룹 이름</th>
		    <th>와이파이명</th>
		    <th>등록일자</th>
		    <th>비고</th>
 		</tr>
 		<%
 			DBConnection dbc = new DBConnection();
 			ArrayList<BookMark_Dtl> arr = dbc.bookmark_Select("All", "");
 			
 			if(arr.size() == 0) {
 		%>
 		<tr>
   			<td colspan="16" align="center">정보가 존재하지 않습니다.</td>
 		</tr>
 		<%		
 			} else {
 				for(BookMark_Dtl item : arr) {
 		%>		
 		<tr>
   			<td><%= item.BM_DTL_ID%></td>
   			<td><%= item.BM_HDR_NAME%></td>
   			<td><a href="Wifi_Info.jsp?MGR_NO=<%= item.X_SWIFI_MGR_NO%>&Lat=<%= item.BM_DTL_LAT%>&Lnt=<%= item.BM_DTL_LNT%>"><%= item.X_SWIFI_MAIN_NM%></a></td>
   			<td><%= item.BM_DTL_IDT%></td>
   			<td style="text-align: center;">
   					<a href="Bookmark_Delete.jsp?BM_DTL_ID=<%= item.BM_DTL_ID%>">삭제</a>
   			</td>
 		</tr>
 		<%
 				}
 			}
 		%>
	</table>
</body>
</html>