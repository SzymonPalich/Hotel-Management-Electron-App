package com.spurvago.components;

import com.spurvago.server.client.Client;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean emailValidation(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);


        return matcher.matches();
    }

    public static Specification<Client> filter(List<String> searchWords) {
        if (searchWords.size() == 1) {
            return (r, q, b) -> {
                Expression<String> firstNameLower = b.lower(r.get("firstName"));
                Expression<String> lastNameLower = b.lower(r.get("lastName"));
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                return b.and(
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                        )
                );
            };
        } else if (searchWords.size() == 2) {
            return (r, q, b) -> {
                Expression<String> firstNameLower = b.lower(r.get("firstName"));
                Expression<String> lastNameLower = b.lower(r.get("lastName"));
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                return b.and(
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(1).toLowerCase() + "%")
                        )
                );
            };
        } else if(searchWords.size() == 3) {
            return (r, q, b) -> {
                Expression<String> firstNameLower = b.lower(r.get("firstName"));
                Expression<String> lastNameLower = b.lower(r.get("lastName"));
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                return b.and(
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(1).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(2).toLowerCase() + "%")
                        )
                );
            };
        } else {
            return (r, q, b) -> {
                Expression<String> firstNameLower = b.lower(r.get("firstName"));
                Expression<String> lastNameLower = b.lower(r.get("lastName"));
                Expression<String> emailLower = b.lower(r.get("email"));
                Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
                return b.and(
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(0).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(0).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(1).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(1).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(2).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(2).toLowerCase() + "%")
                        ),
                        b.or(
                                b.like(firstNameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWords.get(3).toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWords.get(3).toLowerCase() + "%")
                        )
                );
            };
        }
    }
}
