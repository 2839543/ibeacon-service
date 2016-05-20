package com.ibeacon.dao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class BaseDao {

	protected Connection con = null;
	protected PreparedStatement pre = null;
	protected ResultSet res = null;

	/**
	 * 初始化数据库连接
	 */
	protected void connect() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mysql.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String driverClassName = properties.getProperty("driverClassName");
		String url = properties.getProperty("url");
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, properties);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭数据库连接
	 */
	protected void close() {
		try {
			if (con != null)
				con.close();
			if (pre != null)
				pre.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected boolean findTable(String tableName){
		boolean exsit = false;
		this.connect();
		try {
			DatabaseMetaData metaData = con.getMetaData();
			res = metaData.getTables(null, null,  null,new String[] { "TABLE" });
			 while(res.next()){
				 String name = res.getString("TABLE_NAME");
				 if(tableName.equals(name)){
					 exsit = true;
					 System.out.println("table is exsit");
					 break;
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("table query fail");
		}finally{
			this.close();
		}
		return exsit;
	}
	/**   
	  * 根据表名称 和 实体属性 创建一张表 
     * @param tableName   
     * @param obj 具体生成什么样的表看该对象 
	 * @param noCol 排除列key设置为实体类字段名，值不能为null
	 * @return
	 */
    public boolean createTable(String tableName,Object obj, Map noCol){
    	boolean result=false;
    	try { 
    	if(findTable(tableName)){
    		return result;
    	}
    	this.connect();
        StringBuffer sb = new StringBuffer("");     
        sb.append("CREATE TABLE `" + tableName + "` (");     
        sb.append(" `id` int(11) NOT NULL AUTO_INCREMENT,");  
        Class c = obj.getClass();  
        Field field[] = c.getDeclaredFields();  
        for (Field f : field) {  
//        	System.out.println(f.getName()); 
//        	System.out.println(noCol.get(f.getName()) == null); 
            if(noCol.get(f.getName()) == null){
                String type = f.getType().toString();  
                if(type.equals("class java.lang.String")){// Str  
                     sb.append("`" + f.getName() + "` varchar(100) DEFAULT NULL,");     
                }else if(type.equals("int") || type.equals("class java.lang.Integer")){// int  
                    sb.append("`" + f.getName() + "` int(11) DEFAULT NULL,");     
                }else if(type.equals("double") || type.equals("class java.lang.Double")){// double  
                    sb.append("`" + f.getName() + "` double DEFAULT NULL,");     
                }
            }
        } 
        sb.append(" PRIMARY KEY (`id`)");     
        sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");     
        pre = con.prepareStatement(sb.toString());
    	result =  pre.execute();
    	System.out.println("create table success");
        } catch (Exception e) {  
        	e.printStackTrace();
        	System.out.println("create table fail");
        }finally{
        	this.close();
        }  
        return result;
    }   
}
