<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat</title>
</head>
<body>
    <% 
        String username = (String)request.getSession().getAttribute("username");
        out.print("Welcome "+ username);
    %>

    <textarea id="messageTextarea" readonly="readonly"></textarea>
    <input type="text" id="messageText"/>
    <button onclick="sendMessage();">Send</button>
    <script>
        const websocket = new WebSocket("ws://localhost:8080/order-management/chat");
        websocket.onmessage = (message)=>{
            const jsonData = JSON.parse(message.data);
             console.log("Message send",jsonData);
            if(jsonData) messageTextarea.value += jsonData.message + "\n";
        }
        
        const sendMessage = ()=>{
            console.log("mesage",messageText,messageText.value);
            websocket.send(messageText.value);
            messageText.value="";
        }
    </script>
</body>
</html>