package application;

public class bi implements Comparable<bi>{
	private String str1;
	private String str2;
	private double prop;
	private int cunt;
	
	
	public bi(int cunt, String str1, String str2) {
		super();
		this.cunt = cunt;
		this.str1=(str1);
		this.str2=(str2);
	
		
	}
	@Override
	public int compareTo(bi o) {
		if(prop==o.getProp())  
			return 0;  
			else if(prop>o.getProp())  
			return 1;  
			else  
			return -1;  
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

	public void setProp(double prop2) {
		this.prop = prop2;
		
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
}
