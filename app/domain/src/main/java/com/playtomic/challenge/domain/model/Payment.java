package com.playtomic.challenge.domain.model;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record Payment(@NonNull String id) {}
