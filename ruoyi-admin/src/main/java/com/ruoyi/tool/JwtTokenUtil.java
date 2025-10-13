package com.ruoyi.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil{

    // JWT密钥（建议放在配置文件中）
    private static final String SECRET_KEY = "0123456789_0123456789_0123456789_01";  // 可修改为从配置文件中获取

    // Token过期时间（24小时后）
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;  // 24小时

    // 生成Token
    public static String generateToken(Long userId, String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)  // 设置Token的发行时间
                .setExpiration(expirationDate)  // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // 使用HS512算法和密钥签名
                .compact();
    }

    // 解析Token，获取Clais
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)  // 设置密钥
                .parseClaimsJws(token)  // 解析Token
                .getBody();  // 获取Claims部分
    }

    // 从Token中获取用户ID
    public static Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        Integer idInt = claims.get("userId", Integer.class);  // 先拿 Integer
        return idInt != null ? idInt.longValue() : null;      // 再转 Long
    }

    // 从Token中获取用户名
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return  claims.getSubject(); // 返回用户名
    }

    // 判断Token是否过期
    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
}