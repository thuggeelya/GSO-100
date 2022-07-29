<%@ page import="com.example.task3.dto.Item" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catalogue</title>
</head>
<body>

<div id="items">
    <jsp:useBean id="items" scope="request" type="java.util.List<com.example.task3.dto.Item>"/>
    <form method="post">
        <table bgcolor="#dcdcdc">
            <thead>All items</thead><br>
            <input type="submit" value="Add" formaction="items?action=add">
            <tr>
                <td><b>№</b>
                </td>
                <td><b>Code</b>
                </td>
                <td><b>Name</b>
                </td>
                <td><b>Price</b>
                </td>
            </tr>
            <%
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
            %>
            <tr>
                <td><%=i + 1 + ". "%>
                </td>
                <td><%=item.getCode()%>
                </td>
                <td><%=item.getName()%>
                </td>
                <td><%=item.getPrice()%>
                </td>
                <td>
                    <input type="submit" value="Edit" formaction="items?action=edit">
                </td>
                <td>
                    <input type="submit" value="Remove" formaction="items?action=remove">
                </td>
            </tr>
            <%}%>
        </table>
    </form>
</div>

</body>
</html>