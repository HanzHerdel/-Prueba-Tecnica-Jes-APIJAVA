package net.qtech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.qtech.conexion.Conexion;
import net.qtech.modelo.Fiscalia;

public class FiscaliaDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//guardar Fiscalia
	public boolean guardar(Fiscalia fiscalia) throws SQLException {
		String sql=null;
		this.estadoOperacion=false;
		this.connection=obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql="INSERT INTO Fiscalias(Departamento,Municipio,Aldea,Colonia,Referencia,Tel,Dir,Coordenadas,FechaModificado,Nombre)VALUES(?,?,?,?,?,?,?,?,?,?)";
			statement=connection.prepareStatement(sql);			
			//statement.setInt(1,0);//id
			statement.setString(1, fiscalia.getDepartamento());//departamento
			statement.setString(2, fiscalia.getMunicipio());//departamento
			statement.setString(3, fiscalia.getAldea());//aldea 
			statement.setString(4, fiscalia.getColonia());//colonia
			statement.setString(5, fiscalia.getReferencia());//referencia
			statement.setString(6, fiscalia.getTel());//tel
			statement.setString(7, fiscalia.getDir());//dir
			statement.setString(8, fiscalia.getCoordenadas());//coordenandas
			statement.setDate(9, fiscalia.getFechaModificado());//fecha
			statement.setString(10, fiscalia.getNombre());//nombre
			this.estadoOperacion=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.estadoOperacion;
	}
	//editar Fiscalia
	public boolean editar(Fiscalia fiscalia) throws SQLException {
		String sql = null;
		estadoOperacion=false;
		connection=obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql="UPDATE Fiscalias SET Departamento=?,Municipio=?,Aldea=?,Colonia=?,Referencia=?,Tel=?,Dir=?,Coordenadas=?,FechaModificado=?,Nombre=? WHERE fiscaliaid=?";
			statement=connection.prepareStatement(sql);			
			//statement.setInt(1,0);//id
			statement.setString(1, fiscalia.getDepartamento());//departamento
			statement.setString(2, fiscalia.getMunicipio());//departamento
			statement.setString(3, fiscalia.getAldea());//aldea 
			statement.setString(4, fiscalia.getColonia());//colonia
			statement.setString(5, fiscalia.getReferencia());//referencia
			statement.setString(6, fiscalia.getTel());//tel
			statement.setString(7, fiscalia.getDir());//dir
			statement.setString(8, fiscalia.getCoordenadas());//coordenandas
			statement.setDate(9, fiscalia.getFechaModificado());//fecha
			statement.setString(10, fiscalia.getNombre());//nombre
			statement.setInt(11, fiscalia.getId());
			estadoOperacion=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();	

			System.out.println("dice");
		} catch (SQLException e) {

			System.out.println("asdf"+e);
			connection.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estadoOperacion;
	}
	//borrar Fiscalia
	public boolean borrar(int idFiscalia) throws SQLException {
		String sql = null;
		estadoOperacion=false;
		connection=obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql="DELETE Fiscalias WHERE fiscaliaid=?";
			statement=connection.prepareStatement(sql);			
			statement.setInt(1,idFiscalia);//id
			
			estadoOperacion=statement.executeUpdate()>0;
			connection.commit();
			statement.close();
			connection.close();	
		} catch (SQLException e) {

			System.out.println("asdf"+e);
			connection.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.estadoOperacion;
	}
	//lista de fiscalias
	public List<Fiscalia> getFiscalias() throws SQLException {
		ResultSet resultset=null;
		List<Fiscalia> listaFicalias=new ArrayList<Fiscalia>();
		String sql= null;
		estadoOperacion = false;
		connection= obtenerConexion();		
		try {
			sql="SELECT * FROM Fiscalias";
			statement=connection.prepareStatement(sql);
			resultset=statement.executeQuery();
			while(resultset.next()) {
				Fiscalia f= new Fiscalia();
				f.setId(resultset.getInt(1));
				f.setDepartamento(resultset.getString(2));
				f.setMunicipio(resultset.getString(3));
				f.setAldea(resultset.getString(4));
				f.setColonia(resultset.getString(5));
				f.setReferencia(resultset.getString(6));
				f.setTel(resultset.getString(7));
				f.setDir(resultset.getString(8));
				f.setCoordenadas(resultset.getString(9));
				f.setFechaModificado(resultset.getDate(10));
				f.setNombre(resultset.getString(11));
				listaFicalias.add(f);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return listaFicalias;
	}
	//obtener una fiscalia
	public Fiscalia getFiscalia(int idFiscalia) throws SQLException {

		return null;
	}
	// obtener conexion
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
	
}
