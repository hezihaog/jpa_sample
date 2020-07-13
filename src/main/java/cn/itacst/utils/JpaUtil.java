package cn.itacst.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author hezihao
 * @version 1.0
 * @date 2020/7/13 10:37 上午
 * Jpa工具类，解决实体管理器工厂浪费资源和耗时问题
 * 原理：通过静态代码块，第一次访问工具类时，创建一个公共实体管理器工厂，后续通过getEntityManager()方法，获取实体管理器即可
 */
public class JpaUtil {
    private static EntityManagerFactory factory;

    static {
        //加载配置文件，获取实体管理器工厂
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 获取实体管理器
     */
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}