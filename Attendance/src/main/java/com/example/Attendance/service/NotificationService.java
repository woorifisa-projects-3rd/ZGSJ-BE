package com.example.Attendance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final Map<Integer, List<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(Integer presidentId) {
        log.info("Create emitter {}", presidentId);
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitters.computeIfAbsent(presidentId, k -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> {
            emitters.get(presidentId).remove(emitter);
            log.info("Remove emitter {}", presidentId);
        });
        emitter.onTimeout(() -> {
            emitters.get(presidentId).remove(emitter);
            log.info("Timed out emitter {}", presidentId);
        });
        emitter.onError((e) -> {
            emitters.get(presidentId).remove(emitter);
            log.info("Error emitter {}", presidentId);
        });

        return emitter;
    }

    public void sendNotification(Integer presidentId, String message) {
        List<SseEmitter> emitterList = emitters.get(presidentId);

        if (emitterList != null) {
            emitterList.forEach(emitter -> {
                try {
                    emitter.send(SseEmitter.event().name("notification").data(message));
                    log.info("알림 전공 성공: 사장님 ID {}, 메시지: {}", presidentId, message);
                } catch (Exception e) {
                    emitterList.remove(emitter);
                }
            });
        } else {
            log.info("알림 전송 실패: 사장님 ID {}에 연결된 SSE 없음", presidentId);
        }
    }
}
