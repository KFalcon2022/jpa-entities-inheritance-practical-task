package com.walking.inheritance.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public class EntityManagerHelper {

    private final EntityManagerFactory entityManagerFactory;

    public EntityManagerHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void runTransactionalNoResult(Consumer<EntityManager> task) {
        Function<EntityManager, Object> noResultTask = em -> {
            task.accept(em);
            return null;
        };

        runTransactional(noResultTask);
    }

    public <T> T runTransactional(Function<EntityManager, T> task) {
        try (var em = entityManagerFactory.createEntityManager()) {
            try {
                var transaction = em.getTransaction();
                transaction.begin();

                T result = task.apply(em);

                transaction.commit();

                return result;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }

        } catch (Exception e) {
            if (e instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }

            throw new RuntimeException("Ошибка при обработке транзакции", e);
        }
    }

//    JPA не потокобезопасен.
//    Соответственно, если контейнер не берет задачу контроля за созданием EM на себя - лучше перестраховаться
    public synchronized EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
