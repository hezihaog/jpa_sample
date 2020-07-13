package cn.itcast.jpa.test;

import cn.itacst.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 11:05 上午
 * 测试Jpa的Jpql语句
 */
public class JpqlTest {
    /**
     * 查询所有
     * jpql：from cn.itcast.domain.Customer
     * sql：select * from cst_customer
     */
    @Test
    public void testFindAll() {
        //1.获取实体管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.查询全部
//        String jpql = "from cn.itacst.domain.Customer";
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object model : list) {
            System.out.println(model);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 排序查询，倒序查询所有数据
     * sql：select * from cst_customer order by cust_id desc
     * jpal：from Customer order by custId desc
     * <p>
     * 进行jpql查询
     * 1.创建query查询对象
     * 2.对参数进行赋值
     * 3.查询，并获取查询结果集
     */
    @Test
    public void testOrders() {
        //1.获取实体管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = "from Customer order by custId desc";
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object model : list) {
            System.out.println(model);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 统计查询，统计客户的总数
     * jpql：select count(custId) from Customer
     * sql：select count(cust_id) from cst_customer
     */
    @Test
    public void testCount() {
        //1.获取实体管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 分页查询
     * jpql：from Customer
     * sql：select * from cst_customer limit ?,?
     */
    @Test
    public void testPaged() {
        //1.获取实体管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        //对参数进行赋值（分页参数）
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 条件查询，查询客户名称以"传智播客"开头的客户
     * sql：select * from cst_customer where cust_name like ?
     * jpql：from Customer where custName like ?
     */
    @Test
    public void testCondition() {
        //1.获取实体管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //3.查询全部
        String jpql = "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        //对参数进行赋值（占位符参数），第一个参数为占位符的索引，从1开始，第二个参数：取值
        query.setParameter(1, "传智播客%");
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
}