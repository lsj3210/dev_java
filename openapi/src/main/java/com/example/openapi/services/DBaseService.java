package com.example.openapi.services;

import com.example.openapi.bean.IdOperatorEntity;
import com.example.openapi.utils.page.PageParam;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public interface DBaseService<T> {
    public T saveOrUpdate(T t);

    public List<T> saveOrUpdate(Iterable<T> entities);

    public List<T> findAll();

    public void delete(Long id);

    public void delete(T t);

    public T findOne(Long id);

    public List<T> createQuery(String hql, Class<T> c);

    public PageParam<T> createQuery(String hql, PageParam<T> pageparam, Class<T> c);

    public PageParam<T> createNativeQuery(String sql, PageParam<T> pageparam);

    public PageParam<T> createNativeQuery(String sql, PageParam<T> pageparam, Class resultClass);

    public PageParam<T> createCustomNativeQuery(String sql, PageParam<T> pageparam, Class<?> resultClass);

    public EntityManager getEntityManager();

    public <T extends IdOperatorEntity> Collection<T> bulkSave(Collection<T> entities);

    public PageParam createQuery(String hql, PageParam pageparam);
}
