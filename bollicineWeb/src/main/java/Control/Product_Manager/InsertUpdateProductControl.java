package Control.Product_Manager;

import Model.Beans.ProdottoBean;
import Model.DAO.ProdottoDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.Part;


@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class InsertUpdateProductControl extends HttpServlet {
    static ProdottoBean bean = new ProdottoBean();
    static String SAVE_DIR ="/uploadTemp";

    public InsertUpdateProductControl() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if (action!=null && action.equalsIgnoreCase("ins")){
            ProdottoDTO prod = new ProdottoDTO();

            String nome=req.getParameter("nome");
            String categoria=req.getParameter("categoria");
            String descrizione=req.getParameter("descrizione");
            String tipo=req.getParameter("tipo");
            int annata= Integer.parseInt(req.getParameter("annata"));
            double prezzo= Double.parseDouble(req.getParameter("prezzo"));
            int disponibilità= Integer.parseInt(req.getParameter("disponibilita"));
            //prendo il path dell'immagine
            String immagine=getPathImage(req);

            prod.setNome(nome);
            prod.setCategoria(categoria);
            prod.setDescrizione(descrizione);
            prod.setTipo(tipo);
            prod.setAnnata(annata);
            prod.setPrezzo(prezzo);
            prod.setDisponibilità(disponibilità);
            prod.setImmagine(immagine);


            try {
                bean.insertProdotto(prod);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        }else if(action.equalsIgnoreCase("selectP")){
            int idProdotto = Integer.parseInt(req.getParameter("idProdotto"));

            ProdottoDTO prod = new ProdottoDTO();

            try {
                prod=bean.doRetriveById(idProdotto);

                //invio tutti i prodotti alla jsp, altrimenti va nella servllet di visualizzazione
                req.removeAttribute("products");
                req.setAttribute("products",bean.doRetriveAll());

                //invio il prodotto da modificare alla jsp
                req.setAttribute("up",prod);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(action.equalsIgnoreCase("updateP")){
            ProdottoDTO prod = new ProdottoDTO();

            int idProdotto= Integer.parseInt(req.getParameter("idProdotto"));
            String nome=req.getParameter("nome");
            String categoria=req.getParameter("categoria");
            String descrizione=req.getParameter("descrizione");
            String tipo=req.getParameter("tipo");
            int annata= Integer.parseInt(req.getParameter("annata"));
            double prezzo= Double.parseDouble(req.getParameter("prezzo"));
            int disponibilità= Integer.parseInt(req.getParameter("disponibilita"));
            //prendo il path dell'immagine
            String immagine=getPathImage(req);

            prod.setIdProdotto(idProdotto);
            prod.setNome(nome);
            prod.setCategoria(categoria);
            prod.setDescrizione(descrizione);
            prod.setTipo(tipo);
            prod.setAnnata(annata);
            prod.setPrezzo(prezzo);
            prod.setDisponibilità(disponibilità);
            prod.setImmagine(immagine);

            try {
                bean.updateProdotto(prod);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/View/Catalogo/GestioneProdottiView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    private String getPathImage(HttpServletRequest req) throws IOException, ServletException {
        //perendo il path dell'immagine
        String immagine=null;
        String appPath = req.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : req.getParts() ){
            String fileName = extractFileName(part);
            if (fileName != null && !fileName.equals("")){
                part.write(savePath + File.separator + fileName);

                immagine = ""+savePath + File.separator + fileName;

            }
        }

        return immagine;
    }
}