package cn.itcast.domain;


import javax.persistence.*;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 8:52 下午
 */
@Entity
@Table(name = "cst_linkman")
public class LinkMan {
    /**
     * 主键，联系人编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId;
    /**
     * 联系人姓名
     */
    @Column(name = "lkm_name")
    private String lkmName;
    /**
     * 联系人性别
     */
    @Column(name = "lkm_gender")
    private String lkmGender;
    /**
     * 联系人办公电话
     */
    @Column(name = "lkm_phone")
    private String lkmPhone;
    /**
     * 联系人手机
     */
    @Column(name = "lkm_mobile")
    private String lkmMobile;
    /**
     * 联系人邮箱
     */
    @Column(name = "lkm_email")
    private String lkmEmail;
    /**
     * 联系人职位
     */
    @Column(name = "lkm_position")
    private String lkmPosition;
    /**
     * 联系人备注
     */
    @Column(name = "lkm_memo")
    private String lkmMemo;
    /**
     * 多对一关系映射：多个联系人对应客户
     * 注解@ManyToOne：配置多对一关系，targetEntity：对象的实体类的类型
     * 注解@JoinColumn，配置外键，name：外键名称，referencedColumnName参照的主表的主键名称
     */
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    //外键lkm_cust_id，引用主表的cust_id
    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    private Customer customer;

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                ", lkmMobile='" + lkmMobile + '\'' +
                ", lkmEmail='" + lkmEmail + '\'' +
                ", lkmPosition='" + lkmPosition + '\'' +
                ", lkmMemo='" + lkmMemo + '\'' +
                '}';
    }
}