package com.DataBase.Util;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class mysql_Baglanti {

	

	static Connection cnn=null;
	public static Connection Connect()
	{
		try {
			String url = "jdbc:mysql://localhost:/depodb";
			String user = "root";
			String password = "mysql";	 
			cnn=DriverManager.getConnection(url,user,password);
			return cnn;
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Veri Tabaný Baðlantý");
			alert.setHeaderText("Baðlantý Sorunu");
			alert.setContentText("Lütfen baðlantýnýzý Kontrol edin.");
			alert.showAndWait();
			
			return null;
		}
	}

}







