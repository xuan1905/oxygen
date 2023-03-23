package com.spring.oxygen.pool.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.oxygen.pool.domain.Pool;

@Repository
public class ResourceRepository {
    @PersistenceContext
    EntityManager entityManager;

    
    @Transactional
    public void savePool(Pool pool) {
        entityManager.persist(pool);
    }
    
    public int getPoolDetail(int id) {
        return 1;
    }
    
    public Pool getPool(int id) {
    	return entityManager.find(Pool.class, id);
    }
    
    @Transactional
    public void updatePool(int id, int[] values) {
    	Pool p = entityManager.find(Pool.class, id);
    	p.setValues(values);
    	entityManager.flush();
    }
}
