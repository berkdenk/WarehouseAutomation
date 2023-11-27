package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.DataBase.Util.mysql_Baglanti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class stok_goruntule {
	ObservableList<urun_bilgileri> urunler;
	Connection baglanti;
	public stok_goruntule() {
		baglanti=mysql_Baglanti.Connect();
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_barkod;
    
    @FXML
    private TableView<urun_bilgileri> tw_urunler;

    @FXML
    private TableColumn<urun_bilgileri, String> u_barkod;

    @FXML
    private TableColumn<urun_bilgileri, String> u_ad;

    @FXML
    private TableColumn<urun_bilgileri, Integer> u_fiyat;

    @FXML
    private TableColumn<urun_bilgileri, Integer> u_stok;


    @FXML
    private TextField txt_bfiyat;
    
    @FXML
    private Label lbl_urun_hata;

    @FXML
    private TextField txt_uad;

    @FXML
    private TextField txt_sadet;

    @FXML
    private Button btn_guncelle;

    
    PreparedStatement sorgu=null;
    ResultSet cevap=null;
    String sql;
    public void urunleri_getir()
    {
    	urunler=FXCollections.observableArrayList();
    	try {
    		sorgu=baglanti.prepareStatement("select * from urunler");
    		cevap=sorgu.executeQuery();
    		while(cevap.next())
    		{
    			urunler.add(new urun_bilgileri(cevap.getInt("u_id"),cevap.getString("u_barkod") , cevap.getString("u_ad"), cevap.getInt("u_fiyat"),cevap.getInt("u_stok")));
    		}
    		u_barkod.setCellValueFactory(new PropertyValueFactory<>("u_barkod"));
    		u_ad.setCellValueFactory(new PropertyValueFactory<>("u_ad"));
    		u_fiyat.setCellValueFactory(new PropertyValueFactory<>("u_fiyat"));
    		u_stok.setCellValueFactory(new PropertyValueFactory<>("u_stok"));
    		tw_urunler.setItems(urunler);
    		
    	}
    	catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    public void secilen_deger()
    {
    	if(tw_urunler.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		txt_barkod.setText(u_barkod.getCellData(tw_urunler.getSelectionModel().getSelectedIndex()));
    		txt_uad.setText(u_ad.getCellData(tw_urunler.getSelectionModel().getSelectedIndex()));
    		txt_bfiyat.setText(String.valueOf(u_fiyat.getCellData(tw_urunler.getSelectionModel().getSelectedIndex())));
    		txt_sadet.setText(String.valueOf(u_stok.getCellData(tw_urunler.getSelectionModel().getSelectedIndex())));
    	}
    }
    public void guncellestir()
    {
    	if(!txt_barkod.getText().isEmpty() && !txt_uad.getText().isEmpty() && !txt_bfiyat.getText().isEmpty() && !txt_sadet.getText().isEmpty())
    	{
    		try
    		{
    			sorgu=baglanti.prepareStatement("update  urunler set u_ad=?, u_stok=?,u_fiyat=? where u_barkod=?");
    			sorgu.setString(1, txt_uad.getText());
    			sorgu.setInt(2, Integer.parseInt( txt_sadet.getText()));
    			sorgu.setInt(3, Integer.parseInt( txt_bfiyat.getText()));
    			sorgu.setString(4, txt_barkod.getText());
    			sorgu.executeUpdate();
    			lbl_urun_hata.setTextFill(Color.GREEN);
    			lbl_urun_hata.setText("Güncelleþtirme Baþarýlý");
        		lbl_urun_hata.setVisible(true);
        		
        		urunleri_getir();
        		txt_barkod.clear();
        		txt_bfiyat.clear();
        		txt_sadet.clear();
        		txt_uad.clear();
    			
    		}
    		catch (Exception e) {
				System.out.println(e.getMessage());
			}
    	}
    	else
    	{
    		lbl_urun_hata.setTextFill(Color.RED);
    		lbl_urun_hata.setText("Boþ deðerlerle güncelleþtirme yapýlamaz!");
    		lbl_urun_hata.setVisible(true);
    	}
    }
    
    @FXML
    void initialize() {
    	urunleri_getir();
    	txt_barkod.setEditable(false);
    	lbl_urun_hata.setVisible(false);
    }
}



