<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>
    <h1>Home</h1>
    <% 
        String ten = (String)request.getSession().getAttribute("ten");
        if(ten!=null && ten.length()>0){
            out.print("Welcome "+ ten);
            out.write("<a href='/order-management/dang-xuat'>Đăng xuất</a>");
        }else{
            out.write("<a href='/order-management/dang-nhap'>Đăng nhập</a>");
            out.write("<a href='/order-management/dang-ky'>Đăng ký</a>");
        }
    %>
    <div>
        <a href="/order-management/dat-ban">Đặt bàn</a>
    </div>
</body>
</html>