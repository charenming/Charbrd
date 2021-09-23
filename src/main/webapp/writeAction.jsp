<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="chaboard.ChaboardDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="chaboard" class="chaboard.Chaboard" scope="page" />
<jsp:setProperty name="chaboard" property="chaTitle" />
<jsp:setProperty name="chaboard" property="chaContent" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>은미 게시판</title>
</head>
<body>
<%
	String userID = null;
	if(session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if (userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요.')");
		script.println("location.href = 'login.jsp'");
		script.println("</script>");
	} else {
		if (chaboard.getChaTitle() == null || chaboard.getChaContent() == null){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('입력이 안 된 사항이 있습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					ChaboardDAO chaboardDAO = new ChaboardDAO();
			        int result = chaboardDAO.write(chaboard.getChaTitle(), userID, chaboard.getChaContent());
			        if(result == -1){
			            PrintWriter script = response.getWriter();
			            script.println("<script>");
			            script.println("alert('글쓰기에 실패했습니다.')");
			            script.println("history.back()");
			            script.println("</script>");
			        }
			        else {
			            PrintWriter script = response.getWriter();
			            script.println("<script>");
			            script.println("location.href = 'chaboard.jsp'");
			            script.println("</script>");
			            }
			    }
	}
	
%>
</body>
</html>