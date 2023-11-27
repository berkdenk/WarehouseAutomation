package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import com.DataBase.Util.mysql_Baglanti;
import com.DataBase.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SampleController {
	
	Connection baglanti=null;
	Alert alert;
	public SampleController() {
		baglanti=mysql_Baglanti.Connect();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_id;

    @FXML
    private PasswordField txt_sifre;

    @FXML
    private Button btn_giris;

    
    PreparedStatement sorgu=null;
    
    ResultSet cevap=null;
    String sql;
    public void kullanici_giris()
    {
    	if(!txt_id.getText().isEmpty() && !txt_sifre.getText().isEmpty())
    	{
    		try {
				sorgu=baglanti.prepareStatement("Select * from kullanicilar where k_ad=? and k_sifre=?");
				sorgu.setString(1, txt_id.getText());
				sorgu.setString(2, txt_sifre.getText());
				cevap=sorgu.executeQuery();
				if(cevap.next())
				{
					if(cevap.getInt("k_yetki")==0)
					try {
						FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScreen.fxml"));
						AnchorPane MainPane=(AnchorPane) loader.load();
						MainScreen nesne=loader.getController();
						Scene MainScene=new Scene(MainPane);
						Stage MainStage=new Stage();
						MainStage.setScene(MainScene);
						MainStage.getIcons().add(new Image(getClass().getResourceAsStream("storage.png")));
						MainStage.setTitle("Depo Ýþlemleri");
						MainStage.show();
						btn_giris.getScene().getWindow().hide();
					} catch(Exception e) {
						e.printStackTrace();
					}
					else if(cevap.getInt("k_yetki")==1)
						try {
							FXMLLoader loader=new FXMLLoader(getClass().getResource("admin_panel.fxml"));
							AnchorPane MainPane=(AnchorPane) loader.load();
							admin_panel nesne=loader.getController();
							Scene MainScene=new Scene(MainPane);
							Stage MainStage=new Stage();
							MainStage.setScene(MainScene);
							MainStage.getIcons().add(new Image(getClass().getResourceAsStream("storage.png")));
							MainStage.setTitle("Yönetici Ýþlemleri");
							MainStage.show();
							btn_giris.getScene().getWindow().hide();
						} catch(Exception e) {
							e.printStackTrace();
						}
					
				}
				else
				{
					System.out.println(txt_sifre.getText());
					alert= new Alert(AlertType.WARNING);
					alert.setTitle("Kullanici Giriþ");
					alert.setHeaderText("Geçersiz Deðerler");
					alert.setContentText("Eþleþmeyen Kullanici ad veya þifresi");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}
    	else
    	{
    	alert= new Alert(AlertType.WARNING);
		alert.setTitle("Kullanici Giriþ");
		alert.setHeaderText("Geçersiz Deðerler");
		alert.setContentText("Boþ Deðer Býrakmayýnýz.!");
		alert.showAndWait();
    	}
    }
    
    @FXML
    void initialize() {
       txt_id.setText("berktug2");
       txt_sifre.setText("berktug1234");
    	
    }
}
