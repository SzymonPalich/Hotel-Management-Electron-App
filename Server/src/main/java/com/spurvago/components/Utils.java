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
            Predicate finalPredicate = null;
            for (int i = 0; i < searchWords.size(); i++) {
                Predicate temp =
                        b.or(
                                b.like(r.get("firstName"), asLikeQuery(searchWords.get(i))),
                                b.like(r.get("lastName"), asLikeQuery(searchWords.get(i))),
                                b.like(r.get("email"), asLikeQuery(searchWords.get(i))),
                                b.like(r.get("phoneNumber"), asLikeQuery(searchWords.get(i)))
                        );
                if(i == 0) {
                    finalPredicate = temp;
                }else{
                    finalPredicate = b.and(finalPredicate, temp);
                }
            }

            return finalPredicate;
        };
    }

    public static String asLikeQuery(String string) {
        return "%" + string + "%";
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
