package com.fei.projetodecantadorbe.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Type Error
 */
@Validated
public class DomainData {
    @JsonProperty("Type Error Code")
    private String code = null;

    @JsonProperty("Type Error Description")
    private String description = null;

    public DomainData id(String code) {
        this.code = code;
        return this;
    }

    /**
     * Code Error
     *
     * @return id
     **/
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DomainData description(String description) {
        this.description = description;
        return this;
    }

    /**
     * erro | warning | info |
     *
     * @return description
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DomainData other = (DomainData) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Type [code=" + code + ", description=" + description + "]";
    }

}
