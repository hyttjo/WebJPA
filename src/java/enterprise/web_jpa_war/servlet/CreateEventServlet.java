package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Event;
import enterprise.web_jpa_war.entity.Member;
import java.io.*;
import java.util.Collection;
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
 * The servlet class to insert Event into database
 */

@WebServlet(name="CreateEventServlet", urlPatterns={"/CreateManualEvent", "/CreateAutoEvent"})
public class CreateEventServlet extends HttpServlet {
    
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
            
            //Get the data from event's form
            String type   = (String) request.getParameter("type");
            
            if (type == null) {
                type = (String) request.getSession(false).getAttribute("type");
                request.getSession(false).removeAttribute("type");
            }
            
            String member_string = request.getParameter("member");
            
            if (member_string == null) {
                member_string = (String) request.getSession(false).getAttribute("member");
                request.getSession(false).removeAttribute("member");
            }
            
            Integer member_id = Integer.parseInt(member_string);
            
            //Create a event instance out of it
            Event event = new Event(type);
            event.setDate(new Date());
            
            //begin a transaction
            utx.begin();
            //create an em. 
            //Since the em is created inside a transaction, it is associsated with 
            //the transaction
            em = emf.createEntityManager();
            
            Query q = em.createQuery("select m from Member m where m.id = :id");
            q.setParameter("id", member_id);
            
            Member member = (Member)q.getSingleResult();
            event.setMember(member);
            
            Collection<Event> member_events = member.getEvents();
            member_events.add(event);
            member.setEvents(member_events);
            
            //persist the event entity
            em.persist(event);
            //commit transaction which will trigger the em to 
            //commit newly created entity into database
            utx.commit();
            

            
            
            //Forward to the jsp page for rendering
            String url = request.getServletPath();
            
            if (url.equals("/CreateManualEvent")) {
                request.getRequestDispatcher("ListEvent").forward(request, response);
            } else {
                request.getRequestDispatcher("ListMember").forward(request, response);
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
        return "Create Event";
    }
    // </editor-fold>
}
