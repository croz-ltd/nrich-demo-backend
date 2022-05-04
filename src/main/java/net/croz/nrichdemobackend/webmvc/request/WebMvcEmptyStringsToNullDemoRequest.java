package net.croz.nrichdemobackend.webmvc.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WebMvcEmptyStringsToNullDemoRequest {

    private String nonEmptyString;

    private String emptyString;

}
