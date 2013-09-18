/*
 * Copyright 2013 Urban Airship and Contributors
 */

package com.urbanairship.api.client;

import java.util.List;

/**
 * Represents a response from the Urban Airship API for Scheduled Notifications.
 */
public class APIScheduleResponse {

    private final String operationId;
    private final List<String> scheduleUrls;
    private final List<String> pushIds;

    /**
     * New APIScheduleResponse builder
     * @return Builder
     */
    public static Builder newBuilder(){
        return new Builder();
    }

    private APIScheduleResponse(String operationId, List<String> scheduleUrls, List<String> pushIds){
        this.operationId = operationId;
        this.scheduleUrls = scheduleUrls;
        this.pushIds = pushIds;
    }

    /**
     * Get the operation id for this response. This is used by Urban Airship
     * to track an operation through our system, and should be used when support
     * is needed.
     * @return Operation id for this API request
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * List of push id's, one for every actual push message that moves through
     * the API. This is useful for tracking an individual message as part of
     * an operation, and can be used when support is needed.
     * @return List of push ids.
     */
    public List<String> getScheduleUrls() {
        return scheduleUrls;
    }

    /**
     * List of push id's, one for every actual push message that moves through
     * the API. This is useful for tracking an individual message as part of
     * an operation, and can be used when support is needed.
     * @return List of push ids.
     */
    public List<String> getPushIds() {
        return pushIds;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("APIScheduleResponse:");
        stringBuilder.append(String.format("\nOperationId:%s", operationId));
        stringBuilder.append(String.format("\nScheduleUrls:\n%s", scheduleUrls));
        stringBuilder.append("\nPushIds:");
        stringBuilder.append(pushIds.toString());
        return stringBuilder.toString();
    }

    /**
     * APIScheduleResponse Builder
     */
    public static class Builder {

        private String operationId;
        private List<String> scheduleUrls;
        private List<String> pushIds;

        private Builder() {}

        public Builder setOperationId(String operationId){
            this.operationId = operationId;
            return this;
        }

        public Builder setScheduleUrls(List<String> scheduleUrls){
            this.scheduleUrls = scheduleUrls;
            return this;
        }

        public Builder setPushIds(List<String>pushIds){
          this.pushIds = pushIds;
          return this;
        }

        public APIScheduleResponse build(){
            return new APIScheduleResponse(this.operationId, this.scheduleUrls, this.pushIds);
        }
    }
}
