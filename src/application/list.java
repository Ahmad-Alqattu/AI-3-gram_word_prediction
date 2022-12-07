package application;

public class list implements Comparable<list> {
	private double pridict;
	private String str;
	public double getPridict() {
		return pridict;
	}
	public void setPridict(double pridict) {
		this.pridict = pridict;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public list(double pridict, String str) {
		super();
		this.pridict = pridict;
		this.str = str;
	}
	@Override
	public int compareTo(list o) {
		if(pridict==o.getPridict())  
			return 0;  
			else if(pridict>o.getPridict())  
			return 1;  
			else  
			return -1;  
	}
	@Override
	public String toString() {
		return "pridict=" + pridict + ", " + str + "\n";
	}
	
}
