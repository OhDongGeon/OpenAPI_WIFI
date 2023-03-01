<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>
<%@ page import="dto.BookMark_Hdr"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">

<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 그룹 목록</h1>

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
	
	<form id="form" action="Bookmark_Group_Insert.jsp" method="POST">
        <button type="submit">북마크 그룹 이름 추가</button>
	</form>
	
	 
	<table id="table_width_style">
 		<tr>
 			<th>ID</th>
		    <th>북마크 그룹 이름</th>
		    <th>순서</th>
		    <th>등록일자</th>
		    <th>수정일자</th>
		    <th>비고</th>
 		</tr>
 		<%
 			DBConnection dbc = new DBConnection();
 			ArrayList<BookMark_Hdr> arr = dbc.bookmarkgroup_Select("All", "");
 			
 			if(arr.size() == 0) {
 		%>
 		<tr>
   			<td colspan="16" align="center">정보가 존재하지 않습니다.</td>
 		</tr>
 		<%		
 			} else {
 				for(BookMark_Hdr item : arr) {
 		%>		
 		<tr>
   			<td><%= item.BM_HDR_ID%></td>
   			<td><%= item.BM_HDR_NAME%></td>
   			<td><%= item.BM_HDR_SEQ%></td>
   			<td><%= item.BM_HDR_IDT%></td>
   			<td><%= item.BM_HDR_UDT%></td>
   			<td style="text-align: center;">
   					<a href="Bookmark_Group_Update.jsp?BM_HDR_ID=<%= item.BM_HDR_ID%>">수정</a> |
   					<a href="Bookmark_Group_Delete.jsp?BM_HDR_ID=<%= item.BM_HDR_ID%>">삭제</a>
   			</td>
 		</tr>
 		<%
 				}
 			}
 		%>
	</table>
</body>
</html>