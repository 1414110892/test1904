package com.dao;

import com.entity.city;
import com.entity.province;
import com.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {

    DBUtil util = new DBUtil();
    //查询所有省份信息
    public List<province> queryProvinceList(){
        List list = new ArrayList();
        String sql = "select id,name,jiancheng,shenghui from pro order by id";
        PreparedStatement ps = util.createStatement(sql);
        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                province p = null;
                p = new province(rs.getInt("id"),rs.getString("name"),rs.getString("jiancheng"),rs.getString("shenghui"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return list;
    }

    //查询一个省份下面的所有城市
    public List<city> queryCityList(Integer proId){
        List list = new ArrayList();
        String sql = "select id,name from city where provinceid = ?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setInt(1,proId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                city p = null;
                p = new city(rs.getInt("id"),rs.getString("name"),null);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return list;
    }
}
