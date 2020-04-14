package system;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;



public class Test {
	
	public static void  welcome() {//欢迎进入系统
		System.out.println("\t Welcome to the online teaching management system! \t");
	}
	
	static  Scanner  sc = new Scanner(System.in); //定义一个全局的静态变量的扫描器sc
	static  boolean   flag  = false;                //是否进入了系统内
	static  boolean   flag1 = false;               //写入文件是追加还是覆盖
	static  boolean   flag2 = true;                //退出程序
	static  int       load_Times = 0;              //登录次数
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		
		int     select;
		
		do {
			welcome();
			switch ( exist_users() ) {
			    case 1 : load_system();
			             while( flag ) {
			                welcome();
			                select = select_menu();
			                switch ( select ) {
			                     case 1: add_student();   sort_studentScore();          break;
			                     case 2: delet_student();                               break;
			                     case 3: alter_student(); sort_studentScore();          break;
			                     case 4: check_student();                               break;
			                     case 5: check_score();                                 break;
			                     case 6: check_scoreDistribution();                     break;
			                     case 0: flag = false;                                  break;
			                     default:
				                     break;
			                }
		                 }
			             break;
			  case 2 : register_account(); break;     //注册账号
			}
		} while ( flag2 );
		
              
	}
	
	//是否有账号
	public static int   exist_users() {

		System.out.println("\t\t\t您是否已有账号?\t");
		System.out.println("\t 1、已有账号" + "\t\t\t 2、注册账号" );
		while( true ) {
		   int  exist = sc.nextInt(); 
		   sc.nextLine();
		   if(exist > 2 || exist < 0)
			   System.out.println("当前输入非法,请重新输入！");
		   else 
		       return exist;
		   
		 }
	}
	
	//注册账号
	public  static  void register_account() {
		try {
		      String  pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Student.txt";
		      File    file = new File(pathname);
		      if(!file.exists()) {       //要打开的文件夹不存在时，创建该文件夹
			      file.createNewFile();
		        }
		      
		      FileWriter      fw = new FileWriter(file,true);//将要写入的内容追加到以有内容后
		      BufferedWriter  bw = new BufferedWriter(fw);
		      System.out.println("请输入下列信息：" );
		      System.out.print("\t 姓名：" );
		      String name = sc.next();
		      sc.nextLine();
		      
		      System.out.print("\t 身份证号：" );
		      String idNumber = sc.next();
		      sc.nextLine();
		      
		      System.out.print("\t 账户：" );
		      String account = sc.next();
		      sc.nextLine();
		      
		      System.out.print("\t 密码：" );
		      String password = sc.next();
		      sc.nextLine();
		      
		      bw.write(  name + "\t\t" + idNumber + "\t\t" + account + "\t\t" + password + "\t\n" +  "\r\n");
		      bw.close();    //关闭输出流
		      clear();       //清屏

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//登录系统
	public  static  void load_system() {
		try {

			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Student.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("\t 请输入您的账号：");
			String     account = sc.next();
			sc.nextLine();
			
			System.out.print("\t 请输入您的密码：");
			String     password = sc.next();
			sc.nextLine();
			
			String     temp = null; ;
			while( (temp = br.readLine()) != null) {
	            String    s1[] = temp.split("\\s+");
				if( s1[2].equals(account) && s1[3].equals(password) ) {
				    flag = true;        //允许登录系统
				    break;
				}
			}	
			if( temp == null) {
			   System.out.println("密码或账户不正确，请重新输入!");
			   load_Times++;
			   if( load_Times >= 3)                   //输入错误三次后退出程序
		          flag2 = false;
			}
			br.close();
			sc.nextLine();
			clear();        //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//菜单选择方法
	public  static  int select_menu() {
		System.out.println("请选择您要进行的操作：");
		System.out.println("\t 1、增加学生信息");
		System.out.println("\t 2、删除学生信息");
		System.out.println("\t 3、修改学生信息");
		System.out.println("\t 4、根据学号查看学生信息");
		System.out.println("\t 5、查看成绩排行榜");
		System.out.println("\t 6、查看整体成绩分布");
		System.out.println("\t 0、退出系统");
		System.out.print("请输入您的选择：");
		while( true) {
	    int   select = sc.nextInt();
	    sc.nextLine();                         //过滤掉输入select结束时的 enter 键
		if(select >= 0 && select <= 6) {
			return  select;
		}
		else
			System.out.print("输入不合法，请重新输入正确的编号：");
		}
		
	}
	
	//增加学生
	public static void  add_student() {
		System.out.println("Please  input the student`s message that you want to join！");		
		List<Student>   student = new ArrayList<Student>();  //学生信息存储单位
		while( !sc.hasNext("#" )) {
			String    message1 = sc.nextLine();
			String    s1[] = message1.split("\\s+");
			Student   student2 = new Student(s1[0], s1[1], s1[2], s1[3], s1[4], s1[5], s1[6], s1[7]);
			student.add(student2);
		}
		write_file(student); 
		System.out.println("学生信息插入成功，按任意键返回主菜单");
		sc.nextLine();
		clear();      //清屏
	}
	
	//将学生信息写入文件里
	public  static void  write_file(List<Student>   student) {
		try {
		      String  pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
		      File    file = new File(pathname);
		      if(!file.exists()) {       //要打开的文件夹不存在时，创建该文件夹
			      file.createNewFile();
		        }
		      
		      if(flag1) {    //追加文件内容
		      FileWriter      fw = new FileWriter(file,true);//将要写入的内容追加到以有内容后
		      BufferedWriter  bw = new BufferedWriter(fw);
		      for(int i = 0 ; i < student.size() ; i++) {
		    	  Student   s = student.get(i);
		    	  bw.write(s.number + "\t" + s.name + "\t" + s.idNumber + "\t" + s.profession + "\t" + s.className + "\t" + s.birthPlace + "\t" +  s.adress + "\t" + s.score + "\r\n");
		    	  bw.flush();     //刷新输出流，将缓存中存在的数据存入文件中
		      }
		      bw.close();    //关闭输出流
		      } 
		      else{   //覆盖文件内容
			      FileWriter      fw = new FileWriter(file);//将要写入的内容追加到以有内容后
			      BufferedWriter  bw = new BufferedWriter(fw);
			      for(int i = 0 ; i < student.size() ; i++) {
			    	  Student   s = student.get(i);
			    	  bw.write(s.number + "\t" + s.name + "\t" + s.idNumber + "\t" + s.profession + "\t" + s.className + "\t" + s.birthPlace + "\t" +  s.adress + "\t" + s.score + "\r\n");
			    	  bw.flush();     //刷新输出流，将缓存中存在的数据存入文件中
			      }
			      bw.close();    //关闭输出流
			      flag1 = true;
			      }
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//删除学生信息
	public  static  void  delet_student() {
		try {
			List<Student>   student = new ArrayList<Student>();  //学生信息存储单位
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("\t 请输入您要删除学生的学号：");
			String     num = sc.next();
			sc.nextLine();
			String     temp = null; ;
			while( (temp = br.readLine()) != null) {
	            String    s1[] = temp.split("\t");
				if( !s1[0].equals(num) ) {
					Student    st = new Student(s1[0], s1[1], s1[2], s1[3], s1[4], s1[5], s1[6], s1[7]);
				    student.add(st);
				}
			}
			br.close();
			flag1 = false;        //覆盖文件
			write_file(student);
			System.out.println("学生信息删除已完成，请按任意键退出! \n");
			sc.nextLine();
			clear();        //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//修改学生信息
	public static  void  alter_student() {
		try {
			List<Student>   student = new ArrayList<Student>();  //学生信息存储单位
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("\t 请输入您要修改学生的学号：");
			String     num = sc.next();
			sc.nextLine();
			String     temp = null;
			Student    st = null;
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\\s+");
				
				if( s[0].equals(num) ) {
					System.out.println("请输入该学生的新信息：");
					String    message1 = sc.nextLine();
					String    s1[] = message1.split("\\s+");
				    st = new Student(s1[0], s1[1], s1[2], s1[3], s1[4], s1[5], s1[6], s1[7]);
				}
				else {
				    st = new Student(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
				}
				student.add(st);
			}
			br.close();
			flag1 = false;          //覆盖文件
			write_file(student);
			System.out.println("学生信息修改已完成，请按任意键退出");
			sc.nextLine();
			clear();      //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//输入学号查看学生信息
	public static  void  check_student() {
		try {
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("请输入您想要查看的学生的学号：");
			String     num = sc.next();
			sc.nextLine();
			String     temp = null;
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\t");
				if( s[0].equals(num) ) {
					System.out.println("学号" + "\t" + "姓名" + "\t" + "身份证号" + "\t" + "专业" + "\t" + "班级" + "\t" + "籍贯" + "\t" + "地址" + "\t" + "成绩" );
					System.out.println( s[0]  + "\t" + s[1]  +  "\t" +   s[2]    + "\t" +  s[3]  + "\t" +  s[4]  + "\t" + s[5]  +  "\t" +  s[6]  + "\t" + s[7]  );
				}
			}
			br.close();
			System.out.println("查询已完成，请按任意键退出！\n");
			sc.nextLine();
			clear();       //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//学生按成绩的从高到低排序
	public  static  void  sort_studentScore() {
		try {
			List<Student>   student = new ArrayList<Student>();  //学生信息存储单位
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			String     temp = null; ;
			while( (temp = br.readLine()) != null) {
	            String    s1[] = temp.split("\t");
				Student    st = new Student(s1[0], s1[1], s1[2], s1[3], s1[4], s1[5], s1[6], s1[7]);
				student.add(st);
			}
			br.close();
			
			Collections.sort(student, new Comparator<Student>() { //集合里利用比较器排序
				public int compare(Student s1,Student s2) {
					return Integer.parseInt(s2.score) - Integer.parseInt(s1.score);
				}
			});
			
			flag1 = false;       //覆盖文件
			write_file(student);
			sc.nextLine();
			clear();       //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//查看成绩排行榜
	public static  void  check_score() {
		try {
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			String     temp = null;
			System.out.println("学号" + "\t" + "姓名" + "\t" + "身份证号" + "\t" + "专业" + "\t" + "班级" + "\t" + "籍贯" + "\t" + "地址" + "\t" + "成绩" );
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\t");
				System.out.println( s[0]  + "\t" + s[1]  +  "\t" +   s[2]    + "\t" +  s[3]  + "\t" +  s[4]  + "\t" + s[5]  +  "\t" +  s[6]  + "\t" + s[7]  );

			}
			br.close();
			System.out.println("学生成绩已打印，请按任意键退出! \n");
			sc.nextLine();
			clear();      //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//查看整体成绩分布
	public  static   void  check_scoreDistribution() {
		try {
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			String     temp = null;
			int        stage1 = 0;
			int        stage2 = 0;
			int        stage3 = 0;
			int        stage4 = 0;
			int		   stage5 = 0; 
			long       sum = 0;
			double     average;
			int        count = 0;
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\t");
	            if( Integer.parseInt(s[7]) < 60 && Integer.parseInt(s[7]) > 0 )
	            	{ stage5++; count++; 
	            	   sum = sum + Integer.parseInt(s[7]); }
	            else  if( Integer.parseInt(s[7]) < 70 && Integer.parseInt(s[7]) >= 60 )
	            	    { stage4++; count++;
	            	      sum = sum + Integer.parseInt(s[7]); }
	                  else  if( Integer.parseInt(s[7]) < 80 && Integer.parseInt(s[7]) >= 70 )
	                	      { stage3++; count++; 
	                	        sum = sum + Integer.parseInt(s[7]); }
	                  		else  if( Integer.parseInt(s[7]) < 90 && Integer.parseInt(s[7]) >= 80 )
	                  			  { stage2++; count++; 
	                  			    sum = sum + Integer.parseInt(s[7]); }
	                  		      else  if( Integer.parseInt(s[7]) < 100 && Integer.parseInt(s[7]) >= 90 )
	                  		    	     { stage1++;  count++;
	                  		    	       sum = sum + Integer.parseInt(s[7]); }
	            
			}
			average = sum*1.0 / count;
			System.out.println("学生整体成绩分布如下：");
			System.out.println("学生人数为：" + count + "\t\t" + "学生的平均成绩为：成绩" + average);
			System.out.println("成绩在0-59分之间的人数有："   + stage5 + "人" + "\t" + "占总人数的：" + stage5*1.0/count*100 + "%");
			System.out.println("成绩在60-69分之间的人数有："  + stage4 + "人" + "\t" + "占总人数的：" + stage4*1.0/count*100 + "%");
			System.out.println("成绩在70-79分之间的人数有："  + stage3 + "人" + "\t" + "占总人数的：" + stage3*1.0/count*100 + "%");
			System.out.println("成绩在80-89分之间的人数有："  + stage2 + "人" + "\t" + "占总人数的：" + stage2*1.0/count*100 + "%");
			System.out.println("成绩在90-100分之间的人数有：" + stage1 + "人" + "\t" + "占总人数的：" + stage1*1.0/count*100 + "%");
			
			br.close();
			System.out.println("\n学生成绩已打印，请按任意键退出! \n");
			sc.nextLine();
			clear();      //清屏
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	
	//清理控制台
	public static void clear() {

		   try {
				
				Robot r = new Robot();
				r.mousePress(InputEvent.BUTTON3_DOWN_MASK);		//按下鼠标右键
				r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);	//释放鼠标右键
				r.keyPress(KeyEvent.VK_CONTROL);				//按下Ctrl键
				r.keyPress(KeyEvent.VK_R);						//按下R键
				r.keyRelease(KeyEvent.VK_R);					//释放R键
				r.keyRelease(KeyEvent.VK_CONTROL);				//释放Ctrl键
				r.delay(100);
	
		   }
		   catch (AWTException e) {
			   e.printStackTrace();
			 }
	}

		
}
