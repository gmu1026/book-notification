package net.cproduction.booknotification.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDetail {
    private HttpStatus status;
    private String detail;

    public ErrorDetail(HttpStatus status, String detail) {
        this.status = status;
        this.detail = detail;
    }
}
