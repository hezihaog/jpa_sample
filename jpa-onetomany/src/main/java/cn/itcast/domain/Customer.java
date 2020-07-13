package cn.itcast.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户表实体
 * 所有的注解都是使用JPA的规范提供的注解
 * 所以在导入注解包的时候，一定要导入javax.persistence下的
 */
//声明为数据库表的Entity实体
@Entity
//建立实体类和表的映射关系
@Table(name = "cst_customer")
public class Customer {
    //配置为主键
    @Id
    /**
     * 配置主键的生成策略
     *      GenerationType.IDENTITY：自增长，mysql
     *      GenerationType.SEQUENCE：序列
     *          要求：数据库必须支持序列，例如oracle，mysql不支持序列
     *      GenerationType.TABLE：jpa提供的一种机制，通过一张数据库表的形式帮我们完成自增
     *      GenerationType.AUTO：由程序自动帮我们选择主键生成策略，IDENTITY或SEQUENCE
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 指定映射关系
     *
     * 作用：指定实体类属性和数据库表之间的对应关系
     *    	属性：
     *      	name：指定数据库表的列名称。
     *      	unique：是否唯一
     *      	nullable：是否可以为空
     *      	inserttable：是否可以插入
     *      	updateable：是否可以更新
     *      	columnDefinition: 定义建表时创建此列的DDL
     *      	secondaryTable: 从表名。如果此列不建在主表上（默认建在主表），该属性定义该列所在从表的名字搭建开发环境[重点]
     */
    @Column(name = "cust_id")
    /**
     * 客户编号(主键）
     */
    private Long custId;
    /**
     * 客户名称(公司名称)
     */
    @Column(name = "cust_name")
    private String custName;
    /**
     * 客户信息来源
     */
    @Column(name = "cust_source")
    private String custSource;
    /**
     * 客户所属行业
     */
    @Column(name = "cust_industry")
    private String custIndustry;
    /**
     * 客户级别
     */
    @Column(name = "cust_level")
    private String custLevel;
    /**
     * 客户联系地址
     */
    @Column(name = "cust_address")
    private String custAddress;
    /**
     * 客户联系电话
     */
    @Column(name = "cust_phone")
    private String custPhone;
    /**
     * 一对多关系映射：客户和联系人，一个客户可有多个联系人
     * <p>
     * 注解@OneToMany：声明关系，targetEntity对方的实体类类型
     * 注解@JoinColumn：配置外键，name：外键字段名称，referencedColumnName：参照的主表的主键名称
     */
    //由于一的一方可以维护外键，会发送多一条update语句，所以放弃维护外键权
//    @OneToMany(targetEntity = LinkMan.class)
    //联系人表的主键lkm_cust_id，引用该表的主键cust_id
//    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")

    /**
     * 放弃维护外键权，需要使用mappedBy属性，指定维护外键的那一方中使用@JoinColumn注解的属性名
     *
     * mappedBy属性：对方配置关系的属性名称
     * cascade属性：级联属性（可以配置到设置多表映射关系的注解上）
     *      1.CascadeType.ALL：所有
     *      2.CascadeType.MERGE：更新
     *      3.CascadeType.PERSIST：保存
     *      4.CascadeType.REMOVE：删除
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<LinkMan> linkmans = new HashSet<LinkMan>(0);

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkmans() {
        return linkmans;
    }

    public void setLinkmans(Set<LinkMan> linkmans) {
        this.linkmans = linkmans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", linkmans=" + linkmans +
                '}';
    }
}