<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    ProdottoDTO product= (ProdottoDTO) request.getAttribute("product");
%>


<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,Model.DAO.ProdottoDTO"%>

<head>
    <title>prodotto</title>
    <link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="../header.jsp" %>
<div id="SingleProduct">
    <div id="imgProdotto">
        <img src="./GetImmagine?id=<%=product.getIdProdotto() %>" onerror="this.src='../imgs/nophoto.png'">
    </div>

    <div id="descrizioneProd">
        <h1><%=product.getNome() %></h1>
        <h1 id="prezzo"><%=product.getPrezzo() %> €</h1>
        <p> <%=product.getDescrizione() %> </p>

        <form action="./product">
            <input type="hidden" name="action" value="addC">
            <input type="hidden" name="id" value="<%=product.getIdProdotto()%>">
            <input type="number" name="quantita" min="1" required max="<%=product.getDisponibilità()%>" placeholder="max <%=product.getDisponibilità()%>">
            <input id="buttonAddCart" type="submit" value="aggiungi al carrello"/>
        </form>

    </div>

</div>



<%@include file="../footer.html"%>
</body>
</html>