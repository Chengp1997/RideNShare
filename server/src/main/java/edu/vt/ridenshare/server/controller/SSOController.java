package edu.vt.ridenshare.server.controller;

import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.common.log.LogAnnotation;
import edu.vt.ridenshare.server.service.SSOService;
import edu.vt.ridenshare.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("sso")
@Slf4j
public class SSOController {
    @Autowired
    private SSOService ssoService;
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @PostMapping("/register")
    @LogAnnotation(module="/sso/register", operation="register new user")
    public ResponseEntity<User> add(@RequestBody User user, HttpServletRequest request, HttpSession session) throws UnknownHostException {

        User nowUser = ssoService.findUserByEmail(user.getEmail());

        // Check whether there is an account existing
        if (nowUser != null) {
            if (nowUser.getActivateStatus() == 1) {
                return ResponseEntity.ok(nowUser);
            }
            nowUser.setPassword(user.getPassword());
            user = nowUser;
        }
        // Set the username
        String userName = user.getEmail().split("@", 2)[0];
        user.setUsername(userName);

        StringBuilder str = getNewCode();
        user.setActivateCode(str.toString());
        user.setActivateStatus(0);

        long currentTime = System.currentTimeMillis();

        user.setUpdateTime(new Date(currentTime));

        currentTime += 120 * 60 * 1000;
        Date date = new Date(currentTime);

        user.setExpireTime(date);

        if (nowUser != null) {
            userService.deleteUser(nowUser.getId());
        }
        User result = userService.saveUser(user);
        // Send the activation code
        try {
            sendEmail(user.getEmail(), user.getId(), user.getActivateCode(), request);
        }
        catch(Exception e) {
            log.info("Send email failed!");
            System.out.println(e);
        }


        return ResponseEntity.ok(result);
    }

    private StringBuilder getNewCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str;
    }


    void sendEmail(String email, Integer id, String code, HttpServletRequest request) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ridenshareforvt@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Activate Code for Ride N Share");
        // Get the web address
        String url = "";

        String domain = request.getServerName();

        String port = Integer.toString(request.getLocalPort());

        log.info("==========Email Sender===========");
        log.info("Scheme: " + request.getScheme());
        log.info("Domain: " + domain);
        log.info("port: " + domain);
        log.info("Context Path: " + request.getContextPath());
        // For testing

        if (domain.contains("localhost")) {
            url = request.getScheme() + "://" + domain + ":" + port +
                    request.getContextPath() + "/" + "sso/verify/?id=" + id + "&code=" + code;
        }

        // For real server use
        else {
            url = request.getScheme() + "://" + domain +
                    request.getContextPath() + "/" + "sso/verify/?id=" + id + "&code=" + code;
        }

        log.info("url: " + url);


        simpleMailMessage.setText("Hello,\n" +
                "\n" +
                "This is the code of activation.\n" +
                "\n" +
                 code + "\n" +
                "\n" +
                "Please click the link to activate your account.\n" +
                "\n" +
                url + "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Go Hokies!\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Best wishes,\n" +
                "\n" +
                "Ride N Share team");

        log.info("Email ready for sending.");

        mailSender.send(simpleMailMessage);

        log.info("Email sent success.");

        log.info("==========Email Sender End===========");
    }

    @PostMapping("/login")
    @LogAnnotation(module="/sso/login", operation="login to website")
    public User loginController(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = ssoService.loginService(email, password);
        if (user != null) {
            session.setAttribute("user",user);
            return user;
        }
        return null;
    }

    @GetMapping("/verify/")
    @LogAnnotation(module="/sso/verify", operation="verify by email")
    public String verify(@RequestParam(value="id") Integer id, @RequestParam(value="code") String code) {
        User user = userService.queryById(id);
        Date date = new Date(System.currentTimeMillis());
        if (user.getExpireTime().after(date) && Objects.equals(user.getActivateCode(), code)) {
            user.setActivateStatus(1);
            userService.updateCurrentUser(user);
            String returnPage = "\n" +
                    "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Successfully verified!</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h2> Your account has been activated! Please return to " +
                    "login page to log in. </h2>\n" +
                    "</body>\n" +
                    "</html>";
            return returnPage;
        }
        else {
            return "The link has expired or the link is wrong. Please try signing up your account again.";
        }
    }

    @GetMapping("/logout")
    @LogAnnotation(module="/sso/logout", operation="logout to website")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "/login";
    }
}