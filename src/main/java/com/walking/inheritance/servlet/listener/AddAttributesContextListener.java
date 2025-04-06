package com.walking.inheritance.servlet.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.walking.inheritance.constant.ContextAttributeNames;
import com.walking.inheritance.converter.CreatePersonRequestConverter;
import com.walking.inheritance.converter.PersonConverter;
import com.walking.inheritance.converter.UpdatePersonRequestConverter;
import com.walking.inheritance.repository.AdultPersonRepository;
import com.walking.inheritance.repository.ChildRepository;
import com.walking.inheritance.repository.PersonRepository;
import com.walking.inheritance.service.EntityManagerHelper;
import com.walking.inheritance.service.MigrationService;
import com.walking.inheritance.service.person.PersonFamilyService;
import com.walking.inheritance.service.person.PersonService;
import com.walking.inheritance.validator.CreatePersonRequestValidator;
import com.walking.inheritance.validator.UpdatePersonRequestValidator;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;

public class AddAttributesContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(AddAttributesContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("Запущена инициализация атрибутов глобального контекста");

        var servletContext = event.getServletContext();

        var scheduledExecutorService = Executors.newScheduledThreadPool(4);
        servletContext.setAttribute(ContextAttributeNames.SCHEDULED_EXECUTOR_SERVICE, scheduledExecutorService);

        var personConverter = new PersonConverter();
        servletContext.setAttribute(ContextAttributeNames.PERSON_CONVERTER, personConverter);

        var createPersonConverter = new CreatePersonRequestConverter();
        servletContext.setAttribute(ContextAttributeNames.CREATE_PERSON_REQUEST_CONVERTER, createPersonConverter);

        var updatePersonConverter = new UpdatePersonRequestConverter();
        servletContext.setAttribute(ContextAttributeNames.UPDATE_PERSON_REQUEST_CONVERTER, updatePersonConverter);

        var personRepository = new PersonRepository();
        servletContext.setAttribute(ContextAttributeNames.PERSON_REPOSITORY, personRepository);

        var adultPersonRepository = new AdultPersonRepository();
        servletContext.setAttribute(ContextAttributeNames.ADULT_PERSON_REPOSITORY, adultPersonRepository);

        var childRepository = new ChildRepository();
        servletContext.setAttribute(ContextAttributeNames.CHILD_REPOSITORY, childRepository);

        var entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate");
        servletContext.setAttribute(ContextAttributeNames.ENTITY_MANAGER_FACTORY, entityManagerFactory);

        var entityManagerHelper = new EntityManagerHelper(entityManagerFactory);
        servletContext.setAttribute(ContextAttributeNames.ENTITY_MANAGER_HELPER, entityManagerHelper);

        var personService = new PersonService(
                entityManagerHelper,
                personRepository,
                adultPersonRepository,
                childRepository
        );
        servletContext.setAttribute(ContextAttributeNames.PERSON_SERVICE, personService);

        var personFamilyService = new PersonFamilyService(
                entityManagerHelper,
                personRepository,
                childRepository,
                adultPersonRepository);
        servletContext.setAttribute(ContextAttributeNames.PERSON_FAMILY_SERVICE, personFamilyService);

        var dataSource = getDataSource(entityManagerFactory);
        var migrationService = new MigrationService(dataSource);
        servletContext.setAttribute(ContextAttributeNames.MIGRATION_SERVICE, migrationService);

        var objectMapper = getObjectMapper();
        servletContext.setAttribute(ContextAttributeNames.OBJECT_MAPPER, objectMapper);

        var createPersonValidator = new CreatePersonRequestValidator(entityManagerHelper);
        servletContext.setAttribute(ContextAttributeNames.CREATE_PERSON_REQUEST_VALIDATOR, createPersonValidator);

        var updatePersonValidator = new UpdatePersonRequestValidator(entityManagerHelper);
        servletContext.setAttribute(ContextAttributeNames.UPDATE_PERSON_REQUEST_VALIDATOR, updatePersonValidator);

        log.info("Завершена инициализация атрибутов глобального контекста");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        var entityManagerFactory = (EntityManagerFactory) sce.getServletContext()
                .getAttribute(ContextAttributeNames.ENTITY_MANAGER_FACTORY);

        entityManagerFactory.close();
    }

    private ObjectMapper getObjectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        return objectMapper;
    }

    //    Получение объекта датасорса в JPA - нетривиальная задача.
    //    Чтобы не создавать отдельный датасорс только ради миграций - придется повозиться
    private DataSource getDataSource(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactoryServiceRegistry.class)
                .getService(ConnectionProvider.class)
                .unwrap(DataSource.class);
    }
}
