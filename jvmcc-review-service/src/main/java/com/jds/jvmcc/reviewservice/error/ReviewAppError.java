package com.jds.jvmcc.reviewservice.error;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-21
 */
public class ReviewAppError {

    private final String apiVersion;
    private final ErrorBlock error;

    public ReviewAppError(final String apiVersion, final String code, final String message, final String domain,
                             final String reason, final String errorMessage) {
        this.apiVersion = apiVersion;
        this.error = new ErrorBlock(code, message, domain, reason, errorMessage);
    }

    public static ReviewAppError fromDefaultAttributeMap(final String apiVersion,
                                                            final Map<String, Object> defaultErrorAttributes) {
        // original attribute values are documented in org.springframework.boot.web.servlet.error.DefaultErrorAttributes
        return new ReviewAppError(
                apiVersion,
                ((Integer) defaultErrorAttributes.get("status")).toString(),
                (String) defaultErrorAttributes.getOrDefault("message", "no message available"),
                (String) defaultErrorAttributes.getOrDefault("path", "no domain available"),
                (String) defaultErrorAttributes.getOrDefault("error", "no reason available"),
                (String) defaultErrorAttributes.get("message")
        );
    }

    public Map<String, Object> toAttributeMap() {
        return Map.of(
          "apiVersion", apiVersion,
          "error", error
        );
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public ErrorBlock getError() {
        return error;
    }

    private static final class ErrorBlock {

        @JsonIgnore
        private final UUID uniqueId;
        private final String code;
        private final String message;
        private final List<Error> errors;

        public ErrorBlock(final String code, final String message, final String domain,
                          final String reason, final String errorMessage) {
            this.code = code;
            this.message = message;
            this.uniqueId = UUID.randomUUID();
            this.errors = List.of(
                    new Error(domain, reason, errorMessage)
            );
        }

        private ErrorBlock(final UUID uniqueId, final String code, final String message, final List<Error> errors) {
            this.uniqueId = uniqueId;
            this.code = code;
            this.message = message;
            this.errors = errors;
        }

        public static ErrorBlock copyWithMessage(final ErrorBlock s, final String message) {
            return new ErrorBlock(s.uniqueId, s.code, message, s.errors);
        }

        public UUID getUniqueId() {
            return uniqueId;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<Error> getErrors() {
            return errors;
        }

    }

    private static final class Error {
        private final String domain;
        private final String reason;
        private final String message;

        public Error(final String domain, final String reason, final String message) {
            this.domain = domain;
            this.reason = reason;
            this.message = message;
        }

        public String getDomain() {
            return domain;
        }

        public String getReason() {
            return reason;
        }

        public String getMessage() {
            return message;
        }
    }
}