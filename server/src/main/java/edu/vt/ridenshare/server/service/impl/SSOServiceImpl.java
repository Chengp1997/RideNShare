package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.dao.UserDao;
import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.service.SSOService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("SSOService")
public class SSOServiceImpl implements SSOService {
    @Resource
    private UserDao userDao;
    @Override
    public User loginService(String email, String password) {
        User user = userDao.queryByEmailAndPassword(email, password);
        return user;
    }

    @Override
    public User checkSession(HttpSession ses) {
        Integer id = (Integer) ses.getAttribute("id");
        User user = userDao.queryById(id);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        return user;
    }

}
