package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserDao {

    User findUserByEmail(String email);

    User queryByEmailAndPassword(String email, String password);

    User queryById(Integer id);

    /**
     * query by user id list
     * @param userIdList user id list
     * @return user list
     */
    List<User> queryByIds(List<Integer> userIdList);

    List<User> queryAllByLimit(User user, @Param("pageable") Pageable pageable);

    long count(User user);

    int insert(User user);

    int insertBatch(@Param("entities") List<User> entities);

    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    int update(User user);

    int deleteById(Integer id);

}

