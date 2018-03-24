package com.ey.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONDocument {
    @JsonProperty("sessionInfo")
    public SessionInfo sessionInfo;
    @JsonProperty("interfaceElements")
    public InterfaceElements interfaceElements;

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public InterfaceElements getInterfaceElements() {
        return interfaceElements;
    }

    public void setInterfaceElements(InterfaceElements interfaceElements) {
        this.interfaceElements = interfaceElements;
    }
}
