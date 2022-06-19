package jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NguoiDungDao;
import model.object.NguoiDung;

import javax.servlet.Filter;

@WebFilter("/*")
public class JwtBoLoc implements Filter  {

    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter jwt!");
	}

	@Override
	public void destroy() {
		System.out.println("Filter jwt destroy!");
	}

    private String layJwtTuRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken == null || bearerToken.isEmpty()){
            return "";
        }
        return bearerToken.substring(7);
    }


    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods"," POST, GET, OPTIONS, DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        httpResponse.setStatus(200);
		HttpServletRequest req = (HttpServletRequest) request;
        try {
            String jwt = layJwtTuRequest(req);
            if ( JwtTokenCungCap.thoaManToken( jwt,(HttpServletResponse)response)) {
                String ten = JwtTokenCungCap.layTenTuJWT(jwt);
                System.out.println("FUCCCCCCCCCCCCCCCCK "+ ten);
                NguoiDung nguoiDung = NguoiDungDao.layNguoiDungTheoTen(ten);
                request.setAttribute("nguoiDung",nguoiDung);
            }

            
        } catch (Exception ex) {
            System.out.println("Ko xac thuc dc nguoi dung"+ ex);
        }
		chain.doFilter(request, response);
	}
}