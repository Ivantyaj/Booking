package com.booking_maiseyenka_stepovoi.utils.logging;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static String getContentAsString(byte[] buf, String charsetName) {
        if (buf == null || buf.length == 0) return "";
        int maxPayloadLength = 10000;
        int length = Math.min(buf.length, maxPayloadLength);
        try {
            return new String(buf, 0, length, charsetName);
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    public static void logRequest(String prefix, String reqInfo,
                                  String body) {
        if (body.length() > 0) {
            logger.info(prefix + " => " + reqInfo + " " + StringUtils.normalizeSpace(body));
        } else {
            logger.info(prefix + " => " + reqInfo);
        }
    }

    static void logResponse(String prefix,
                            String reqInfo,
                            int status,
                            byte[] buf,
                            String encoding,
                            long duration) {
        String body = getContentAsString(buf, encoding);
        logResponse(prefix, reqInfo, status, body, duration);
    }

    public static void logResponse(String prefix,
                                   String reqInfo,
                                   int status,
                                   String body,
                                   long duration) {
        logger.info(prefix + " <= " + reqInfo + ": returned status=" + status + " in " + duration + "ms" + ". Response body: " + StringUtils.normalizeSpace(body));
    }

}

