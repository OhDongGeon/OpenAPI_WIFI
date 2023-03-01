<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>
<%@ page import="dto.BookMark_Dtl"%>  
 
<%
	request.setCharacterEncoding("UTF-8");
	String str_BM_Id = request.getParameter("BM_DTL_ID");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">

<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 삭제</h1>

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
	
	<nav id="form">
		<label>북마크를 삭제 하시겠습니까?</label>
	</nav>
	
	<%
		DBConnection dbc = new DBConnection();
 		ArrayList<BookMark_Dtl> arr = dbc.bookmark_Select("Equals", str_BM_Id);
 		for(BookMark_Dtl item : arr) {
 	%>
 	<form action="Bookmark_DB.jsp" method="POST">
		<table id="table_height_style">
 			<tr>
 				<th>북마크 이름</th>
 				<td><%= item.BM_HDR_NAME%></td>
 			</tr>
 			<tr>
   				<th>와이파이명</th>
   				<td><%= item.X_SWIFI_MAIN_NM%></td>
 			</tr>
 			<tr>
   				<th>등록일자</th>
   				<td><%= item.BM_DTL_IDT%></td>
 			</tr>
 			<tr>
 				<td style="text-align: center;" colspan="2">
   					<a href="Bookmark_List.jsp">돌아가기</a>
   					<label>|</label>
   					<button type="submit" name="DBConnection" value="Delete">삭제</button>
   					<input name="nm_BM_Id" type="hidden" value="<%= str_BM_Id%>">
   				</td>
 			</tr>
		</table>
	</form>
	<%
		}
	%>
</body>
</html>