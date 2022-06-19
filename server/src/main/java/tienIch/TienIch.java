package tienIch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hangSo.HangSo;
import static java.util.stream.Collectors.joining;


public class TienIch extends HttpServlet{
    HangSo hangSo = new HangSo();
    public static String getUrl(String url){
       return "/" + HangSo.url_roort + "/" + url;
    }

	public static String docLuongDauVao(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
	}

	public static String bienDoiThanhJson(Object obj){
		Gson _gson = new Gson();
		return _gson.toJson(obj);
	}

    public static  void guiJson(HttpServletResponse response, Object obj) throws IOException {
		Gson _gson = new Gson();
		response.setContentType("application/json; charset=UTF-8");
		String res = _gson.toJson(obj);
		     
		PrintWriter out = response.getWriter();
		  
		out.print(res);
		out.flush();
	}

	public static  Object layObject(HttpServletRequest req, Class object) throws IOException{
		Gson GSON = new GsonBuilder().create();
		String json = docLuongDauVao(req.getInputStream());
		return  GSON.fromJson(json,object);
	}

	public static  Object layObject(String json, Class object) throws IOException{
		Gson GSON = new GsonBuilder().create();
		return  GSON.fromJson(json,object);
	}
}

