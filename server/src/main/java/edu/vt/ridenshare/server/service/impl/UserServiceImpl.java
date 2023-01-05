package edu.vt.ridenshare.server.service.impl;

import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.dao.UserDao;
import edu.vt.ridenshare.server.service.UserService;
import edu.vt.ridenshare.server.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    @Override
    public UserVo findUserById(Integer id) {
        User user = userDao.queryById(id);
        return copy(user);
    }

    @Override
    public List<User> queryByIds(List<Integer> userIdList) {
        return userDao.queryByIds(userIdList);
    }

    @Override
    public Page<UserVo> queryByPage(PageRequest pageRequest) {
        User temp = new User();
        long total = userDao.count(temp);
        System.out.println(pageRequest.getPageSize());
        List<User> users = userDao.queryAllByLimit(temp, pageRequest);
        if (users == null|| users.size()==0){
            return null;
        }
        return new PageImpl<>(copyToList(users), pageRequest, total);
    }

    @Override
    public User saveUser(User user) {
        this.userDao.insert(user);
        return user;
    }

    @Override
    public UserVo updateCurrentUser(User user) {
        this.userDao.update(user);
        return findUserById(user.getId());
    }

    @Override
    public boolean deleteUser(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    private UserVo copy(User user){
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setEmail(user.getEmail());
        userVo.setPhone(user.getPhone());
        userVo.setUsername(user.getUsername());
        return userVo;
    }

    private List<UserVo> copyToList(List<User> users){
        List<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = copy(user);
            userVos.add(userVo);
        }
        return userVos;
    }

}
