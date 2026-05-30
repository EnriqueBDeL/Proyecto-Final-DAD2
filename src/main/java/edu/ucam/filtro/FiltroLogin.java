package edu.ucam.filtro;
 
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import edu.ucam.domain.Usuario;
 

@WebFilter("/secured/*")
public class FiltroLogin implements Filter {
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
 
        HttpSession session = req.getSession(false);
 
        // Verificar si hay sesión con usuario autenticado
        if (session == null || session.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
 
        // Verificar acceso a zona de administración (solo ADMIN)
        String uri = req.getRequestURI();
        if (uri.contains("/secured/admin/")) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (!usuario.isAdmin()) {
                res.sendRedirect(req.getContextPath() + "/secured/menuUsuario.jsp");
                return;
            }
        }
 
        chain.doFilter(request, response);
    }
 
    @Override
    public void destroy() {}
}
 