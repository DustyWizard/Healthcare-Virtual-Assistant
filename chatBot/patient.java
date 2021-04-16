package chatBot;


public class patient implements Comparable<patient> {

	String first_name;
	String last_name ;
	String birthdate ;
	int priority;
			
	public patient() {
		
	}
	
	public patient(String first_name,
	String last_name ,
	String birthdate ,
	int priority) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthdate = birthdate;
		this.priority = priority;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(patient o) {
		if(priority<o.priority)
			return 1;
		else if(priority>o.priority) {
			return -1;
		}
		
			
					
					
		return 0;
	}
	
}

