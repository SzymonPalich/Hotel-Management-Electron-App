package com.spurvago.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spurvago.server.client.Client;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean validateEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);


        return matcher.matches();
    }

    public static Specification<Client> filter(List<String> searchWords) {
        return (r, q, b) -> {
            Expression<String> firstNameLower = b.lower(r.get("firstName"));
            Expression<String> lastNameLower = b.lower(r.get("lastName"));
            Expression<String> emailLower = b.lower(r.get("email"));
            Expression<String> phoneNumberLower = b.lower(r.get("phoneNumber"));
            Predicate finalPredicate = null;
            for (String searchWord : searchWords) {
                Predicate temp =
                        b.or(
                                b.like(firstNameLower, "%" + searchWord.toLowerCase() + "%"),
                                b.like(lastNameLower, "%" + searchWord.toLowerCase() + "%"),
                                b.like(emailLower, "%" + searchWord.toLowerCase() + "%"),
                                b.like(phoneNumberLower, "%" + searchWord.toLowerCase() + "%")
                        );
                finalPredicate = b.and(temp);
            }

            return finalPredicate;
        };
    }


    public static boolean isNullOrBlank(String string) {
        return (string == null || string.isBlank());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
