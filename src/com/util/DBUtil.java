package com.util;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Map;
import java.util.Set;

/*
    JDBC工具类，简化JDBC编程
 */
public class DBUtil {

    Connection conn = null;
    PreparedStatement ps = null;

    //-------------通过全局作用域对象得到Connection---------------
    public Connection getCon(HttpServletRequest request){

        //1.通过请求对象，得到全局作用域对象
        ServletContext application = request.getServletContext();
        //2.从全局作用域对象得到map
        Map map = (Map)application.getAttribute("key1");
        //3.从Map得到一个处于空闲状态Connection
        Set<Connection> set = map.keySet();
        for(Connection conn : set){
            if((Boolean) map.get(conn) == true){
                map.put(conn,false);
                break;
            }
        }
        return conn;
    }
    //-------------通过全局作用域对象得到Connection---------------
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
    }
    public Connection getCon(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springdb","root","gaoming666");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return conn;
        }
    }

    public PreparedStatement createStatement(String sql) {
        try {
            conn = getCon();
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }
    public PreparedStatement createStatement(String sql,HttpServletRequest request) {
        try {
            conn = getCon();
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }
    public void close(){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(HttpServletRequest request){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        map.put(conn,true);
    }
    public void close(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
