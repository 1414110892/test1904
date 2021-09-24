package com.controller;

import com.dao.QueryDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class CityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        //获取请求传过来的省份id
        String id = request.getParameter("proid");
        if(id != null && !"".equals(id.trim())){
            QueryDao dao = new QueryDao();
            List list = dao.queryCityList(Integer.valueOf(id));

            //把list转为json
            ObjectMapper ow = new ObjectMapper();
            json = ow.writeValueAsString(list);
        }

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void fun(){

    }
}
