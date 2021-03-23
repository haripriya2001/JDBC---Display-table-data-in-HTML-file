package com.priyasoft.jdbc;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayData {

	public static void main(String[] args) {
		FileOutputStream fos;
		Connection con= null;
		Statement st=null;
		ResultSet rs=null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			st=con.createStatement();
			rs=st.executeQuery("select * from emp1");
			String data="";
			data=data+"<html><body><table border='1' align='center' cellpadding='5' cellspacing='5'>";
			data=data+"<tr><th>ENO</th><th>ENAME</th><th>ESAL</th><th>EADDR</th></tr>";
			while(rs.next()){
				data=data+"<tr>";
				data=data+"<td>"+rs.getInt("ENO")+"</td>";
				data=data+"<td>"+rs.getString("ENAME")+"</td>";
				data=data+"<td>"+rs.getFloat("ESAL")+"</td>";
				data=data+"<td>"+rs.getString("EADDR")+"</td>";
				data=data+"</tr>";
				
			}
			data=data+"</table></body></html>";
			fos=new FileOutputStream("E:/document/emp.html");
			byte[] b=data.getBytes();
			fos.write(b);
			System.out.println("Data Transfered to E:/Document/emp.");
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
			rs.close();
			st.close();
			con.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}

}
