
# ULTRON AI Assistant - Software Requirements

## 1. Project Overview

ULTRON is a Java-based personal AI assistant developed
using Eclipse and the OpenAI API. It allows users to ask
questions, receive AI-generated responses, and maintain
conversation memory.

## 2. Functional Requirements

FR1: The application shall accept questions from users
through a console interface.

FR2: The application shall connect to the OpenAI API
to generate intelligent responses.

FR3: The application shall display AI-generated answers
in the console.

FR4: The application shall maintain conversation context
during an active session.

FR5: The application shall save conversation information
to a local text file.

FR6: The application shall retrieve saved information
when restarted.

FR7: The application shall allow users to exit using
the "exit" command.

## 3. Non-Functional Requirements

NFR1: The application shall be developed in Java.

NFR2: The source code shall be organized into separate
classes for easier maintenance.

NFR3: The application shall handle file-related errors
without unexpectedly terminating.

NFR4: The application shall use an environment variable
to access the OpenAI API key.

NFR5: The application shall provide clear and readable
console output.

## 4. Assumptions

- Users have Java installed on their computers.
- Users have access to the internet.
- Users have a valid OpenAI API key.
- The OpenAI API is available when questions are submitted.
- Users interact with ULTRON through the console.

## 5. Project Scope

### Included
- Java console application
- OpenAI API integration
- Interactive question-and-answer functionality
- Conversation context
- Local memory storage
- Basic error handling

### Not Included
- Graphical user interface
- Voice recognition
- Mobile application
- Cloud-based memory database

## 6. Technologies Used

- Java
- Eclipse IDE
- OpenAI API
- Git
- GitHub
