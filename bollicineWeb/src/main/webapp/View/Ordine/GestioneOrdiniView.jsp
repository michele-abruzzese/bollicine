<%
    //Check user credentials
    String adminRoles = (String) session.getAttribute("adminRoles");
    if ((adminRoles == null) || (!adminRoles.equalsIgnoreCase("gestOrd")))
    {
        response.sendRedirect("../Login_Logout/LoginView.jsp");
    return;
    }
%>

<%

    List<OrdineDTO> order = (List<OrdineDTO>) request.getAttribute("ordini");
    List<IndirizzoSpedDTO> indirizzi = (List<IndirizzoSpedDTO>) request.getAttribute("indirizzi");
    List<CartaCreditoDTO> carte = (List<CartaCreditoDTO>) request.getAttribute("carte");

%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.DTO.OrdineDTO" %>
<%@ page import="Model.DAO.*" %>
<%@ page import="Model.DTO.CartaCreditoDTO" %>
<%@ page import="Model.DTO.IndirizzoSpedDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
    <title>Ordini</title>
</head>
<body>
<%@ include file="../header.jsp" %>
<div id="ordiniAdministrador">
    <div id="contenutoOrdiniAdm">
        <h1 class="intestazione">Area riservata</h1>

        <hr>

        <h2 class="intestazione">Ordini</h2>

        <%
            if (order.size() != 0 && indirizzi.size()!=0 && carte.size()!=0) {
                Iterator<?> itOr = order.iterator();
                Iterator<?> itIn = indirizzi.iterator();
                Iterator<?> itCa = carte.iterator();

        %>
        <div class="no-more-tables">
            <table>
                <thead>
                <tr>
                    <th>ID Ordine</th>
                    <th>Codice Cliente</th>
                    <th>Data</th>
                    <th>Indirizzo</th>
                    <th>N. Carta</th>
                    <th>Totale</th>
                </tr>
                </thead>
                <%
                    while (itOr.hasNext() && itIn.hasNext() && itCa.hasNext()) {
                        OrdineDTO ordine = (OrdineDTO) itOr.next();
                        IndirizzoSpedDTO indirizzo= (IndirizzoSpedDTO) itIn.next();
                        CartaCreditoDTO carta = (CartaCreditoDTO) itCa.next();
                %>
                <tbody>
                <tr>
                    <td data-title="Codice"><%=ordine.getIdOrdine() %></td>
                    <td data-title="Cliente"><%=ordine.getIdAccount() %> </td>
                    <td data-title="Data"><%=ordine.getData()%></td>
                    <td data-title="Indirizzo"><%=indirizzo.getCittà() %> - <%=indirizzo.getIndirizzo()%> - <%=indirizzo.getCap()%></td>
                    <td data-title="N. Carta"><%=carta.getNumero()%></td>
                    <td data-title="Totale"><%=ordine.getTotOrdine() %> €</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <%
        }else{
        %>
        <h3>Nessun ordine</h3>
        <%
            }
        %>

    </div>
</div>
<%@include file="../footer.html"%>
</body>
</html>