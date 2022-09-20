/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mario
 */

import java.awt.List;
import java.math.BigInteger;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BD {
    private Connection con;
    public BD(){
        con = null;
    }
    
    private Connection conectar(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_examen","root","root34");
            if (con != null) {
                System.out.println("Conectado");
            } 
        }
        catch(SQLException e)
        {
            con = null;
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            con = null;
            System.out.println(e.getMessage());
        }
        
        return con;
    }
    
    private void desconectar() throws SQLException{
        if(con != null)
        {
            con.close();
            con = null;
        }
    }
    
    public boolean InsertarCliente(Estudiantes est){
        boolean resp = false;
        
        
        try{
            if(conectar() != null){
                String query = "INSERT INTO estudiantes (carnet, nombres, apellidos, direccion, telefono, email) " +
                        "VALUES (?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, est.getCarnet());
                statement.setString(2, est.getNombres());
                statement.setString(3, est.getApellidos());
                statement.setString(4, est.getDireccion());
                statement.setInt(5, est.getTelefono());
                statement.setString(6, est.getEmail());
                
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp = true;
                }else{
                    resp = false;
                }
            }
        }catch(Exception ex){
            resp = false;
            System.out.println(ex.getMessage());
        }
        return resp;
    }
    
    public ArrayList<Estudiantes> GetClientes(){
        ArrayList<Estudiantes> lst_Clientes = new ArrayList<Estudiantes>();
        try{
            if(conectar() != null){
                String sql = "SELECT * FROM estudiantes";
 
                Statement statement = con.createStatement();
                ResultSet result = statement.executeQuery(sql);

                int count = 0;

                while (result.next()){
                    Estudiantes cli = new Estudiantes();
                    cli.setId_estudiante(result.getLong(1));
                    cli.setCarnet(result.getString(2));
                    cli.setNombres(result.getString(3));
                    cli.setApellidos(result.getString(4));
                    cli.setDireccion(result.getString(5));
                    cli.setTelefono(result.getInt(6));
                    lst_Clientes.add(cli);
                }
            }
        }catch(Exception ex){
            lst_Clientes = null;
            System.out.println(ex.getMessage());
        }
        return lst_Clientes;
    }
    
    public boolean UpdateCliente(Estudiantes cli){
        boolean resp = false;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        try{
            if(conectar() != null){
                String query = "UPDATE estudiantes SET nombres = ?, apellidos = ?, direccion = ?, telefono = ? " +
                        "WHERE carnet = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, cli.getNombres());
                statement.setString(2, cli.getApellidos());
                statement.setString(3, cli.getDireccion());
                statement.setInt(4, cli.getTelefono());
                statement.setString(5, cli.getCarnet());
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp = true;
                }else{
                    resp = false;
                }
            }
        }catch(Exception ex){
            resp = false;
            System.out.println(ex.getMessage());
        }
        return resp;
    }
    
    public boolean DeleteCliente(String id_cli){
        boolean resp = false;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        try{
            if(conectar() != null){
                String query = "DELETE FROM estudiantes WHERE carnet = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, id_cli);
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp = true;
                }else{
                    resp = false;
                }
            }
        }catch(Exception ex){
            resp = false;
            System.out.println(ex.getMessage());
        }
        return resp;
    }
}
