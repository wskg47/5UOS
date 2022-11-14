package com.example.server.config.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by HMF on 2021/07/23 11:48
 */
@Component  // @Component注解使用在任意层中，交给IOC实例化
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub"; // 用户名的key
    private static final String CLAIM_KEY_CREATED = "created"; // Jwt创建时间的key

    // 从配置文件application.yml中拿到JWT的配置内容，使用@Value注解绑定
    @Value("${jwt.secret}")
    private String secret; // JWT加解密使用的密钥

    @Value("${jwt.expiration}")
    private Long expiration; // JWT的超期限时间（60*60*24）

    /**
     * 【根据用户信息生成token:】
     * 用户信息根据Spring Security的UserDetails来获取 // 实际可以被调用的方法
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // 自定义申明的键值对
        // 主体，用户 {"sub": "Rose"}，如 .setSubject("Rose")
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        // 创建日期 {"iat": "当前的时间"}，如 .setIssuedAt(new Date())
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 【从Token中获取登录用户名】  实际可以被调用的方法
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        // 先去从token中获取荷载，因为用户名放在了荷载里面
        String username;
        try {
            Claims claims = getClaimsFromToken(token); // 获取荷载，从token中，即解析token，通过 claims.getSubject() 获取用户名
            username = claims.getSubject();
        } catch (Exception e) {
            // 有异常时，用户名重新赋值 null
            username = null;
        }
        return username;
    }

    /**
     * 【验证token是否有效:（需要判断两个方面）】 / 实际可以被调用的方法
     * 1.判断token是否过期
     * 2.判断token荷载里面的用户名和UserDetails里的用户名是否一致
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 【判断token是否可以被刷新】  / 实际可以被调用的方法
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        // 如果过期就可以被刷新
        return !isTokenExpired(token);
    }

    /**
     * 【刷新token】 / 实际可以被调用的方法
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        // 重新设置token的创建时间为当前时间，从而重新生成token，达到刷新token的目的
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

//    以下都是一些内部的私有方法
    /**
     * 判断token是否失效过期
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredData = getExpiredDateFromToken(token);
        // 直接使用 before() 方法，判断失效时间是否在当前时间的前面
        return expiredData.before(new Date());
    }

    /**
     * 从token中获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);  // 从token中获取荷载
        return claims.getExpiration(); // 从荷载中获取过期时间，然后return
    }

    /**
     * 从token中获取荷载，即 解析传入的 token
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            // 解析 token，获取负载中声明的对象
            claims = Jwts.parser()
                    // 解析的秘钥 secret 即 yun-secret
                    .setSigningKey(secret)
                    // 需要被解析的token
                    .parseClaimsJws(token)
                    // 解析之后的主体
                    .getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 方法的重载:
     * 根据荷载构建生成JWT TOKEN
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) { // 方法的重载
        return Jwts.builder()
                // 自定义申明，键值对的形式
                .setClaims(claims)
                // 设置 token 的失效时间是 24h
                .setExpiration(generateExpirationDate())
                // 签名算法 HS512 和 secret 盐 yun-secret(在配置文件中配置了内容）
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        // 设置为 当前时间 + 设置的过期限时间 (24小时)
        return new Date(System.currentTimeMillis() + expiration * 100000);
    }

}

