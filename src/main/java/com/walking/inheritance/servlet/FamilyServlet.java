package com.walking.inheritance.servlet;

import com.walking.inheritance.constant.ContextAttributeNames;
import com.walking.inheritance.converter.PersonConverter;
import com.walking.inheritance.service.person.PersonFamilyService;
import com.walking.inheritance.servlet.filter.ResponseJsonSerializerFilter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public class FamilyServlet extends HttpServlet {
    private PersonFamilyService personFamilyService;

    private PersonConverter personConverter;

    @Override
    public void init(ServletConfig config) {
        var servletContext = config.getServletContext();

        personFamilyService = (PersonFamilyService) servletContext.getAttribute(
                ContextAttributeNames.PERSON_FAMILY_SERVICE);

        personConverter = (PersonConverter) servletContext.getAttribute(ContextAttributeNames.PERSON_CONVERTER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var personId = UUID.fromString(request.getParameter("personId"));

        var family = personFamilyService.getByMemberId(personId);
        var result = personConverter.convert(family);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, result);
    }
}
