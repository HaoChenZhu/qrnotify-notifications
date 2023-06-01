package com.nebrija.tfg.qrnotify.notifications.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ApiUserResponseDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T17:15:55.819838+02:00[Europe/Paris]")
public class ApiUserResponseDto {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("phone_number")
  private String phoneNumber;

  @JsonProperty("created_date")
  private String createdDate;

  @JsonProperty("modificatied_date")
  private String modificatiedDate;

  @JsonProperty("deleted_date")
  private String deletedDate;

  public ApiUserResponseDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "123456789", value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ApiUserResponseDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "theUser", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApiUserResponseDto phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @ApiModelProperty(example = "12345", value = "")


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public ApiUserResponseDto createdDate(String createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
  */
  @ApiModelProperty(value = "")


  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public ApiUserResponseDto modificatiedDate(String modificatiedDate) {
    this.modificatiedDate = modificatiedDate;
    return this;
  }

  /**
   * Get modificatiedDate
   * @return modificatiedDate
  */
  @ApiModelProperty(value = "")


  public String getModificatiedDate() {
    return modificatiedDate;
  }

  public void setModificatiedDate(String modificatiedDate) {
    this.modificatiedDate = modificatiedDate;
  }

  public ApiUserResponseDto deletedDate(String deletedDate) {
    this.deletedDate = deletedDate;
    return this;
  }

  /**
   * Get deletedDate
   * @return deletedDate
  */
  @ApiModelProperty(value = "")


  public String getDeletedDate() {
    return deletedDate;
  }

  public void setDeletedDate(String deletedDate) {
    this.deletedDate = deletedDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiUserResponseDto userResponseDto = (ApiUserResponseDto) o;
    return Objects.equals(this.id, userResponseDto.id) &&
        Objects.equals(this.name, userResponseDto.name) &&
        Objects.equals(this.phoneNumber, userResponseDto.phoneNumber) &&
        Objects.equals(this.createdDate, userResponseDto.createdDate) &&
        Objects.equals(this.modificatiedDate, userResponseDto.modificatiedDate) &&
        Objects.equals(this.deletedDate, userResponseDto.deletedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phoneNumber, createdDate, modificatiedDate, deletedDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiUserResponseDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    modificatiedDate: ").append(toIndentedString(modificatiedDate)).append("\n");
    sb.append("    deletedDate: ").append(toIndentedString(deletedDate)).append("\n");
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

