package model;

import java.io.Serializable;
import java.util.UUID;

public class Human implements Serializable, Comparable<Human> {
	private static final long serialVersionUID = 1784305009113326548L;

	public Human() {
	}

	public Human(String id, String firstName, String lastName, String nickname, String birthday, String phone, String mail,
			String homepage, String street, String city, String country, String notes, String imageFilename) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.birthday = birthday;
		this.phone = phone;
		this.mail = mail;
		this.homepage = homepage;
		this.street = street;
		this.city = city;
		this.country = country;
		this.notes = notes;
		this.imageFilename = imageFilename;
	}
	
	private String id = UUID.randomUUID().toString();
	private String firstName = "";
	private String lastName = "";
	private String nickname = "";
	private String birthday = "";
	private String phone = "";
	private String mail = "";
	private String homepage = "";
	private String street = "";
	private String city = "";
	private String country = "";
	private String notes = "";
	private String imageFilename = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public boolean isEmpty() {
		return ((firstName.equals("")) && (lastName.equals("")) && (nickname.equals("")) && (birthday.equals(""))
				&& (phone.equals("")) && (mail.equals("")) && (homepage.equals("")) && (street.equals(""))
				&& (city.equals("")) && (country.equals("")) && (notes.equals("")));
	}

	@Override
	public int compareTo(Human o) {
		return (lastName + " " + firstName).compareTo(o.getLastName() + " " + o.getFirstName());
	}

	public String toString() {
		return "[ " + firstName + " " + lastName + " " + nickname + " " + birthday + " " + phone + " " + mail + " "
				+ homepage + " " + street + " " + city + " " + country + " " + notes + " ]";
	}

}