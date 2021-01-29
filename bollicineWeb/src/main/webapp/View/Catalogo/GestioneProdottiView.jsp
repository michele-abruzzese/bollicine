<%
    //Check user credentials
    String adminRoles = (String) session.getAttribute("adminRoles");
    if ((adminRoles == null) || (!adminRoles.equalsIgnoreCase("gestCat")))
    {
        response.sendRedirect("../Login_Logout/LoginView.jsp");
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

<%@ page language="java" import="java.util.*,Model.DTO.ProdottoDTO" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
                <form action="./InsertUpdateProduct" enctype="multipart/form-data"  method="post">
                    <input type="hidden" name="action" value="ins">

                    Nome:<br>
                    <input name="nome" type="text" maxlength="45" required placeholder="enter name"><br>

                    Categoria:<br>
                    <select name="categoria">
                        <option value="bianchi">bianchi</option>
                        <option value="rossi">rossi</option>
                        <option value="spumanti">spumanti</option>
                    </select><br>

                    Descrizione:<br>
                    <textarea name="descrizione" maxlength="500" rows="3" required placeholder="enter description"></textarea><br>

                    Tipo:<br>
                    <input name="tipo" type="text" maxlength="45" required placeholder="enter tipo" required><br>

                    Annata:<br>
                    <input name="annata" type="number" min="0" max="4000" value="annata" required><br>

                    Prezzo:<br>
                    <input name="prezzo" type="number" min="1" value="1" step="any" required><br>

                    Disponibilità:<br>
                    <input name="disponibilita" type="number" min="0" value="0" required><br>


                    <input class="file" type="file" name="talkPhoto" value="" maxlength="255" required><br>

                    <input class="buttonProtected" type="submit" value="Add"> <input class="buttonProtected" type="reset" value="Reset">
                </form>
            </div>
            <div id="formUpdate">
                <%if(up==null) { %>


                <h2 class="h2Form">Aggiorna prodotto</h2>
                <%-- selezionare l'id del prodotto da modificare --%>
                <form action="./InsertUpdateProduct" enctype="multipart/form-data"  method="post">
                    <input type="hidden" name="action" value="selectP">

                    Idprodotto:<br>
                    <select name="idProdotto">
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
                <form action="./InsertUpdateProduct" enctype="multipart/form-data"  method="post">
                    <input type="hidden" name="action" value="updateP">
                    <input type="hidden" name="idProdotto" value="<%=up.getIdProdotto()%>">

                    Nome:<br>
                    <input name="nome" type="text" maxlength="45" required value="<%=up.getNome()%>"><br>

                    <%String cat=up.getCategoria();%>
                    Categoria:<br>
                    <select name="categoria">
                        <option value="bianchi" <%if(cat.equalsIgnoreCase("bianchi")){%> selected<%}%> >bianchi</option>
                        <option value="rossi" <%if(cat.equalsIgnoreCase("rossi")){%> selected<%}%> >rossi</option>
                        <option value="spumanti" <%if(cat.equalsIgnoreCase("spumanti")){%> selected<%}%> >spumanti</option>
                    </select><br>

                    Descrizione:<br>
                    <textarea name="descrizione" maxlength="500" rows="3" required ><%=up.getDescrizione()%></textarea><br>

                    Tipo:<br>
                    <input name="tipo" type="text" maxlength="45" required value="<%=up.getTipo()%>" required><br>

                    Annata:<br>
                    <input name="annata" type="number" min="0" max="4000" value="<%=up.getAnnata()%>" required><br>

                    Prezzo:<br>
                    <input name="prezzo" type="number" min="1" value="<%=up.getPrezzo()%>" step="any" required><br>

                    Disponibilità:<br>
                    <input name="disponibilita" type="number" min="0" value="<%=up.getDisponibilità()%>" required><br>


                    <input class="file" type="file" name="talkPhoto" value="" maxlength="255" required><br>

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

                    <td data-title="Elimina"><a href="${pageContext.servletContext.contextPath}/DeleteProduct?id=<%=bean.getIdProdotto()%>"><i class="fas fa-trash"></i></a><br></td>

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
