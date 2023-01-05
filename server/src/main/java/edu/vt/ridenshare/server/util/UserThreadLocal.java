package edu.vt.ridenshare.server.util;

import edu.vt.ridenshare.server.entity.User;

/**
 * used the ThreadLocal to store current thread,
 * <key, value> -- key: current thread; value: sysUser
 */
public class UserThreadLocal {
    private UserThreadLocal(){}
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User user){
        LOCAL.set(user);
    }

    public static User get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
