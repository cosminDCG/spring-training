package com.spring.company.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.company.model.Employee;
import com.spring.company.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET = "my_super_secret_key";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String AUTHORITIES = "AUTHORITIES";
    public static final String USER_ID = "USER_ID";

    private final AuthenticationManager authenticationManager;
    private final ApplicationContext context;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext context) {
        this.authenticationManager = authenticationManager;
        this.context = context;

        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            Employee credentials = new ObjectMapper().readValue(req.getInputStream(), Employee.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException {
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        Employee employee = employeeService.getByEmail(((Employee) auth.getPrincipal()).getEmail());

        String token = JWT.create()
                .withSubject(employee.getEmail())
                .withClaim(USER_ID, employee.getUuid().toString())
                .withClaim(AUTHORITIES, List.of(employee.getRole().name()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        res.setHeader("Authorization", token);
        res.getWriter().flush();
    }
}
