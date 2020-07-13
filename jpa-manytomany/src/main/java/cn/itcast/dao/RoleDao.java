package cn.itcast.dao;

import cn.itcast.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 11:02 下午
 * 角色表Dao
 */
public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}