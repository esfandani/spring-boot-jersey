package com.esfandani.labs.reminder.dtos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.esfandani.labs.reminder.entities.Status;

public class SearchCriteria {
    private final static LocalDateTime past = LocalDateTime.now().minusYears(200);

    private LocalDateTime from;
    private LocalDateTime to;
    private Status status;

    private SearchCriteria(SearchCriteriaBuilder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.status = builder.status;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public Optional<Status> getStatus() {
        return Optional.ofNullable(status);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class SearchCriteriaBuilder {
        private LocalDateTime from;
        private LocalDateTime to;
        private Status status;

        public SearchCriteriaBuilder setFrom(LocalDateTime from) {
            this.from = from;
            return this;
        }

        public SearchCriteriaBuilder setTo(LocalDateTime to) {
            this.to = to;
            return this;
        }

        public SearchCriteriaBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public SearchCriteria build() {
            if (this.from == null) {
                this.from = past;
            }
            if (this.to == null) {
                this.to = LocalDateTime.now();
            }
            this.from.truncatedTo(ChronoUnit.MINUTES);
            this.to.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);
            return new SearchCriteria(this);
        }
    }
}
