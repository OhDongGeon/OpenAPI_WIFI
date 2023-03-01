<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>   
   
<%@ page import="dao.DBConnection"%>
<%@ page import="dto.BookMark_Hdr"%>  
    
<%
	request.setCharacterEncoding("UTF-8");
	String str_BM_Id = request.getParameter("BM_HDR_ID");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">

<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 그룹 수정</h1>
	
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
 		ArrayList<BookMark_Hdr> arr = dbc.bookmarkgroup_Select("Equals", str_BM_Id);
 		for(BookMark_Hdr item : arr) {
 	%>
	<form action="Bookmark_Group_DB.jsp" method="POST">
		<table id="table_height_style">
 			<tr>
 				<th>북마크 그룹 이름</th>
 				<td><input id="input_text" name="nm_BM_Name" type="text" value="<%= item.BM_HDR_NAME%>"></td>
 			</tr>
 			<tr>
   				<th>순서</th>
   				<td><input id="input_text" name="nm_BM_Seq" type="text" value="<%= item.BM_HDR_SEQ%>"></td>
 			</tr>
 		 	<tr>
   				<td style="text-align: center;" colspan="2">
   					<a href="Bookmark_Group_List.jsp">돌아가기</a>
   					<label>|</label>
   					<button type="submit" name="DBConnection" value="Update">수정</button>
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