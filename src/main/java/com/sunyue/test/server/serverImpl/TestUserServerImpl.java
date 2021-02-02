package com.sunyue.test.server.serverImpl;

import com.sunyue.test.dao.TestUserMapper;
import com.sunyue.test.entity.TestUser;
import com.sunyue.test.server.TestUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TestUserServerImpl implements TestUserServer {
    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    @Transactional
    public List<TestUser> selectAll() {
        return testUserMapper.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestUser selectByOne(String id){
        return testUserMapper.selectOne(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(TestUser testUser) {
        testUserMapper.insert(testUser);
        //推荐：在业务层将异常抛出
/*        throw new RuntimeException("添加发生异常了..");*/
    }

    @Override
    public void delete(String id) {
        testUserMapper.delete(id);
    }

    @Override
    public void update(TestUser testUser) {
        testUserMapper.update(testUser);
    }
}
