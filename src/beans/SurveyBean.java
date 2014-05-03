package beans;

import java.io.Serializable;

public class SurveyBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -746794596075103557L;
	private String q,a1,a2,a3,a4;
	private int id,a1Count,a2Count,a3Count,a4Count,active;
	
	public SurveyBean(){
		this(0,"","","","","",0,0,0,0,0);
	}

	public SurveyBean(int id, String q, String a1, String a2,
			String a3, String a4, int a1Count, int a2Count, int a3Count, int a4Count, int active) {
		this.id=id;
		this.q=q;
		this.a1=a1;
		this.a2=a2;
		this.a3=a3;
		this.a4=a4;
		this.a1Count=a1Count;
		this.a2Count=a2Count;
		this.a3Count=a3Count;
		this.a4Count=a4Count;
		this.active=active;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public String getA4() {
		return a4;
	}

	public void setA4(String a4) {
		this.a4 = a4;
	}

	public int getA1Count() {
		return a1Count;
	}

	public void setA1Count(int a1Count) {
		this.a1Count = a1Count;
	}

	public int getA2Count() {
		return a2Count;
	}

	public void setA2Count(int a2Count) {
		this.a2Count = a2Count;
	}

	public int getA3Count() {
		return a3Count;
	}

	public void setA3Count(int a3Count) {
		this.a3Count = a3Count;
	}

	public int getA4Count() {
		return a4Count;
	}

	public void setA4Count(int a4Count) {
		this.a4Count = a4Count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}