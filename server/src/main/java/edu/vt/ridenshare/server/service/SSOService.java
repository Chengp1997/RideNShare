package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.User;

import javax.servlet.http.HttpSession;

public interface SSOService {
    User findUserByEmail(String email);
    User loginService(String email, String password);
    User checkSession(HttpSession ses);
}
