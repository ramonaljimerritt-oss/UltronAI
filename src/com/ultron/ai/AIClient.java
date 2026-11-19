
package com.ultron.ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AIClient {
	
	private String previousResponseID = null;

private String savedMemory = "";

public void setSavedMemory(String savedMemory) {
    this.savedMemory = savedMemory == null ? "" : savedMemory;
}


    private final HttpClient client;

    public AIClient() {
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    public String getStatus() {
        return "ULTRON AI client initialized successfully!";
    }

    public String askAI(String question) throws Exception {

if (!savedMemory.isBlank()) {
    question = "Previously saved information about our conversation:\n"
            + savedMemory
            + "\n\nCurrent user question:\n"
            + question;
}


        String apiKey = System.getenv("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            return "ERROR: OpenAI API key not found.";
        }

        // Escape special characters for JSON
        String safeQuestion = question
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        String memoryField = "";

        if (previousResponseID != null) {
            memoryField = ", \"previous_response_id\": \""
                    + previousResponseID + "\"";
        }


String json = """
    {
        "model": "gpt-5-mini",
        "instructions": "You are ULTRON, an intelligent and professional personal AI assistant. Your name is ULTRON. Be confident, friendly, and respectful. Explain complicated subjects in simple language. Help with Java programming, computer science, technology, studying, and everyday questions. Remember details from earlier messages in the current conversation. Give clear step-by-step instructions when teaching. If you do not know something, say so rather than guessing.",
        "input": "%s",
        "max_output_tokens": 1500%s
    }
    """.formatted(safeQuestion, memoryField);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                    "https://api.openai.com/v1/responses"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(60))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            return "API request failed. HTTP status: "
                    + response.statusCode();
        }

        String responseBody = response.body();
        java.util.regex.Pattern idPattern =
                java.util.regex.Pattern.compile(
                        "\"id\"\\s*:\\s*\"(resp_[^\"]+)\""
                );

        java.util.regex.Matcher idMatcher =
                idPattern.matcher(responseBody);

        if (idMatcher.find()) {
            previousResponseID = idMatcher.group(1);
        }

        String marker = "\"type\":\"output_text\"";

        String compact = responseBody.replaceAll("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", "");

        int start = compact.indexOf(marker);

        if (start != -1) {
            int textStart = compact.indexOf("\"text\":", start);

            if (textStart != -1) {
                int firstQuote = compact.indexOf('"', textStart + 7);

                if (firstQuote != -1) {
                    int endQuote = firstQuote + 1;

                    while (endQuote < compact.length()) {
                        if (compact.charAt(endQuote) == '"'
                                && compact.charAt(endQuote - 1) != '\\') {
                            break;
                        }
                        endQuote++;
                    }

                    if (endQuote < compact.length()) {
                    	String answer = compact.substring(firstQuote + 1, endQuote);

                    	answer = answer.replace("\\n", "\n")
                    	               .replace("\\\"", "\"");

                    	java.util.regex.Pattern unicodePattern =
                    	        java.util.regex.Pattern.compile("\\\\u([0-9a-fA-F]{4})");

                    	java.util.regex.Matcher unicodeMatcher =
                    	        unicodePattern.matcher(answer);

                    	StringBuilder decoded = new StringBuilder();

                    	while (unicodeMatcher.find()) {
                    	    char character = (char) Integer.parseInt(
                    	            unicodeMatcher.group(1), 16);

                    	    unicodeMatcher.appendReplacement(
                    	            decoded,
                    	            java.util.regex.Matcher.quoteReplacement(
                    	                    String.valueOf(character)));
                    	}

                    	unicodeMatcher.appendTail(decoded);

                    	return decoded.toString();
                    }
                }
            }
        }

        return "ULTRON: Could not extract the AI response.";
    }
}
