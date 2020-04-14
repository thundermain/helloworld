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
	
	public static void  welcome() {//��ӭ����ϵͳ
		System.out.println("\t Welcome to the online teaching management system! \t");
	}
	
	static  Scanner  sc = new Scanner(System.in); //����һ��ȫ�ֵľ�̬������ɨ����sc
	static  boolean   flag  = false;                //�Ƿ������ϵͳ��
	static  boolean   flag1 = false;               //д���ļ���׷�ӻ��Ǹ���
	static  boolean   flag2 = true;                //�˳�����
	static  int       load_Times = 0;              //��¼����
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
		
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
			  case 2 : register_account(); break;     //ע���˺�
			}
		} while ( flag2 );
		
              
	}
	
	//�Ƿ����˺�
	public static int   exist_users() {

		System.out.println("\t\t\t���Ƿ������˺�?\t");
		System.out.println("\t 1�������˺�" + "\t\t\t 2��ע���˺�" );
		while( true ) {
		   int  exist = sc.nextInt(); 
		   sc.nextLine();
		   if(exist > 2 || exist < 0)
			   System.out.println("��ǰ����Ƿ�,���������룡");
		   else 
		       return exist;
		   
		 }
	}
	
	//ע���˺�
	public  static  void register_account() {
		try {
		      String  pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Student.txt";
		      File    file = new File(pathname);
		      if(!file.exists()) {       //Ҫ�򿪵��ļ��в�����ʱ���������ļ���
			      file.createNewFile();
		        }
		      
		      FileWriter      fw = new FileWriter(file,true);//��Ҫд�������׷�ӵ��������ݺ�
		      BufferedWriter  bw = new BufferedWriter(fw);
		      System.out.println("������������Ϣ��" );
		      System.out.print("\t ������" );
		      String name = sc.next();
		      sc.nextLine();
		      
		      System.out.print("\t ���֤�ţ�" );
		      String idNumber = sc.next();
		      sc.nextLine();
		      
		      System.out.print("\t �˻���" );
		      String account = sc.next();
		      sc.nextLine();
		      
		      System.out.print("\t ���룺" );
		      String password = sc.next();
		      sc.nextLine();
		      
		      bw.write(  name + "\t\t" + idNumber + "\t\t" + account + "\t\t" + password + "\t\n" +  "\r\n");
		      bw.close();    //�ر������
		      clear();       //����

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//��¼ϵͳ
	public  static  void load_system() {
		try {

			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Student.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("\t �����������˺ţ�");
			String     account = sc.next();
			sc.nextLine();
			
			System.out.print("\t �������������룺");
			String     password = sc.next();
			sc.nextLine();
			
			String     temp = null; ;
			while( (temp = br.readLine()) != null) {
	            String    s1[] = temp.split("\\s+");
				if( s1[2].equals(account) && s1[3].equals(password) ) {
				    flag = true;        //�����¼ϵͳ
				    break;
				}
			}	
			if( temp == null) {
			   System.out.println("������˻�����ȷ������������!");
			   load_Times++;
			   if( load_Times >= 3)                   //����������κ��˳�����
		          flag2 = false;
			}
			br.close();
			sc.nextLine();
			clear();        //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//�˵�ѡ�񷽷�
	public  static  int select_menu() {
		System.out.println("��ѡ����Ҫ���еĲ�����");
		System.out.println("\t 1������ѧ����Ϣ");
		System.out.println("\t 2��ɾ��ѧ����Ϣ");
		System.out.println("\t 3���޸�ѧ����Ϣ");
		System.out.println("\t 4������ѧ�Ų鿴ѧ����Ϣ");
		System.out.println("\t 5���鿴�ɼ����а�");
		System.out.println("\t 6���鿴����ɼ��ֲ�");
		System.out.println("\t 0���˳�ϵͳ");
		System.out.print("����������ѡ��");
		while( true) {
	    int   select = sc.nextInt();
	    sc.nextLine();                         //���˵�����select����ʱ�� enter ��
		if(select >= 0 && select <= 6) {
			return  select;
		}
		else
			System.out.print("���벻�Ϸ���������������ȷ�ı�ţ�");
		}
		
	}
	
	//����ѧ��
	public static void  add_student() {
		System.out.println("Please  input the student`s message that you want to join��");		
		List<Student>   student = new ArrayList<Student>();  //ѧ����Ϣ�洢��λ
		while( !sc.hasNext("#" )) {
			String    message1 = sc.nextLine();
			String    s1[] = message1.split("\\s+");
			Student   student2 = new Student(s1[0], s1[1], s1[2], s1[3], s1[4], s1[5], s1[6], s1[7]);
			student.add(student2);
		}
		write_file(student); 
		System.out.println("ѧ����Ϣ����ɹ�����������������˵�");
		sc.nextLine();
		clear();      //����
	}
	
	//��ѧ����Ϣд���ļ���
	public  static void  write_file(List<Student>   student) {
		try {
		      String  pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
		      File    file = new File(pathname);
		      if(!file.exists()) {       //Ҫ�򿪵��ļ��в�����ʱ���������ļ���
			      file.createNewFile();
		        }
		      
		      if(flag1) {    //׷���ļ�����
		      FileWriter      fw = new FileWriter(file,true);//��Ҫд�������׷�ӵ��������ݺ�
		      BufferedWriter  bw = new BufferedWriter(fw);
		      for(int i = 0 ; i < student.size() ; i++) {
		    	  Student   s = student.get(i);
		    	  bw.write(s.number + "\t" + s.name + "\t" + s.idNumber + "\t" + s.profession + "\t" + s.className + "\t" + s.birthPlace + "\t" +  s.adress + "\t" + s.score + "\r\n");
		    	  bw.flush();     //ˢ����������������д��ڵ����ݴ����ļ���
		      }
		      bw.close();    //�ر������
		      } 
		      else{   //�����ļ�����
			      FileWriter      fw = new FileWriter(file);//��Ҫд�������׷�ӵ��������ݺ�
			      BufferedWriter  bw = new BufferedWriter(fw);
			      for(int i = 0 ; i < student.size() ; i++) {
			    	  Student   s = student.get(i);
			    	  bw.write(s.number + "\t" + s.name + "\t" + s.idNumber + "\t" + s.profession + "\t" + s.className + "\t" + s.birthPlace + "\t" +  s.adress + "\t" + s.score + "\r\n");
			    	  bw.flush();     //ˢ����������������д��ڵ����ݴ����ļ���
			      }
			      bw.close();    //�ر������
			      flag1 = true;
			      }
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//ɾ��ѧ����Ϣ
	public  static  void  delet_student() {
		try {
			List<Student>   student = new ArrayList<Student>();  //ѧ����Ϣ�洢��λ
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("\t ��������Ҫɾ��ѧ����ѧ�ţ�");
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
			flag1 = false;        //�����ļ�
			write_file(student);
			System.out.println("ѧ����Ϣɾ������ɣ��밴������˳�! \n");
			sc.nextLine();
			clear();        //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//�޸�ѧ����Ϣ
	public static  void  alter_student() {
		try {
			List<Student>   student = new ArrayList<Student>();  //ѧ����Ϣ�洢��λ
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("\t ��������Ҫ�޸�ѧ����ѧ�ţ�");
			String     num = sc.next();
			sc.nextLine();
			String     temp = null;
			Student    st = null;
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\\s+");
				
				if( s[0].equals(num) ) {
					System.out.println("�������ѧ��������Ϣ��");
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
			flag1 = false;          //�����ļ�
			write_file(student);
			System.out.println("ѧ����Ϣ�޸�����ɣ��밴������˳�");
			sc.nextLine();
			clear();      //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//����ѧ�Ų鿴ѧ����Ϣ
	public static  void  check_student() {
		try {
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			System.out.print("����������Ҫ�鿴��ѧ����ѧ�ţ�");
			String     num = sc.next();
			sc.nextLine();
			String     temp = null;
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\t");
				if( s[0].equals(num) ) {
					System.out.println("ѧ��" + "\t" + "����" + "\t" + "���֤��" + "\t" + "רҵ" + "\t" + "�༶" + "\t" + "����" + "\t" + "��ַ" + "\t" + "�ɼ�" );
					System.out.println( s[0]  + "\t" + s[1]  +  "\t" +   s[2]    + "\t" +  s[3]  + "\t" +  s[4]  + "\t" + s[5]  +  "\t" +  s[6]  + "\t" + s[7]  );
				}
			}
			br.close();
			System.out.println("��ѯ����ɣ��밴������˳���\n");
			sc.nextLine();
			clear();       //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//ѧ�����ɼ��ĴӸߵ�������
	public  static  void  sort_studentScore() {
		try {
			List<Student>   student = new ArrayList<Student>();  //ѧ����Ϣ�洢��λ
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
			
			Collections.sort(student, new Comparator<Student>() { //���������ñȽ�������
				public int compare(Student s1,Student s2) {
					return Integer.parseInt(s2.score) - Integer.parseInt(s1.score);
				}
			});
			
			flag1 = false;       //�����ļ�
			write_file(student);
			sc.nextLine();
			clear();       //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//�鿴�ɼ����а�
	public static  void  check_score() {
		try {
			String     pathname = "D:\\Users\\PUG\\eclipse-workspace\\system\\src\\system\\Test.txt";
			File          file  = new File(pathname);
			FileReader       fr = new FileReader(file);
			BufferedReader   br = new BufferedReader(fr);
			String     temp = null;
			System.out.println("ѧ��" + "\t" + "����" + "\t" + "���֤��" + "\t" + "רҵ" + "\t" + "�༶" + "\t" + "����" + "\t" + "��ַ" + "\t" + "�ɼ�" );
			while( (temp = br.readLine()) != null) {
	            String    s[] = temp.split("\t");
				System.out.println( s[0]  + "\t" + s[1]  +  "\t" +   s[2]    + "\t" +  s[3]  + "\t" +  s[4]  + "\t" + s[5]  +  "\t" +  s[6]  + "\t" + s[7]  );

			}
			br.close();
			System.out.println("ѧ���ɼ��Ѵ�ӡ���밴������˳�! \n");
			sc.nextLine();
			clear();      //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//�鿴����ɼ��ֲ�
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
			System.out.println("ѧ������ɼ��ֲ����£�");
			System.out.println("ѧ������Ϊ��" + count + "\t\t" + "ѧ����ƽ���ɼ�Ϊ���ɼ�" + average);
			System.out.println("�ɼ���0-59��֮��������У�"   + stage5 + "��" + "\t" + "ռ�������ģ�" + stage5*1.0/count*100 + "%");
			System.out.println("�ɼ���60-69��֮��������У�"  + stage4 + "��" + "\t" + "ռ�������ģ�" + stage4*1.0/count*100 + "%");
			System.out.println("�ɼ���70-79��֮��������У�"  + stage3 + "��" + "\t" + "ռ�������ģ�" + stage3*1.0/count*100 + "%");
			System.out.println("�ɼ���80-89��֮��������У�"  + stage2 + "��" + "\t" + "ռ�������ģ�" + stage2*1.0/count*100 + "%");
			System.out.println("�ɼ���90-100��֮��������У�" + stage1 + "��" + "\t" + "ռ�������ģ�" + stage1*1.0/count*100 + "%");
			
			br.close();
			System.out.println("\nѧ���ɼ��Ѵ�ӡ���밴������˳�! \n");
			sc.nextLine();
			clear();      //����
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	
	//�������̨
	public static void clear() {

		   try {
				
				Robot r = new Robot();
				r.mousePress(InputEvent.BUTTON3_DOWN_MASK);		//��������Ҽ�
				r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);	//�ͷ�����Ҽ�
				r.keyPress(KeyEvent.VK_CONTROL);				//����Ctrl��
				r.keyPress(KeyEvent.VK_R);						//����R��
				r.keyRelease(KeyEvent.VK_R);					//�ͷ�R��
				r.keyRelease(KeyEvent.VK_CONTROL);				//�ͷ�Ctrl��
				r.delay(100);
	
		   }
		   catch (AWTException e) {
			   e.printStackTrace();
			 }
	}

		
}
