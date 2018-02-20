/* Member Classes that provide necessary Members varaibles */
package entities;

public class Members {
	private String memberCode;
	private String type;
	private String primaryContact;
	private String name;
	private MembersAddress address;
	private boolean isStudent;
	
	public Members(String memberCode, String type, String primaryContact, String name, MembersAddress address) {
		super();
		this.memberCode = memberCode;
		this.type = type;
		this.primaryContact = primaryContact;
		this.name = name;
		this.address = address;
		this.isStudent = type.toLowerCase().equals("s");
	}
	
	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public MembersAddress getAddress() {
		return address;
	}

	public void setAddress(MembersAddress address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// Changes added to phase 2

	@Override
	public String toString() {
		if(isStudent) {
			return name + " [Student]";
		} 
		return name + " [General]";
	}
	
}
