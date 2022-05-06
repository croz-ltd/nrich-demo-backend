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

package net.croz.nrichdemobackend.encrypt.controller;

import net.croz.nrich.encrypt.api.annotation.DecryptArgument;
import net.croz.nrich.encrypt.api.annotation.EncryptResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("encrypt")
@RestController
public class EncryptDemoController {

    @EncryptResult
    @GetMapping("encrypt-result")
    public String encryptResult(@RequestParam String value) {
        return value;
    }

    @GetMapping("decrypt-argument")
    public String decryptArgument(@DecryptArgument String value) {
        return value;
    }

    @EncryptResult(resultPathList = "nestedValue")
    @PostMapping("encrypt-nested-result")
    public Map<String, String> encryptNestedResult(@RequestBody Map<String, String> value) {
        return value;
    }

    @PostMapping("decrypt-nested-argument")
    public Map<String, String> decryptNestedArgument(@DecryptArgument(argumentPathList = "nestedValue") @RequestBody Map<String, String> value) {
        return value;
    }

    @GetMapping("encrypt-result-defined-in-properties")
    public String encryptResultDefinedInProperties(@RequestParam String value) {
        return value;
    }

    @GetMapping("decrypt-argument-defined-in-properties")
    public String decryptArgumentInProperties(String value) {
        return value;
    }
}
