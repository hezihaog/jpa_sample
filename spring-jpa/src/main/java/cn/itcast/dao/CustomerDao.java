package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 2:50 下午
 * Customer表的Dao层，Jpa的Dao接口需要继承2个接口，JpaRepository接口，JpaSpecificationExecutor接口
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 简单案例
     * <p>
     * 使用Jpql语句进行查询
     * jpql：from Customer where custName = ?
     */
    @Query(value = "from Customer where custName = ?")
    Customer findJpql(String custName);

    /**
     * 多占位符案例
     * <p>
     * 根据客户名称和客户id，查询客户
     * jpql：from Customer where custName = ? and custId = ?
     * <p>
     * 对于多个占位符参数
     * 赋值的时候，默认情况下，占位符的位置需要和方法参数中的位置保持一致
     * <p>
     * 可以指定占位符参数的位置
     * ?索引的方式，指定此占位符的取值，例如参数调换位置，from Customer where custName = ?2 and custId = ?1
     */
//    @Query(value = "from Customer where custName = ?2 and custId = ?1")
//    List<Customer> findCustNameAndId(Long id,String name);
    @Query(value = "from Customer where custName = ? and custId = ?")
    List<Customer> findCustNameAndId(String name, Long id);

    /**
     * 使用Jpql完成更新数据
     * <p>
     * 根据id，更新客户的名称
     * <p>
     * sql：update cust_customer set cust_name =? where cust_id = ?
     * jpql：update Customer set custName = ? where custId = ?
     * <p>
     * 注解@Query是用来查询用的，我们的操作是更新，所以需要再增加一个注解
     * 增加@Modifying注解，表示当前的方法是一个更新操作
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    void updateCustomer(long id, String custName);

    /**
     * 使用sql语句查询
     * sql：select * from cst_customer
     * <p>
     * 使用sql语句查询，还是使用@Query注解，但需要设置nativeQuery属性为true，代表value中的是sql语句，默认值为false，就是代表是jpql
     * <p>
     * 注解@Query：配置查询
     * value：sql语句或jpql
     * nativeQuery：查询方式，true为sql，false为jpql
     */
//    @Query(value = "select * from cst_customer", nativeQuery = true)
//    List<Object[]> findSql();
    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    List<Object[]> findSql(String name);

    /**
     * 约定方式，方法按规则进行命名，就无须使用@Query写语句进行查询
     * findBy开头：查询，后面是对象属性名（首字母大学），查询条件
     * <p>
     * findByCustName()，根据客户名称查询
     * <p>
     * 在运行阶段，会根据方法名称进行解析，findBy from xxx（实体类） 属性名称 where custName = ?
     * <p>
     * 规则：
     *
     * 注意：如果是精准匹配，查询方式可以不写！
     * 1.精确查询：findBy + 属性名称（根据属性名称进行精确匹配的查询，就是=等于）
     * 2.模糊查询：findBy + 属性名称 + 查询方式（Like | isnull），例如：findByCustNameLike
     * 3.多条件查询：
     *      findBy + 属性名 + 查询方式 + 多条件的连接符（and|or） + 属性名 + 查询方式
     */
    Customer findByCustName(String custName);

    /**
     * 模糊查询
     * findBy + 属性名称 + 查询方式（Like | isnull）
     * 模糊查询：findByCustNameLike
     */
    List<Customer> findByCustNameLike(String custName);

    /**
     * 使用客户名称模糊查询，和客户所属行业精准匹配的查询
     */
    Customer findByCustNameLikeAndCustIndustry(String custName, String custIndustry);
}