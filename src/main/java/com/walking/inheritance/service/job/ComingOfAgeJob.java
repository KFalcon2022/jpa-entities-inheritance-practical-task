package com.walking.inheritance.service.job;

import com.walking.inheritance.domain.AdultPerson;
import com.walking.inheritance.domain.Child;
import com.walking.inheritance.repository.ChildRepository;
import com.walking.inheritance.service.EntityManagerHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ComingOfAgeJob {
    private static final Logger log = LogManager.getLogger(ComingOfAgeJob.class);

    private final EntityManagerHelper entityManagerHelper;

    private final ChildRepository childRepository;

    private final ScheduledExecutorService scheduler;

    public ComingOfAgeJob(EntityManagerHelper entityManagerHelper, ChildRepository childRepository,
                          ScheduledExecutorService scheduler) {
        this.entityManagerHelper = entityManagerHelper;
        this.childRepository = childRepository;
        this.scheduler = scheduler;
    }

    /**
     * Наивная реализация. Не учитывает как минимум потенциально большое число обрабатываемый сущностей,
     * что может привести с таймауту соединения и другим проблемам
     */
    public void schedule() {
        scheduler.scheduleAtFixedRate(getTask(), 0, 1, TimeUnit.DAYS);
    }

    private Runnable getTask() {

        var birthDate = LocalDate.now().minusYears(18);

        return () -> entityManagerHelper.runTransactionalNoResult(em -> {
            log.info("Запуск перевода Child в adultPerson");

            var children = childRepository.findAllByBirthDateLessThanEqual(birthDate, em);

            log.info("Обнаружено {} объектов Child, достигших совершеннолетия", children.size());

            children.stream()
                    .peek(em::remove)
                    .map(this::buildByChild)
                    .forEach(em::persist);

            log.info("Завершение перевода Child в adultPerson");
        });
    }

    private AdultPerson buildByChild(Child child) {
        var adult = new AdultPerson();

        adult.setFirstName(child.getFirstName());
        adult.setLastName(child.getLastName());
        adult.setBirthDate(child.getBirthDate());
        adult.setGender(child.getGender());
        adult.setAddress(child.getAddress());
        adult.setMonthlyIncome(0d);

        return adult;
    }
}
