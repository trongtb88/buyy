/**
 *
 */
package com.dragon88.microservices.invoice.dto;

import com.fasterxml.jackson.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hitjoshi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "itemId",
        "quantity",
        "taxAmount",
        "unitCost"
})
public class Item {

    @JsonProperty("itemId")
    private Long itemId;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("taxAmount")
    private BigInteger taxAmount;
    @JsonProperty(value = "unitCost", required = true)
    private BigInteger unitCost;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("itemId")
    public Long getItemId() {
        return itemId;
    }

    @JsonProperty("itemId")
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("taxAmount")
    public BigInteger getTaxAmount() {
        return taxAmount;
    }

    @JsonProperty("taxAmount")
    public void setTaxAmount(BigInteger taxAmount) {
        this.taxAmount = taxAmount;
    }

    @JsonProperty("unitCost")
    public BigInteger getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unitCost")
    public void setUnitCost(BigInteger unitCost) {
        this.unitCost = unitCost;
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
