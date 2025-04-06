package com.walking.inheritance.servlet;

import com.walking.inheritance.constant.ContextAttributeNames;
import com.walking.inheritance.converter.PersonConverter;
import com.walking.inheritance.domain.PersonCategory;
import com.walking.inheritance.service.person.PersonService;
import com.walking.inheritance.servlet.filter.ResponseJsonSerializerFilter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class PersonGetAllServlet extends HttpServlet {
    private PersonService personService;

    private PersonConverter personConverter;

    @Override
    public void init(ServletConfig config) {
        var servletContext = config.getServletContext();

        personService = (PersonService) servletContext.getAttribute(ContextAttributeNames.PERSON_SERVICE);
        personConverter = (PersonConverter) servletContext.getAttribute(ContextAttributeNames.PERSON_CONVERTER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var category = Optional.of(request)
                .map(it -> it.getParameter("category"))
                .map(PersonCategory::valueOf)
                .orElse(null);

        var persons = personService.getAll(category);
        var result = personConverter.convert(persons);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, result);
    }
}
