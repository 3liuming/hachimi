package com.ruoyi.framework;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImgPathRewriteFilter implements Filter {

    // 目标静态服务器（你期望的地址）
    private static final String TARGET_HOST = "localhost";
    private static final int TARGET_PORT = 5500; // 你期望的端口

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();            // e.g. /dev-api./img/cat/001.jpg  或 /cat/001.jpg
        String hostHeader = req.getHeader("Host");   // 可能为 "img" 或 "localhost:8080"
        String query = req.getQueryString();

        boolean pathHasDotImg = uri != null && (uri.contains("./img/") || uri.contains("/./img/"));
        boolean badHostLikeImg = hostHeader != null &&
                (hostHeader.equalsIgnoreCase("img") ||
                        hostHeader.startsWith("img.") ||
                        hostHeader.contains("dev-api."));

        if (pathHasDotImg || badHostLikeImg) {
            // 规范化路径：将 "./img/" 或 "/./img/" -> "/img/"
            String newPath = uri.replace("./img/", "/img/").replace("/./img/", "/img/");

            // 如果 host 是类似 "img" 且路径没有 /img 前缀，则补上
            if (badHostLikeImg && !newPath.startsWith("/img/")) {
                if (newPath.startsWith("/")) newPath = "/img" + newPath;
                else newPath = "/img/" + newPath;
            }

            // 构造绝对目标 URL，确保跳到你期望的静态服务器/端口
            String scheme = req.getScheme() == null ? "http" : req.getScheme();
            String target = scheme + "://" + TARGET_HOST + ":" + TARGET_PORT + newPath;
            if (query != null && !query.isEmpty()) target += "?" + query;

            // 永久重定向（如果希望临时 302，可用 sendRedirect）
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            resp.setHeader("Location", target);
            resp.setHeader("Connection", "close");
            return;
        }

        chain.doFilter(request, response);
    }
}
