<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%

    CarrelloBean cart = (CarrelloBean) request.getSession().getAttribute("cart");

%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,Model.Beans.CarrelloBean,Model.DAO.ProdottoDTO"%>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Carrello</title>
    <link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../../fontawesome-free-5.13.0-web/css/all.css">
</head>
<body>

<%@ include file="../header.jsp" %>
<div id="Cart">
    <div id="contentCart">

        <%
            if(!cart.noProduct()){
                List<ProdottoDTO> prodcart = cart.getProducts();
                for(ProdottoDTO beancart: prodcart) {
        %>

        <div id="elementCart">

            <div id="imgCart">
                <img src="${pageContext.servletContext.contextPath}/GetImmagine?id=<%=beancart.getIdProdotto() %>"  style="width: 100px">
            </div>

            <div id="nomeCart">
                <h3><%=beancart.getNome()%></h3>
            </div>
            <div id="responsive">


                <div id="quantitaCart">
                    <form id="formQt" action="${pageContext.servletContext.contextPath}/UpdateFromCart">

                        <input type="hidden" name="id" value="<%=beancart.getIdProdotto()%>">
                        <input id="qtCart" type="number" name="quantita" min="1" max="<%=beancart.getDisponibilità()%>"  placeholder="già nel carrello <%=cart.getQ(beancart)%> max <%=beancart.getDisponibilità()%>" required>

                        <button id="aggiornaQt"  class="buttonCart" type="submit"><i class="far fa-edit"></i> Quantità</button>
                    </form>

                </div>

                <div id="prezzoProdCart">
                    <%=beancart.getPrezzo()*cart.getQ(beancart) %> €
                </div>
                <div id="deleteCart">
                    <a href="${pageContext.servletContext.contextPath}/DeleteFromCart?id=<%=beancart.getIdProdotto()%>"><i class="fas fa-trash"></i></a>
                </div>
            </div>
        </div>
        <%
            }
        %>
        <h3>TOTALE: <%=cart.getTotal() %> €</h3>
        <div id=checkoutCart>
            <a class="buttonCart" href="CartCheckoutView.jsp">procedi all'acquisto</a>
        </div>

    </div>

    <%
        /**se non ci sono prodotti**/
    }else {
    %>

    <div id="noProductCart">
        <h1>Nessun prodotto nel carrello</h1>
        <div id="imgNoProduct"><img src="${pageContext.servletContext.contextPath}/imgs/cartempty.jpg"></div>
    </div>
    <%
        }
    %>


</div>

<%@include file="../footer.html" %>

</body>

</html>