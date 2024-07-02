package ro.uaic.clinic_care.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * This class is used to filter requests for JWT token.
 * @author Marius Achitei, truiID (Pty) Ltd, 2024
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        if (req.getMethod().equals("OPTIONS")) {
            return true;
        }

        return true;

    }
}