<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="post" action="upload" enctype="multipart/form-data">
    <p>Choose the file</p>
    <input type="file" name="file" required>
    <input type="submit" value="Upload">
</form>
</body>
</html>