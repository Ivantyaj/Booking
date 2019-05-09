package com.booking_maiseyenka_stepovoi.utils.logging;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long startTime = System.currentTimeMillis();

        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        String requestInfo = "" + request.getMethod() + " " + sessionId + " " + request.getURI();


        LogUtil.getInstance().logRequest("EXTERNAL", requestInfo, new String(body, StandardCharsets.UTF_8));
        ClientHttpResponse response = execution.execute(request, body);
        LogUtil.getInstance().logResponse("EXTERNAL", requestInfo, response.getRawStatusCode(), StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()), System.currentTimeMillis() - startTime);
        return response;
    }
}
