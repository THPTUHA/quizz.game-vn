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
    <table border ='1'>
        <tbody >
            <tr>
                <th></th>
                <%for(int i =1; i< 10 ;++i){%>
                    <th>
                        <%= i %>
                    </th>
                <%}%>
            </tr>
            <%for(int i =1; i< 10 ;++i){%>
                <tr>
                    <th>
                        <%= i %>
                    </th>
                    <%for(int j =1; j< 10 ;++j){%>
                    <th>
                        <%= i %> * <%= j %> = <%= i*j %>
                    </th>
                     <%}%>
                </tr>
            <%}%>
        </tbody>
    </table>
</body>
</html>