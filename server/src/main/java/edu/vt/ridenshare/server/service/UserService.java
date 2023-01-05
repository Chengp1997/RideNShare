package edu.vt.ridenshare.server.service;

import edu.vt.ridenshare.server.entity.User;
import edu.vt.ridenshare.server.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {

    User queryById(Integer id);

    UserVo findUserById(Integer id);

    /**
     * query by user id list
     * @param userIdList user id list
     * @return user list
     */
    List<User> queryByIds(List<Integer> userIdList);

    Page<UserVo> queryByPage(PageRequest pageRequest);

    User saveUser(User user);

    UserVo updateCurrentUser(User user);

    boolean deleteUser(Integer id);

}
