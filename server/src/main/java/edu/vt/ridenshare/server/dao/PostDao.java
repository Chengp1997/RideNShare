package edu.vt.ridenshare.server.dao;

import edu.vt.ridenshare.server.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostDao {

    /**
     * query post detail by id
     *
     * @param id post id
     * @param statusCode status code
     * @return post detail
     */
    Post queryById(@Param("id") Integer id, @Param("statusCode") int statusCode);

    /**
     * query posts by userId
     *
     * @param userId user id
     * @param statusCode status code
     * @return posts
     */
    List<Post> queryByUserId(@Param("userId") Integer userId, @Param("statusCode") int statusCode);

    /**
     * query filtered posts
     *
     * @param post query condition
     * @param pageable page object
     * @return all filtered posts
     */
    List<Post> queryAllByLimit(@Param("post") Post post, @Param("pageable") Pageable pageable);

    /**
     * count rows
     *
     * @param post query condition
     * @return total rows
     */
    long count(Post post);

    /**
     * insert a post
     *
     * @param post post
     * @return rows affected
     */
    int insert(Post post);

    /**
     * insert batch
     *
     * @param entities List<Post> posts
     * @return rows affected
     */
    int insertBatch(@Param("entities") List<Post> entities);

    /**
     * insert or update batch
     *
     * @param entities List<Post> posts
     * @return rows affected
     * @throws org.springframework.jdbc.BadSqlGrammarException exception
     */
    int insertOrUpdateBatch(@Param("entities") List<Post> entities);

    /**
     * update post
     *
     * @param post post
     * @return rows affected
     */
    int update(Post post);

    /**
     * delele by id
     *
     * @param id post id
     * @return rows affected
     */
    int deleteById(Integer id);

}

