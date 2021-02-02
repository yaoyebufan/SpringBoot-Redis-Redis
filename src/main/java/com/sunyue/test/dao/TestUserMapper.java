package com.sunyue.test.dao;

import com.sunyue.test.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestUserMapper {
    List<TestUser> findAll();

    TestUser selectOne(String id);

    void insert(TestUser testUser);

    void update(TestUser testUser);

    void delete(String id);
}
