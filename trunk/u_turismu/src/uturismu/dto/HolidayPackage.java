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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "HOLIDAY_PACKAGE")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "id_tour_operator" }))
public class HolidayPackage implements Serializable {

	private static final long serialVersionUID = -138153679026481915L;
	private Long id;
	private String name;
	private String description;
	private Integer customerNumber;
	// è il numero di pacchetti vacanza messi a disposizione
	private Integer availability;
	// tiene il conto di quanti pacchetti sono stati venduti
	private Integer counter;
	private TourOperator tourOperator;
	private Set<Booking> bookings;
	private Set<Service> services;
	private Set<HolidayTag> holidayTags;

	public HolidayPackage() {
		bookings = new HashSet<Booking>();
		services = new HashSet<Service>();
		holidayTags = new HashSet<HolidayTag>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Column(name = "customer_number", nullable = false)
	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public Integer getAvailability() {
		return availability;
	}

	public Integer getCounter() {
		return counter;
	}

	@ManyToOne
	@JoinColumn(name = "id_tour_operator")
	@ForeignKey(name = "FK_HOLIDAYPACKAGE_TOUROPERATOR")
	public TourOperator getTourOperator() {
		return tourOperator;
	}

	@OneToMany(mappedBy = "holidayPackage")
	public Set<Booking> getBookings() {
		return Collections.unmodifiableSet(bookings);
	}

	@OneToMany(mappedBy = "holidayPackage")
	public Set<Service> getServices() {
		return Collections.unmodifiableSet(services);
	}

	@ManyToMany
	@JoinTable(name = "HOLIDAY_CLASSIFICATION", joinColumns = @JoinColumn(name = "id_holiday_package"), inverseJoinColumns = @JoinColumn(name = "id_holiday_tag"))
	@ForeignKey(name = "FK_HOLIDAYCLASSIFICATION_HOLIDAYPACKAGE", inverseName = "FK_HOLIDAYCLASSIFICATION_HOLIDAYTAG")
	public Set<HolidayTag> getHolidayTags() {
		return Collections.unmodifiableSet(holidayTags);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public void setTourOperator(TourOperator tourOperator) {
		this.tourOperator = tourOperator;
	}

	protected void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public boolean addBooking(Booking booking) {
		return bookings.add(booking);
	}

	public boolean removeBooking(Booking booking) {
		return bookings.remove(booking);
	}

	protected void setHolidayTags(Set<HolidayTag> holidayTags) {
		this.holidayTags = holidayTags;
	}

	public boolean addHolidayTag(HolidayTag holidayTag) {
		return holidayTags.add(holidayTag);
	}

	public boolean removeHolidayTag(HolidayTag holidayTag) {
		return holidayTags.remove(holidayTag);
	}

	protected void setServices(Set<Service> services) {
		this.services = services;
	}

	public boolean addService(Service service) {
		return services.add(service);
	}

	public boolean removeService(Service service) {
		return services.remove(service);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availability == null) ? 0 : availability.hashCode());
		result = prime * result + ((counter == null) ? 0 : counter.hashCode());
		result = prime * result + ((customerNumber == null) ? 0 : customerNumber.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((holidayTags == null) ? 0 : holidayTags.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tourOperator == null) ? 0 : tourOperator.hashCode());
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
		HolidayPackage other = (HolidayPackage) obj;
		if (availability == null) {
			if (other.availability != null)
				return false;
		} else if (!availability.equals(other.availability))
			return false;
		if (counter == null) {
			if (other.counter != null)
				return false;
		} else if (!counter.equals(other.counter))
			return false;
		if (customerNumber == null) {
			if (other.customerNumber != null)
				return false;
		} else if (!customerNumber.equals(other.customerNumber))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (holidayTags == null) {
			if (other.holidayTags != null)
				return false;
		} else if (!holidayTags.equals(other.holidayTags))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tourOperator == null) {
			if (other.tourOperator != null)
				return false;
		} else if (!tourOperator.equals(other.tourOperator))
			return false;
		return true;
	}

}
