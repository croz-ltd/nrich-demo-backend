package net.croz.nrichdemobackend.seach.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class StringSearchCarDemoRequest {

    @NotEmpty
    private String searchTerm;

    @Size(min = 1)
    private List<String> propertyToSearchList;

}
