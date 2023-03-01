<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../css/style.css" rel="stylesheet" type="text/css">

<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1>북마크 그룹 추가</h1>

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
	
	<form action="Bookmark_Group_DB.jsp" method="POST">
		<table id="table_height_style">
 			<tr>
 				<th>북마크 그룹 이름</th>
 				<td><input id="input_text" name="nm_BM_Name" type="text"></td>
 			</tr>
 			<tr>
   				<th>순서</th>
   				<td><input id="input_text" name="nm_BM_Seq" type="text"></td>
 			</tr>
 		 	<tr>
   				<td style="text-align: center;" colspan="2"><button type="submit" name="DBConnection" value="Insert">추가</button></td>
 			</tr>
		</table>
	</form>
</body>
</html>