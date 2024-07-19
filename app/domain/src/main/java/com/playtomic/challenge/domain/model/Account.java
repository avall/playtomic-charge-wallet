package com.playtomic.challenge.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record Account(UUID id,
                      @NonNull String firstName,
                      @NonNull String lastName,
                      String alias,
                      @NonNull String email,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {}