
public class User {

	private String full_name, email,address,password,wallet,phone_number;
	public User( String full_name,String email,String address,String password,String wallet, String phone_number) {
		
		this.full_name=full_name;
		this.email=email;
		this.address=address;
		this.password=password;
		this.wallet=wallet;
		this.phone_number=phone_number;
	}
	public String getFull_name() {
		return full_name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public String getWallet() {
		return wallet;
	}

	public String getPhone_number() {
		return phone_number;
	}

}

