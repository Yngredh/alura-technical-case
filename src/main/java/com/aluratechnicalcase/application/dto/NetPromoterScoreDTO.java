package com.aluratechnicalcase.application.dto;

import lombok.Getter;

@Getter
public class NetPromoterScoreDTO {
    private String courseName;
    private Integer score;
    private String message;

    public NetPromoterScoreDTO(String courseName, Integer score) {
        this.courseName = courseName;
        this.score = score;
        this.message = getMessageByScore();
    }

    private String getMessageByScore() {
        if (score > 75) return "Excelente";
        if (score >= 47 && score <= 50) return "Muito bom";
        if (score >= 0 && score <= 49) return "Razoável";
        if (score >= -100 && score <= -1) return "Ruim";
        return  "";
    }

}
