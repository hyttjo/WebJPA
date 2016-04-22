package enterprise.web_jpa_war.servlet;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

/**
 * The servlet class to list Members from database
 */
@WebServlet(name="ListMemberServlet", urlPatterns={"/ListMember", "/ListEditMember", "/CreateEvent"})
public class ListMemberServlet extends HttpServlet {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        
        try {
            em = emf.createEntityManager();

            //query for all the members in database
            List members = em.createQuery("select p from Member p").getResultList();
            request.setAttribute("memberList",members);
            
            //Forward to the jsp page for rendering
            String url = request.getServletPath();
            
            if (url.equals("/ListMember")) {
                request.getRequestDispatcher("ListMember.jsp").forward(request, response);
            } else if (url.equals("/ListEditMember")) {
                request.getRequestDispatcher("EditMember.jsp").forward(request, response);
            } else if (url.equals("/CreateEvent")) {
                request.getRequestDispatcher("CreateEvent.jsp").forward(request, response);
            }
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            //close the em to release any resources held up by the persistebce provider
            if(em != null) {
                em.close();
            }
        }
      
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "ListMember servlet";
    }
    // </editor-fold>
}
