package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 2:58 下午
 *
 * CustomerDao测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(3L);
        System.out.println(customer);
    }

    /**
     * 测试保存
     * save：保存或更新
     * 根据传入的对象是否有id主键属性，如果有，则为更新，如没有就是新增
     * 更新：先查询，再更新
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("黑马程序员");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);
    }

    /**
     * 更新
     */
    @Test
    public void testUpdate() {
        Customer customer = customerDao.findOne(5L);
        customer.setCustName("黑马程序员2");
        customerDao.save(customer);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        customerDao.delete(3L);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 测试统计查询：查询客户总数量
     */
    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 测试，判断id为4的客户是否存在
     */
    @Test
    public void testExists() {
        boolean isExists = customerDao.exists(4L);
        System.out.println(isExists);
    }

    /**
     * 根据id查询
     * 注解@Transactional，作用：保证getOne()正常运行
     *
     * findOne()：使用了em.find()，立即加载
     * getOne()：使用了em.getReference()，延迟加载
     */
    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(4L);
        System.out.println(customer);
    }
}