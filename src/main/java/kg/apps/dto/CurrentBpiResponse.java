package kg.apps.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CurrentBpiResponse {
    private Map<String, Rate> bpi;
}
