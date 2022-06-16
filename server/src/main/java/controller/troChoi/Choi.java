package controller.troChoi;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import socket.CauHinh;

@ServerEndpoint(value="/tro-choi", configurator = CauHinh.class)
public class Choi {
    static Set<Session> chatroom_users = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void handleOpen(EndpointConfig endpoint_config, Session user_session){
        System.out.println("-----------Session--"+user_session.getUserProperties()+"//"+endpoint_config.getUserProperties().get("email"));
        user_session.getUserProperties().put("email", endpoint_config.getUserProperties().get("email"));
        chatroom_users.add(user_session);
    }

    @OnMessage
    public void handleMessage(String message, Session user_session){
        System.out.println("message "+ message);
        String email = (String)user_session.getUserProperties().get("email");
        System.out.println("-----------Email"+email);
        // for(Session x : chatroom_users){
        //     System.out.println("-----------Message SEND"+message);
        //      x.getAsyncRemote().sendText(buildStringJson(email, message));
        // }
        for (Iterator<Session> i = chatroom_users.stream().iterator(); i.hasNext();) {
            Session x = i.next();
            try {
                x.getAsyncRemote().sendText(buildStringJson(email, message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // chatroom_users.stream().forEach(x ->{
        //     try {
                
        //     } catch (Exception e) {
        //         //TODO: handle exception
        //     }
        // });
    }

    @OnClose
    public void handleClose(Session user_session){
        System.out.println("-----------Close"+user_session);
        // chatroom_users.remove(user_session);
    }

    @OnError
    public void handleError(Throwable t){}

    private String buildStringJson(String username, String message){
        JsonObject  json_object = Json.createObjectBuilder().add("message",username+": "+message).build();
        System.out.println("-----------MessageJSON"+username+":"+message);
        StringWriter sw = new StringWriter();
        JsonWriter json_writer =  Json.createWriter(sw);
        json_writer.write(json_object);
        return sw.toString();
    }
}
