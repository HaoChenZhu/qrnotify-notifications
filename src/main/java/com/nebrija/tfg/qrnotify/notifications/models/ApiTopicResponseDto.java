package com.nebrija.tfg.qrnotify.topic.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.nebrija.tfg.qrnotify.notifications.models.ApiDestination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ApiTopicResponseDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-23T15:31:32.086234600+02:00[Europe/Paris]")
public class ApiTopicResponseDto   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("publish_name")
  private String publishName;

  @JsonProperty("destinations")
  private ApiDestination destinations;

  @JsonProperty("owner")
  private String owner;

  @JsonProperty("created_date")
  private String createdDate;

  @JsonProperty("modificated_date")
  private String modificatedDate;

  @JsonProperty("deleted_date")
  private String deletedDate;

  @JsonProperty("created_by")
  private String createdBy;

  public ApiTopicResponseDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @ApiModelProperty(example = "13214", value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ApiTopicResponseDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @ApiModelProperty(example = "Fruteria", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApiTopicResponseDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @ApiModelProperty(example = "Fruteria de XXX", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ApiTopicResponseDto publishName(String publishName) {
    this.publishName = publishName;
    return this;
  }

  /**
   * Get publishName
   * @return publishName
   */
  @ApiModelProperty(example = "MERCADO/XXX/FRUTERIA", value = "")


  public String getPublishName() {
    return publishName;
  }

  public void setPublishName(String publishName) {
    this.publishName = publishName;
  }

  public ApiTopicResponseDto destinations(ApiDestination destinations) {
    this.destinations = destinations;
    return this;
  }

  /**
   * Get destinations
   * @return destinations
   */
  @ApiModelProperty(value = "")

  @Valid

  public ApiDestination getDestinations() {
    return destinations;
  }

  public void setDestinations(ApiDestination destinations) {
    this.destinations = destinations;
  }

  public ApiTopicResponseDto owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   */
  @ApiModelProperty(example = "admin", value = "")


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public ApiTopicResponseDto createdDate(String createdDate) {
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

  public ApiTopicResponseDto modificatedDate(String modificatedDate) {
    this.modificatedDate = modificatedDate;
    return this;
  }

  /**
   * Get modificatedDate
   * @return modificatedDate
   */
  @ApiModelProperty(value = "")


  public String getModificatedDate() {
    return modificatedDate;
  }

  public void setModificatedDate(String modificatedDate) {
    this.modificatedDate = modificatedDate;
  }

  public ApiTopicResponseDto deletedDate(String deletedDate) {
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

  public ApiTopicResponseDto createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  /**
   * Get createdBy
   * @return createdBy
   */
  @ApiModelProperty(value = "")


  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiTopicResponseDto topicResponseDto = (ApiTopicResponseDto) o;
    return Objects.equals(this.id, topicResponseDto.id) &&
            Objects.equals(this.name, topicResponseDto.name) &&
            Objects.equals(this.description, topicResponseDto.description) &&
            Objects.equals(this.publishName, topicResponseDto.publishName) &&
            Objects.equals(this.destinations, topicResponseDto.destinations) &&
            Objects.equals(this.owner, topicResponseDto.owner) &&
            Objects.equals(this.createdDate, topicResponseDto.createdDate) &&
            Objects.equals(this.modificatedDate, topicResponseDto.modificatedDate) &&
            Objects.equals(this.deletedDate, topicResponseDto.deletedDate) &&
            Objects.equals(this.createdBy, topicResponseDto.createdBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, publishName, destinations, owner, createdDate, modificatedDate, deletedDate, createdBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiTopicResponseDto {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    publishName: ").append(toIndentedString(publishName)).append("\n");
    sb.append("    destinations: ").append(toIndentedString(destinations)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    modificatedDate: ").append(toIndentedString(modificatedDate)).append("\n");
    sb.append("    deletedDate: ").append(toIndentedString(deletedDate)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
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

