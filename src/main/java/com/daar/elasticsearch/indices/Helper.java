package com.daar.elasticsearch.indices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

public class Helper {
    public static final Logger LOG = LoggerFactory.getLogger(Helper.class);


    public static String loadAsString(final String path) {
        try {
            // we use getInputStream instead of getFile for good execution with jar
            // see https://stackoverflow.com/questions/14876836/file-inside-jar-is-not-visible-for-spring
            final InputStream resource = new ClassPathResource(path).getInputStream();
            return StreamUtils.copyToString(resource, Charset.defaultCharset());
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
