package com.zhongsou.mybatis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LobJdbcDao {
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=UTF8";
	private static final String username = "root";
	private static final String password = "root";
	
	public static Connection makeConnection() {
		Connection conn = null;
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void insertLob() {
		Connection conn = makeConnection();
		try {
			conn.setAutoCommit(false);
			
			PreparedStatement statement = conn.prepareStatement(
					"insert into lob(lob_blob, lob_text) values(?, ?)");
			
			File blobFile = new File(LobJdbcDao.class.getResource("1.gif").getFile());
			InputStream blobStream = new FileInputStream(blobFile);
			statement.setBinaryStream(1, blobStream, blobFile.length());
			
			File textFile = new File(LobJdbcDao.class.getResource("1.txt").getFile());
			InputStream textStream = new FileInputStream(textFile);
			InputStreamReader textReader = new InputStreamReader(textStream);
			statement.setCharacterStream(2, textReader, textFile.length());
			
			statement.executeUpdate();
			conn.commit();
			
			statement.close();
			
		} catch (FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
	}
	
	public static void selectLob() {
		Connection conn = makeConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"select * from lob where lob_id = ?");
			statement.setLong(1, 4);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Reader reader = resultSet.getCharacterStream("lob_text");
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = null;
                while (null != (line = bufferedReader.readLine())) {
                    System.out.println(line); //将其输出至屏幕，实际你可以按照需要处理
                }
			}
			
			resultSet.close();
			statement.close();
			
		} catch (FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
	}
}
