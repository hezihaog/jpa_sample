package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 6:26 下午
 * <p>
 * 条件查询测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据查询，查询单个对象
     */
    @Test
    public void testSpec1() {
        /**
         * 自定义查询条件，创建Specification实例，复写toPredicate()方法，返回查询条件
         * toPredicate()方法参数解释：
         *  1.Root，获取需要查询的对象属性
         *  2.CriteriaBuilder，构造查询条件的，内部封装了很多查询条件（模糊查询、精准匹配）
         *
         *  需求：根据客户名称查询，查询客户名称为黑马程序员的客户
         *  查询条件
         *      1.查询方式，CriteriaBuilder对象
         *      2.比较的属性名称，root对象
         */
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件，select * from cst_customer where cust_name = '黑马程序员'
                //进行精确匹配（参数：比较的属性，比较的属性的值）
                return cb.equal(custName, "黑马程序员");
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     * 需求：根据客户名"黑马程序员"，和客户所属行业（it教育）
     */
    @Test
    public void testSpec2() {
        /**
         * root：获取属性
         *      客户名
         *      所属行业
         * cb：构造查询
         *      1.构造客户名的精准匹配查询
         *      2.构造所属行业的精准匹配查询
         *      3.将以上2个查询联系起来
         */
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //客户名
                Path<Object> custName = root.get("custName");
                //所属行业
                Path<Object> custIndustry = root.get("custIndustry");
                //构造查询
                Predicate p1 = cb.equal(custName, "黑马程序员");
                Predicate p2 = cb.equal(custIndustry, "it教育");
//                return cb.or(p1, p2);//或关系
                //联合多个查询条件，满足条件1和条件2，与关系
                return cb.and(p1, p2);
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 模糊查询
     * 需求：根据客户的名称模糊匹配，返回客户列表
     * <p>
     * equal：直接获取path对象，直接传入进行比较即可
     * gt，lt，ge，like：得到path对象，根据path指定比较的参数类型，再去比较
     * 指定参数类型：path.as(类型的字节码对象)；
     */
    @Test
    public void testSpec3() {
        //构造查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                return cb.like(custName.as(String.class), "黑马程序员%");
            }
        };
        /**
         * 排序
         *
         * 参数一：
         * Sort.Direction.DESC：倒序
         * Sort.Direction.ASC：正序
         * 参数二：
         * 需要按照排序的字段
         */
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> list = customerDao.findAll(specification, sort);
//        List<Customer> list = customerDao.findAll(specification);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     * <p>
     * Pageable分页参数
     * findAll(Specification, Pageable)：带有条件的分页
     * findAll(Pageable)：没有条件的分页
     * <p>
     * 返回Page对象
     * Pageable接口，实现类PageRequest
     * 构造方法：
     * 1.第一个参数：当前查询的页数（从0开始）
     * 2.第二参数：每页查询的数量
     */
    @Test
    public void testSpec4() {
        Page<Customer> pages = customerDao.findAll(null, new PageRequest(0, 2));
        //获取总条数
        long totalElements = pages.getTotalElements();
        System.out.println("总条数 totalElements：" + totalElements);
        //获取总页数
        int totalPages = pages.getTotalPages();
        System.out.println("总页数 totalPages：" + totalPages);
        //获取数据集合
        List<Customer> list = pages.getContent();
        System.out.println("数据集合 list：" + list);
    }
}