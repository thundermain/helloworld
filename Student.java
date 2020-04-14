package system;

public class Student {
	public   String    number;
	public   String    name;
	public   String    idNumber;
	public   String    profession;
	public   String    className;
	public   String    birthPlace;
	public   String    adress;
	public   String    score;
	
	
	public Student(String num, String na , String id, String pro, String cla, String bir, String adr, String sco) {
		super();
		this.number = num;
		this.name   = na;
		this.idNumber = id;
		this.profession = pro;
		this.className  = cla;
		this.birthPlace = bir;
		this.adress     = adr;
		this.score      = sco;
	}


	/*public int compareTo(Student o) {
		// TODO 自动生成的方法存根
			return Integer.parseInt(o.score)-Integer.parseInt(this.score);

	}*/
}
