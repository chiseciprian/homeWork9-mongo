package ro.fasttrackit.homeWork9.model;

import lombok.Value;

@Value
public class RoomsFilters {
    String number;
    Integer floor;
    String hotel;
    Boolean hasTv;
    Boolean hasDoubleBed;
}
