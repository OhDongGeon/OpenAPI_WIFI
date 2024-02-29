<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="service.OpenAPI"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<nav style="text-align: center;">
		<label style="font-size: 20px;">
			<%	
				OpenAPI openapi = new OpenAPI();
				int i_Save_Cnt = openapi.open_Wifi_Save();
				
				if(i_Save_Cnt == -1) { 
			%>
					WIFI 정보를 다시 저장해 주세요.
			<%	
				} else {
			%>
				<%= i_Save_Cnt %>개의 WIFI 정보를 정상적으로 저장하였습니다.
			<%
				}
			%>
		</label>
	</nav>


	<nav style="text-align: center; padding-top: 15px">
		<a href="Main_Page.jsp">홈 으로 가기</a>
	</nav>

</body>
</html>