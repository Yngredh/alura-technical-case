package com.aluratechnicalcase.infrastructure.service;

import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.entity.User;

public class EmailSender {
    public static void send(String recipientEmail, String subject, String body) {
        System.out.println(
                "Simulating sending email to [%s]:\n".formatted(recipientEmail)
        );
        System.out.println("""
                Subject: %s
                Body: %s
                """.formatted(subject, body));
    }

    public static void sendEmailWhenAvaliationValueIsUnderSix(Course course, User apprentice, User instructor, String message) {
        StringBuilder subject = new StringBuilder("Aviso! Um de seus cursos recebeu uma avaliação negativa :c");
        StringBuilder body = new StringBuilder(String.format("""
                O curso %s acabou de receber uma nova avaliação:
                	%s: "%s"
            """, course.getCode(), apprentice.getUsername(), message));

        EmailSender.send(instructor.getEmail(), subject.toString(), body.toString());
    }
}
