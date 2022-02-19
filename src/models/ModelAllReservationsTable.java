package models;

import java.sql.Time;
import java.util.Date;

public class ModelAllReservationsTable {

	int reservationId;
	String email;
	String dni;
	String shop;
	String barCode;
	int quantity;
	double price;
	Date endReservationDate;
	Time endReservationTime;
	
	public ModelAllReservationsTable(int reservationId, String email, String dni, String shop, String barCode,
			int quantity, double price, Date endReservationDate, Time endReservationTime) {
		super();
		this.reservationId = reservationId;
		this.email = email;
		this.dni = dni;
		this.shop = shop;
		this.barCode = barCode;
		this.quantity = quantity;
		this.price = price;
		this.endReservationDate = endReservationDate;
		this.endReservationTime = endReservationTime;
	}

	public int getReservationId() {
		return reservationId;
	}

	public String getStrReservationId() {
		return String.valueOf(reservationId);
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public String getStrQuantity() {
		return String.valueOf(quantity);
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}
	
	public String getStrPrice() {
		return String.valueOf(price);
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getEndReservationDate() {
		return endReservationDate;
	}
	
	public String getStrEndReservationDate() {
		return String.valueOf(endReservationDate);
	}

	public void setEndReservationDate(Date endReservationDate) {
		this.endReservationDate = endReservationDate;
	}
	
	public Date getEndReservationTime() {
		return endReservationTime;
	}
	
	public String getStrEndReservationTime() {
		return String.valueOf(endReservationTime);
	}

	public void setEndReservationTime(Time endReservationTime) {
		this.endReservationTime = endReservationTime;
	}
	
}
