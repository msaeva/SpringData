package com.example.productshopxml.entitities.dtos.users.wrappers;

import com.example.productshopxml.entitities.dtos.users.UserWithProductsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithProductsWrapperDto {

    @XmlAttribute
    private Integer count;

    @XmlElement(name = "user")
    private List<UserWithProductsDto> users;

    public UsersWithProductsWrapperDto(List<UserWithProductsDto> users) {
        this.users = users;
        this.count = users.size();
    }
}
