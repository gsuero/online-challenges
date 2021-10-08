package com.vmware;//Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.
//For example, in "alice@vmware.com", "alice" is the local name, and "vmware.com" is the domain name.

//If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.
//For example, "alice.z@vmware.com" and "alicez@vmware.com" forward to the same email address.

//If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.
//For example, "m.y+name@vmware.com" will be forwarded to "my@vmware.com".

//It is possible to use both of these rules at the same time.

//Problem:
//Given an array of strings emails where we send one email to each email[i], return the number of different addresses that actually receive mails.

//Example 1:
//Input: emails = ["test.email+alex@vmware.com","test.e.mail+bob.cathy@vmware.com","testemail+david@vm.ware.com"]
//Output: 2
//Explanation: "testemail@vmware.com" and "testemail@vm.ware.com" actually receive mails.

//Example 2:
//Input: emails = ["alex@vmware.com","bob@vmware.com","c@vmware.com"]
//Output: 3


// ["test.email+alex@vmware.com","test.e.mail+bob.cathy@vmware.com","testemail+david@vm.ware.com"]
// [".+@vmware.com"]
// explanation : ["@vmware.com"]
// output: 1


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Email {


    public static void main(String[] args) {
        System.out.println(getTotalEmailCount(Arrays.asList("test.email+alex@vmware.com","test.e.mail+bob.cathy@vmware.com","testemail+david@vm.ware.com")));
        System.out.println(getTotalEmailCount(Arrays.asList("alex@vmware.com","bob@vmware.com","c@vmware.com")));
    }

    public static int getTotalEmailCount(List<String> emails) {

        Set<String> purifiedEmails = new HashSet<>();
        for (String email : emails) {
            String purifiedEmail = getPurifiedEmail(email);
            System.out.println(purifiedEmail);

            if (purifiedEmail != null) {
                purifiedEmails.add(purifiedEmail);
            }

        }
        return purifiedEmails.size();

    }

    private static String getPurifiedEmail(String email) {

        char dot = '.';
        char plus = '+';

        if (email == null || email.length() < 1) {
            return null;
        }

        int atLocation = email.indexOf('@');

        if (atLocation < 0) {
            return null;
        }


        StringBuilder validated = new StringBuilder();
        for (int a = 0; a < atLocation; a++) {
            char current = email.charAt(a);

            if (current == dot) {
                continue;
            }
            if (current == plus) {
                break;
            }

            validated.append(current);

        }

        if (validated.length() < 1) {
            return null;
        }

        validated.append(email.substring(atLocation));


        return validated.toString();
    }



}

