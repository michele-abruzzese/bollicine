<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    ProdottoDTO product= (ProdottoDTO) request.getAttribute("product");
    CarrelloService cart = (CarrelloService) request.getSession().getAttribute("cart");
%>


<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,Model.DTO.ProdottoDTO,Model.Services.CarrelloService"%>

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
        <p>Tipo:<%=product.getTipo()%></p>
        <p>Annata:<%=product.getAnnata()%></p>
        <p> <%=product.getDescrizione() %> </p>

        <div id="adProductInCart">
            <!-- bottone per aprire l'inserimento -->
            <button id="myBtn">Aggiungi al carrello</button>

            <!-- The Modal -->
            <div id="myModal" class="modal">

                <!-- Modal content -->
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <%
                        //se nel carrello la quantità del prodotto è minore della disponibilità allora posso aggiugerne altro
                        if(cart.getQ(product)<product.getDisponibilità()) {
                    %>
                        <h3>Inserisci la quantità da aggiungere al carrello</h3>

                        <form action="./AddInCart">
                            <input type="hidden" name="id" value="<%=product.getIdProdotto()%>">
                            <input class="qtAddCart" type="number" name="quantita" min="1"  max="<%=product.getDisponibilità()-cart.getQ(product)%>" placeholder="max <%=(product.getDisponibilità()-cart.getQ(product))%>" required>
                            <input id="buttonAddCartModal" type="submit" value="aggiungi al carrello">
                        </form>
                    <%
                        }else{
                    %>
                    <h3>Hai raggiunto la disponibilità massima!</h3>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>

    </div>

</div>



<%@include file="../footer.html"%>
<script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>