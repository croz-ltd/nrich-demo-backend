package net.croz.nrichdemobackend.webmvc.exception;

import lombok.Getter;
import net.croz.nrich.core.api.exception.ExceptionWithArguments;

@Getter
public class ConfiguredExceptionWithArguments extends RuntimeException implements ExceptionWithArguments {

    private final transient Object[] argumentList;

    public ConfiguredExceptionWithArguments(String message, Object... argumentList) {
        super(message);
        this.argumentList = argumentList;
    }
}
