package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Log {
    private String date;
    private String millis;
    private String nanos;
    private String sequence;
    private String logger;
    private String level;
    private String className;
    private String method;
    private String thread;
    private String message;

    public Log(){

    }
    public Log(String date, String millis, String nanos, String sequence, String logger, String level, String className, String method, String thread, String message) {
        this.date = date;
        this.millis = millis;
        this.nanos = nanos;
        this.sequence = sequence;
        this.logger = logger;
        this.level = level;
        this.className = className;
        this.method = method;
        this.thread = thread;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMillis() {
        return millis;
    }

    public void setMillis(String millis) {
        this.millis = millis;
    }

    public String getNanos() {
        return nanos;
    }

    public void setNanos(String nanos) {
        this.nanos = nanos;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Log{" +
                "date='" + date + '\'' + "\n" +
                ", millis='" + millis + '\'' + "\n" +
                ", nanos='" + nanos + '\'' + "\n" +
                ", sequence='" + sequence + '\'' + "\n" +
                ", logger='" + logger + '\'' + "\n" +
                ", level='" + level + '\'' + "\n" +
                ", className='" + className + '\'' + "\n" +
                ", method='" + method + '\'' + "\n" +
                ", thread='" + thread + '\'' + "\n" +
                ", message='" + message + '\'' + "\n" +
                '}';
    }
}
