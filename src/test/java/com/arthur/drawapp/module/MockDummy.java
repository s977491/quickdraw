package com.arthur.drawapp.module;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.Getter;

/**
 * Created by laia on 11/25/2017.
 */
public class MockDummy {

    @Getter
    @Inject
    @Named("test.check.override")
    private String testOverride;
}
