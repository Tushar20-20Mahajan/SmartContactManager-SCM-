package com.tusharSCM.tusharSCM.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.micrometer.core.ipc.http.HttpSender.Request;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage(){
        try {
            System.out.println("Removing Message from session");
            HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error in Session Helper: " + e);
            e.printStackTrace();
        }
    }
}
