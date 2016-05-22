package com.viethoa.mvvm.BaseApplications.modules.Network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

public final class DateConverter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final DateFormat iso8601FormatStandard;
    private final DateFormat iso8601FormatCustom;

    @Inject
    public DateConverter() {
        this.iso8601FormatStandard = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale.US);
        this.iso8601FormatCustom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        this.iso8601FormatCustom.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

        //Note: this is used only when submitting time as epoch seconds to server
        //      normally time in Java is millisecond-based
        long seconds = src.getTime() / 1000;
        return new JsonPrimitive(seconds);
    }

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        Date date = deserializeToDate(json);
        if (typeOfT == Date.class) {
            return date;
        } else if (typeOfT == Timestamp.class) {
            return new Timestamp(date.getTime());
        } else {
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + typeOfT);
        }
    }

    private Date deserializeToDate(JsonElement json) {
        Date theDate = null;
        JsonSyntaxException ex;

        //Handle custom format
        try {
            theDate = iso8601FormatCustom.parse(json.getAsString());
        } catch (ParseException e) {
            ex = new JsonSyntaxException(json.getAsString(), e);
            theDate = null;
        }

        //Handle standard ISO8601 format
        if (theDate == null) {
            ex = null;
            try {
                theDate = iso8601FormatStandard.parse(json.getAsString());
            } catch (ParseException e) {
                ex = new JsonSyntaxException(json.getAsString(), e);
                theDate = null;
            }
        }

        if (theDate != null)
            return theDate;

        //Handle epoch time
        ex = null;
        try {
            theDate = new Date(Long.parseLong(json.getAsString()) * 1000);
        } catch (Exception e) {
            ex = new JsonSyntaxException(json.getAsString(), e);
        }

        if (ex != null)
            throw ex;

        return theDate;
    }
}