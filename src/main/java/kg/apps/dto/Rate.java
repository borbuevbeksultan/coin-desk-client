package kg.apps.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Rate {

    @SerializedName("code")
    private String code;

    @SerializedName("rate")
    private String rate;

    @SerializedName("description")
    private String description;

    @SerializedName("rate_float")
    private Double rateFloat;

}
