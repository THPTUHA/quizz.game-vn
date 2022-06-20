package lop;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import tienIch.TienIch;

public class GiaiMa implements Decoder.Text<Lenh> {

    @Override
    public Lenh decode(String s) throws DecodeException {
        return (Lenh)TienIch.layObject(s, Lenh.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
