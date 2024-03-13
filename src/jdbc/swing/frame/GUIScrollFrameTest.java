package jdbc.swing.frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class GUIScrollFrameTest extends JFrame implements ActionListener,WindowListener {
	
	private static final String GET_STUDENT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	   private JLabel lsno,lsname,laddr,lavg;
	   private JTextField tsno,tsname,taddr,tavg;
	   private JButton bFirst, bLast, bPrevious, bNext;
	   private Connection con;
	   private PreparedStatement ps;
	   private ResultSet rs;
	   
	   //Constructor
		public GUIScrollFrameTest() {
			System.out.println("GuIFrented-ScrollFrameTest:: 0-param Construcotr (Start)"); 
			setTitle("GuIFrented-Scroll Frame");
			setSize(400,300);
			setLayout(new FlowLayout());
			
			//add comps
			lsno=new JLabel("sno");
			add(lsno);
			tsno=new JTextField(10);
			add(tsno);
			
			lsname=new JLabel("sname");
			add(lsname);
			 tsname=new JTextField(10);
			add(tsname);
			
		    laddr=new JLabel("student Avg");
			add(laddr);
			 taddr=new JTextField(10);
			add(taddr);
			
			lavg=new JLabel("student Avg");
			add(lavg);
			tavg=new JTextField(10);
			add(tavg);
			
			
			 bFirst=new JButton("first");
			 bLast=new JButton("Lst");
			 bPrevious=new JButton("Previus");
			 bNext=new JButton("Next");
			add(bFirst);add(bLast);add(bPrevious);add(bNext);
			
			//register and activate ActionListener for all the 4button
			bFirst.addActionListener(this);
			bLast.addActionListener(this);
			bPrevious.addActionListener(this);
			bNext.addActionListener(this);
			
			//add WindowListener(this)
			this.addWindowListener(this);
			
			//disable editing on text Boxes
			tsno.setEditable(false);
			tsname.setEditable(false);
			taddr.setEditable(false);
			tavg.setEditable(false);
			
			
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// the closing of frame window will terminates 
			initializeJDBC();
			  System.out.println("GuIFrented-ScrollFrameTest:: 0-param Construcotr (End)");                                              //Application flow.
		}
		
		private void initializeJDBC() {
			System.out.println("GUIScrollFrameTest.initializeJDBC()");
			try{
				//establish the Connection				
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
				//create PrepadeStatement Obj
			 ps=con.prepareStatement(GET_STUDENT_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                                 ResultSet.CONCUR_UPDATABLE);
			
			//prepared Scrollable Rs obj
			rs=ps.executeQuery();		
			}//try
			catch (SQLException se) {
				se.printStackTrace();				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}//INITIALLIZATIONjdbc
		
		public static void main (String[]args) {
			System.out.println("GuIFrented-ScrollFrameTest :Main()(Start)"); 
			new GUIScrollFrameTest();//anonymous object
			System.out.println("end of Main method");
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("GUIScrollFrameTest.actionPerformed()");
			boolean flag=false;
			if(ae.getSource()==bFirst) {
				try {
					rs.first();
					flag=true;
					System.out.println("First button is clicked");
				}
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
			else if (ae.getSource()==bNext) {
				System.out.println("Next button is clicked");
				try {
					if(!rs.isLast()) {
					  rs.next();					
					flag=true;
				}					
			}
				catch (SQLException se) {
					se.printStackTrace();
				}
		}
			else if (ae.getSource()==bPrevious) {
				System.out.println("Previous buttin is Clicked");
				try {
					if(!rs.isFirst()) {
						rs.previous();
						flag=true;
					}
				}
				catch (SQLException se) {
				   se.printStackTrace();
				}
			}
			else {
				System.out.println("last button is clicked");
				try {
					rs.last();
					flag=true;
				} catch (SQLException se) {
					se.printStackTrace();
					
				}
			}//else
			if(flag==true) {
				try {
					tsno.setText(rs.getString(1));
					tsname.setText(rs.getString(2));
					
					//tsname.setText(rs.getString(2));
					  taddr.setText(rs.getString(3));
					  tavg.setText(rs.getString(4));					
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}//if
				
			}//Action performed
		@Override
		public void windowOpened(WindowEvent e) {
			
		}
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("GUIScrollFrameTest.WindowClosing()");
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
				System.out.println("GUIScrollFrameTEST.windowClosing()::JDBC con is close");
			}		
		   catch (Exception ea) {
				ea.printStackTrace();
			}
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			
		}
		
		public void windowIconified(WindowEvent e) {
					
		}
		
		public void windowDeiconified(WindowEvent e) {
				
		}
		
		public void windowActivated(WindowEvent e) {
			
		}
		
		public void windowDeactivated(WindowEvent e) {
			
		}
}//class



