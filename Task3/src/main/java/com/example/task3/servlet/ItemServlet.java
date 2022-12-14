package com.example.task3.servlet;

import com.example.task3.dto.Item;
import com.example.task3.services.ItemService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

@WebServlet(name = "itemServlet", value = "/items")
public class ItemServlet extends HttpServlet {

    private final ItemService service = ItemService.getInstance();
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("doGet method");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        write(writer, service.getItems());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("doPost method");
        response.setContentType("application/json;charset=UTF-8");
        String action = request.getParameter("action");
        action = (action == null) ? "action" : action;
        PrintWriter writer = response.getWriter();
        Item item;
        int code;

        switch (action) {
            case "add":
                item = getItem(request);
                code = item.getCode();
                logger.info(service.addItem(item) ? "item №" + code + " was added successfully" :
                        "error adding new item №" + code);
                write(writer, item);
                break;
            case "edit":
                item = getItem(request);
                code = item.getCode();
                int oldCode = parseInt(request.getParameter("oldCode"));
                logger.info(service.editItem(oldCode, item) ? "item №" + code + " was edited successfully" :
                        "error editing item №" + code);
                write(writer, item);
                break;
            case "remove":
                code = parseInt(request.getParameter("code"));
                item = service.findByCode(code);
                logger.info(service.removeItem(code) ? "item №" + code + " was removed successfully" :
                        "error removing item №" + code);
                write(writer, item);
                break;
            default:
                throw new ServletException("Unable execute " + action);
        }
    }

    private Item getItem(HttpServletRequest request) {
        try {
            return new Item(
                    parseInt(request.getParameter("code")),
                    request.getParameter("name"),
                    parseInt(request.getParameter("price"))
            );
        } catch (RuntimeException e) {
            logger.severe(e.getMessage());
            throw new IllegalArgumentException("Illegal request parameters ..");
        }
    }

    private void write(PrintWriter writer, Object o) {
        writer.println(gson.toJson(o));
        writer.flush();
    }
}