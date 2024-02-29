<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="dao.DBConnection"%>

<%
	request.setCharacterEncoding("UTF-8");
	String str_DB = request.getParameter("DBConnection");
	String str_HI_Id = request.getParameter("nm_HI_id");
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
		
		if(str_DB.equals("Delete")){ // 위치 히스토리 삭제
			i_Chk = dbc.history_Info_Delete(str_HI_Id);
			if(i_Chk == 0) {
	%>
				<script>alert("위치 히스토리를 삭제하였습니다.")</script>
				<script>location.href = 'History_List.jsp'</script>
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