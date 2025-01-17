<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="Model.DTO.AccountDTO,Model.DTO.IndirizzoSpedDTO,Model.DTO.CartaCreditoDTO" %>
<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.TemporalUnit" %>
<%@ page import="java.time.temporal.ChronoUnit" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Checkout</title>
    <link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
</head>
<body>
<script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<%@ include file="../header.jsp" %>
<div id="cartIndirizzi">
    <%

        AccountDTO cliente=(AccountDTO) request.getSession().getAttribute("utente");

        if(cliente==null){
            response.sendRedirect("../Login_Logout/LoginView.jsp");
            return;
        }
        List <IndirizzoSpedDTO> indirizzi=(List <IndirizzoSpedDTO>) request.getSession().getAttribute("indirizzi");
        List <CartaCreditoDTO> carte=(List <CartaCreditoDTO>) request.getSession().getAttribute("carte");

        //se il cliente ha indirizzi di spedizione e carte di credito
        if(cliente!=null && indirizzi.size()!=0 && carte.size()!=0){

    %>
        <!-- indirizzi -->
        <h3> I tuoi indirizzi di spedizione</h3>

        <div id="visualIndirizzi">
            <%
                Iterator<?> ind=indirizzi.iterator();
                while(ind.hasNext()){
                    IndirizzoSpedDTO indirizzo=(IndirizzoSpedDTO) ind.next();

            %>

            <div class="selectIndirizzi">
                <p id="aliasIndirizzo"><%=indirizzo.getAlias()%></p>
                <div class="dropdown_indirizzi">
                    <a><%=indirizzo.getNome() %></a>
                    <a><%=indirizzo.getCognome() %></a><br>
                    <a><%=indirizzo.getIndirizzo() %></a>
                    <a><%=indirizzo.getCittà() %></a><br>
                    <a><%=indirizzo.getCap() %></a><br>
                    <a><%=indirizzo.getProvincia() %></a>
                </div>
            </div>
            <%
                }
            %>
        </div>

        <!-- carte -->
        <h3> Le tue carte di credito</h3>

        <div id="visualCarte">
            <%
                //per dare il nome alla carta
                int i=0;
                Iterator<?> creditCard=carte.iterator();
                while(creditCard.hasNext()){
                    i++;
                    CartaCreditoDTO card=(CartaCreditoDTO) creditCard.next();

            %>

            <div class="selectCarte">
                <p id="aliasCarte">Carta <%=i%></p>
                <div class="dropdown_carte">
                    <a><%=card.getNome() %></a>
                    <a><%=card.getCognome() %></a><br>
                    <a><%=card.getNumero() %></a>
                    <a><%=card.getCcv() %></a><br>
                    <a><%=card.getScandenza() %></a><br>
                </div>
            </div>
            <%
                }
            %>
        </div>

    <%
        //se il cliente non ha indirizzi di spedizione e carte di credito
    }else if(cliente!=null && indirizzi.size()==0 && carte.size()==0){

    %>
        <h3>Non hai registrato indirizzi di spedizzione e carte di credito</h3>
        <h4>Aggiungi un nuovo indirizzo di spedizione e una nuova carta di credito per continuare</h4>

    <%
        }

        //se hai aggiunto il primo indirizzo
        Boolean firstIndirizzo=Boolean.FALSE;

        if(request.getAttribute("insertFirstIndirizzo")!=null) {
            firstIndirizzo = (Boolean) request.getAttribute("insertFirstIndirizzo");
        }

        if(firstIndirizzo==true && indirizzi.size()<2 && carte.size()==0){
    %>
            <h3>Hai appena inserito un indirizzo di spedizione!</h3>
            <h3>Aggiungi una carta di credito per continuare</h3>
    <%
        }

        //se hai aggiunto la prima carta
        Boolean firstCarta=Boolean.FALSE;

        if(request.getAttribute("insertFirstCarta")!=null) {
            firstCarta = (Boolean) request.getAttribute("insertFirstCarta");
        }
        if(firstCarta==true && carte.size()<2 && indirizzi.size()==0){
    %>
            <h3>Hai appena inserito una carta di credito!</h3>
            <h3>Aggiungi un indirizzo di spedizione per continuare</h3>
    <%
        }
    %>


    <!-- Nuovo indirizzo di spedizione -->
    <div id="nuovoIndirizzo">
        <!-- bottone per aprire l'inserimento dell'indirizzo -->
        <button id="myBtnIn1">Aggiungi nuovo indirizzo</button>

        <!-- The Modal -->
        <div id="myModalIn1" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <h3>Inserisci un nuovo indirizzo di spedizione</h3>
                <form id="formIndirizzo" class="formModal" action="${pageContext.servletContext.contextPath}/AddNewIndirizzo" method="post">
                    <input type="hidden" name="idCliente" value="<%=cliente.getId()%>">
                    Cognome<input type="text" name="cognome"  maxlength="30" required>
                    Nome<input type="text" name="nome" maxlength="30" required>
                    Indirizzo<input type="text" name="indirizzo"  maxlength="50" required>
                    Città<input type="text" name="citta"  maxlength="50" required>
                    Cap<input type="text" name="cap"  placeholder="5 cifre" required>
                    Provincia<input type="text" name="provincia" maxlength="50" required>
                    Alias<input type="text" name="alias" required  maxlength="30" placeholder="es. casa, lavoro ecc">
                    <input id="salvaIndirizzo" class="buttonFormModal" type="submit" value="salva">
                </form>
            </div>

        </div>
    </div>

    <!-- Nuova carta di credito -->
    <div id="nuovaCarta">
        <!-- bottone per aprire l'inserimento dell'indirizzo -->
        <button id="myBtnCt1">Aggiungi nuova carta</button>

        <!-- The Modal -->
        <div id="myModalCt1" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <h3>Inserisci una nuova carta di credito</h3>
                <form id ="formCC" class="formModal" action="${pageContext.servletContext.contextPath}/AddNewCard" method="post">
                    <input type="hidden" name="idCliente" value="<%=cliente.getId()%>">
                    Cognome<input type="text" name="cognome"  maxlength="30"  required>
                    Nome<input type="text" name="nome"  maxlength="30" required>
                    Numero<input type="text" name="numero" placeholder="da 13 a 16 cifre" required>
                    CCV<input type="text" name="ccv"  placeholder="3 cifre" required>
                    Scadenza<input type="date" name="scadenza" min="<%=LocalDate.now().plus(1,ChronoUnit.DAYS)%>" required>
                    <input id="salvaCarta" class="buttonFormModal" type="submit" value="salva">
                </form>
            </div>

        </div>
    </div>

    <%
        //se ci sono indirizzi di spedizione e carte faccio selezionare
        if(cliente!=null && carte.size()!=0 && indirizzi.size()!=0){
    %>
    <!-- select indirizzo e carta -->
    <h3>Scegli l'indirizzo per la spedizione e la carta per il pagamento</h3>
    <form action="${pageContext.servletContext.contextPath}/AddNewOrdine">
        <select name="idIndirizzo">
            <%
                Iterator<?> indi=indirizzi.iterator();
                while(indi.hasNext()){
                    IndirizzoSpedDTO indirizzo=(IndirizzoSpedDTO) indi.next();

            %>
            <option value="<%=indirizzo.getIdIndirizzo()%>"><%=indirizzo.getAlias()%></option>
            <%
                }
            %>
        </select>
        <select name="idCarta">
            <%
                //per dare il nome alle carte
                int count=0;
                Iterator<?> carteCRed=carte.iterator();
                while(carteCRed.hasNext()){
                    count++;
                    CartaCreditoDTO carta=(CartaCreditoDTO) carteCRed.next();

            %>
            <option value="<%=carta.getIdCartaCredito()%>">Carta <%=count%></option>
            <%
                }
            %>
        </select>
        <input  class="buttonCeckout" type="submit" value="Checkout">
    </form>
    <%
        }
    %>
</div>

<%@include file="../footer.html" %>
<script>
    $.validator.addMethod("regex",
        function (value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },"Please check your input."
    );
    $(document).ready(function()
    {

        $("#formIndirizzo").validate(
            {
                rules:
                    {
                        'cognome':{
                            minlength: 2
                        },
                        'nome':{
                            minlength: 2
                        },
                        'indirizzo':{
                            minlength: 2
                        },
                        'citta':{
                            minlength: 2
                        },
                        'cap':{
                            regex:"^[0-9]{5}$"
                        },
                        'provincia':{
                            minlength: 2
                        },
                        'alias':{
                            minlength: 2
                        }
                    },
                messages:
                    {
                        'cognome':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'nome':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'indirizzo':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'indirizzo':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'citta':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'cap':{
                            regex: '<br><b>rispetta il formato richiesto</b>'
                        },
                        'provincia':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'alias':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        }
                    }
            });
        $("#formCC").validate(
            {
                rules:
                    {
                        'cognome':{
                            minlength: 2
                        },
                        'nome':{
                            minlength: 2
                        },
                        'numero':{
                            regex: "^[0-9]{13,16}$"
                        },
                        'ccv':{
                            regex: "^[0-9]{3}$"
                        }
                    },
                messages:
                    {
                        'cognome':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'nome':{
                            minlength: '<br><b>si prega di inserire almeno due caratteri</b>'
                        },
                        'numero':{
                            regex: '<br><b>rispetta il formato richiesto</b>'
                        },
                        'ccv':{
                            regex: '<br><b>rispetta il formato richiesto</b>'
                        }
                    }
            });
    });
</script>
<script>
    // Get the modal
    var modal1 = document.getElementById("myModalIn1");

    // Get the button that opens the modal
    var btn1 = document.getElementById("myBtnIn1");

    // Get the <span> element that closes the modal
    var span1 = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn1.onclick = function() {
        modal1.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span1.onclick = function() {
        modal1.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal1) {
            modal1.style.display = "none";
        }
    }
</script>
<script>
    // Get the modal
    var modal2 = document.getElementById("myModalCt1");

    // Get the button that opens the modal
    var btn2 = document.getElementById("myBtnCt1");

    // Get the <span> element that closes the modal
    var span2 = document.getElementsByClassName("close")[1];

    // When the user clicks the button, open the modal
    btn2.onclick = function() {
        modal2.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span2.onclick = function() {
        modal2.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal2) {
            modal2.style.display = "none";
        }
    }
</script>
</body>
</html>