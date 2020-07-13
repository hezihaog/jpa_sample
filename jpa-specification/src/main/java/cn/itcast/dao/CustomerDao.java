package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 6:28 下午
 * 客户表Dao层接口
 */
public interface CustomerDao extends JpaSpecificationExecutor<Customer>, JpaRepository<Customer, Long> {
}