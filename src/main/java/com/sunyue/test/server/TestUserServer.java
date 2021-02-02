package com.sunyue.test.server;

import com.sunyue.test.entity.TestUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestUserServer {
    /*查询所有*/
    List<TestUser> selectAll();

    /*查询一个用户*/
    TestUser selectByOne(String id);

    /*增加一个用户*/
    void insert(TestUser testUser);

    /*删除一个用户*/
    void delete(String id);

    /*更新一个用户*/
    void update(TestUser testUser);
}
