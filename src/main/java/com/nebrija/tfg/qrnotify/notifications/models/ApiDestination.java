package com.nebrija.tfg.qrnotify.notifications.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ApiDestination
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-01T21:40:09.278631300+02:00[Europe/Paris]")
public class ApiDestination {
  @JsonProperty("company")
  private String company;

  @JsonProperty("area")
  private String area;

  @JsonProperty("store")
  private String store;

  public ApiDestination company(String company) {
    this.company = company;
    return this;
  }

  /**
   * Get company
   * @return company
  */
  @ApiModelProperty(example = "MERCADO", value = "")


  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public ApiDestination area(String area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  */
  @ApiModelProperty(example = "XXX", value = "")


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public ApiDestination store(String store) {
    this.store = store;
    return this;
  }

  /**
   * Get store
   * @return store
  */
  @ApiModelProperty(example = "store ID", value = "")


  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiDestination destination = (ApiDestination) o;
    return Objects.equals(this.company, destination.company) &&
        Objects.equals(this.area, destination.area) &&
        Objects.equals(this.store, destination.store);
  }

  @Override
  public int hashCode() {
    return Objects.hash(company, area, store);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiDestination {\n");
    
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    store: ").append(toIndentedString(store)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

