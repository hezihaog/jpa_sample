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
}