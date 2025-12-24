package org.example.teachhive.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class LogUtil {
    public static void logApplicationStartup(Environment env) {
        String applicationName = env.getProperty("spring.application.name", "Application");
        String protocol = Objects.isNull(env.getProperty("server.ssl.key-store")) ? "http" : "https";
        String port = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "/api");
        String activeProfiles = resolveActiveProfiles(env);
        String dbUrl = env.getProperty("spring.datasource.url", "-");
        String startedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));

        String logMsg = String.format("""
                        \n
                        Application '%s' is has successfully been started!
                        Access URLs:
                         Local:       %s://localhost:%s%s
                        Started at:   %s
                        Profile(s):   %s
                        Database:     %s
                        -----------------------------------------------------
                        \n
                        """, applicationName, protocol,
                port, contextPath, startedAt, activeProfiles,
                dbUrl);

        log.error(logMsg);
    }


    private static String resolveActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) return "default";
        return String.join(", ", profiles);
    }

}
