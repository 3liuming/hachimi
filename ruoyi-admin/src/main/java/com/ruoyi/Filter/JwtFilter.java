package com.ruoyi.Filter;

import com.ruoyi.tool.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";  // 请求头中的 token 字段名

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println(">>> JwtFilter: " + request.getRequestURI());
        String requestURI = request.getRequestURI();
        String header = request.getHeader(TOKEN_HEADER);   // TOKEN_HEADER = "Authorization"
        if (header == null || !header.startsWith("Bearer ")) {
            sendUnauthorizedError(response, "Invalid token");
            return;
        }
        String token = header.substring(7);                // 去掉 "Bearer "

        System.out.println(">>> JwtFilter: token=" + token);
        if (token != null && !token.isEmpty()) {
            try {
                // 判断token是否过期
                if (JwtTokenUtil.isTokenExpired(token)) {
                    // 如果token过期，返回统一格式的错误响应
                    sendUnauthorizedError(response, "Token has expired");
                    return;
                }

                // 获取token中的用户信息
                Claims claims = JwtTokenUtil.parseToken(token);

                // 将username和userId存入请求的Attribute中，供后续使用
//                Long userId = claims.get("userId", Long.class);
                Long userId = claims.get("userId", Integer.class).longValue();
                String username = claims.getSubject();

                // 将用户信息放入RequestAttribute
                request.setAttribute("username", username);
                request.setAttribute("userId", userId);

                System.out.println("=== Token claims ===");
                claims.forEach((k, v) -> System.out.println(k + ": " + v + " (" + v.getClass() + ")"));

            } catch (ExpiredJwtException e) {
                // 如果token过期，返回统一格式的错误响应
                sendUnauthorizedError(response, "Token has expired");
                return;
            } catch (Exception e) {
                // 处理其他类型的token无效错误
                sendUnauthorizedError(response, "Invalid token");
                return;
            }
        }

        // 继续执行过滤链
        filterChain.doFilter(request, response);
    }

    // 统一处理401 Unauthorized错误，返回JSON格式的错误信息
    private void sendUnauthorizedError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write("{\"error\": \"" + message + "\"}");
        out.flush();
    }
}
