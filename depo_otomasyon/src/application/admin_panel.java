package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.DataBase.Util.mysql_Baglanti;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class admin_panel {
	ObservableList<kullanicilar> kullanici_list;
	ObservableList<kullanicilar> temp_kullanici_list;
	Connection baglanti;
	public admin_panel() {
		baglanti=mysql_Baglanti.Connect();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<kullanicilar> tw_kullanicilar;

    @FXML
    private TableColumn<kullanicilar, Integer> tc_k_id;

    @FXML
    private TableColumn<kullanicilar, String> tc_kul_ad;

    @FXML
    private TableColumn<kullanicilar, String> tc_kul_sifre;

    @FXML
    private TableColumn<kullanicilar, Integer> tc_kul_yetki;
    

    @FXML
    private Label lbl_ekle_durum;

    @FXML
    private TextField txt_filtrele;

    @FXML
    private Label lbl_durum;
    
    @FXML
    private TextField txt_guncelle_id;

    @FXML
    private TextField txt_guncelle_ad;

    @FXML
    private TextField txt_guncelle_yetki;

    @FXML
    private TextField txt_kul_sifre;

    @FXML
    private Button btn_guncellestir;

    @FXML
    private ImageView iw_giris;

    @FXML
    private ImageView iw_guncelle;
    
    @FXML
    private Button btn_kul_ekle;
    
    @FXML
    private TextField txt_ekle_ad;

    @FXML
    private TextField txt_ekle_sifre;

    @FXML
    private ComboBox<String> cb_ekle_yetki;

    @FXML
    private ImageView iw_stok;

    @FXML
    private ImageView iw_cikis;

    @FXML
    private Label lbl_giris;

    @FXML
    private Label lbl_cikis;

    @FXML
    private Label lbl_stok;

    @FXML
    private Label lbl_guncelle;
    
    
    public void iw_giris_entered() {     image_karatma(iw_giris,lbl_giris) ;    }
    public void iw_cikis_entered() {      image_karatma(iw_cikis,lbl_cikis) ;   }
    public void iw_stok_entered() {       image_karatma(iw_stok,lbl_stok) ;    }
    public void iw_guncelle_entered() {       image_karatma(iw_guncelle,lbl_guncelle) ;    }
    
    public void iw_giris_exited() {    image_duzeltme(iw_giris,lbl_giris);     }
    public void iw_cikis_exited() {     image_duzeltme(iw_cikis,lbl_cikis);     }
    public void iw_stok_exited() {      image_duzeltme(iw_stok,lbl_stok);    }
    public void iw_guncelle_exited() {        image_duzeltme(iw_guncelle,lbl_guncelle);  }
    
    public void image_karatma(ImageView image, Label lbl)
    {
    	ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
       	image.setEffect(colorAdjust);
       	image.setCursor(Cursor.HAND);
       	lbl.setVisible(true);
    }
    public void image_duzeltme(ImageView image,Label lbl)
    {
    	image.setEffect(null);
    	lbl.setVisible(false);
    }
    PreparedStatement sorgu=null;
    ResultSet cevap=null;
    String sql;
    public void kullanicilari_getir()
    {
    	kullanici_list=FXCollections.observableArrayList();
    	try {
    		sorgu=baglanti.prepareStatement("select * from kullanicilar");
    		cevap=sorgu.executeQuery();
    		while(cevap.next())
    		{
    			kullanici_list.add(new kullanicilar(cevap.getInt("k_id"),cevap.getString("k_ad") , cevap.getString("k_sifre"), cevap.getInt("k_yetki")));
    		}
    		tc_k_id.setCellValueFactory(new PropertyValueFactory<>("k_id"));
    		tc_kul_ad.setCellValueFactory(new PropertyValueFactory<>("k_ad"));
    		tc_kul_sifre.setCellValueFactory(new PropertyValueFactory<>("k_sifre"));
    		tc_kul_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
    		tw_kullanicilar.setItems(kullanici_list);
    		
    	}
    	catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    public void secilen_deger()
    {
    	if(tw_kullanicilar.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		txt_guncelle_id.setText(String.valueOf( tc_k_id.getCellData(tw_kullanicilar.getSelectionModel().getSelectedIndex())));
    		txt_guncelle_ad.setText(tc_kul_ad.getCellData(tw_kullanicilar.getSelectionModel().getSelectedIndex()));
    		txt_kul_sifre.setText(String.valueOf(tc_kul_sifre.getCellData(tw_kullanicilar.getSelectionModel().getSelectedIndex())));
    		txt_guncelle_yetki.setText(String.valueOf(tc_kul_yetki.getCellData(tw_kullanicilar.getSelectionModel().getSelectedIndex())));
    	}
    }
    
  
    
    public void filtrele()
    {
    	
    	temp_kullanici_list=FXCollections.observableArrayList();
    	if(!txt_filtrele.getText().isEmpty())
    	{
    		
    		for(kullanicilar kullanici:kullanici_list)
    		{
    			if(txt_filtrele.getText().equals(String.valueOf(kullanici.getK_ad())))
    			{
    				temp_kullanici_list.add(kullanici);
    			}
    		}
    		tc_k_id.setCellValueFactory(new PropertyValueFactory<>("k_id"));
    		tc_kul_ad.setCellValueFactory(new PropertyValueFactory<>("k_ad"));
    		tc_kul_sifre.setCellValueFactory(new PropertyValueFactory<>("k_sifre"));
    		tc_kul_yetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
    		tw_kullanicilar.setItems(temp_kullanici_list);
    	}
    	else kullanicilari_getir();
    }
    public void guncelle()
    {
    	
    	if(!txt_guncelle_ad.getText().isEmpty() && !txt_guncelle_id.getText().isEmpty() && !txt_guncelle_yetki.getText().isEmpty() && !txt_kul_sifre.getText().isEmpty())
    	{
    		int id=tc_k_id.getCellData(tw_kullanicilar.getSelectionModel().getSelectedIndex());
    		try {
				sorgu=baglanti.prepareStatement("update kullanicilar set k_ad=?,k_sifre=?,k_yetki=? where k_id=?");
				
	    		sorgu.setString(1, txt_guncelle_ad.getText());
	    		sorgu.setString(2, txt_kul_sifre.getText());
	    		sorgu.setString(3, txt_guncelle_yetki.getText());
	    		sorgu.setInt(4, id);
	    		sorgu.executeUpdate();
	    		lbl_durum.setVisible(true);
	    		lbl_durum.setTextFill(Color.GREEN);
	    		lbl_durum.setText("Ýþlem baþarýlý");
	    		txt_guncelle_ad.clear();
	    		txt_guncelle_id.clear();
	    		txt_guncelle_yetki.clear();
	    		txt_kul_sifre.clear();
	    		kullanicilari_getir();
	    		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				lbl_durum.setTextFill(Color.RED);
	    		lbl_durum.setVisible(true);
	    		lbl_durum.setText(e.getMessage());
			}
    		
    		
    		
    	}
    	else 
    	{
    		lbl_durum.setVisible(true);
    		lbl_durum.setTextFill(Color.RED);
    		lbl_durum.setText("Boþ Deðer býrakýlmaz");
    	}
    		
    }
    public void kullanici_ekle()
    {
    	if(!txt_ekle_ad.getText().isEmpty() && !txt_ekle_sifre.getText().isEmpty() && !txt_ekle_sifre.getText().isEmpty() && cb_ekle_yetki.getSelectionModel().getSelectedIndex()!=-1)
    	try {
    		sorgu=baglanti.prepareStatement("insert into kullanicilar(k_ad,k_sifre,k_yetki) values(?,?,?)");
    		sorgu.setString(1, txt_ekle_ad.getText());
    		sorgu.setString(2,txt_ekle_sifre.getText());
    		sorgu.setInt(3, cb_ekle_yetki.getSelectionModel().getSelectedIndex());
    		sorgu.executeUpdate();
    		lbl_ekle_durum.setVisible(true);
    		lbl_ekle_durum.setTextFill(Color.GREEN);
    		lbl_ekle_durum.setText("Ýþlem baþarýlý");
    		txt_ekle_ad.clear();
    		txt_ekle_sifre.clear();
    		kullanicilari_getir();
    		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	else 
    	{
    		lbl_ekle_durum.setVisible(true);
    		lbl_ekle_durum.setTextFill(Color.RED);
    		lbl_ekle_durum.setText("Boþ Deðer býrakýlmaz");
    	}
    }
    
    public void kullanici_sil()
    {
    	if(tw_kullanicilar.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		try {

				sorgu=baglanti.prepareStatement("delete from kullanicilar where k_id=?");
				sorgu.setInt(1, tc_k_id.getCellData(tw_kullanicilar.getSelectionModel().getSelectedIndex()));
				sorgu.executeUpdate();
				lbl_durum.setVisible(true);
	    		lbl_durum.setTextFill(Color.GREEN);
	    		lbl_durum.setText("Ýþlem baþarýlý");
	    		kullanicilari_getir();
	    		txt_guncelle_ad.clear();
	    		txt_guncelle_id.clear();
	    		txt_guncelle_yetki.clear();
	    		txt_kul_sifre.clear();
    		}
    		catch (Exception e) {
    			lbl_durum.setTextFill(Color.RED);
	    		lbl_durum.setVisible(true);
	    		lbl_durum.setText(e.getMessage());
			}
    	}
    	else
    	{
    		lbl_durum.setTextFill(Color.RED);
    		lbl_durum.setVisible(true);
    		lbl_durum.setText("Lütfen kullanici seçin!");
    	}
    }
    public void menu_cikis_ac()
    {
    	try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("mal_cikis.fxml"));
			AnchorPane MainPane=(AnchorPane) loader.load();
			mal_cikis nesne=loader.getController();
			Scene MainScene=new Scene(MainPane);
			Stage MainStage=new Stage();
			MainStage.setScene(MainScene);
			MainStage.getIcons().add(new Image(getClass().getResourceAsStream("storage.png")));
			MainStage.setTitle("Mal Cikis Menusu");
			MainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    public void menu_ekle_ac()
    {
    	try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("mal_giris.fxml"));
			AnchorPane MainPane=(AnchorPane) loader.load();
			mal_giris nesne=loader.getController();
			Scene MainScene=new Scene(MainPane);
			Stage MainStage=new Stage();
			MainStage.setScene(MainScene);
			MainStage.getIcons().add(new Image(getClass().getResourceAsStream("storage.png")));
			MainStage.setTitle("Yeni Mal Ekleme");
			MainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    public void menu_stokekle_ac()
    {
    	try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("stok_ekle.fxml"));
			AnchorPane MainPane=(AnchorPane) loader.load();
			stok_ekle nesne=loader.getController();
			Scene MainScene=new Scene(MainPane);
			Stage MainStage=new Stage();
			MainStage.setScene(MainScene);
			MainStage.getIcons().add(new Image(getClass().getResourceAsStream("storage.png")));
			MainStage.setTitle("Stok ekleme menüsü");
			MainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    public void menu_stokgoruntule_ac()
    {
    	try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("stok_goruntule.fxml"));
			AnchorPane MainPane=(AnchorPane) loader.load();
			stok_goruntule nesne=loader.getController();
			Scene MainScene=new Scene(MainPane);
			Stage MainStage=new Stage();
			MainStage.setScene(MainScene);
			MainStage.getIcons().add(new Image(getClass().getResourceAsStream("storage.png")));
			MainStage.setTitle("Stok Görüntüleme");
			MainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void initialize() {
    	kullanicilari_getir();
    	txt_guncelle_id.setEditable(false);
    	lbl_durum.setVisible(false);
      lbl_giris.setVisible(false);
      lbl_cikis.setVisible(false);
      lbl_guncelle.setVisible(false);
      lbl_stok.setVisible(false);
      cb_ekle_yetki.getItems().add("Normal Personel");
      cb_ekle_yetki.getItems().add("Yetkili");
      lbl_ekle_durum.setVisible(false);
      
    }
}
