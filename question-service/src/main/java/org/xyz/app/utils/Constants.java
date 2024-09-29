package org.xyz.app.utils;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    public static final String TOPIC_NAME="question-topic";
    @Value("${spring.kafka.template.default-topic}")
    public static String topic;
}
