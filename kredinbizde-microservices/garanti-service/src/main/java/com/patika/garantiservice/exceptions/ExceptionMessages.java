package com.patika.garantiservice.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessages {
    public static final String DUPLICATE_APPLICATION = "Daha önce aynı başvuru yapılmış !";
}
