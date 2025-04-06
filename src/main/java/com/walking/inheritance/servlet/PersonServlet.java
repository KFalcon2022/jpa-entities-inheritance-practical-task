package com.walking.inheritance.servlet;

import com.walking.inheritance.constant.ContextAttributeNames;
import com.walking.inheritance.converter.CreatePersonRequestConverter;
import com.walking.inheritance.converter.PersonConverter;
import com.walking.inheritance.converter.UpdatePersonRequestConverter;
import com.walking.inheritance.model.dto.CreatePersonRequest;
import com.walking.inheritance.model.dto.UpdatePersonRequest;
import com.walking.inheritance.service.person.PersonService;
import com.walking.inheritance.servlet.filter.RequestJsonDeserializerFilter;
import com.walking.inheritance.servlet.filter.ResponseJsonSerializerFilter;
import com.walking.inheritance.validator.CreatePersonRequestValidator;
import com.walking.inheritance.validator.UpdatePersonRequestValidator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public class PersonServlet extends HttpServlet {

    private CreatePersonRequestValidator createPersonValidator;
    private UpdatePersonRequestValidator updatePersonValidator;

    private PersonService personService;

    private PersonConverter personConverter;
    private CreatePersonRequestConverter createPersonConverter;
    private UpdatePersonRequestConverter updatePersonConverter;

    @Override
    public void init(ServletConfig config) {
        var servletContext = config.getServletContext();

        createPersonValidator = (CreatePersonRequestValidator) servletContext.getAttribute(
                ContextAttributeNames.CREATE_PERSON_REQUEST_VALIDATOR);
        updatePersonValidator = (UpdatePersonRequestValidator) servletContext.getAttribute(
                ContextAttributeNames.UPDATE_PERSON_REQUEST_VALIDATOR);

        personService = (PersonService) servletContext.getAttribute(ContextAttributeNames.PERSON_SERVICE);

        personConverter = (PersonConverter) servletContext.getAttribute(ContextAttributeNames.PERSON_CONVERTER);
        createPersonConverter = (CreatePersonRequestConverter) servletContext.getAttribute(
                ContextAttributeNames.CREATE_PERSON_REQUEST_CONVERTER);
        updatePersonConverter = (UpdatePersonRequestConverter) servletContext.getAttribute(
                ContextAttributeNames.UPDATE_PERSON_REQUEST_CONVERTER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var id = UUID.fromString(request.getParameter("id"));

        var person = personService.getById(id);
        var result = personConverter.convert(person);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var createRequest = (CreatePersonRequest) request.getAttribute(RequestJsonDeserializerFilter.POJO_REQUEST_BODY);
        createPersonValidator.validate(createRequest);

        var person = createPersonConverter.convert(createRequest);
        var result = personService.create(person);
        var personDto = personConverter.convert(result);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, personDto);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        var updateRequest = (UpdatePersonRequest) request.getAttribute(RequestJsonDeserializerFilter.POJO_REQUEST_BODY);
        updatePersonValidator.validate(updateRequest);

        var updatePersonItem = updatePersonConverter.convert(updateRequest);
        var result = personService.update(updatePersonItem);
        var personDto = personConverter.convert(result);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, personDto);
    }
}
