<%--
  Created by IntelliJ IDEA.
  User: asdfLaptop
  Date: 29.08.2016
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Oving1</title>
  </head>
  <body>
      <p><b>Tekst: </b><%= request.getParameter("tekst")%></p>

      <p><%out.println(new java.util.Date()); %></p>
      <p><script>document.write(new Date());</script></p>

      <button onclick="window.history.back()">Back</button>
  </body>
</html>
