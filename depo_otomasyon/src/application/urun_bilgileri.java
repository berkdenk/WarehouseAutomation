package application;

public class urun_bilgileri {

	private int u_id;
	private String u_barkod;
	private String u_ad;
	private int u_fiyat;
	private int u_stok;
	public urun_bilgileri(int urun_id,String urun_barkod,String urun_ad,int urun_fiyat,int urun_stok) {
		this.u_id=urun_id;
		this.u_ad=urun_ad;
		this.u_barkod=urun_barkod;
		this.u_fiyat=urun_fiyat;
		this.u_stok=urun_stok;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_barkod() {
		return u_barkod;
	}
	public void setU_barkod(String u_barkod) {
		this.u_barkod = u_barkod;
	}
	public String getU_ad() {
		return u_ad;
	}
	public void setU_ad(String u_ad) {
		this.u_ad = u_ad;
	}
	public int getU_fiyat() {
		return u_fiyat;
	}
	public void setU_fiyat(int u_fiyat) {
		this.u_fiyat = u_fiyat;
	}
	public int getU_stok() {
		return u_stok;
	}
	public void setU_stok(int u_stok) {
		this.u_stok = u_stok;
	}
}
