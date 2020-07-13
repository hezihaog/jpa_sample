package cn.itcast.dao;

import cn.itcast.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 11:02 下午
 * 用户表Dao
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}