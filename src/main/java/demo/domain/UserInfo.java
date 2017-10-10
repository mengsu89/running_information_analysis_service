package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {

    @AttributeOverride(name = "username", column = @Column(name = "userName"))
    private String username;

    @AttributeOverride(name = "address", column = @Column(name = "userAddress"))
    private String address;

    public UserInfo() {}

    public UserInfo(String username) {
        this.username = username;
    }

    public UserInfo(String username, String address) {
        this.username = username;
        this.address = address;
    }


}
