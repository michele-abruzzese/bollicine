<%
    //Check user credentials
    Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
    if ((adminRoles == null) || (!adminRoles.booleanValue()))
    {
        response.sendRedirect("./login-form.jsp");
        return;
    }


%>

<%
    /** collezione dei prodotti da visualizzare**/
    List<ProdottoDTO> products = (List<ProdottoDTO>) request.getAttribute("products");
    if(products == null) {
        response.sendRedirect("./VisualizzaProdotti");
        return;
    }

    /** in fase di modifica dei prodotti settiamo i campi
     come valori di default del prodotto selezionato**/
    ProdottoDTO up=(ProdottoDTO) request.getAttribute("up");
%>

<%@ page language="java" import="java.util.*,Model.DAO.ProdottoDTO" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../../fontawesome-free-5.13.0-web/css/all.css">
    <title>Administrator Page</title>
</head>

<body>
<%@ include file="../header.jsp" %>
<div id="protectedPage">
    <div id="contenutoProtected">

        <h1 class="intestazione">Area riservata</h1>

        <hr>


        <h2 class="intestazione">Prodotti</h2>
        <div id="InsertUpdate">
            <div id="formInsert">
                <h2 class="h2Form">Inserisci prodotto</h2>
                <form action="#sevletPerInserireProdotto" enctype="multipart/form-data"  method="post">
                    <input type="hidden" name="action" value="insert">

                    <label for="nome">Nome:</label><br>
                    <input id="nome" type="text" maxlength="45" required placeholder="enter name"><br>

                    <label for="categoria"> Categoria </label><br>
                    <select id="categoria">
                        <option value="bianchi">bianchi</option>
                        <option value="rossi">rossi</option>
                        <option value="spumanti">spumanti</option>
                    </select><br>

                    <label for="descrizione">Descrizione:</label><br>
                    <textarea id="descrizione" maxlength="500" rows="3" required placeholder="enter description"></textarea><br>

                    <label for="tipo">Nome:</label><br>
                    <input id="tipo" type="text" maxlength="45" required placeholder="enter tipo"><br>

                    <label for="annata">Annata:</label><br>
                    <input id="annata" type="number" min="0" value="annata"><br>

                    <label for="prezzo">Prezzo:</label><br>
                    <input id="prezzo" type="number" min="1" value="1" required><br>

                    <label for="disponibilita">Disponibilità:</label><br>
                    <input id="disponibilita" type="number" min="0" value="0"><br>


                    <input class="file" type="file" name="talkPhoto" value="" maxlength="255"><br>

                    <input class="buttonProtected" type="submit" value="Add"> <input class="buttonProtected" type="reset" value="Reset">
                </form>
            </div>
            <div id="formUpdate">
                <%if(up==null) { %>


                <h2 class="h2Form">Aggiorna prodotto</h2>
                <%-- selezionare l'id del prodotto da modificare --%>
                <form action="#servletperaggiornare" enctype="multipart/form-data"  method="post">
                    <input type="hidden" name="action" value="updateP">

                    <label for="idprodotto">Idprodotto:</label><br>
                    <select id="idprodotto">
                        <%
                            if (products != null && products.size() != 0) {
                                Iterator<?> it = products.iterator();
                                while (it.hasNext()) {
                                    ProdottoDTO bean = (ProdottoDTO) it.next();
                        %>

                            <option value="<%=bean.getIdProdotto() %>"> <%=bean.getIdProdotto() %></option>

                        <%
                                }

                            }
                        %>

                    </select>
                    <input class="buttonProtected" type="submit" value="Seleziona">
                </form>

                <%}else { %>
                <h2 class="h2Form">Aggiorna prodotto</h2>
                <form action="#servletperaggiornare" enctype="multipart/form-data"  method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="idProdotto" value="<%=up.getIdProdotto()%>">

                    <label for="nomeUp">Nome:</label><br>
                    <input id="nomeUp" type="text" maxlength="20" value="<%=up.getNome() %>" required><br>


                    <label for="categoriaUp"> Categoria </label><br>
                    <select id="categoriaUp">
                        <option value="bianchi">bianchi</option>
                        <option value="rossi">rossi</option>
                        <option value="spumanti">spumanti</option>
                    </select><br>


                    <label for="descrizioneUp">Descrizione:</label><br>
                    <textarea id="descrizioneUp" maxlength="500" rows="3" required placeholder="enter description"></textarea><br>

                    <label for="tipoUp">Nome:</label><br>
                    <input id="tipoUp" type="text" maxlength="45" required placeholder="enter tipo"><br>

                    <label for="annataUp">Annata:</label><br>
                    <input id="annataUp" type="number" min="0" value="annata"><br>

                    <label for="prezzoUp">Prezzo:</label><br>
                    <input id="prezzoUp" type="number" min="1" value="1" required><br>

                    <label for="disponibilitaUp">Disponibilità:</label><br>
                    <input id="disponibilitaUp" type="number" min="0" value="0"><br>


                    <input class="file" type="file" name="talkPhoto" value="" maxlength="255"><br>

                    <input class="buttonProtected" type="submit" value="Update"> <input class="buttonProtected" type="reset" value="Reset">
                </form>
                <%} %>
            </div>

        </div>
        <div class="no-more-tables">

            <table>
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Codice </th>
                        <th>Nome </th>
                        <th>Categoria</th>
                        <th>Descrizione </th>
                        <th>Tipo</th>
                        <th>Annata</th>
                        <th>Prezzo</th>
                        <th>Disponibilità</th>
                        <th>Elimina</th>
                    </tr>
                </thead>

                <%
                    if (products != null && products.size() != 0) {
                        Iterator<?> it = products.iterator();

                        while (it.hasNext()) {
                            ProdottoDTO bean = (ProdottoDTO) it.next();
                %>

                <tbody>
                <tr>
                    <td data-title="Immagine"><img src="./GetImmagine?id=<%=bean.getIdProdotto() %>" onerror="this.src='${pageContext.servletContext.contextPath}/imgs/nophoto.png'" style="width:100px"></td>
                    <td data-title="Codice"><%=bean.getIdProdotto()%></td>
                    <td data-title="Nome"><%=bean.getNome()%></td>
                    <td data-title="Categoria"><%=bean.getCategoria()%></td>
                    <td data-title="Descrizione"><%=bean.getDescrizione()%></td>
                    <td data-title="Tipo"><%=bean.getTipo()%></td>
                    <td data-title="Annata"><%=bean.getAnnata() %> </td>
                    <td data-title="Prezzo"><%=bean.getPrezzo() %> €</td>
                    <td data-title="Disponibilità"><%=bean.getDisponibilità()%></td>

                    <td data-title="Elimina"><a href="#servlet per eliminare il prodotto &id=<%=bean.getIdProdotto()%>&cat=<%=bean.getCategoria()%>"><i class="fas fa-trash"></i></a><br></td>

                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">No products available</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>


    </div>

</div>
<%@include file="../footer.html"%>
</body>
</html>
