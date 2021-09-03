<%@ page import="jsp02.*" %>
<html>
<body>
<h1>Encapsulación no estática</h1>
<%! Calculadora calc = new Calculadora(); %>
<%= calc.metodoSuma(5, 6) %>

De esta otra forma no funciona
<%
Calculadora calc2 = new Calculadora();
calc2.metodoSuma(7,3);
%>
</body>
</html>