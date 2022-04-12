package net.croz.nrichdemobackend.notification.request;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrich.validation.api.constraint.InList;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class NotificationDemoRequest {

    @InList({ "INFO", "WARNING", "ERROR" })
    @NotNull
    private String severity;

}
