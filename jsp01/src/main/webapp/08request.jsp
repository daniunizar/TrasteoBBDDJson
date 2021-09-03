<html>
<body>
<h2>Objetos Prefefinidos JSP</h2>
<h3>Objeto Request</h3>
Datos del navegador que realiza el Request:
<%=request.getHeader("User-Agent") %>
<br/><br/>
Petición del idioma utilizado:
<%=request.getLocale() %>
</body>
</html>