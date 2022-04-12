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
