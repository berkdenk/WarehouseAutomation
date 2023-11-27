package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class MainScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar menu_islemler;

    @FXML
    private Button btn_giris;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_stok;

    @FXML
    private AnchorPane ana_Pane;

    public void mal_giris_getir()
    {
    	
    	 AnchorPane pane1;
 		try {
 			
 			pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("mal_giris.fxml"));
 			   ana_Pane.getChildren().setAll(pane1);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			System.out.println(e.getMessage());
 		}
    }
    public void mal_cikis_getir()
    {
    	 AnchorPane pane2;
  		try {
  			
  			pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("mal_cikis.fxml"));
  			   ana_Pane.getChildren().setAll(pane2);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			System.out.println(e.getMessage());
  		}
    }
    public void stok_arttir()
    {
    	 AnchorPane pane3;
   		try {
   			
   			pane3 = (AnchorPane)FXMLLoader.load(getClass().getResource("stok_ekle.fxml"));
   			   ana_Pane.getChildren().setAll(pane3);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			System.out.println(e.getMessage());
   		}
    }
    
    public void urun_penceresi()
    {
    	AnchorPane pane4;
   		try {
   			
   			pane4 = (AnchorPane)FXMLLoader.load(getClass().getResource("stok_goruntule.fxml"));
   			   ana_Pane.getChildren().setAll(pane4);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			System.out.println(e.getMessage());
   		}
    }
    
    @FXML
    void initialize() {
       
    	  
     	

    }
}
