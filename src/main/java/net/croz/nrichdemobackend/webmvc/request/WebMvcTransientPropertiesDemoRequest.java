package net.croz.nrichdemobackend.webmvc.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class WebMvcTransientPropertiesDemoRequest implements Serializable {

    private String nonTransientValue;

    private transient String transientValue;

}
