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
package uturismu.dao;

import java.util.List;

import uturismu.dto.HolidayPackage;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public interface HolidayPackageDao extends GenericDao<HolidayPackage> {
	
	public List<HolidayPackage> findAllPublished();
	
	public Long getAllPublishedNumber();

	public List<HolidayPackage> findAllPublishedByTourOperator(Long id);

	public List<HolidayPackage> findAllPublishedByTags(Long... tags);

	public List<HolidayPackage> findAllDraftByTourOperator(Long id);

	public List<HolidayPackage> findAllExpiredByTourOperator(Long id);

	public List<HolidayPackage> findAllByTourOperator(Long id);
	
	public List<HolidayPackage> findAllBookedByBooker(Long id);
	
	public List<HolidayPackage> findAllExpiredBookedByBooker(Long id);
	

}