import com.ssm.base.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ReedMi on 2017/3/22.
 */
@WebAppConfiguration
@ContextConfiguration({
        "classpath:spring/context.xml",
        "classpath:spring/controller.xml",
        "classpath:spring/context-db.xml"})
public class UserTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        System.out.println(userMapper.findAll().size());
    }
}
