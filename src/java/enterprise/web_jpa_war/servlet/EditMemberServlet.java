package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Member;
import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.UserTransaction;


/**
 * The servlet class to insert Member into database
 */
@WebServlet(name="EditMemberServlet", urlPatterns={"/EditMember"})
public class EditMemberServlet extends HttpServlet {
    
    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;  
    
    @Resource
    private UserTransaction utx;

    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        try {
            
            //Get the data from url
            Integer id = Integer.parseInt((String) request.getParameter("id"));
            String firstName  = (String) request.getParameter("firstName");
            String lastName   = (String) request.getParameter("lastName");
            String email   = (String) request.getParameter("email");
            
            em = emf.createEntityManager();
            
            Query q = em.createQuery("select m from Member m where m.id = :id");
            q.setParameter("id", id);
            
            Member member = (Member) q.getSingleResult();
            
            member.setFirstName(firstName);
            member.setLastName(lastName);
            member.setEmail(email);
            
            //begin a transaction
            utx.begin();
            //create an em. 
            //Since the em is created inside a transaction, it is associsated with 
            //the transaction
            em = emf.createEntityManager();
            
            //merges the member entity
            em.merge(member);
            //commit transaction which will trigger the em to 
            //commit newly edited entity into database
            utx.commit();
            
            HttpSession session = request.getSession(true);
            session.setAttribute("type", "EditedMember");
            session.setAttribute("member", member.getId().toString());
            
            RequestDispatcher rd = request.getRequestDispatcher("CreateAutoEvent");
            rd.forward(request, response);

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
        return "Create Member Servlet";
    }
    // </editor-fold>
}
