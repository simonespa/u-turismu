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
package uturismu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uturismu.dto.Booker;
import uturismu.dto.HolidayPackage;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
public interface BookerService {

	public void book(Booker booker, HolidayPackage holidayPackage);
	
	public List<HolidayPackage> getAllHolidayPackage(Long bookerID);
	
	public List<HolidayPackage> getBookedHolidayPackage(Long bookerID);
	
	public List<HolidayPackage> getExpireBookedPackage(Long bookerID);
	
	
}
