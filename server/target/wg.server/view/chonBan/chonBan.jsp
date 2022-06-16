<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn Bàn</title>
</head>
<body>
    <%@ page import ="java.util.List"%>
    <%@ page import ="model.object.Ban"%>
    <% List<Ban> bans = ( List<Ban>)request.getAttribute("bans");  %>
    <form action="chon-ban"  method="post">
    <table border ='1'>
        <tbody >
             <tr>
                <th>ID</th>
                <th>Ten</th>
                <th>Mo Ta</th>
                <th>Chon</th>
            </tr>
            <% for(Ban ban: bans){ %>
                 <tr>
                    <th><%= ban.getID() %></th>
                    <th><%= ban.getTen() %></th>
                    <th><%= ban.getMoTa() %></th>
                    <th><input type="checkbox" name=<%= ban.getID() %> /></th>
                </tr>
            <%}%>
        </tbody>
    </table>
        <input type="submit" >
    </form>
</body>
</html>