<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<%@ page import="dao.DBConnection"%>
    
<%
	request.setCharacterEncoding("UTF-8");
 	String str_DB = request.getParameter("DBConnection");
	String str_Option = request.getParameter("nm_Option");
	String str_Mgr_No = request.getParameter("nm_Mgr_No");
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
			if(str_Option.equals("")) {
	%>
			<script>alert("북마크 그룹을 선택하세요.")</script>
			<script>history.back()</script>
	<%
			} else {
				i_Chk = dbc.bookmark_Insert(str_Option, str_Mgr_No);
				if(i_Chk == 0) {
	%>
					<script>alert("북마크 정보를 추가하였습니다.")</script>
					<script>location.href = 'Bookmark_List.jsp'</script>
	<%
					
				} else {
	%>
					<script>alert("다시 시도해 주세요.")</script>
					<script>history.back()</script>
	<%	
				}
			}
		} else if(str_DB.equals("Delete")) {
			i_Chk = dbc.bookmark_Delete(str_BM_Id);
			if(i_Chk == 0) {
				if(i_Chk == 0) {
	%>
					<script>alert("북마크 정보를 삭제하였습니다.")</script>
					<script>location.href = 'Bookmark_List.jsp'</script>
	<%
				} else {
	%>
					<script>alert("다시 시도해 주세요.")</script>
					<script>history.back()</script>
	<%
				}
			}
		}
	%>
</body>
</html>