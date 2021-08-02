package pl.wasko.Library.Validators;

import org.passay.*;
import pl.wasko.Library.Annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(8, 15),
                        new UppercaseCharacterRule(1),
                        new DigitCharacterRule(1),
                        new SpecialCharacterRule(1),
                        new NumericalSequenceRule(3, false),
                        new AlphabeticalSequenceRule(3, false),
                        new QwertySequenceRule(3, false),
                        new WhitespaceRule()
                )
        );
        RuleResult result = validator.validate(
                new PasswordData(password)
        );

        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                String.join(",", validator.getMessages(result))
        ).addConstraintViolation();
        return false;
    }
}
