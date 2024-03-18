package com.project.sportsManagement.views;

import com.project.sportsManagement.entity.Event;
import com.project.sportsManagement.entity.Student;
import com.project.sportsManagement.service.AuthenticationService;
import com.project.sportsManagement.service.EventService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;

@Route(value = "my-events",layout = MainLayout.class)
@PermitAll
public class ParticipatedEventView extends VerticalLayout {

    Span headerText = new Span("Find the list of events you have participated below.");
    Grid<Event> participatedEventsGrid = new Grid<>(Event.class);

    H2 noParticipatedEvents = new H2("Sorry, You have currently not Participated in any events.");


    EventService eventService;


    AuthenticationService authenticationService;

    @Autowired
    public ParticipatedEventView(AuthenticationService authenticationService, EventService eventService) {
        this.authenticationService = authenticationService;
        this.eventService = eventService;
        if (!eventService.getParticipatedEvents(getCurrentUser()).isEmpty()){
            configureGrid();
            add(headerText,participatedEventsGrid);
        }else {
            add(headerText,noParticipatedEvents);
        }


    }

    private void configureGrid() {
        participatedEventsGrid.setColumns("eventId","eventName");
        participatedEventsGrid.addColumn(event -> event.getHost().getInstitutionName()).setHeader("Host");
        participatedEventsGrid.addColumn(event -> event.getLevel().getLevel()).setHeader("Level");
        participatedEventsGrid.addColumn(new ComponentRenderer<>(event -> {
            String status = determineStatus(event.getStartTime(), event.getEndTime());
            Span statusBadge = new Span(status);
            if ("Live".equals(status)) {
                statusBadge.getElement().getThemeList().add("badge success");
            } else if ("Past".equals(status)) {
                statusBadge.getElement().getThemeList().add("badge error");
            } else {
                statusBadge.getElement().getThemeList().add("badge primary");
            }
            return statusBadge;
        })).setHeader("Status").setAutoWidth(true);
        participatedEventsGrid.getColumns().forEach(column -> column.setAutoWidth(true));
        participatedEventsGrid.setItems(eventService.getParticipatedEvents(getCurrentUser()));

    }

    private String determineStatus(Timestamp startTime, Timestamp endTime) {
        Instant currentInstant = Instant.now();
        if (startTime.toInstant().isBefore(currentInstant) && endTime.toInstant().isBefore(currentInstant)){
            return "Past";
        }else if(startTime.toInstant().isBefore(currentInstant) && endTime.toInstant().isAfter(currentInstant)){
            return "Live";
        }else {
            return "Upcoming";
        }
    }

    private Student getCurrentUser() {
        return (Student)authenticationService.getAuthenticatedUser();
    }
}
