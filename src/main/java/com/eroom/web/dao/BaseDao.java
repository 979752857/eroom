package com.eroom.web.dao;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.ByteType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.jboss.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class BaseDao {
    @Resource
    protected JdbcTemplate jdbcTemplate;

    protected Logger logger = LoggerFactory.logger(getClass());

    @Resource
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public <T> T save(T object) throws Exception {
        try {
            this.getSession().save(object);
            return object;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void update(Object object) throws Exception {
        try {
            this.getSession().update(object);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void saveOrUpdate(Object object) throws Exception {
        try {
            this.getSession().saveOrUpdate(object);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("rawtypes")
    public void deleteById(Class entityClass, Long id) throws Exception {
        try {
            Object obj = this.getSession().get(entityClass, id);
            if (obj != null) {
                this.getSession().delete(obj);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public int update(String hql, Map<String, Object> params) {
        Query query = this.getSession().createQuery(hql);
        query.setProperties(params);
        int i = query.executeUpdate();
        return i;
    }

    public void delete(Object object) throws Exception {
        try {
            this.getSession().delete(object);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void batch(Object object) throws Exception {
        try {
            this.getSession().delete(object);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> entityClass, Long id) throws Exception {
        try {
            Assert.notNull(id, "PK id not null !");
            return (T) this.getSession().get(entityClass, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String queryString, Map<String, Object> values) throws Exception {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            Query query = getSession().createQuery(queryString);

            if (values != null && !values.isEmpty()) {
                query.setProperties(values);
            }

            return query.list();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String queryString) throws Exception {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            Query query = getSession().createQuery(queryString);

            return query.list();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getPageList(String queryString, Map<String, Object> values, Integer pageNo,
            Integer pageSize) throws Exception {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            Query query = getSession().createQuery(queryString);

            if (values != null && !values.isEmpty()) {
                query.setProperties(values);

            }
            if (pageNo != null && pageNo >= 0 && pageSize != null && pageSize > 0) {
                query.setFirstResult((pageNo == 0 ? 0 : pageNo)*pageSize);
                query.setMaxResults(pageSize);
            }

            return query.list();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> getPageListBySQL(String queryString, Map<String, Object> values,
            Integer pageNo, Integer pageSize, Class<T> clazz) {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            SQLQuery query = this.getSession().createSQLQuery(queryString);
            ;
            if (values != null && !values.isEmpty()) {
                query.setProperties(values);
            }
            if (pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0) {
                query.setFirstResult((pageNo - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                    if (!field.getName().equals("serialVersionUID")) {
                    switch (field.getType().getName()) {
                    case "java.lang.String":
                        query.addScalar(field.getName(), StringType.INSTANCE);
                        break;
                    case "java.lang.Long":
                    case "long":
                        query.addScalar(field.getName(), LongType.INSTANCE);
                        break;
                    case "java.lang.Integer":
                    case "int":
                        query.addScalar(field.getName(), IntegerType.INSTANCE);
                        break;
                    case "byte":
                    case "java.lang.Byte":
                        query.addScalar(field.getName(), ByteType.INSTANCE);
                        break;
                    case "java.math.BigDecimal":
                        query.addScalar(field.getName(), BigDecimalType.INSTANCE);
                        break;
                    case "java.util.Date":
                        query.addScalar(field.getName(), TimestampType.INSTANCE);
                        break;
                    }
                }
            }
            return query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 
     * @param entity
     * @return
     */
    public long getTotal(String entity) {
        String hql = "select count(*) from " + entity;
        Query query = this.getSession().createQuery(hql);
        long count = ((Number) query.uniqueResult()).longValue();
        return count;
    }

    @SuppressWarnings("rawtypes")
    public List executeSql(String sql) {
        SQLQuery ss = this.getSession().createSQLQuery(sql);
        List list = ss.list();
        return list;
    }

    /**
     * 批量保存对象
     * 
     * @param sql
     * @param list
     * @author tendy
     */
    public void saveObjectBatch(String sql, List<Object[]> list) {
        jdbcTemplate.batchUpdate(sql, list);
    }

    @SuppressWarnings("rawtypes")
    public int executeUpdateSql(String sql, Map map) {
        int num = this.getSession().createSQLQuery(sql).setProperties(map).executeUpdate();
        return num;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getListBySQL(String queryString, Map<String, Object> values,
            Class<T> clazz) {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            SQLQuery query = this.getSession().createSQLQuery(queryString);
            if (values != null && !values.isEmpty()) {
                query.setProperties(values);
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                switch (field.getType().getName()) {
                case "java.lang.String":
                    query.addScalar(field.getName(), StringType.INSTANCE);
                    break;
                case "java.lang.Long":
                case "long":
                    query.addScalar(field.getName(), LongType.INSTANCE);
                    break;
                case "java.lang.Integer":
                case "int":
                    query.addScalar(field.getName(), IntegerType.INSTANCE);
                    break;
                case "java.lang.Byte":
                case "byte":
                    query.addScalar(field.getName(), ByteType.INSTANCE);
                    break;
                case "java.math.BigDecimal":
                    query.addScalar(field.getName(), BigDecimalType.INSTANCE);
                    break;
                case "java.util.Date":
                    query.addScalar(field.getName(), TimestampType.INSTANCE);
                    break;
                }
            }
            return query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    /**
     * 按条件统计记录数
     * 
     * @param queryString
     * @return
     */
    public Long getCount(String queryString, Map<String, Object> values) {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            Query query = getSession().createQuery(queryString);

            if (values != null && !values.isEmpty()) {
                query.setProperties(values);
            }

            return ((Number) query.uniqueResult()).longValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 按条件使用原生sql统计记录数
     * 
     * @param queryString
     * @param values
     * @return
     */
    public Long getCountBySQL(String queryString, Map<String, Object> values) {
        try {
            Assert.hasText(queryString, "queryString must not null !");
            Query query = this.getSession().createSQLQuery(queryString);

            if (values != null && !values.isEmpty()) {
                query.setProperties(values);
            }
            return ((Number) query.uniqueResult()).longValue();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}