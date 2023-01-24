package no.eg.nexstephub.server.demoserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Simple model object
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto {
    private String message;

    public MessageDto() {
    }

    public MessageDto(String message) {
        this.message = message;
    }

    @SuppressWarnings("unused")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
