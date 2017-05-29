package com.purchase.manager.service;

import com.purchase.manager.entity.CostsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;


public class CostsService {

    protected EntityManager em;

    public CostsService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        em = emf.createEntityManager();
    }

    public CostsEntity save(CostsEntity costsEntity) {
        em.getTransaction().begin();
        em.persist(costsEntity);
        em.getTransaction().commit();
        return costsEntity;
    }

    public void deleteCosts(Date date) {
        Query query = em.createQuery(
        "DELETE FROM CostsEntity c WHERE c.date=:date");
        query.setParameter("date", date);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }

    public List<CostsEntity> findAllCosts() {
        Query query = em.createQuery("SELECT c FROM CostsEntity c WHERE c.date=c.date  ORDER BY c.date");
        return (List<CostsEntity>)query.getResultList();
    }

}
