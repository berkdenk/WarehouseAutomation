package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.DataBase.Util.mysql_Baglanti;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class mal_cikis {

	Connection baglanti;
	public mal_cikis() {
		baglanti=mysql_Baglanti.Connect();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_cikar_barkod;

    @FXML
    private TextField txt_cikar_adet;

    @FXML
    private Label lbl_cikar_hata;

    @FXML
    private Button btn_cikar;

    PreparedStatement sorgu=null;
    ResultSet cevap=null;
    String sql;
    public void mal_cikis()
    {
    	
    	if(!txt_cikar_adet.getText().isEmpty() && !txt_cikar_barkod.getText().isEmpty())
    	{
    		try
    		{
    		sorgu=baglanti.prepareStatement("select * from urunler where u_barkod=?");
    		sorgu.setString(1, txt_cikar_barkod.getText());
    		cevap=sorgu.executeQuery();
    		if(cevap.next())
    		{
    			if(cevap.getInt("u_stok")-Integer.parseInt(txt_cikar_adet.getText())>=0)
    			{
    				try {
    					
    				
    			sorgu=baglanti.prepareStatement("update urunler set u_stok=u_stok-? where u_barkod=?");
    			sorgu.setInt(1, Integer.parseInt(txt_cikar_adet.getText()));
    			sorgu.setString(2, txt_cikar_barkod.getText());
    			sorgu.executeUpdate();
    			lbl_cikar_hata.setTextFill(Color.GREEN);
        		lbl_cikar_hata.setText("Mal ��karma i�lemi ba�ar�l�.!");
        		lbl_cikar_hata.setVisible(true);
    				}
    				catch (Exception e) {
    					lbl_cikar_hata.setTextFill(Color.RED);
    	        		lbl_cikar_hata.setText("Beklenmiyen hata 002:"+e.getMessage());
    	        		System.out.println(e.getMessage());
    	        		lbl_cikar_hata.setVisible(true);
					}
    			
    			}
    			else
    			{
    				lbl_cikar_hata.setTextFill(Color.RED);
            		lbl_cikar_hata.setText("Yeterli Stok yok!");
            		lbl_cikar_hata.setVisible(true);
    			}
    		}
    		else
    		{
    			lbl_cikar_hata.setTextFill(Color.RED);
        		lbl_cikar_hata.setText("Ge�ersiz barkod numarasi");
        		lbl_cikar_hata.setVisible(true);
    		}
    		}
    		catch (Exception e) {
    			lbl_cikar_hata.setTextFill(Color.RED);
        		lbl_cikar_hata.setText("Beklenmiyen hata 001:");
        		System.out.println(e.getMessage());
        		lbl_cikar_hata.setVisible(true);
			}
    	}
    	else
    	{
    		lbl_cikar_hata.setTextFill(Color.RED);
    		lbl_cikar_hata.setText("Bo� alan b�rakmay�n�z.!");
    		lbl_cikar_hata.setVisible(true);
    	}
    }
    
    @FXML
    void initialize() {
    	lbl_cikar_hata.setVisible(false);
    }
}
