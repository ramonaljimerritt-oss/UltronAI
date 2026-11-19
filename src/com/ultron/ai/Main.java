package com.ultron.ai;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("===============================");
		System.out.println( "\t ULTRON");
		System.out.println("===============================");
		
		
	
		
		System.out.println("Welcome to ULTRON!");
		System.out.println("Your Java AI Assistent");
		System.out.println("System initialized sucessfully!!");
		
		Scanner input = new Scanner(System.in);
		
		UltronAI ultron = new UltronAI();
		AIClient aiClient = new AIClient();
		MemoryManager memory = new MemoryManager();

String savedMemory = memory.loadMemory();

if (!savedMemory.isBlank()) {
    System.out.println("ULTRON: Previous memory loaded successfully!");
} else {
    System.out.println("ULTRON: No previous memory found.");
}

aiClient.setSavedMemory(savedMemory);


		

String apiKey = System.getenv("OPENAI_API_KEY");

if (apiKey != null && !apiKey.isBlank()) {

    System.out.println("ULTRON: API key detected successfully!");

} else {

    System.out.println("ULTRON: API key not found.");

}

		
		System.out.println(aiClient.getStatus());
		
		
		while (true) {
		System.out.println("\nAsk ULTRON a question? ");
		
	
		String question = input.nextLine();
		
	if (question.equalsIgnoreCase("exit")) {
			
			System.out.println("\nULTRON: Shutting down...");
			System.out.println("Goodbye!");
			
			break;
		}
		

String response;

try {
    response = aiClient.askAI(question);
} catch (Exception e) {
    response = "Unable to connect to OpenAI: "
            + e.getMessage();
}

	
	System.out.println("\nULTRON: " + response);

memory.saveMemory("User: " + question);
memory.saveMemory("ULTRON: " + response);

	
	}
		
		input.close();
		
	

	}
	

}
