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

package net.croz.nrichdemobackend.validation.controller;

import lombok.extern.slf4j.Slf4j;
import net.croz.nrich.validation.api.constraint.InList;
import net.croz.nrich.validation.api.constraint.MaxSizeInBytes;
import net.croz.nrich.validation.api.constraint.ValidFile;
import net.croz.nrich.validation.api.constraint.ValidFileResolvable;
import net.croz.nrich.validation.api.constraint.ValidOib;
import net.croz.nrichdemobackend.validation.request.NotNullWhenDemoRequest;
import net.croz.nrichdemobackend.validation.request.NullWhenDemoRequest;
import net.croz.nrichdemobackend.validation.request.ValidRangeDemoRequest;
import net.croz.nrichdemobackend.validation.request.ValidSearchPropertiesDemoRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Validated
@RequestMapping("validation")
@RestController
public class ValidationDemoController {

    @GetMapping("max-size-in-bytes")
    public void maxSizeInBytes(@MaxSizeInBytes(5) @RequestParam String value) {
        logValue(value);
    }

    @GetMapping("in-list")
    public void inList(@InList({ "first", "second" }) @RequestParam String value) {
        logValue(value);
    }

    @GetMapping("oib")
    public void oib(@ValidOib @RequestParam String value) {
        logValue(value);
    }

    @PostMapping("not-null-when")
    public void notNullWhen(@Valid @RequestBody NotNullWhenDemoRequest request) {
        logValue(request);
    }

    @PostMapping("null-when")
    public void nullWhen(@Valid @RequestBody NullWhenDemoRequest request) {
        logValue(request);
    }

    @PostMapping("valid-file")
    public void validFile(@ValidFile(allowedContentTypeList = "text/plain", allowedExtensionList = "txt") @RequestParam MultipartFile file) {
        logValue(file);
    }

    @PostMapping("valid-file-resolvable")
    public void validFileResolvable(@ValidFileResolvable @RequestParam MultipartFile file) {
        logValue(file);
    }

    @PostMapping("valid-range")
    public void validRangeRequest(@Valid @RequestBody ValidRangeDemoRequest request) {
        logValue(request);
    }

    @PostMapping("valid-search-properties")
    public void validSearchProperties(@Valid @RequestBody ValidSearchPropertiesDemoRequest request) {
        logValue(request);
    }

    private void logValue(Object value) {
        log.info("Received: {} parameter", value);
    }
}
