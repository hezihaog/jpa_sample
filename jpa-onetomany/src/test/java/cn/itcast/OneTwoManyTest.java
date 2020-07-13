package cn.itcast;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 9:19 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneTwoManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试，保存一个客户，保存一个联系人
     */
    @Test
    @Transactional//配置事务
    @Rollback(value = false)//不自动回滚
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");
        //向客户中，添加一个联系人
        //从客户的角度：发送2条insert语句，再发送一条update更新语句，更新外键（由于我们配置了客户到联系人的关系，客户可以对外键进行维护）
        Set<LinkMan> linkmans = customer.getLinkmans();
        linkmans.add(linkMan);
        //保存客户
        customerDao.save(customer);
        //保存联系人
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional//配置事务
    @Rollback(value = false)//不自动回滚
    public void testAdd2() {
        Customer customer = new Customer();
        customer.setCustName("百度");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        //联系人中，添加一个客户
        //配置联系人到客户的关系，只发送了2条insert语句（由于我们配置了联系人到客户的映射关系（多对一））
        linkMan.setCustomer(customer);

        //保存客户
        customerDao.save(customer);
        //保存联系人
        linkManDao.save(linkMan);
    }

    /**
     * 会有一条多余的update语句
     *  1.由于一的一方可以维护外键，会发送多一条update语句
     *  2.要解决此问题，只需要在一的一方放弃维护权即可
     */
    @Test
    @Transactional//配置事务
    @Rollback(value = false)//不自动回滚
    public void testAdd3() {
        Customer customer = new Customer();
        customer.setCustName("百度");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        //由于配置了多的一方到一的一方的关联关系（当保存的时候，已经对外键进行赋值）
        linkMan.setCustomer(customer);
        //由于配置一的一方到多的一方的关联关系（发送一条update语句）
        customer.getLinkmans().add(linkMan);

        //保存客户
        customerDao.save(customer);
        //保存联系人
        linkManDao.save(linkMan);
    }

    /**
     * 级联添加，保存一个客户的同时，保存客户的所有联系人
     * 需要在操作主体的实体类上，配置cascade
     */
    @Test
    @Transactional//配置事务
    @Rollback(value = false)//不自动回滚
    public void testCascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度1");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李1");
        //设置关系
        linkMan.setCustomer(customer);
        customer.getLinkmans().add(linkMan);
        //保存客户，同时保存客户的所有联系人
        customerDao.save(customer);
    }

    /**
     * 级联删除，删除1号客户的同时，删除1号客户的所有联系人
     */
    @Test
    @Transactional//配置事务
    @Rollback(value = false)//不自动回滚
    public void testCascadeRemove() {
        //1.查询1号客户
        Customer customer = customerDao.findOne(1L);
        //2.删除1号客户
        customerDao.delete(customer);
    }
}