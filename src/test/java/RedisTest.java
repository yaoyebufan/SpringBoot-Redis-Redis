import com.sunyue.test.TestApplication;
import com.sunyue.test.entity.TestUser;
import com.sunyue.test.server.TestUserServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private TestUserServer testUserServer;

    @Test
    public void redisTest() {
        TestUser testUser = new TestUser();
        testUser.setId(UUID.randomUUID().toString());
        testUser.setName("孙跃");
        testUser.setBir(new Date());
        testUser.setAge(24);
        testUserServer.insert(testUser);
        TestUser testUser1 = new TestUser();
        testUser1.setId(UUID.randomUUID().toString());
        testUser1.setName("孙越");
        testUser1.setBir(new Date());
        testUser1.setAge(22);
        testUserServer.insert(testUser1);
    }
    @Test
    public void delete(){
        testUserServer.delete("1a88f8dc-b9aa-45cf-af53-38e5a6d52b38");
    }
    @Test
    public void update(){
        TestUser testUser = new TestUser();
        testUser.setId("1a88f8dc-b9aa-45cf-af53-38e5a6d52b38");
        testUser.setName("孙跃1w");
        testUser.setBir(new Date());
        testUser.setAge(18);
        testUserServer.update(testUser);
    }
    @Test
    public void select() {
        List<TestUser> testUsers = testUserServer.selectAll();
        testUsers.forEach(System.out::println);
        System.out.println(testUserServer.selectByOne("1a88f8dc-b9aa-45cf-af53-38e5a6d52b38"));
    }








    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void redisString(){
        HashMap<String, TestUser> map = new HashMap<>();
        map.put("123",new TestUser().setName("sunyue").setAge(1).setBir(new Date()).setId(UUID.randomUUID().toString()));
        try {
            redisTemplate.opsForHash().putAll("23",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(redisTemplate.opsForHash().keys("23"));
        System.out.println(redisTemplate.opsForHash().values("23"));
    }

}
