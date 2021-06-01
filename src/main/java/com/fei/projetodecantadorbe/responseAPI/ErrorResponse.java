package com.fei.projetodecantadorbe.responseAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Data
@Validated
public class ErrorResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("nativeMessage")
    private String nativeMessage;

    @JsonProperty("trackid")
    private String trackid;

    public ErrorResponse() {
    }

    public ErrorResponse(String code, String message, String nativeMessage) {
        this.code = code;
        this.message = message;
        this.nativeMessage = nativeMessage;
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse code(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse nativeMessage(String nativeMessage) {
        this.nativeMessage = nativeMessage;
        return this;
    }

    public String getNativeMessage() {
        return nativeMessage;
    }

    public void setNativeMessage(String nativeMessage) {
        this.nativeMessage = nativeMessage;
    }

    public ErrorResponse trackid(String trackid) {
        this.trackid = trackid;
        return this;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse error = (ErrorResponse) o;
        return Objects.equals(this.code, error.code) && Objects.equals(this.message, error.message)
                && Objects.equals(this.nativeMessage, error.nativeMessage)
                && Objects.equals(this.trackid, error.trackid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, nativeMessage, trackid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");

        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    nativeMessage: ").append(toIndentedString(nativeMessage)).append("\n");
        sb.append("    trackid: ").append(toIndentedString(trackid)).append("\n");
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

    public void setCode(int code) {
        this.code = String.valueOf(code);
    }

}