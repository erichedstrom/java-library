/*
 * Copyright 2013 Urban Airship and Contributors
 */

package com.urbanairship.api.client.parse;

import com.urbanairship.api.client.APIScheduleResponse;
import com.urbanairship.api.common.parse.JsonObjectReader;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Readers are the part of the deserialization process that actually builds and
return an object.
 */
public class APIScheduleResponseReader implements JsonObjectReader <APIScheduleResponse> {

    private final APIScheduleResponse.Builder builder;

    public APIScheduleResponseReader (){
        this.builder = APIScheduleResponse.newBuilder();
    }

    public void readOperationId(JsonParser jsonParser) throws IOException {
        builder.setOperationId(jsonParser.readValueAs(String.class));
    }

    public void readScheduleIds(JsonParser jsonParser) throws IOException {
        List<String> list =
                jsonParser.readValueAs(new TypeReference<List<String>>(){});
        builder.setScheduleUrls(list);
    }

    public void readPushIds(JsonParser jsonParser) throws IOException {
      List<String> pushIds = new ArrayList<String>();

      List<Map<String, Object>> list =
              jsonParser.readValueAs(new TypeReference<List<Map<String, Object>>>(){});
      
      for (Map<String, Object> scheduleMap : list) {
        @SuppressWarnings("unchecked")
        List<String> schedulePushIds = (List<String>) scheduleMap.get("push_ids");
        if (schedulePushIds != null) {
          pushIds.addAll(schedulePushIds);
        }
      }

      builder.setPushIds(pushIds);
    }

    @Override
    public APIScheduleResponse validateAndBuild() throws IOException {
        try{
            return builder.build();
        }
        catch (Exception ex){
            throw new APIParsingException(ex.getMessage());
        }
    }
}
