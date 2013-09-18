/*
 * Copyright 2013 Urban Airship and Contributors
 */

package com.urbanairship.api.client.parse;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.urbanairship.api.client.APIScheduleResponse;
import com.urbanairship.api.common.parse.*;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/*
Deserializers create a mapping between Jackson and an object. This abstracts all
the boilerplate necessary for Jackson stream parsing, which is essentially what
 we're doing. This will be a lot cleaner when lambda's come down.
 If you're using Intellij, be sure and toggle open the code that's
 been collapsed.
 */
class APIScheduleResponseDeserializer extends JsonDeserializer<APIScheduleResponse> {

    private static final FieldParserRegistry<APIScheduleResponse, APIScheduleResponseReader> FIELD_PARSER =
            new MapFieldParserRegistry<APIScheduleResponse, APIScheduleResponseReader>(
                    ImmutableMap.<String, FieldParser<APIScheduleResponseReader>>builder()
                                .put("operation_id", new FieldParser<APIScheduleResponseReader>() {
                                    @Override
                                    public void parse(APIScheduleResponseReader reader,
                                                      JsonParser jsonParser,
                                                      DeserializationContext deserializationContext) throws IOException {
                                        reader.readOperationId(jsonParser);
                                    }
                                })
                                .put("schedule_urls", new FieldParser<APIScheduleResponseReader>() {
                                    @Override
                                    public void parse(APIScheduleResponseReader reader,
                                                      JsonParser jsonParser,
                                                      DeserializationContext deserializationContext) throws IOException {
                                        reader.readScheduleIds(jsonParser);
                                    }
                                })
                                .put("schedules", new FieldParser<APIScheduleResponseReader>() {
                                    @Override
                                    public void parse(APIScheduleResponseReader reader,
                                                      JsonParser jsonParser,
                                                      DeserializationContext deserializationContext) throws IOException {
                                        reader.readPushIds(jsonParser);
                                    }
                                })
                                .build()
            );

    private final StandardObjectDeserializer<APIScheduleResponse, ?> deserializer;

    // See Google Guava for Supplier details
    public APIScheduleResponseDeserializer(){
        this.deserializer = new StandardObjectDeserializer<APIScheduleResponse, APIScheduleResponseReader>(
            FIELD_PARSER,
            new Supplier<APIScheduleResponseReader>() {
                @Override
                public APIScheduleResponseReader get() {
                    return new APIScheduleResponseReader();
                }
            }
        );
    }

    @Override
    public APIScheduleResponse deserialize(JsonParser jsonParser, DeserializationContext
        deserializationContext)
            throws IOException {
        return deserializer.deserialize(jsonParser, deserializationContext);
    }
}
