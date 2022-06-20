package lop;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import tienIch.TienIch;

public class MaHoa implements Encoder.Text<Lenh>{

    @Override
    public String encode(Lenh lenh) throws EncodeException {
        return TienIch.bienDoiThanhJson(lenh);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
