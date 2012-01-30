/*
 * This file is part of "U Turismu" project. 
 * 
 * U Turismu is an enterprise application in support of calabrian tour operators.
 * This system aims to promote tourist services provided by the operators
 * and to develop and improve tourism in Calabria.
 *
 * Copyright (C) 2012 "LagrecaSpaccarotella" team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uturismu.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity
public class Booking implements Serializable {

	private static final long serialVersionUID = 7295908518751530161L;
	private Long id;
	private Date bookingTimeAndDate;
	private Booker booker;
	private HolidayPackage holidayPackage;
	private Set<Customer> customers;

	public Booking() {
		customers = new HashSet<Customer>();
	}

	public Long getId() {
		return id;
	}

	public Date getBookingTimeAndDate() {
		return bookingTimeAndDate;
	}

	public Booker getBooker() {
		return booker;
	}

	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}

	public Set<Customer> getCustomers() {
		return Collections.unmodifiableSet(customers);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBookingTimeAndDate(Date bookingTimeAndDate) {
		this.bookingTimeAndDate = bookingTimeAndDate;
	}

	public void setBooker(Booker booker) {
		this.booker = booker;
	}

	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}

	protected void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public boolean addCustomer(Customer customer) {
		return customers.add(customer);
	}

	public boolean removeCustomer(Customer customer) {
		return customers.remove(customer);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booker == null) ? 0 : booker.hashCode());
		result = prime * result + ((bookingTimeAndDate == null) ? 0 : bookingTimeAndDate.hashCode());
		result = prime * result + ((customers == null) ? 0 : customers.hashCode());
		result = prime * result + ((holidayPackage == null) ? 0 : holidayPackage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (booker == null) {
			if (other.booker != null)
				return false;
		} else if (!booker.equals(other.booker))
			return false;
		if (bookingTimeAndDate == null) {
			if (other.bookingTimeAndDate != null)
				return false;
		} else if (!bookingTimeAndDate.equals(other.bookingTimeAndDate))
			return false;
		if (customers == null) {
			if (other.customers != null)
				return false;
		} else if (!customers.equals(other.customers))
			return false;
		if (holidayPackage == null) {
			if (other.holidayPackage != null)
				return false;
		} else if (!holidayPackage.equals(other.holidayPackage))
			return false;
		return true;
	}

}
