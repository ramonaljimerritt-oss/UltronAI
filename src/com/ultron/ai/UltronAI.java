package com.ultron.ai;


public class UltronAI {

    public String getResponse(String question) {

        String response;

        if (question.toLowerCase().contains("java")) {

            response = "Java is a programming language "
                     + "used to build many types of applications.";

        } else if (question.toLowerCase().contains("inheritance")) {

            response = "Inheritance allows one class "
                     + "to inherit methods and fields from another.";

        } else if (question.toLowerCase().contains("loop")) {

            response = "A loop repeats code until "
                     + "a condition is met.";

        } else if (question.toLowerCase().contains("array")) {

            response = "An array stores multiple values "
                     + "of the same type.";

        } else {

            response = "I don't know the answer yet. "
                     + "I'm still learning!";
        }

        return response;
    }
}

