
package com.example.stocktracker.Response;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.example.stocktracker.POJO.TrendingStockResponse.Finance;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "finance"
})
@Generated("jsonschema2pojo")
public class TrendingStockResponse {

    @JsonProperty("finance")
    private Finance finance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("finance")
    public Finance getFinance() {
        return finance;
    }

    @JsonProperty("finance")
    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
