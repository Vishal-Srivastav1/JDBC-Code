package jdbc.swing.frame;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
  CREATE TABLE "C##MYDB11AM"."JDBC_PAYMENT" 
   (	"ACC_ID" NUMBER NOT NULL ENABLE, 
	"ACC_NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"BALANCE" NUMBER NOT NULL ENABLE, 
	 CONSTRAINT "JDBC_PAYMENT_PK" PRIMARY KEY ("ACC_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;*/

public class Transfer_Money {
       private static final String QUERY_STRING="UPDATE SET ACC_NAME, BALANCE WHERE ACC_ID=? ";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long SrcAccNo=0, DesAccNo=0;
		double amount=0;
		try (Scanner scanner=new Scanner(System.in)){
			if (scanner!=null) {
				 // read Input
				 System.out.println("ENTER Your AccNO ");
				 SrcAccNo=scanner.nextLong();
				 System.out.println("Enter Your Deposit_AccNO ");
				 DesAccNo=scanner.nextLong();
				 System.out.println("Enter Your Deposit Balance_AccNo");
				 amount=scanner.nextDouble();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
           // Class.forName("oracle.jdbc.driver.OracleDriver");
		try(Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
				Statement statement=connection.createStatement()  ) {
			
			      if (connection!=null) {
					connection.setAutoCommit(false);
				}
			if (statement!=null) {
				 // Add Query to the Batch (Executing Query in Batch )
				// for Withdraw operation
				if (amount==0 || amount<0 ) throw new IllegalArgumentException(); {
					    
					statement.addBatch("update JDBC_PAYMENT set BALANCE= BALANCE-"+amount+" where ACC_ID="+SrcAccNo);
					 // for Deposit operation
					statement.addBatch("update JDBC_PAYMENT set BALANCE= BALANCE+"+amount+" where ACC_ID="+DesAccNo);
					
				//Execute the batch 
			int result []=statement.executeBatch();
				
				boolean flag= true;
				for (int i = 0; i < result.length; ++i) {
					if (result[i]==0) {
						flag= false;
						break;
					}
				}
				if (flag==true) {
					connection.commit();
					System.out.println(" Tx Commited :  Money Transffered");
				}else {
					connection.rollback();
					System.out.println(" Tx Rollback :  Money Not Transffered");
				}	
				
			}// throw if  close 
			
		}// if close 
			
		}// try close
		catch (IllegalArgumentException ie) {
			System.out.println("Erro: Balance Should be grether than > 0 ");
		}
		catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}

}
