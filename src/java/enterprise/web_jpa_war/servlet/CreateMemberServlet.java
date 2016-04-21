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
import javax.transaction.UserTransaction;


/**
 * The servlet class to insert Member into database
 */
@WebServlet(name="CreateMemberServlet", urlPatterns={"/CreateMember"})
public class CreateMemberServlet extends HttpServlet {
    
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
            
            //Get the data from member's form
            String firstName  = (String) request.getParameter("firstName");
            String lastName   = (String) request.getParameter("lastName");
            String email   = (String) request.getParameter("email");
            
            //Create a member instance out of it
            Member member = new Member(firstName, lastName, email);
            member.setSince(new Date());
            
            //begin a transaction
            utx.begin();
            //create an em. 
            //Since the em is created inside a transaction, it is associsated with 
            //the transaction
            em = emf.createEntityManager();
            //persist the member entity
            em.persist(member);
            //commit transaction which will trigger the em to 
            //commit newly created entity into database
            utx.commit();
            
            HttpSession session = request.getSession(true);
            session.setAttribute("type", "CreatedMember");
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
