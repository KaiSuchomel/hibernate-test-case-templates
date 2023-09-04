package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM,
 * using the Java Persistence API.
 */
public class JPAUnitTestCase {
    
    private EntityManagerFactory entityManagerFactory;
    
    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }
    
    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void hhh123Test() throws Exception {
        // Do stuff...
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            // Do stuff...
            String name = "Name";
            UUID plantId = UUID.randomUUID();
            entityManager.getTransaction().begin();
            MyEntity entity = new MyEntity()
                    .setName(name)
                    .setPlantId(plantId)
                    .setDescription(new Translation().setDe("German"));
            entityManager.persist(entity);
            assertFalse(entity.getHasPictures());
            entityManager.getTransaction().commit();
            //
            entityManager.getTransaction().begin();
            int updatedRows = entityManager.createQuery("UPDATE MyEntity u SET u.hasPictures = :att WHERE u.id = :entityId")
                    .setParameter("att", Boolean.TRUE)
                    .setParameter("entityId", entity.getId())
                    .executeUpdate();
            assertTrue(updatedRows > 0);
            entityManager.getTransaction().commit();
            //
            entityManager.getTransaction().begin();
            entityManager.clear();
            List<MyEntity> resList = entityManager.createQuery("select x from MyEntity x", MyEntity.class).getResultList();
            assertTrue(resList.size() == 1);
            MyEntity entityOnDB = entityManager.find(MyEntity.class, new MyEntityPK().setName(name).setPlantId(plantId));
            assertTrue(entityOnDB.getHasPictures());
            entityManager.getTransaction().commit();
            //
        }
    }
}
