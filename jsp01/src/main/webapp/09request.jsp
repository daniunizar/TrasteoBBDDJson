<html>
<head>
<meta charset="utf-8">
</head>
<body>
<h1>Objeto JSP Predefinido: Request</h1>
Mostramos en pantalla los registros recuperados del formulario HTML
Nombre:<%=request.getParameter("nombre") %><br/>
Apellido:<%=request.getParameter("apellido") %>
</body>
</html>