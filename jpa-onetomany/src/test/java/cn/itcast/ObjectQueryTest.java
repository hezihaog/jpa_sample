package cn.itcast;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/14 9:51 上午
 * <p>
 * 对象导航查询测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试对象导航查询（查询一个对象时，通过此对象，查询所有关联的对象）
     * 添加注解@Transactional，解决could not initialize proxy - no Session错误
     */
    @Test
    @Transactional
    public void testQuery1() {
        //查询1号客户
        Customer customer = customerDao.getOne(1L);
        //对象导航查询
        Set<LinkMan> linkmans = customer.getLinkmans();
        for (LinkMan linkman : linkmans) {
            System.out.println(linkman);
        }
    }

    /**
     * 对象导航查询
     * 导航到的属性是多的一方（一个集合），默认使用的是延迟加载的形式查询，也就是说调用get()方法并不会立即发送查询，而是在使用关联对象时，才会查询
     * 如果不想延迟加载，我们只需要修改配置，将延迟加载改为立即加载即可
     * fetch：需要配置到多表映射关系的注解上，@OneToMany、@ManyToMany，这里去Customer类中添加
     */
    @Test
    @Transactional
    public void testQuery2() {
        //查询1号客户
        Customer customer = customerDao.findOne(1L);
        //对象导航查询
        Set<LinkMan> linkmans = customer.getLinkmans();
        System.out.println(linkmans.size());
    }

    /**
     * 从联系人对象导航到他所属的客户
     * 导航到的属性是一的一方（单个对象属性），默认使用的是立即加载的方式查询
     */
    @Test
    @Transactional
    public void testQuery3() {
        //查询1号客户
        LinkMan linkMan = linkManDao.findOne(1L);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}