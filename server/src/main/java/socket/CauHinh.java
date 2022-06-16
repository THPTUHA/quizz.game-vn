package socket;

import java.util.List;
import java.util.Map;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import jwt.JwtTokenCungCap;

public class CauHinh extends ServerEndpointConfig.Configurator{
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest req, HandshakeResponse resp){
        Map<String,List<String>> params = req.getParameterMap();
        System.out.println("TOKEN"+params.get("token"));
        String email = JwtTokenCungCap.layEmailTuJWT(params.get("token").get(0));
        // String room_id = params.get("room_id").get(0);
        // System.out.println("ROOM_ID "+ room_id);
        sec.getUserProperties().put("email", email);
        // sec.getUserProperties().put("room_id",room_id);
    }
}
