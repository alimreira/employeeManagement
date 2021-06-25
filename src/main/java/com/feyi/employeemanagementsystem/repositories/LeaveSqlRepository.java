package com.feyi.employeemanagementsystem.repositories;

import com.feyi.employeemanagementsystem.models.Leave;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LeaveSqlRepository{

    @PersistenceContext
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<Leave> getAllLeavesOnStatus(StringBuffer whereQuery) {

        Query query = entityManager.createNativeQuery("select * from leave_details where " + whereQuery,
                Leave.class);

        return query.getResultList();
    }
}