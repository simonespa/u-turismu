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
import java.sql.Date;
import java.util.Set;

import uturismu.dto.util.Gender;
import uturismu.dto.util.IDType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = -6323910189513397033L;
	/* è il codice fiscale */
	private String taxCode;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthDate;
	private City birthPlace;
	private Address livingPlace;
	/* è il codice del documento identificativo */
	private String idNumber;
	/* è la tipologia (patente, passaporto, ecc) */
	private IDType idType;
	private String issuingAuthority;
	private Booker booker;
	private Set<Booking> bookings;

}