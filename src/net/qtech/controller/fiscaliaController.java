package net.qtech.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.qtech.dao.FiscaliaDAO;
import net.qtech.modelo.Fiscalia;
/**
 * Servlet implementation class fiscaliaController
 */

@WebServlet(description = "administrador tabla ficalias", urlPatterns = { "/fiscalia" })
public class fiscaliaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fiscaliaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			addCorsHeader(response);
			FiscaliaDAO fiscaliaDao = new FiscaliaDAO();
			List<Fiscalia> fiscaliasList= new ArrayList<Fiscalia>();
			try {
				fiscaliasList=fiscaliaDao.getFiscalias();

				
				String fiscaliasJson = this.gson.toJson(fiscaliasList);
				PrintWriter out = response.getWriter();
		        out.print(fiscaliasJson);
		        out.flush(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("err"+e.toString());
				e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.checkError();
		        out.print("Err");
		        out.flush(); 
			}		
	}
    private void addCorsHeader(HttpServletResponse response){
        //TODO: externalize the Allow-Origin
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addCorsHeader(response);
		String opcion = request.getParameter("opcion");
		StringBuffer jb = new StringBuffer();
		String line = null;
		JsonObject jsonObject=null;
	  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {System.out.println(e); }
	  System.out.println(jb);
	  try {
		    jsonObject =  new JsonParser().parse(jb.toString()).getAsJsonObject();
		  } catch (JsonIOException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }
		Date fecha=new Date();
		FiscaliaDAO fiscaliaDAO= new FiscaliaDAO();
		Fiscalia fiscalia= new Fiscalia();
		fiscalia.setNombre(jsonObject.get("Nombre").getAsString());
		fiscalia.setDepartamento(jsonObject.get("Departamento").getAsString());
		fiscalia.setAldea(jsonObject.get("Aldea").getAsString());
		fiscalia.setColonia(jsonObject.get("Colonia").getAsString());
		fiscalia.setCoordenadas(jsonObject.get("Coordenadas").getAsString());
		fiscalia.setDir(jsonObject.get("Dir").getAsString());
		fiscalia.setReferencia(jsonObject.get("Referencia").getAsString());
		fiscalia.setTel(jsonObject.get("Tel").getAsString());
		fiscalia.setMunicipio(jsonObject.get("Municipio").getAsString());		
		fiscalia.setFechaModificado(new java.sql.Date(fecha.getTime()));
		System.out.println(jsonObject.get("Nombre").getAsString());
		System.out.println(fiscalia);
		//doGet(request, response);
			if(opcion.equals("crear"))
				try {
					fiscaliaDAO.guardar(fiscalia);
				} catch (SQLException e) {
					//POST TODO Auto-generated catch block
					e.printStackTrace();
				}
			else {
				int id = Integer.parseInt(request.getParameter("opcion"));
				System.out.println(id);
				fiscalia.setId(id);
				
				try {
					fiscaliaDAO.editar(fiscalia);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PrintWriter out = response.getWriter();
		        out.print("200");
		        out.flush(); 		        
			}

	}
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		addCorsHeader(resp);
		System.out.println("del");
	}

}
