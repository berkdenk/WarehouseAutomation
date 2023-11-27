package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.text.Text;

import com.DataBase.Util.mysql_Baglanti;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class mal_giris extends MainScreen {

	
	Connection baglanti;
	public mal_giris() {
		baglanti=mysql_Baglanti.Connect();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_barkod;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_fiyat;

    @FXML
    private TextField txt_adet;

    @FXML
    private Button btn_kaydet;


    @FXML
    private Label lbl_hata;

    

    
    PreparedStatement sorgu=null;
    ResultSet cevap=null;
    String sql;
    public void mal_kaydet()
    {
    	if(!txt_ad.getText().isEmpty() && !txt_adet.getText().isEmpty() && !txt_barkod.getText().isEmpty() && !txt_fiyat.getText().isEmpty())
    	{
    		
    		try {
    			sorgu=baglanti.prepareStatement("select * from urunler where u_barkod=?");
				sorgu.setString(1, txt_barkod.getText());
				cevap=sorgu.executeQuery();
				if(cevap.next())
				{
					lbl_hata.setTextFill(Color.RED);
					lbl_hata.setText("Ayný barkod ile ürün bulunmaktadir.");
					lbl_hata.setVisible(true);
				}
				else
				{
					try
					{
					sorgu=baglanti.prepareStatement("insert into urunler(u_barkod,u_ad,u_fiyat,u_stok) values(?,?,?,?)");
					sorgu.setString(1, txt_barkod.getText());
					sorgu.setString(2, txt_ad.getText());
					sorgu.setInt(3, Integer.parseInt(txt_fiyat.getText()) );
					sorgu.setInt(4, Integer.parseInt(txt_adet.getText()));
					sorgu.executeUpdate();
					lbl_hata.setTextFill(Color.GREEN);
					lbl_hata.setText("Ürün baþarýyla eklendi.");
					lbl_hata.setVisible(true);
					}
					catch (Exception e) {
						lbl_hata.setTextFill(Color.RED);
						lbl_hata.setText(e.getMessage());
						lbl_hata.setVisible(true);
					}
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				lbl_hata.setTextFill(Color.RED);
				lbl_hata.setText(e.getMessage());
				lbl_hata.setVisible(true);
			}
    		
    	}
    	else
    	{
    		lbl_hata.setTextFill(Color.RED);
    		lbl_hata.setText("Lütfen boþ alan býrakmayýnýz!");
    		 lbl_hata.setVisible(true);
    	}
    }

    @FXML
    void initialize() {
      lbl_hata.setVisible(false);
    }
}
