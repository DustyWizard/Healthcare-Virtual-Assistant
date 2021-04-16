package chatBot;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;



public class Main extends synonymAPI   {
		
	static PriorityQueue<patient> patientQ = new PriorityQueue<>();
	static patient p1 = new patient("Jon","Jones","2020",2);
	static patient p2 = new patient("Daniel","Cormier","2020",3);
	static patient p3 = new patient("Michael","Bisping","2020",4);
	static int x = 0;

	
	//The review method allows the bot to ask the user many questions about their experience while differentiating between yes/no answers to specific questions
	//This allows our bot to respond slightly differently depending on the question asked prior.
		
	//The review method is not used until the end of the program, given the user the option to give a review if they wish.
	static void review() {
		//These variables are used to verify various things throughout the method to ensure the user is being taken down the correct path.
		int n = 0;
		boolean correct = true;
		boolean match[] = new boolean [30];
		boolean tf = false;
		String yn = "";
		String answer = "";
		
		//These arrays utilize the synonym API we implemented, giving us many different version of "yes" and/or "no" answers.
		String[] positive = new String[50];
		positive = synonyms("yes");
		String[] negative = new String[50];
		negative = synonyms("no");
		
		Scanner rev = new Scanner(System.in);
		
		for(int i = 0; i < match.length; i++) {
			match[i] = false;
		}
		
		//The while statement gives a way to escape our review method, however this has not been implemented yet
		//This is considered preparation for a future build where we will optimize different aspects of this program, where this will be one of them.
		while (!answer.equalsIgnoreCase("OUT")||!yn.equalsIgnoreCase("OUT")){
		
		botOutput("Quick notice before we begin: Do you mind if we attach your personal information along with your review? (Yes to stay anonymous; No to allow us to associate this with your personal information)");	
		yn = retrieveUserInput(x);
		x++;
		//yn = rev.nextLine();
		botOutput("Let us know your Address and we will send you the directions (ex. 3289 Mcgregor rd., West Kelowna, BC");
		yn = retrieveUserInput(x);
		x++;
		yn = geocode(yn);
		botOutput("Perfect, here are your Directions: " + directions(yn));
		
		botOutput("Thanks for participating in our review! Have you been here with us before today?");
		
		yn = retrieveUserInput(x);
		x++;
		
		
		//This while statement only allows the user to proceed to the next set of questions if they give a variation of yes or no that is accepted by our program.
		//Otherwise it will loop, telling them it is an invalid input and to try again.
		while(!match[n]) {
			
			if(!correct) {
				botOutput("Invalid input, try again.");
				yn = retrieveUserInput(x);
				x++;
			}
				
		for(String positives:positive) {
				
			if (yn.matches("(.*)yes(.*)")||yn.matches("(.*)"+positives+"(.*)")){
				botOutput("Great to hear! Do you think you'll come back?");
				match[n] = true;
				break;
			}
		}
		for(String negatives:negative) {
			
			if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
				botOutput("There's a first for everything! Do you think you'll come back?");
				match[n] = true;
				break;
			}
		}
		correct = false;
		}
		n++;
		correct = true;
		
		yn = retrieveUserInput(x);
		x++;
			
		//We repeat this while statement multiple times to ensure the review method is given the answers that are required in specific places.
		while(!match[n]) {
			
			if(!correct) {
				botOutput("Invalid input, try again.");
				yn = retrieveUserInput(x);
				x++;
			}
				
		for(String positives:positive) {
				
			if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
				botOutput("Awesome! Would you recommend our service to friends and family?");
				match[n] = true;
				break;
			}
		}
		for(String negatives:negative) {
				
			if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
				botOutput("Oh no! ): Would you recommend our service to friends and family?");
				match[n] = true;
				break;
			}
		}
		correct = false;
		}
		n++;
		correct = true;
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("Are there any recommendations you'd like to make in order for us to make your experience better next time?");
			
		yn = retrieveUserInput(x);
		x++;
			
			
		botOutput("Were you able to accomplish what you came here to do today?");
			
		yn = retrieveUserInput(x);
		x++;
			
			
		while(!match[n]) {
				
			if(!correct) {
				botOutput("Invalid input, try again.");
				yn = retrieveUserInput(x);
				x++;
			}
				
		for(String positives:positive) {
				
			if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
				botOutput("Great! Is there any way we could make this easier the next time?");
				match[n] = true;
				break;
			}
		}
		for(String negatives:negative) {
				
			if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
				botOutput("Is there any way we could fix this for you?");
				match[n] = true;
				break;
			}
		}
		correct = false;
		}
		n++;
		correct = true;
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("We'll be sure to take any suggestions into consideration!");
		botOutput("Did you feel you were safe while you were in our care?");
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("Do you mind explaining how or why you felt this way?");
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("Thank you for providing us with this information.");
		botOutput("Did it feel like you were talking with a real person today?");
			
		yn = retrieveUserInput(x);
		x++;
			
			
		while(!match[n]) {
				
			if(!correct) {
				botOutput("Invalid input, try again.");
				yn = retrieveUserInput(x);
				x++;
			}
				
		for(String positives:positive) {
				
			if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
				botOutput("What made you feel this way?");
				match[n] = true;
				break;
			}
		}
		for(String negatives:negative) {
				
			if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
				botOutput("How can we improve this?");
				match[n] = true;
				break;
			}
		}
		correct = false;
		}
		n++;
		correct = true;
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("This \'bot\' thanks you for your cooperation! :)");
		botOutput("Do you have any general complaints at all from you experience here?");
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("Thank you for letting us know.");
		botOutput("We've reached near the halfway mark in this review. Would you like to continue?");
			
		yn = retrieveUserInput(x);
		x++;
			
			
		//This response determines if the user would like to continue the survey. If they respond "no" or some variation this will allow the user to escape the survey and end the chat.
		for(String negatives:negative) {
				
			if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
				correct = false;
				break;
			}
		}
			
		if(!correct) {
			return;
		}
			
		botOutput("Great! We'll continue on with the service review. You're almost done!");
		botOutput("Did you feel our information gathering process was efficient?");
			
		yn = retrieveUserInput(x);
		x++;
			
			
		while(!match[n]) {
				
			if(!correct) {
				botOutput("Invalid input, try again.");
				yn = retrieveUserInput(x);
				x++;
			}
				
		for(String positives:positive) {
				
			if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
				botOutput("Great! Do you have any suggestions for a more efficient method?");
				match[n] = true;
				break;
			}
		}
		for(String negatives:negative) {
				
			if (yn.matches("(.*)no(.*)")||yn.matches("(.*)"+negatives+"(.*)")){
				botOutput("Do you have any suggestions to create a speedier experience for you next time?");
				match[n] = true;
				break;
			}
		}
		correct = false;
		}
		n++;
		correct = true;
			
		yn = retrieveUserInput(x);
		x++;
			
		botOutput("Would you be willing to rate us today? (Type OUT to exit)");
			
		yn = retrieveUserInput(x);
		x++;
			
		for(String positives:positive) {
				
			if (yn.matches("yes")||yn.matches("(.*)"+positives+"(.*)")){
				botOutput("Rate our service today on a scale of 1-10 (1 being poor and 10 being amazing)");
					
				yn = retrieveUserInput(x);
				x++;
					
				botOutput("Are there any specific reasons you gave us the score you did today?");
					
				answer = retrieveUserInput(x);
				x++;
				break;
			}
		}
			
		break;
			
		}
			
			
		//This signifies the end of the review() method, where it will be exited accordingly
		botOutput("Thank you for participating in our service review! Do you have any last comments/suggestions you'd like to make?");
			
		answer = retrieveUserInput(x);
		x++;
		rev.close();
		return;
	}
		
	static boolean showListWith(patient p){
		if(p.getFirst_name()==null){
			return false;
		}
		int counter = 0;
		patientQ.add(p1);
		patientQ.add(p2);
		patientQ.add(p3);
		patientQ.add(p);
		Iterator<patient> itr = patientQ.iterator();

		while(itr.hasNext()) {
			counter++;
			botOutput("Week " + counter + ", Patient Name: "+patientQ.peek().getFirst_name()+", Patient Last Name:  "+patientQ.poll().getLast_name());
		}
		return true;
	}
	
	static boolean validate(String first) {
	if(first.length()>1 && first.length()<17) {
				
			
			return true;
			
			}
			
			
			else {
				return false;
				}
		
	}
	public static boolean validBirthDate(String bDate) {
		boolean valid = false;
		
		try {
			LocalDate.parse(bDate, DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
			valid = true;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
	
	/*
		 * The point of this chatbot is to verify an appointment or book an appointment if no appointment is
		 * present , levels of conversations are used to control the flow of conversation
		 * build is still in early production.
		 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		gui GUI = new gui();
		
		
		// define the strings. TODO store data in csv instead of multiple strings
		boolean conversation = true; 
		boolean proceed =false;
		patient user = new patient();
		int pain =0;
		String dob ="";
		boolean isValid= false;
		String fName = "";
		String sName= "";
		String answer ="";
		String end = "";
		String review = "";
		String symptomsSentence ="";
		String worseSymptom = "";
		int durationOfSymptoms = 0;
		String drSexPreference ="";
		String familyDoctor ="";
		String[] positive = new String[50];
		positive = synonyms("yes");
		String[] negative = new String[50];
		negative = synonyms("no");
		int level=0;
		
		
		
		botOutput("Hello, thanks for contacting our clinic, do you have an appointment booked already? (Type OUT to exit)");
		
		// main conversation loop
		while (conversation) {
			answer = retrieveUserInput(x);
			x++;
			if(answer.equalsIgnoreCase("OUT")) {
				break;
			}
			
		switch(level) {// Switch statement that determines levels of dialogue during main conversation
		
		case 0:// this is the first level of conversation where we ask if the customer has an appointment 
			// we iterate through all the words in the array of positives and if we have a match we go to level 1 
			for(String positives:positive) {
				
				if (answer.toLowerCase().matches("(.*)yes(.*)")||answer.matches("(.*)"+positives+"(.*)")){
					botOutput("Perfect what is your appointment date ? (DD/MM/YYYY)");
					level=30;
				break;
				}
			}	
			// if we have a negative we go to level 0 and book an appointment 
			for(String negatives:negative) {
						
				if (answer.toLowerCase().matches("(.*)no(.*)")||answer.matches("(.*)"+negatives+"(.*)")){
					botOutput("Sorry to hear that let's get you an appointment booked !");
					
					level =1;
					botOutput("Please enter your first name below: ");
					}
					break;
				}
				break;
				
		case 1: //Gets First name
			fName = answer;
			fName.trim();
			fName = fName.substring(0, 1).toUpperCase() + fName.substring(1).toLowerCase();
			isValid= validate(fName);
			if (!isValid) {
				botOutput("Sorry your input wasn't valid. Try that again");
			}else {
				botOutput("Thanks " + fName + ", what is your family name ?");
				GUI.setName(fName);
				level =2;
				user.setFirst_name(fName);
			}
			break;
			
		case 2: // Gets Last Name
			sName = answer;
			isValid = validate(sName);
			if (!isValid) {
				botOutput("Sorry your input wasn't valid. Try that again");
			}
			else { botOutput("Thanks for that info, let's move on to your date of birth (dd/mm/yyyy): ");
			user.setLast_name(sName);
				level=3;
				}
			break;
			
		case 3: // Gets Date of birth
			dob = answer;
			if(!validBirthDate(dob)) {
				// !dob.isValid TODO: verify date of birth 
				botOutput("Please enter a valid birth date");
			}
			else{
				botOutput("Thanks, if you had to describe your level of pain from 1 to 10 what would it be?");	
			user.setBirthdate(dob);
			level =4;
			break;
			}
			break;
			
		case 4: // Gets pain level
		try{
			pain = Integer.parseInt(answer); }
			catch(Exception e ){botOutput("Sorry, something went wrong please try again.");
			break;}
			if(pain>10||pain<1) {
				botOutput("Please enter an integer from 1-10");
			}else {	
				user.setPriority(pain);
				level=5;
				botOutput("Can you give a brief description of your symptoms?");
				}
			break;
			
		case 5: // Symptoms
			symptomsSentence = answer;
			level =6;
			botOutput("If you are having multiple symptoms, which is bothering you the most? (if not please type 'none')");
			break;
			
		case 6: //Which is the worse symptom
			worseSymptom = answer;
			botOutput("If you are having chest pains, trouble breathing, severe bleeding, or extreme dizziness, please stop using this bot and Call 911.");
			botOutput("How many days have you experienced these symptoms?");
			level =7;
			break;
			
		case 7: // get duration of symptoms
		try{
			durationOfSymptoms = Integer.parseInt(answer);}
			catch (Exception e ){botOutput("Sorry, something went wrong please enter the number of days again.");break;}
			botOutput("Okay. Would you prefer a male or female doctor?");
			level =8;
			break;
		case 8:// gets dr sex/gender preference
			drSexPreference = answer;
			if(!(drSexPreference.matches("male(.*)") || drSexPreference.matches("female(.*)"))) {
				botOutput("Please answer either 'male' or 'female'");
			}else {	
				botOutput("Sounds good. Do you have a family doctor?" );
				level =9;	
			}
			break;
			
		case 9: // branch for get family doctor if no family doctor end chat
		
			if(answer.matches("(.*)no(.*)")) {
				botOutput("Okay, thank you for specifying that. We have all of the information that we need.");
				showListWith(user);
				botOutput("Based on our conversation, I ranked you in the following order, please bring ID when you come in to the clinic.");
				
				level = -1;
				
			}
			else if(answer.equalsIgnoreCase("yes")) {
				level =10;
				botOutput("What is your family doctors name?");
			}
			
			break;
			
		case 10: // get family doctors name
			familyDoctor = answer;
			if(!familyDoctor.toLowerCase().matches("dr.(.*)")) {
				botOutput("Please enter a valid family doctors name (Dr. ..)");
			}else {
				showListWith(user);
				botOutput("Based on our conversation, I ranked you in the following order, please bring ID when you come in to the clinic.");
				level = -1;
			}
			
			break;
			
		case 11:// extra method in case we add more.
			break;
			
		case 30: // TODO: Add more verfication 
			if(answer.matches("(.*)2021")){ //if answer matches a date format go to level 3 
				botOutput("The appointment date has been verified.");
					level++;
					conversation = false;
					break;
			}else {
				botOutput("please enter a valid date");
				}
			break;
			
		//exit case
			case -1 : botOutput("Thank you for using our service.");
					conversation = false;
					break;
		}
		//conversation while-loop end
		}	
		//Catches users name here in order to use it in the GUI if it has not been specified already
		boolean isNameValid = false;
		
		while(!isNameValid) {
			if(fName.equals("")) {
				botOutput("Please verify with us by telling us your first name.");
				answer = retrieveUserInput(x);
				
				fName = answer;
				fName.trim();
				fName = fName.substring(0, 1).toUpperCase() + fName.substring(1).toLowerCase();
				isValid= validate(fName);
				
				if (!isValid) {
					botOutput("Sorry your input wasn't valid. Try that again");
					fName = "";
					x++;
				}else {
					botOutput("Thanks " + fName + ".");
					GUI.setName(fName);
					x++;
					isNameValid = true;
				}
				
			
			}
			else {
				isNameValid = true;
			}
		}
		
		botOutput("Would you like to participate in our quick service review and let us know how we did?");
		answer = retrieveUserInput(x);
		x++;
		
		for(String positives:positive) {
			
			if (answer.matches("(.*)yes(.*)")||answer.matches("(.*)"+positives+"(.*)")){
				review();
				
				//review = "no";
				break;
			}
			else{
				break;}
		}
		
		botOutput("Chat has now ended. Thanks for the chat! Feel free to say thank you to our bot.");
		end = retrieveUserInput(x);
		x++;
		sc.close();
		botOutput("Goodbye :)");

		
		
}

	public static Object validate;
}