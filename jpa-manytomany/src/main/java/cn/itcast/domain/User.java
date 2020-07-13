package cn.itcast.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 10:58 下午
 */
@Entity
@Table(name = "sys_user")
public class User {
    /**
     * 用户Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 用户年龄
     */
    @Column(name = "age")
    private Integer age;
    /**
     * 配置用户到角色的多对多关系
     * 1.配置多对多的映射关系
     *  注解@ManyToMany：多对多关系，targetEntity：代表对方的实体类字节码
     * 2.配置中间表（包含2个外键）
     *
     * 注解@JoinTable：
     *  name属性：中间表的名称
     *  joinColumns属性（数组）：
     *      name：配置当前对象在中间表的外键
     *      referencedColumnName：参照的主表的主键名
     *  inverseJoinColumns属性（数组）：配置对象对象在中间表的位置
     *      name：配置当前对象在中间表的外键
     *      referencedColumnName：参照的主表的主键名
     */
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",
            //joinColumns，当前对象在中间表的外键
            joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}