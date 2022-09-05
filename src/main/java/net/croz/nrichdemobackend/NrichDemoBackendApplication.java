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

package net.croz.nrichdemobackend;

import net.croz.nrichdemobackend.main.service.BootstrapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.ProxyBits;

import java.util.TimeZone;

@AotProxyHint(targetClass = net.croz.nrichdemobackend.encrypt.controller.EncryptDemoController.class, proxyFeatures = ProxyBits.IS_STATIC)
@SpringBootApplication
public class NrichDemoBackendApplication {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(NrichDemoBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initBootStrapData(BootstrapService bootStrapService) {
        return args -> bootStrapService.createBootstrapData();
    }
}
