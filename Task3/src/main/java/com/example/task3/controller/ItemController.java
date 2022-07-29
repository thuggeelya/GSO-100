package com.example.task3.controller;

import com.example.task3.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "itemController", value = "/items")
public class ItemController extends HttpServlet {

    private final ItemService service = ItemService.getInstance();

    @Override
    public void init() throws ServletException {
        Logger.getGlobal().info("init method");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Logger.getGlobal().info("doGet method");
        request.setAttribute("items", service.getItems());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.getGlobal().info("doPost method");
    }
}