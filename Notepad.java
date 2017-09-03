import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import javax.swing.*;

public class Notepad implements ActionListener,KeyListener
{
	Frame f;
	MenuBar mb;
	Menu m1,m2;
	MenuItem nw,opn,sve,sveas,ext,fnd,fr;
	Frame f1,f2,f3,f4;
	JFrame f5;
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
	TextField t1,t2,t3,t4;
	Panel p1,p2,p3,p4,p5,p6,p7;
	String str,str1,path,file;
	String str2,str3,replace;
	String fp,fn;
	FileDialog fd;
	FileWriter writer;
	TextArea ta=new TextArea();
	String nm="Untitled 1";
	File temp;
	String wr;
	int tt1,t=0,flag=0,c,d;
	int count=0;
	Stack st=new Stack();
	String abc;
	boolean key=false;
	boolean saved=false;
	int arr[]=new int[20];
	public Notepad()
	{
		st.push("A");
		f=new Frame(nm);
		f.setSize(400,400);
		mb=new MenuBar();
		m1=new Menu("File");
		m2=new Menu("Edit");
		nw=new MenuItem("New");
		opn=new MenuItem("Open");
		sve=new MenuItem("Save");
		sveas=new MenuItem("Save As");
		ext=new MenuItem("Exit");
		fnd=new MenuItem("Find");
		fr=new MenuItem("Find & Replace");
		b1=new Button("FindNext");
		b2=new Button("Close");
		b3=new Button("Find Next");
		b4=new Button("Replace");
		b5=new Button("Replace All");
	    b6=new Button("Close");
		nw.addActionListener(this);
		opn.addActionListener(this);
		sve.addActionListener(this);
		sveas.addActionListener(this);
		ext.addActionListener(this);
		fnd.addActionListener(this);
		fr.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
	    b3.addActionListener(this);
	    b4.addActionListener(this);
	    b5.addActionListener(this);
	    b6.addActionListener(this);
		m2.add(fnd);    m2.add(fr);
		m1.add(nw);     m1.add(opn);
		m1.add(sve); m1.add(sveas);
		m1.addSeparator();  m1.add(ext);
		mb.add(m1);
		mb.add(m2);
		f.add(ta);
		f.setMenuBar(mb);
		f.setVisible(true);

		f1=new Frame("Find");
    	f1.setSize(400,250);
    	f1.setLayout(new GridLayout(2,2));
    	p1=new Panel();
    	p2=new Panel();
    	Label l1=new Label("Find");
    	p1.add(l1);
    	t3=new TextField(5);
    	p1.add(t3);

    	p2.add(b1);       p2.add(b2);
    	f1.add(p1);       f1.add(p2);

    	f3=new Frame("Find & Replace");
    	f3.setSize(400,250);
    	f3.setLayout(new GridLayout(5,4));
    	p1=new Panel();  p2=new Panel();
    	p3=new Panel();  p4=new Panel();  p5=new Panel();
    	l1=new Label("Find");
    	p1.add(l1);
    	t1=new TextField(5);
    	p2.add(t1);
    	Label l2=new Label("Replace With");
    	p3.add(l2);
    	t4=new TextField(5);
    	p4.add(t4);
    	p5.add(b3);  p5.add(b4);
    	p5.add(b5);  p5.add(b6);
    	f3.add(p1);  f3.add(p2);
    	f3.add(p3);  f3.add(p4);
    	f3.add(p5);

    	f4=new Frame("Close");
		f4.setSize(400,250);
		f4.setLayout(new GridLayout(3,2));
		p6=new Panel();  p7=new Panel();
		Label l10=new Label("Do you want to save the changes ?");
		p6.add(l10);
		b7=new Button("Yes");
		b8=new Button("No");
		b9=new Button("Cancel");
		p7.add(b7);  p7.add(b8);   p7.add(b9);
		f4.add(p6);
		f4.add(p7);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		ta.addKeyListener(this);

		f.addWindowListener(new WindowAdapter()
		{
	    	public void windowClosing(WindowEvent we)
				{
					while(true)
					{
					if(st.peek().equals("A"))
					{
						break;
					}
					else
					{
						st.pop();
					}
					System.out.println(st);
					}
					if(ta.getText().equals("") | key==false)
					{
						count=1;
						System.exit(0);
					}
					if(key)
					{
						if(count==1)
						{

						}
						else
						{
							f4.setVisible(true);
						}
						count=0;
					}



				}
		});

		f1.addWindowListener(new WindowAdapter()
		{
	    	public void windowClosing(WindowEvent e)
				{
				Window w=e.getWindow();
				w.setVisible(false);
				w.dispose();
				}
		});

		f3.addWindowListener(new WindowAdapter()
		{
	    	public void windowClosing(WindowEvent e)
				{
					Window w=e.getWindow();
					w.setVisible(false);
					w.dispose();
				}
		});

		f4.addWindowListener(new WindowAdapter()
		{
	    	public void windowClosing(WindowEvent e)
			{
				Window w=e.getWindow();
				w.setVisible(false);
				w.dispose();
			}
		});


	}

	public void actionPerformed(ActionEvent e)
	{
		//st.push("A");
		//st.push("B");
		str=e.getActionCommand();
		if(str.equals("New"))
   		{
   			st.push("New");
   			System.out.println(st.peek());
   			if(key==false)
   			{
   				ta.setText("");
   				nm="Untitled 1";
   				f.setTitle(nm);
   			}
   			else
   			{
   				f4.setVisible(true);
   			}
   			saved=false;


		}
		if(str.equals("Open"))
	    {
	    	st.push("Open");
	    	System.out.println(st.peek());
	    	if((saved & key==false) || saved==false & key==false)
	    	{
	    		open();
	    	}

   			else
   			{
   				f4.setVisible(true);
   			}
	    }
	    if(str.equals("Save"))
	   	 {
	   	 	if((key & saved)|| (!key & saved))
			{
				saveWithout();
			}
			else
			{
				save();
			}
	   	 }
	   	if(str.equals("Save As"))
	   	{
	   		save();
	   	}
	   	if(str.equals("Close"))
    	{
    		f1.setVisible(false);
    		f3.setVisible(false);
    	}
    	if(str.equals("Find"))
    	{
    		t3.setText("");
    		f1.setVisible(true);
    	}
    	if(str.equals("FindNext"))
    	 {
    	 	find1();
    	 }
    	if(str.equals("Find Next"))
    	 {
    	 	find();
    	 }
    	 if(str.equals("Replace"))
    	 {
    	 	replace();
    	 }
    	 if(str.equals("Replace All"))
    	{

	    	replaceAll();
    	}
    	if(str.equals("Find & Replace"))
    	{
    		t1.setText("");
    		t4.setText("");
    		f3.setVisible(true);
    	}
    	if(str.equals("Exit"))
    	{
    		if(key)
    		{
    			f4.setVisible(true);
    		}
    		else
    		{
    			f.setVisible(false);
    		}

    	}
    	if(str.equals("Yes"))
		{
			while(true)
			{
				if(key & saved)
				{
				if(st.peek().equals("New"))
				{
				  st.pop();
				  System.out.println(1);
				  f4.setVisible(false);
				  saveWithout();
				  ta.setText("");
				  nm="Untitled1";
				  f.setTitle(nm);
				  break;

				}

				if(st.peek().equals("Open"))
				{
					 st.pop();
					 System.out.println(2);
					 f4.setVisible(false);
				  	 saveWithout();
				  	 open();
				  	 break;
				}

				else
				{

					System.out.println(3);
					f4.setVisible(false);
					saveWithout();
					System.exit(0);
				}

				}
			if(key)
			{
				if(st.peek().equals("New"))
				{
				  st.pop();
				  System.out.println(4);
				  f4.setVisible(false);
				  if(st.peek().equals("Open"))
					{
						saveWithout();
					}
					else
					{
						save();
					}

				  if(saved==false)
				  {
				  	try
				  	{
				  		writer.close();
				  	}
				  	catch(IOException m)
				  	{

				  	}
				  	catch(NullPointerException h)
			  	{
			  		break;
							  	}

				  }
				  else
				  {
				  ta.setText("");
				  nm="Untitled1";
				  f.setTitle(nm);
				  break;
				  }

				}

				if(st.peek().equals("Open"))
				{
					st.pop();
					System.out.println(5);
					f4.setVisible(false);
				  	save();
				  	 if(saved==false)
				  	 {
				  		try
				  		{
				  		 writer.close();
				  		}
				  		catch(IOException m)
				  		{

				  		}
				  		catch(NullPointerException h)
			  	{
			  		break;
							  	}

				  	}
				  	else
				  	{
				  		open();
				  		break;
				  	}
				}
				else
				{
				  System.out.println(6);
				  f4.setVisible(false);
				  save();
				  System.out.println(saved);
				  if(saved==false)
				  {
				  	try
				  	{
				  		writer.close();
				  	}
				  	catch(IOException m)
				  	{

				  	}
			  	catch(NullPointerException h)
			  	{
			  		break;
				}


				  }
				  else
				  {
				  	System.exit(0);
				  }


				}

			}


			}
		}
		if(str.equals("No"))
		{
			while(true)
			{
			if(key)
			{
				if(st.peek().equals("New"))
				{
				  st.pop();
				  f4.setVisible(false);
				  ta.setText("");
				  nm="Untitled1";
				  f.setTitle(nm);
				  key=false;
				  break;
				}

				if(st.peek().equals("Open"))
				{
					 st.pop();
					 f4.setVisible(false);
				  	 open();
				  	 break;
				}
				else
				{
					System.exit(0);
				}

			}
			}


		}
		if(str.equals("Cancel"))
		{
			f4.setVisible(false);
		}


	}

	void save()
	{
				fd= new FileDialog(f,"Save File",FileDialog.SAVE);
	    		fd.setVisible(true);
		    		fn=fd.getFile();
	    		nm=fd.getFile();
	    		f.setTitle(nm);
	    		fp=fd.getDirectory();

	    		if(fd.getFile()==null)
	    		{
	    			count=1;
	    			f.setTitle(nm);
	    		}

	    		else
	    		{
	    			File f=new File(fp,fn);
	    			temp=new File(fp,fn);

	    		try
	    		{

	    	    f.createNewFile();
	    		writer = new FileWriter(f);
	   			wr=ta.getText();
	   			writer.write(wr);
	      		writer.flush();
	     		writer.close();

	     			key=false;
	     			saved=true;
	    		}

	    		catch(Exception a)
	    		{
	    			a.printStackTrace();
	    		//	ta.setText("MOhit Basnal");
	    		}
	    	}
	}

	void saveWithout()
	{
			try
			{
					 writer = new FileWriter(temp);
		   			String wr=ta.getText();
		         	writer.write(wr);
			      	writer.flush();
			     	writer.close();
			     	key=false;
			     	saved=true;
		   	}

		   	catch(Exception d)
		   	{
		   		System.out.println("file exists");
		    }
	}

	void open()
	{

			fd= new FileDialog(f,"Choose File",FileDialog.LOAD);
			fd.setVisible(true);
			fn=fd.getFile();
			nm=fn;
			f.setTitle(nm);
			fp=fd.getDirectory();
			if(fd.getFile()==null)
	    		{
	    			nm="Untitled 1";
	    			f.setTitle(nm);

	    		}

	    		else
	    		{
					f.setTitle(nm);

			         File f1=new File(fp,fn);
			         temp=new File(fp,fn);
			         ta.setText("");

			       	try{
			       		FileReader fis=new FileReader(f1);
			    	  	BufferedReader br=new BufferedReader(fis);

                                 int ch;
                                 while((ch=br.read())!=-1)
                                 {
                          		        String n=""+(char)ch;
			    				        ta.setText(ta.getText()+n);
			    			     }
			    			     saved=true;
			    			     key=false;

			    			fis.close();
			   		}
			  	catch(Exception a)
			    	{
			    			System.out.println("File not Found");
			    	}
				}
			}

		void find()
		{
			tt1=ta.getSelectedText().length();
		    t=ta.getCaretPosition()+tt1;
		    ta.requestFocus();
            str2=t1.getText();

           	Pattern p=Pattern.compile(Pattern.quote(str2));
            str3=ta.getText();
            str3 = str3.replaceAll("\r", "");
           	ta.setText(str3);
        	Matcher m=p.matcher(str3);
           	ta.setCaretPosition(t);
           		System.out.println(t);
           		int str5=ta.getText().length();
           	if(t>str5)
           	{
           				JFrame f2=new JFrame();
          		    	f2.setSize(200,200);
          		    	JLabel l1=new JLabel("Cannot Find "+str2);
          		    	JButton b1=new JButton("Ok");
          		    	b1.addActionListener(this);
          		    	f2.add(l1);f.add(b1);
          		    	f2.setVisible(true);
          		    	t=0;

           	}
		           	else
		           		if(m.find(t))
                    {
                           c=m.start();
           	               d=m.end();
  	                       ta.select(c,d);
  	                       System.out.println("mohit");
  	          		}
		           else

          		    {
          		    	f5=new JFrame();
          		    	f5.setSize(200,200);
          		    	JLabel l1=new JLabel("Cannot find "+str2);
          		    	JButton b1=new JButton("Ok");
          		    	b1.addActionListener(this);

          		    	f5.add(l1);f.add(b1);
          		    	f5.setVisible(true);
          		    	t=0;

          		    }
		}

		void find1()
		{
			tt1=ta.getSelectedText().length();
		    t=ta.getCaretPosition()+tt1;
		    System.out.println(t);
		    ta.requestFocus();
            str2=t3.getText();
           	Pattern p=Pattern.compile(Pattern.quote(str2));
            str3=ta.getText();
            str3 = str3.replaceAll("\r", "");
           	ta.setText(str3);
        	Matcher m=p.matcher(str3);
           	ta.setCaretPosition(t);
           	System.out.println(t);
           	int str5=ta.getText().length();
           	if(t>str5)
           	{
           				f5=new JFrame();
          		    	f5.setSize(200,200);
          		    	JLabel l1=new JLabel("Cannot Find "+str2);
          		    	JButton b1=new JButton("Ok");
          		    	b1.addActionListener(this);
          		    	f5.add(l1);f.add(b1);
          		    	f5.setVisible(true);
          		    	t=0;

           	}
           	else
           		if(m.find(t))
                    {
                           c=m.start();
           	               d=m.end();
  	                       ta.select(c,d);

  	          		}

              else//if(!m.find(t))
          		    {
          		    	f5=new JFrame();
          		    	f5.setSize(200,200);
          		    	JLabel l1=new JLabel("Cannot Find "+str2);
          		    	JButton b1=new JButton("Ok");
          		    	b1.addActionListener(this);
          		    	f5.add(l1);f.add(b1);
          		    	f5.setVisible(true);
          		    	t=0;

          		    }
		}

		void replace()
		{
			String nm= ta.getSelectedText();
			System.out.println(nm);


			 if(flag==1 || ta.getSelectedText()!=null)
			 {
	            replace=t4.getText();
   	            ta.replaceText(replace,c,d);
			 }
			 ta.requestFocus();
    		 int tt1=ta.getSelectedText().length();
    		 t=ta.getCaretPosition()+tt1;
             System.out.println(t);
   			 str2=t1.getText();
             Pattern p=Pattern.compile(Pattern.quote(str2));
             str3=ta.getText();
             str3 = str3.replaceAll("\r", "");
             ta.setText(str3);
             Matcher m=p.matcher(str3);
             ta.setCaretPosition(t);
             if(m.find(t))
          		{
          			flag=1;
          			c=m.start();
           	        d=m.end();
           	        ta.select(c,d);
  	           		key=true;
          		}

          		    if(!m.find(t))
          		    {

          		    	f5=new JFrame();
          		    	f5.setSize(200,200);
          		    	JLabel l1=new JLabel("Cannot find "+str2);
          		    	JButton b1=new JButton("Ok");
          		    	b1.addActionListener(this);
          		    	f5.add(l1);
          		    	f5.setVisible(true);
          		    }



		}
		void replaceAll()
		{
     		ta.requestFocus();
          	str2=t1.getText();
           	str3=ta.getText();
            str3 = str3.replaceAll("\r", "");
            ta.setText(str3);
       		replace=t4.getText();
           	str3=str3.replaceAll(str2,replace);
			ta.setText(str3);
			key=true;
		}

		public void keyTyped(KeyEvent e)
		{

			key=true;
		}
		public void keyPressed (KeyEvent e)
		{
		//	arr[0]=e.VK_SHIFT;
		//	arr[1]=e.VK_CONTROL;
			arr[2]=e.VK_ALT;
			arr[3]=e.VK_PAUSE;
			arr[4]=e.VK_CAPS_LOCK;
			arr[5]=e.VK_ESCAPE;
			arr[6]=e.VK_PAGE_UP;
			arr[7]=e.VK_PAGE_DOWN;
			arr[8]=e.VK_END;
			arr[9]=e.VK_HOME;
			arr[10]=e.VK_LEFT;
			arr[11]=e.VK_DOWN;
			arr[12]=e.VK_UP;
			arr[13]=e.VK_RIGHT;
			arr[14]=e.VK_NUM_LOCK;
			arr[15]=e.VK_F1;
			arr[16]=e.VK_WINDOWS;

			int j=e.getKeyCode();
		//	System.out.println(j+"mohit");
			for(int i=0;i<17;i++)
			{
			//	System.out.println(arr[i]);
			 if(arr[i]==j)
			 {
			 	key=false;
			 	break;
			 }
			 else
			 {
			 	key=true;
			 }
			}
			System.out.println(key);
		}

		public void keyReleased(KeyEvent e)
		{
		//	arr[0]=e.VK_SHIFT;
		//	arr[1]=e.VK_CONTROL;
			arr[2]=e.VK_ALT;
			arr[3]=e.VK_PAUSE;
			arr[4]=e.VK_CAPS_LOCK;
			arr[5]=e.VK_ESCAPE;
			arr[6]=e.VK_PAGE_UP;
			arr[7]=e.VK_PAGE_DOWN;
			arr[8]=e.VK_END;
			arr[9]=e.VK_HOME;
			arr[10]=e.VK_LEFT;
			arr[11]=e.VK_DOWN;
			arr[12]=e.VK_UP;
			arr[13]=e.VK_RIGHT;
			arr[14]=e.VK_NUM_LOCK;
			arr[15]=e.VK_F1;
			arr[16]=e.VK_WINDOWS;

			int j=e.getKeyCode();
		//	System.out.println(j+"mohit");
			for(int i=0;i<17;i++)
			{
		//		System.out.println(arr[i]);
			 if(arr[i]==j)
			 {
			 	key=false;
			 	break;
			 }
			 else
			 {
			 	key=true;
			 }
			}
			System.out.println(key);
		}


		public static void main(String args[])
		{
			Notepad n=new Notepad();
		}
}
