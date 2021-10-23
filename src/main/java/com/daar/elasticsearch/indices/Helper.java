package com.daar.elasticsearch.indices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;

public class Helper {
    public static final Logger LOG = LoggerFactory.getLogger(Helper.class);

    public static String loadAsString(final String path) {
        try {
            final File resource = new ClassPathResource(path).getFile();
            return new String(Files.readAllBytes(resource.toPath()));
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
