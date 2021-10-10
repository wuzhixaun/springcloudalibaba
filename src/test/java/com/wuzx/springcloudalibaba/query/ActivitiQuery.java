package com.wuzx.springcloudalibaba.query;

import com.wuzx.springcloudalibaba.SpringcloudalibabaApplicationTests;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class ActivitiQuery extends SpringcloudalibabaApplicationTests {


    @Resource
    private IdentityService identityService;

    /**
     * 插入
     */
    @Test
    public void insertUser() {
        for (int i = 0; i < 10; i++) {
            final User user = identityService.newUser(String.valueOf(i));
            user.setFirstName("张三" + i);
            user.setEmail("email" + i);
            identityService.saveUser(user);
        }
    }


    /**
     * 查询list
     */

    @Test
    public void getUserList() {
        final List<User> list = identityService.createUserQuery().list();
        list.forEach(user -> {
            System.out.println("id:" + user.getId());
            System.out.println("firstName:" + user.getFirstName());
            System.out.println("email:" + user.getEmail());
        });
    }

    /**
     * 分页
     */
    @Test
    public void getSingleUser() {
        final List<User> users = identityService.createUserQuery().listPage(5, 2);

        users.forEach(user -> {
            System.out.println("id:" + user.getId());
            System.out.println("firstName:" + user.getFirstName());
            System.out.println("email:" + user.getEmail());
        });
    }


    /**
     * 一个字段排序
     */
    @Test
    public void querySingleSort() {
        final List<User> list = identityService.createUserQuery().orderByUserEmail().desc().list();
        list.forEach(user -> {
            System.out.println("id:" + user.getId());
            System.out.println("firstName:" + user.getFirstName());
            System.out.println("email:" + user.getEmail());
        });

    }


    /**
     * 一个字段排序
     */
    @Test
    public void queryMultiSort() {
        final List<User> list = identityService.createUserQuery().orderByUserEmail().desc().orderByUserId().asc().list();
        list.forEach(user -> {
            System.out.println("id:" + user.getId());
            System.out.println("firstName:" + user.getFirstName());
            System.out.println("email:" + user.getEmail());
        });

    }

    @Test
    public void queryContidion() {
        final List<User> list = identityService.createUserQuery().userFirstNameLike("%张三%").list();
        list.forEach(user -> {
            System.out.println("id:" + user.getId());
            System.out.println("firstName:" + user.getFirstName());
            System.out.println("email:" + user.getEmail());
        });

    }

}
