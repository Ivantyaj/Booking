package com.booking_maiseyenka_stepovoi.utils_maiseyenka_stepovoi.logging_maiseyenka_stepovoi;


import org.apache.commons.io.IOUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class HttpServletLogger extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        String prefix = getPrefix(request.getServletPath());

        if (isSwagger(servletPath) || isStatic(servletPath) || isRoot(servletPath) || isCSRF(servletPath)) {
            filterChain.doFilter(request, response);
        } else {
            long startTime = System.currentTimeMillis();
            String reqInfo = getHttpServletRequestInfo(request);

            MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest(request);
            ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

            LogUtil.logRequest(prefix, reqInfo, IOUtils.toString(multiReadHttpServletRequest.getReader()));

            filterChain.doFilter(multiReadHttpServletRequest, wrappedResponse);

            LogUtil.logResponse(prefix,
                    reqInfo,
                    wrappedResponse.getStatus(),
                    wrappedResponse.getContentAsByteArray(),
                    wrappedResponse.getCharacterEncoding(),
                    System.currentTimeMillis() - startTime);

            wrappedResponse.copyBodyToResponse();
        }
    }

    private boolean isCSRF(String servletPath) {
        return servletPath.contains("csrf");
    }

    private String getHttpServletRequestInfo(HttpServletRequest request) {
        StringBuilder requestInfo = new StringBuilder()
                .append(request.getMethod())
                .append(" ");

        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null) {
            String passThroughToken = authorizationToken.substring(authorizationToken.length() - 25);
            requestInfo.append(passThroughToken);
        }

        requestInfo.append(" ")
                .append(request.getSession().getId())
                .append(" ")
                .append(request.getRemoteAddr())
                .append(" ")
                .append(request.getServletPath());

        String queryString = request.getQueryString();
        if (queryString != null) {
            requestInfo.append("?").append(queryString);
        }

        if (request.getAuthType() != null) {
            requestInfo.append(", authType=")
                    .append(request.getAuthType());
        }
        if (request.getUserPrincipal() != null) {
            requestInfo.append(", principalName=")
                    .append(request.getUserPrincipal().getName());
        }
        return requestInfo.toString();

    }

    private String getPrefix(String servletPath) {
        String[] strings = servletPath.split("/");
        if (strings.length >= 2) return strings[1].toUpperCase();
        return "";
    }

    private boolean isRoot(String servletPath) {
        return Objects.equals(servletPath, "/");
    }

    private boolean isStatic(String requestURL) {
        return requestURL.contains("swagger") || String.valueOf(requestURL).contains("v2/api-docs");
    }

    private boolean isSwagger(String requestURL) {
        return requestURL.contains("static");
    }
}

