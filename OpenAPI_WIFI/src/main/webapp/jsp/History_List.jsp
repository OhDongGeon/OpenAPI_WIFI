<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>
<%@ page import="dto.History_Info"%>  
    
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
	 
	<table id="table_width_style">
 		<tr>
 			<th>ID</th>
		    <th>X좌표</th>
		    <th>Y좌표</th>
		    <th>조회일자</th>
		    <th>비고</th>
 		</tr>
 		<%
 			DBConnection dbc = new DBConnection();
 			ArrayList<History_Info> arr = dbc.history_Info_Select();
 			
 			if(arr.size() == 0) {
 		%>
 		<tr>
   			<td colspan="17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
 		</tr>
 		<%
 			} else {
 				for(History_Info item : arr) {
 		%>
 		<tr>
 			<td><%= item.HI_ID%></td>
   			<td><%= item.HI_LNT%></td>
   			<td><%= item.HI_LAT%></td>
   			<td><%= item.HI_IDT%></td>
   			<td style="text-align: center;"><form action="History_DB.jsp" method="post">
   					<button name = "DBConnection" type="submit" value="Delete">삭제</button>
   					<input name = "nm_HI_id" type="hidden" value="<%= item.HI_ID%>">
   				</form></td>		
 		</tr>
 
 		<%
 				}
 			}
 		%>
	</table>
</body>
</html>