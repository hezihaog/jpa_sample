package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 4:48 下午
 * <p>
 * Spring Data Jpa 使用Jpql进行查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试基本查询
     */
    @Test
    public void testFindJpql() {
        Customer customer = customerDao.findJpql("黑马程序员");
        System.out.println(customer);
    }

    /**
     * 测试多占位符
     */
    @Test
    public void testFindCustNameAndId() {
        List<Customer> customers = customerDao.findCustNameAndId("小码哥", 4L);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * 测试Jpql的更新操作
     * Spring Data jpa，执行更新、删除操作，必须使用事务，就是添加@Transactional注解
     * 但默认执行完毕后，回滚事务，所以需要手动指定不自动回滚
     * <p>
     * 注解@Rollback，value值，true自动回滚，false不自动回滚
     */
    @Test
    @Transactional//添加事务支持，否则执行会报错
    @Rollback(value = false)//设置不自动回滚
    public void testUpdateCustomer() {
        customerDao.updateCustomer(5L, "黑马程序员666");
    }

    /**
     * 测试使用sql查询
     */
    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql("黑马程序员");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    /**
     * 测试，按方法命名规则查询（精确查询）
     */
    @Test
    public void testFindByCustName() {
        Customer customer = customerDao.findByCustName("黑马程序员");
        System.out.println(customer);
    }

    /**
     * 测试，按方法命名规则模糊查询（模糊查询）
     */
    @Test
    public void testFindByCustNameLike() {
        List<Customer> customers = customerDao.findByCustNameLike("黑马程序员%");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * 测试，使用客户名称模糊查询，和客户所属行业精准匹配的查询（多条件查询）
     */
    @Test
    public void testFindByCustNameLikeAndCustIndustry() {
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("黑马程序员%", "it教育");
        System.out.println(customer);
    }
}