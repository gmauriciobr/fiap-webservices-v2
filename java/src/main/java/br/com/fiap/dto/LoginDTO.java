package br.com.fiap.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public UsernamePasswordAuthenticationToken parse() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }


}
