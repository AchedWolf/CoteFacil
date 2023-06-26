package com.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.models.PlanetModel;

public class H2Controller {
	
	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public H2Controller() {
    	System.out.println("H2 executando");
		try{
			createTable();
		}
		catch(SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
		}
    }
    
	public void init() throws SQLException {
	}
    
	private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

	private static void createTable() throws SQLException {
		Connection connection = getDBConnection();
        Statement stmt = null;
        
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PLANETS(id int primary key, nome varchar(255), clima varchar(255), terreno varchar(255), numAparicao int);");
            stmt.close();
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            System.out.println("Tabela Criada");
        }
	}
    
    public boolean insert(PlanetModel planet) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;

        String InsertQuery = "INSERT INTO PLANETS" + "(id, nome, clima, terreno, numAparicao) values" + "(?,?,?,?,?);";
        try {
            connection.setAutoCommit(false);
           
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, planet.getId());
            insertPreparedStatement.setString(2, planet.getNome());
            insertPreparedStatement.setString(3, planet.getClima());
            insertPreparedStatement.setString(4, planet.getTerreno());
            insertPreparedStatement.setInt(5, planet.getNumAparicao());
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
        }
        
        return true;
    }
    
    public List<PlanetModel> getAll() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        List<PlanetModel> planetList = new ArrayList<PlanetModel>();
        
        String SelectQuery = "SELECT * FROM PLANETS;";
        try {
            connection.setAutoCommit(false);
            
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                PlanetModel planet = new PlanetModel(rs.getInt("id"));
        		planet.setNome(rs.getString("nome"));
        		planet.setClima(rs.getString("clima"));
        		planet.setTerreno(rs.getString("terreno"));
        		planet.setNumAparicao(rs.getInt("numAparicao"));
        		
        		planetList.add(planet);
            }
            selectPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
     
        return planetList;        
    }
    
    public PlanetModel getById(int id) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        PlanetModel planet = new PlanetModel(0);
        
        String SelectQuery = "SELECT * FROM PLANETS WHERE id=" + id + ";";
        try {
            connection.setAutoCommit(false);
            
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            planet.setId(rs.getInt("id"));
        	planet.setNome(rs.getString("nome"));
        	planet.setClima(rs.getString("clima"));
        	planet.setTerreno(rs.getString("terreno"));
        	planet.setNumAparicao(rs.getInt("numAparicao"));
            selectPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
     
        return planet;        
    }
    
    public PlanetModel getByNome(String nome) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        PlanetModel planet = new PlanetModel(0);
        
        String SelectQuery = "SELECT * FROM PLANETS WHERE nome=" + nome + ";";
        try {
            connection.setAutoCommit(false);
            
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            planet.setId(rs.getInt("id"));
        	planet.setNome(rs.getString("nome"));
        	planet.setClima(rs.getString("clima"));
        	planet.setTerreno(rs.getString("terreno"));
        	planet.setNumAparicao(rs.getInt("numAparicao"));
            selectPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
     
        return planet;        
    }
    
    public boolean removetById(int id) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement removePreparedStatement = null;
        
        String RemoveQuery = "DELETE FROM PLANETS WHERE id=" + id + ";";
        try {
            connection.setAutoCommit(false);
            
            removePreparedStatement = connection.prepareStatement(RemoveQuery);
            removePreparedStatement.executeUpdate();
            removePreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
        }
     
        return true;        
    }
    
    public boolean removeByNome(String nome) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement removePreparedStatement = null;
        
        String RemoveQuery = "DELETE FROM PLANETS WHERE nome=" + nome + ";";
        try {
            connection.setAutoCommit(false);
            
            removePreparedStatement = connection.prepareStatement(RemoveQuery);
            removePreparedStatement.executeUpdate();
            removePreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
        }
     
        return true;     
    }
    
}
