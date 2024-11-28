package com.example.Attendance.controller;

import com.example.Attendance.model.President;
import com.example.Attendance.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(value = "/notification/commute/{presidentId}", produces = "text/event-stream")
    public SseEmitter streamNotifications(@PathVariable Integer presidentId) {
        return notificationService.createEmitter(presidentId);
    }

}
