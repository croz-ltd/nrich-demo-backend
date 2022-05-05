/*
 *  Copyright 2022 CROZ d.o.o, the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package net.croz.nrichdemobackend.webmvc.controller;

import net.croz.nrichdemobackend.webmvc.exception.ConfiguredException;
import net.croz.nrichdemobackend.webmvc.exception.ConfiguredExceptionWithArguments;
import net.croz.nrichdemobackend.webmvc.exception.WebMvcDemoRequest;
import net.croz.nrichdemobackend.webmvc.request.WebMvcEmptyStringsToNullDemoRequest;
import net.croz.nrichdemobackend.webmvc.request.WebMvcTransientPropertiesDemoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

// combination of notification resolving and @RestController advice from nrich-webmvc
@RequestMapping("webmvc")
@RestController
public class WebMvcDemoController {

    @GetMapping("generic-exception")
    public void genericException() {
        throw new RuntimeException(); // NOSONAR
    }

    @GetMapping("configured-exception")
    public void configuredException() {
        throw new ConfiguredException();
    }

    @GetMapping("unwrapped-configured-exception")
    public void unwrappedExceptionResolving() throws ExecutionException {
        throw new ExecutionException(new ConfiguredException());
    }

    @GetMapping("configured-exception-with-arguments")
    public void configuredExceptionWithArguments() {
        throw new ConfiguredExceptionWithArguments("Exception occurred", "first argument", "second argument");
    }

    @PostMapping("validation-failed-exception")
    public String validationFailedException(@Valid @RequestBody WebMvcDemoRequest request) {
        return request.getName();
    }

    @PostMapping("bind-validation-failed-exception")
    public String bindValidationFailedException(@Valid WebMvcDemoRequest request) {
        return request.getName();
    }

    @PostMapping("transient-properties-serialization")
    public WebMvcTransientPropertiesDemoRequest transientPropertiesSerialization(WebMvcTransientPropertiesDemoRequest request) {
        return request;
    }

    @PostMapping("empty-strings-to-null")
    public WebMvcEmptyStringsToNullDemoRequest emptyStringsToNull(WebMvcEmptyStringsToNullDemoRequest request) {
        return request;
    }
}
