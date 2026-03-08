/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */

package filter;

import dao.BrandDAO;
import dao.CategoryDAO;
import model.BrandDTO;
import model.CategoryDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonLayoutFilter
 *
 * Author: NamTQ  
 * Created on: 12/02/2026
 * Description: 
 * This filter is responsible for loading shared layout data that is required across multiple pages in the application.
 *
 * Specifically, it loads:
 * - All product categories
 * - All brands grouped by category
 * 
 * The data is attached to the request scope so that it can be accessed directly in JSP pages (e.g., header, sidebar, navigation menu).
 * 
 * This filter is mapped to all URL patterns (/*), meaning it executes before every request in the system.
 */
@WebFilter(filterName="CommonLayoutFilter", urlPatterns={"/*"})
public class CommonLayoutFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public CommonLayoutFilter() {
    } 

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("CommonLayoutFilter:DoBeforeProcessing");

	// Write code here to process the request and/or response before
	// the rest of the filter chain is invoked.

	// For example, a logging filter might log items on the request object,
	// such as the parameters.
	/*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
	*/
    } 

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
	throws IOException, ServletException {
	if (debug) log("CommonLayoutFilter:DoAfterProcessing");

	// Write code here to process the request and/or response after
	// the rest of the filter chain is invoked.
	
	// For example, a logging filter might log the attributes on the
	// request object after the request has been processed. 
	/*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
	*/

	// For example, a filter might append something to the response.
	/*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
	*/
    }

    /**
     * Main filtering method.
     *
     * This method is executed for every incoming request.
     * It loads shared layout data before passing the request down the filter chain.
     * 
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {
        
        // Load shared categories and brands for layout rendering
        loadCategoriesAndBrands(request);

        // Continue processing the request
	chain.doFilter(request, response);
    }
    
    /**
     * Loads all categories and their corresponding brands.
     *
     * The method retrieves:
     * - A list of all categories
     * - A mapping between categoryId and its associated brand list
     *
     * The results are stored in request scope as:
     * - "categories"
     * - "brands"
     *
     * @param request the current servlet request
     */
    private void loadCategoriesAndBrands (ServletRequest request){
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        
        // Retrieve all categories from database
        List<CategoryDTO> categoryList = categoryDAO.getAllCategory();
        
        // Map categoryId -> List of brands
        Map<Integer, List<BrandDTO>> brandMap = new HashMap<>();
        for (CategoryDTO categoryDTO : categoryList){
            int key = categoryDTO.getCategoryId();
            
            // Load brands belonging to this category
            brandMap.put(key, brandDAO.getBrandsByCategoryId(key));
        }
        
        // Store shared data in request scope
        request.setAttribute("categories", categoryList);
        request.setAttribute("brands", brandMap);
    }
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    public void destroy() { 
    }

    /**
     * Init method for this filter 
     */
    public void init(FilterConfig filterConfig) { 
	this.filterConfig = filterConfig;
	if (filterConfig != null) {
	    if (debug) { 
		log("CommonLayoutFilter:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("CommonLayoutFilter()");
	StringBuffer sb = new StringBuffer("CommonLayoutFilter(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
