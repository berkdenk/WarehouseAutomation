package application;

public class kullanicilar {

	private int k_id;
	private String k_ad;
	private String k_sifre;
	private int yetki;
	public kullanicilar(int kid,String kad,String ksifre,int yet) {
		this.k_id=kid;
		this.k_sifre=ksifre;
		this.k_ad=kad;
		this.yetki=yet;
		// TODO Auto-generated constructor stub
	}
	public int getK_id() {
		return k_id;
	}
	public void setK_id(int k_id) {
		this.k_id = k_id;
	}
	public String getK_ad() {
		return k_ad;
	}
	public void setK_ad(String k_ad) {
		this.k_ad = k_ad;
	}
	public String getK_sifre() {
		return k_sifre;
	}
	public void setK_sifre(String k_sifre) {
		this.k_sifre = k_sifre;
	}
	public int getYetki() {
		return yetki;
	}
	public void setYetki(int yetki) {
		this.yetki = yetki;
	}
}
