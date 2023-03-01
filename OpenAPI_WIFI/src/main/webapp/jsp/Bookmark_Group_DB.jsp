<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dao.DBConnection"%>

<%
	request.setCharacterEncoding("UTF-8");
	String str_DB = request.getParameter("DBConnection");
	String str_BM_Name = request.getParameter("nm_BM_Name");
	String str_BM_Seq = request.getParameter("nm_BM_Seq");
	String str_BM_Id = request.getParameter("nm_BM_Id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		DBConnection dbc = new DBConnection();
		int i_Chk;
		
		if(str_DB.equals("Insert")){ // 북마크 저장
			i_Chk = dbc.bookmarkgroup_Insert(str_BM_Name, str_BM_Seq);
			if(i_Chk == 0) {
	%>
				<script>alert("북마크 그룹 정보를 추가하였습니다.")</script>
				<script>location.href = 'Bookmark_Group_List.jsp'</script>
	<%		
			} else {
	%>
				<script>alert("다시 시도해 주세요.")</script>
				<script>history.back()</script>
	<%
			}
		} else if(str_DB.equals("Update")){ // 북마크 수정
			i_Chk = dbc.bookmarkgroup_Update(str_BM_Id, str_BM_Name, str_BM_Seq);
			if(i_Chk == 0) {
	%>	
				<script>alert("북마크 그룹 정보를 수정하였습니다.")</script>
				<script>location.href = 'Bookmark_Group_List.jsp'</script>
	<%	
			} else {
	%>
				<script>alert("다시 시도해 주세요.")</script>
				<script>history.back()</script>
	<%
			}
		} else if(str_DB.equals("Delete")){ // 북마크 삭제
			i_Chk = dbc.bookmarkgroup_Delete(str_BM_Id);
			if(i_Chk == 0) {
	%>
				<script>alert("북마크 그룹 정보를 삭제하였습니다.")</script>
				<script>location.href = 'Bookmark_Group_List.jsp'</script>
	<%
			} else if(i_Chk == 2) {
	%>
				<script>alert("해당 북마크 그룹을 사용한 북마크를 삭제해주세요.")</script>
				<script>history.back()</script>	
	<%
			} else {
	%>
				<script>alert("다시 시도해 주세요.")</script>
				<script>history.back()</script>
	<%
			}
		}
	%>
</body>
</html>