package com.walking.inheritance.servlet.listener;

import com.walking.inheritance.constant.ContextAttributeNames;
import com.walking.inheritance.repository.ChildRepository;
import com.walking.inheritance.service.EntityManagerHelper;
import com.walking.inheritance.service.job.ComingOfAgeJob;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.concurrent.ScheduledExecutorService;

public class StartComingOfAgeJobContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var servletContext = sce.getServletContext();

        var entityManagerHelper = (EntityManagerHelper) servletContext
                .getAttribute(ContextAttributeNames.ENTITY_MANAGER_HELPER);

        var childRepository = (ChildRepository) servletContext.getAttribute(ContextAttributeNames.CHILD_REPOSITORY);

        var scheduledExecutorService = (ScheduledExecutorService) servletContext
                .getAttribute(ContextAttributeNames.SCHEDULED_EXECUTOR_SERVICE);

        new ComingOfAgeJob(entityManagerHelper, childRepository, scheduledExecutorService)
                .schedule();
    }
}
