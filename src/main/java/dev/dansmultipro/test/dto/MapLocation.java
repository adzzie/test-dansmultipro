package dev.dansmultipro.test.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MapLocation {
    private String location;
    private List<Posistions> data;
}
