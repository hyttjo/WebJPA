/*
 * Copyright (c) 2010, Oracle. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of Oracle nor the names of its contributors
 *   may be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package enterprise.web_jpa_war.servlet;

import enterprise.web_jpa_war.entity.Event;
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
