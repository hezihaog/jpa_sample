package cn.itcast.test;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 11:17 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     * 多对多放弃维护权：被动的一方，放弃维护权
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd() {
        User user = new User();
        user.setUserName("小李");
        Role role = new Role();
        role.setRoleName("Java程序员");

        //配置用户到角色的关系，可以对中间表的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护，2方都可以维护，会导致主键重复，而抛出异常，所以需要有一方放弃维护权
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 测试级联添加，保存一个用户的同时，保存用户关联的角色
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeAdd() {
        User user = new User();
        user.setUserName("小李");
        Role role = new Role();
        role.setRoleName("Java程序员");

        //配置用户到角色的关系，可以对中间表的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表的数据进行维护，2方都可以维护，会导致主键重复，而抛出异常，所以需要有一方放弃维护权
        role.getUsers().add(user);

        userDao.save(user);
    }

    /**
     * 级联删除，删除id为1的用户，同时删除他关联的角色
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascadeDelete() {
        //查询1号用户
        User user = userDao.findOne(1L);
        //删除1号用户
        userDao.delete(user);
    }
}