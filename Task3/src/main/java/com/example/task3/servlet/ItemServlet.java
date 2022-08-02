package com.example.task3.servlet;

import com.example.task3.dto.Item;
import com.example.task3.services.ItemService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name = "itemController", value = "/items")
public class ItemServlet extends HttpServlet {

    private final ItemService service = ItemService.getInstance();
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("doGet method");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(gson.toJson(service.getItems()));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("doPost method");
        response.setContentType("application/json;charset=UTF-8");
        String action = request.getParameter("action");
        PrintWriter writer = response.getWriter();
        Item item;

        switch (action == null ? "action" : action) {
            case "add":
                item = getItem(request);
                logger.info(service.addItem(item) ? "item №" + item.getCode() + " was added successfully" :
                                "error adding new item");
                service.addItem(item);
                write(writer);
                break;
            case "edit":
                try {
                    item = getItem(request);
                    int itemCode = item.getCode();
                    int oldCode = Integer.parseInt(request.getParameter("oldCode"));
                    logger.info(service.editItem(oldCode, item) ? "item №" + itemCode + " was edited successfully" :
                                    "error editing item №" + itemCode);
                    write(writer);
                } catch (RuntimeException e) {
                    logger.severe(e.getMessage());
                    throw e;
                }
                break;
            case "remove":
                try {
                    int code = Integer.parseInt(request.getParameter("code"));
                    logger.info(service.removeItem(code) ? "item №" + code + " was removed successfully" :
                                    "error removing item №" + code);
                    write(writer);
                } catch (RuntimeException e) {
                    logger.warning(e.getMessage());
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal request parameters ..");
        }
    }

    private Item getItem(HttpServletRequest request) {
        try {
            return new Item(
                    Integer.parseInt(request.getParameter("code")),
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("price"))
            );
        } catch (RuntimeException e) {
            logger.severe(e.getMessage());
            throw new IllegalArgumentException("Illegal request parameters ..");
        }
    }

    private void write(PrintWriter writer) {
        writer.println(gson.toJson(service.getItems()));
        writer.flush();
    }
}