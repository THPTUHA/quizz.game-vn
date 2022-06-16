<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
</head>
<body>
    <%
        String message = (String)request.getAttribute("message");
        if(message!= null && message.length() > 0){
            out.write("<h1 style='color:red;'>"+ message +"</h1>");
        }
    %>
    <form action="dang-ky" method="post">  
        UserName:<input type="text" name="username"/><br/>  
        FullName:<input type="text" name="fullname"/><br/> 
        Email:<input type="text" name="email"/><br/>  
        Password:<input type="password" name="password"/><br/>  
        RePassword:<input type="password" name="repassword"/><br/> 
        <input type="submit" />  
    </form>  
</body>
</html>