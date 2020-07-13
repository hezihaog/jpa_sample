package cn.itcast.jpa.test;

import cn.itacst.domain.Customer;
import cn.itacst.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 10:10 上午
 * Jpa测试
 */
public class JpaTest {
    /**
     * 测试Jpa的保存
     * 步骤：
     * 1.加载配置文件，创建实体管理器工厂
     * 2.通过实体管理器工厂，获取实体管理器
     * 3.获取事务对象，开启事务
     * 4.执行增删改
     * 5.提交事务或回滚事务
     * 6.释放资源
     */
    @Test
    public void testSave() {
        //1.加载配置文件，创建实体管理器工厂，传入持久化单元名称
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂，获取实体管理器
//        EntityManager entityManager = factory.createEntityManager();

        EntityManager entityManager = JpaUtil.getEntityManager();

        //3.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //4.执行增删改，保存一个客户到数据库
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");
        //保存操作
        entityManager.persist(customer);
        //5.提交事务
        tx.commit();
        //6.释放资源
        entityManager.close();
//        factory.close();
    }

    /**
     * 测试根据Id查询客户，使用find()
     * 使用find()方法进行查询
     * 1.查询对象就是当前客户对象本身
     * 2.在调用find()方法时，就会发送sql语句
     * <p>
     * 立即加载
     */
    @Test
    public void testFind() {
        //1.获取EntityManager
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.执行操作
        /**
         * find()，根据Id查询数据
         *  1.class：查询数据的结果，封装到哪个实体类
         *  2.id：查询的主键值
         */
        Customer customer = entityManager.find(Customer.class, 3L);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 测试根据Id查询客户，使用getReference()
     * 使用使用getReference()方法进行查询
     * 1.获取的对象是一个动态代理对象
     * 2.调用getReference()方法，不会立即发送sql语句查询数据库
     * 3.当调用查询结果对象的属性的时候，才会发送查询sql，什么时候用，什么时候发送sql语句
     * 延迟加载（懒加载）
     */
    @Test
    public void testReference() {
        //1.获取EntityManager
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.执行操作
        /**
         * getReference()，根据Id查询数据
         *  1.class：查询数据的结果，封装到哪个实体类
         *  2.id：查询的主键值
         */
        Customer customer = entityManager.getReference(Customer.class, 3L);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 测试删除
     */
    @Test
    public void testRemove() {
        //1.获取EntityManager
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.执行操作
        //先查询
        Customer customer = entityManager.find(Customer.class, 3L);
        //再删除
        entityManager.remove(customer);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 测试删除
     */
    @Test
    public void testUpdate() {
        //1.获取EntityManager
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.执行操作
        //先查询
        Customer customer = entityManager.find(Customer.class, 3L);
        customer.setCustIndustry("it教育");
        //再更新
        entityManager.merge(customer);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
}