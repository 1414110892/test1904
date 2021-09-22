package com.controller;

import com.dao.QueryDao;
import com.entity.province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        //调用dao，获取所有的省份信息，是一个List集合
        QueryDao dao = new QueryDao();
        List<province> p = dao.queryProvinceList();
        System.out.println(p);
        //把list转为json数据格式
        if(p != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(p);
        }

        //输出json数据，响应ajax请求的，返回数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);

    }
}
