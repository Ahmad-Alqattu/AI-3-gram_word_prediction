package application;

public class tri  implements Comparable<tri>{
	private int cunt;
	private String str12;
	private String str3;
	private double prop;
	
	
	public tri(int cunt, String str12,String str3) {
		super();
		this.cunt = cunt;
		this.str12 = str12;
		this.str3 = str3;
	}

	public int getCunt() {
		return cunt;
	}
	public void setCunt(int cunt) {
		this.cunt = cunt;
	}
	public double getProp() {
		return prop;
	}
	public void setProp(double prop) {
		this.prop = prop;
	}
	@Override
	public int compareTo(tri o) {
		if(prop==o.getProp())  
			return 0;  
			else if(prop>o.getProp())  
			return 1;  
			else  
			return -1;  
	}
	public String getStr12() {
		return str12;
	}
	public void setStr12(String str12) {
		this.str12 = str12;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}


	

}
