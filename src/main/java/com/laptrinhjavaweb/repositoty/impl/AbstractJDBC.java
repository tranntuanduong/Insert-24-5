package com.laptrinhjavaweb.repositoty.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.repository.GenericJDBC;

public class AbstractJDBC<T> implements GenericJDBC<T> {
	
	private Class<T> zClass;
	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/estate4month2019";
			String user = "root";
			String password = "1998";
			return DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> select(String sql, Object... parameters) {	
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();	
		try(Connection conn = getConnection();
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ResultSet resultSet = ptmt.executeQuery()
				){
			Class.forName("com.mysql.jdbc.Driver");
			if(conn!=null) {			
				for (int i = 0 ; i < parameters.length ; i++) {
					int index = i + 1;
					ptmt.setObject(index, parameters[i]);
				}	
				return resultSetMapper.mapRow(resultSet, this.zClass);	
			}	
		} catch (ClassNotFoundException | SQLException e) { 
			System.out.println(e.getMessage());
		}	
		return null;
	}

	@Override
	public void update(String sql, Object... parameters) {	
		Connection conn = null;
		PreparedStatement ptmt = null;	
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql);
			Class.forName("com.mysql.jdbc.Driver");	
			if(conn!=null) {
				for( int i = 0 ; i < parameters.length ; i++) {
					int index = i + 1;
					ptmt.setObject(index, parameters[i]);
				}
			}
			ptmt.executeUpdate();{
				System.out.println("thanh cong");
			}
			conn.commit();
		} catch (SQLException | ClassNotFoundException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(ptmt!=null) {
					ptmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}			
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			Class.forName("com.mysql.jdbc.Driver");
			if(conn!=null) {
				for (int i = 0 ; i < parameters.length ; i++) {
					int index = i + 1;
					ptmt.setObject(index, parameters[i]);
				}			
				int rowInserted = ptmt.executeUpdate();
				resultSet = ptmt.getGeneratedKeys();
				conn.commit();
				
				if(rowInserted!=0) {
					if(resultSet.next()) {
						long id = resultSet.getLong(1);
						return id;
					}
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			if(conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {		
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(ptmt!=null) {
					ptmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
