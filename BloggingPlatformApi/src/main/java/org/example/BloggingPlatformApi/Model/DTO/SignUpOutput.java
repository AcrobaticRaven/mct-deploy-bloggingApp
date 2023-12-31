package org.example.BloggingPlatformApi.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpOutput {
    private String signUpStatusMessage;
    private boolean signUpStatus;
}
