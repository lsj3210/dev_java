package com.example.openapi.services.impl;

import com.example.openapi.bean.IdOperatorEntity;
import com.example.openapi.services.DBaseService;
import com.example.openapi.utils.page.PageParam;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public abstract class DBaseServiceImpl<T> implements DBaseService<T> {
    private int batchSize = 1000;

    @Autowired
    @PersistenceContext
    public EntityManager entityManager;

    public abstract JpaRepository<T, Long> getBaseDao();

    @Override
    public T saveOrUpdate(T t) {
        return getBaseDao().save(t);
    }

    @Override
    public List<T> saveOrUpdate(Iterable<T> entities) {
        return (List<T>) getBaseDao().saveAll(entities);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) getBaseDao().findAll();
    }

    @Override
    public void delete(T t) {
        getBaseDao().delete(t);
    }

    @Override
    public void delete(Long id) {
        getBaseDao().deleteById(id);
    }

    @Override
    public T findOne(Long id) {
        Optional<T> tmp = getBaseDao().findById(id);
        return tmp.get();
    }

    @Override
    public List<T> createQuery(String hql, Class<T> c) {
        //拦截注入sql，发现有拦截注入可能则不执行sql
        if (!Strings.isNullOrEmpty(hql) && hql.indexOf("and 1=2") > 0) {
            return new ArrayList<T>();
        }
        return entityManager.createQuery(hql, c).getResultList();
    }

    @Override
    public PageParam<T> createQuery(String hql, PageParam<T> param, Class<T> c) {

        PageParam<T> result = sqlIntercept(hql, param);
        if (result != null) {
            return result;
        }

        TypedQuery<T> query = entityManager.createQuery(hql, c);

        int total = query.getResultList().size();
        query.setFirstResult(param.getFirstResult());
        query.setMaxResults(param.getPageSize());
        List<T> list = query.getResultList();
        param.setFirstResult(param.getFirstResult());
        param.setTotal(total);
        param.setResults(list);
        return param;
    }

    // 原生分页sql调用
    @Override
    public PageParam<T> createNativeQuery(String sql, PageParam<T> param) {

        PageParam<T> result = sqlIntercept(sql, param);
        if (result != null) {
            return result;
        }

        Query query = entityManager.createNativeQuery(sql);

        int total = query.getResultList().size();
        query.setFirstResult(param.getFirstResult());
        query.setMaxResults(param.getPageSize());
        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();
        param.setFirstResult(param.getFirstResult());
        param.setTotal(total);
        param.setResults(list);
        return param;
    }

    // 原生分页sql调用
    @Override
    public PageParam<T> createNativeQuery(String sql, PageParam<T> param, Class resultClass) {

        PageParam<T> result = sqlIntercept(sql, param);
        if (result != null) {
            return result;
        }
        Query query = entityManager.createNativeQuery(sql, resultClass);
        int total = query.getResultList().size();
        query.setFirstResult(param.getFirstResult());
        query.setMaxResults(param.getPageSize());
        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();
        param.setFirstResult(param.getFirstResult());
        param.setTotal(total);
        param.setResults(list);
        return param;
    }

    @Override
    public PageParam<T> createCustomNativeQuery(String sql, PageParam<T> param, Class<?> resultClass) {

        PageParam<T> result = sqlIntercept(sql, param);
        if (result != null) {
            return result;
        }
        Query query = entityManager.createNativeQuery(sql, resultClass);
        int total = query.getResultList().size();
        query.setFirstResult(param.getFirstResult());
        query.setMaxResults(param.getPageSize());
        List<T> list = query.getResultList();
        param.setFirstResult(param.getFirstResult());
        param.setTotal(total);
        param.setResults(list);
        return param;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public <T extends IdOperatorEntity> Collection<T> bulkSave(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int i = 0;
        for (T t : entities) {
            savedEntities.add(persistOrMerge(t));
            i++;
            if (i % batchSize == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
        return savedEntities;
    }

    private <T extends IdOperatorEntity> T persistOrMerge(T t) {
        if (t.getId() == null) {
            entityManager.persist(t);
            return t;
        } else {
            return entityManager.merge(t);
        }
    }

    /**
     * @param @param  sql
     * @param @param  pageparam
     * @param @return 设定文件
     * @return Pageparam<T>    返回类型
     * @throws
     * @Title: sql_intercept
     * @Description: 拦截注入sql，发现有拦截注入可能则不执行sql
     */
    private PageParam<T> sqlIntercept(String sql, PageParam<T> param) {
        if (!Strings.isNullOrEmpty(sql) && sql.indexOf("and 1=2") > 0) {
            param = new PageParam<T>();
            param.setFirstResult(0);
            param.setTotal(0);
            param.setResults(new ArrayList());
            return param;
        }
        return null;

    }

    public PageParam createQuery(String hql, PageParam param) {
        Query query = entityManager.createQuery(hql);

        int total = query.getResultList().size();
        query.setFirstResult(param.getFirstResult());
        query.setMaxResults(param.getPageSize());
        List<T> list = query.getResultList();
        param.setFirstResult(param.getFirstResult());
        param.setTotal(total);
        param.setResults(list);
        return param;
    }
}
