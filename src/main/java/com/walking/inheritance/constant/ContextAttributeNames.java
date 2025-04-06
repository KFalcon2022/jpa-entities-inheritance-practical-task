package com.walking.inheritance.constant;

public class ContextAttributeNames {
    public static final String PERSON_CONVERTER = "personConverter";
    public static final String CREATE_PERSON_REQUEST_CONVERTER = "createPersonRequestConverter";
    public static final String UPDATE_PERSON_REQUEST_CONVERTER = "updatePersonRequestConverter";

    public static final String PERSON_REPOSITORY = "personRepository";
    public static final String ADULT_PERSON_REPOSITORY = "adultPersonRepository";
    public static final String CHILD_REPOSITORY = "childRepository";

    public static final String PERSON_SERVICE = "personService";
    public static final String PERSON_FAMILY_SERVICE = "personFamilyService";

    public static final String CREATE_PERSON_REQUEST_VALIDATOR = "сreatePersonRequestValidator";
    public static final String UPDATE_PERSON_REQUEST_VALIDATOR = "updatePersonRequestValidator";

    public static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    public static final String ENTITY_MANAGER_HELPER = "entityManagerHelper";

    public static final String MIGRATION_SERVICE = "migrationService";

    public static final String OBJECT_MAPPER = "objectMapper";
    public static final String SCHEDULED_EXECUTOR_SERVICE = "scheduledExecutorService";

    private ContextAttributeNames() {
    }
}
