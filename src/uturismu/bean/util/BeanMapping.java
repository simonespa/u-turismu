package uturismu.bean.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import uturismu.bean.BookerBean;
import uturismu.bean.BookerSignup;
import uturismu.bean.BookerUpdate;
import uturismu.bean.CityBean;
import uturismu.bean.GenderBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.HolidayPackageCreateBean;
import uturismu.bean.HolidayPackageSummaryBean;
import uturismu.bean.TourOperatorBean;
import uturismu.bean.TourOperatorSignup;
import uturismu.bean.TourOperatorUpdate;
import uturismu.bean.UserSignup;
import uturismu.dto.Account;
import uturismu.dto.Address;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IdType;
import uturismu.dto.enumtype.Status;

public class BeanMapping {

	private BeanMapping() {
	}

	public static Booker getBooker(BookerSignup bean, City birthPlace, City residenceCity) {
		Booker dto = new Booker();
		dto.setTaxCode(bean.getTaxCode());
		dto.setFirstName(bean.getFirstName());
		dto.setLastName(bean.getLastName());
		// acquisisce la stringa che denota il sesso
		String gender = bean.getGender();
		if (gender.equals("m")) {
			dto.setGender(Gender.MALE);
		} else if (gender.equals("f")) {
			dto.setGender(Gender.FEMALE);
		} else if (gender.equals("-")) {
			dto.setGender(Gender.NOT_SPECIFIED);
		}
		// setta la città con un oggetto City di tipo DTO
		dto.setBirthPlace(birthPlace);
		// acquisisce il giorno/mese/anno di nascita
		Integer day = bean.getBirthDay();
		Integer month = bean.getBirthMonth();
		Integer year = bean.getBirthYear();
		// acquisisce un'istanza di Calendare e setta la data corretta
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		dto.setBirthDate(calendar.getTime());
		// setta l'indirizzo
		Address address = new Address();
		address.setStreet(bean.getStreet());
		address.setZipCode(bean.getZipCode());
		address.setCity(residenceCity);
		dto.setResidence(address);
		// acquisisce la stringa che denota il tipo di documento
		String documentType = bean.getIdentificationDocumentType();
		if (documentType.equals("id")) {
			dto.setIdentificationDocumentType(IdType.ID);
		} else if (documentType.equals("pat")) {
			dto.setIdentificationDocumentType(IdType.DRIVER_LICENSE);
		} else if (documentType.equals("pas")) {
			dto.setIdentificationDocumentType(IdType.PASSPORT);
		} else if (documentType.equals("vis")) {
			dto.setIdentificationDocumentType(IdType.VISA);
		}
		dto.setIdentificationDocumentNumber(bean.getIdentificationDocumentNumber());
		dto.setIssuingAuthority(bean.getIssuingAuthority());
		return dto;
	}

	public static Booker getBooker(BookerUpdate bean, City birthPlace, City residenceCity, Booker dto) {
		// Booker dto = new Booker();
		dto.setTaxCode(bean.getTaxCode());
		dto.setFirstName(bean.getFirstName());
		dto.setLastName(bean.getLastName());
		// acquisisce la stringa che denota il sesso
		String gender = bean.getGender();
		if (gender.equals("m")) {
			dto.setGender(Gender.MALE);
		} else if (gender.equals("f")) {
			dto.setGender(Gender.FEMALE);
		} else if (gender.equals("-")) {
			dto.setGender(Gender.NOT_SPECIFIED);
		}
		// setta la città con un oggetto City di tipo DTO
		dto.setBirthPlace(birthPlace);
		// acquisisce il giorno/mese/anno di nascita
		Integer day = bean.getBirthDay();
		Integer month = bean.getBirthMonth();
		Integer year = bean.getBirthYear();
		// acquisisce un'istanza di Calendare e setta la data corretta
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		dto.setBirthDate(calendar.getTime());
		// setta l'indirizzo
		Address address = new Address();
		address.setStreet(bean.getStreet());
		address.setZipCode(bean.getZipCode());
		address.setCity(residenceCity);
		dto.setResidence(address);
		// acquisisce la stringa che denota il tipo di documento
		String documentType = bean.getIdentificationDocumentType();
		if (documentType.equals("id")) {
			dto.setIdentificationDocumentType(IdType.ID);
		} else if (documentType.equals("pat")) {
			dto.setIdentificationDocumentType(IdType.DRIVER_LICENSE);
		} else if (documentType.equals("pas")) {
			dto.setIdentificationDocumentType(IdType.PASSPORT);
		} else if (documentType.equals("vis")) {
			dto.setIdentificationDocumentType(IdType.VISA);
		}
		dto.setIdentificationDocumentNumber(bean.getIdentificationDocumentNumber());
		dto.setIssuingAuthority(bean.getIssuingAuthority());
		return dto;
	}

	public static HolidayPackage getHolidayPackage(HolidayPackageCreateBean bean) {
		HolidayPackage pack = new HolidayPackage();
		pack.setAvailability(bean.getAvailability());
		pack.setCustomerNumber(bean.getCustomerNumber());
		pack.setDescription(bean.getDescription());

		Calendar c = Calendar.getInstance();
		c.set(bean.getYear(), bean.getMonth(), bean.getDay());
		pack.setDueDate(c.getTime());

		pack.setName(bean.getName());

		String str = bean.getStatus();
		if (str.equals("pub")) {
			pack.setStatus(Status.PUBLISHED);
		} else if (str.equals("draft")) {
			pack.setStatus(Status.DRAFT);
		} else if (str.equals("exp")) {
			pack.setStatus(Status.EXPIRED);
		} else {
			pack.setStatus(Status.DRAFT);
		}
		return pack;
	}

	public static TourOperator getTourOperator(TourOperatorSignup bean, City city) {
		TourOperator dto = new TourOperator();
		dto.setName(bean.getName());
		dto.setVatNumber(bean.getVatNumber());
		dto.setHolderName(bean.getHolderName());
		Address address = new Address();
		address.setStreet(bean.getStreet());
		address.setZipCode(bean.getZipCode());
		address.setCity(city);
		dto.setHeadOffice(address);
		return dto;
	}

	public static Account getAccount(UserSignup bean) {
		Account account = new Account();
		account.setEmail(bean.getEmail());
		account.setPassword(bean.getPassword());
		return account;
	}

	public static List<CityBean> encode(Collection<City> collection) {
		List<CityBean> cityList = new ArrayList<CityBean>(collection.size());
		for (City c : collection) {
			cityList.add(encode(c));
		}
		return cityList;
	}

	public static CityBean encode(City city) {
		CityBean bean = new CityBean();
		bean.setId(city.getId());
		bean.setName(city.getName());
		return bean;
	}

	public static List<HolidayPackageSummaryBean> encode(List<HolidayPackage> collection) {
		List<HolidayPackageSummaryBean> holidayPackageList = new ArrayList<HolidayPackageSummaryBean>(
				collection.size());
		for (HolidayPackage h : collection) {
			holidayPackageList.add(getHolidayPackageSummary(h));
		}
		return holidayPackageList;
	}

	public static HolidayPackageSummaryBean getHolidayPackageSummary(HolidayPackage holidayPackage) {
		HolidayPackageSummaryBean bean = new HolidayPackageSummaryBean();
		bean.setId(holidayPackage.getId());
		bean.setName(holidayPackage.getName());
		bean.setDescription(holidayPackage.getDescription());
		Set<Service> services = holidayPackage.getServices();
		Double price = 0.0;
		for (Service service : services) {
			price += service.getPrice();
		}
		bean.setPrice(price);
		bean.setStatus(holidayPackage.getStatus().toString());
		return bean;
	}

	public static HolidayPackageBean getHolidayPackage(HolidayPackage holiday) {
		HolidayPackageBean bean = new HolidayPackageBean();
		bean.setAvailability(holiday.getAvailability());
		bean.setCounter(holiday.getCounter());
		bean.setCustomerNumber(holiday.getCustomerNumber());
		bean.setDescription(holiday.getDescription());
		bean.setDueDate(holiday.getDueDate());
		bean.setId(holiday.getId());
		bean.setName(holiday.getName());
		bean.setStatus(holiday.getStatus());
		TourOperator tour = holiday.getTourOperator();
		bean.setTourOperatorEmail(tour.getAccount().getEmail());
		bean.setTourOperatorHolderName(tour.getHolderName());
		bean.setTourOperatorName(tour.getName());
		return bean;
	}

	public static BookerBean encode(Account account, Booker booker) {
		BookerBean bean = new BookerBean();
		bean.setUserId(booker.getId());
		bean.setEmail(account.getEmail());
		bean.setType(AccountType.BOOKER);

		bean.setTaxCode(booker.getTaxCode());
		bean.setFirstName(booker.getFirstName());
		bean.setLastName(booker.getLastName());
		return bean;
	}

	public static TourOperatorBean encode(Account account, TourOperator tourOperator) {
		TourOperatorBean bean = new TourOperatorBean();
		Long id = tourOperator.getId();
		bean.setUserId(id);
		bean.setEmail(account.getEmail());
		bean.setType(AccountType.TOUR_OPERATOR);

		bean.setVatNumber(tourOperator.getVatNumber());
		bean.setName(tourOperator.getName());
		bean.setHolderName(tourOperator.getHolderName());
		return bean;
	}

	public static TourOperatorUpdate encodeTourOperatorUpdate(Account account,
			TourOperator tourOperator) {
		TourOperatorUpdate bean = new TourOperatorUpdate();
		bean.setCity(tourOperator.getHeadOffice().getCity().getId());
		bean.setEmail(account.getEmail());
		bean.setHolderName(tourOperator.getHolderName());
		bean.setName(tourOperator.getName());
		// bean.setPassword(account.getPassword());
		bean.setStreet(tourOperator.getHeadOffice().getStreet());
		bean.setVatNumber(tourOperator.getVatNumber());
		bean.setZipCode(tourOperator.getHeadOffice().getZipCode());
		return bean;

	}

	public static Booker getBooker(BookerUpdate bean, City birthPlace, City residenceCity) {
		Booker dto = new Booker();
		dto.setTaxCode(bean.getTaxCode());
		dto.setFirstName(bean.getFirstName());
		dto.setLastName(bean.getLastName());
		// acquisisce la stringa che denota il sesso
		String gender = bean.getGender();
		if (gender.equals("m")) {
			dto.setGender(Gender.MALE);
		} else if (gender.equals("f")) {
			dto.setGender(Gender.FEMALE);
		} else if (gender.equals("-")) {
			dto.setGender(Gender.NOT_SPECIFIED);
		}
		// setta la città con un oggetto City di tipo DTO
		dto.setBirthPlace(birthPlace);
		// acquisisce il giorno/mese/anno di nascita
		Integer day = bean.getBirthDay();
		Integer month = bean.getBirthMonth();
		Integer year = bean.getBirthYear();
		// acquisisce un'istanza di Calendare e setta la data corretta
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		dto.setBirthDate(calendar.getTime());
		// setta l'indirizzo
		Address address = new Address();
		address.setStreet(bean.getStreet());
		address.setZipCode(bean.getZipCode());
		address.setCity(residenceCity);
		dto.setResidence(address);
		// acquisisce la stringa che denota il tipo di documento
		String documentType = bean.getIdentificationDocumentType();
		if (documentType.equals("id")) {
			dto.setIdentificationDocumentType(IdType.ID);
		} else if (documentType.equals("pat")) {
			dto.setIdentificationDocumentType(IdType.DRIVER_LICENSE);
		} else if (documentType.equals("pas")) {
			dto.setIdentificationDocumentType(IdType.PASSPORT);
		} else if (documentType.equals("vis")) {
			dto.setIdentificationDocumentType(IdType.VISA);
		}
		dto.setIdentificationDocumentNumber(bean.getIdentificationDocumentNumber());
		return dto;
	}

	public static BookerUpdate encodeBookerUpdate(Account account, Booker booker) {
		BookerUpdate update = new BookerUpdate();
		Date data = booker.getBirthDate();
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		update.setBirthDay(c.get(Calendar.DAY_OF_MONTH));
		update.setBirthMonth(c.get(Calendar.MONTH) + 1);
		update.setBirthYear(c.get(Calendar.YEAR));

		update.setBirthPlace(booker.getBirthPlace().getId());
		update.setCity(booker.getResidence().getCity().getId());

		update.setEmail(account.getEmail());
		update.setFirstName(booker.getFirstName());

		GenderBean gender = new GenderBean();

		if (booker.getGender().equals(Gender.MALE)) {
			gender.setLabel("Maschile");
			gender.setValue("m");
		} else if (booker.getGender().equals(Gender.FEMALE)) {
			gender.setLabel("Femminile");
			gender.setValue("f");
		} else {
			gender.setLabel("--Sesso");
			gender.setValue("-");
		}
		update.setGender(gender.getValue());
		update.setIdentificationDocumentNumber(booker.getIdentificationDocumentNumber());

		// acquisisce la stringa che denota il tipo di documento
		String documentType = booker.getIdentificationDocumentNumber();
		if (documentType.equals(IdType.ID)) {
			update.setIdentificationDocumentType("id");
		} else if (documentType.equals(IdType.DRIVER_LICENSE)) {
			update.setIdentificationDocumentType("pat");
		} else if (documentType.equals(IdType.PASSPORT)) {
			update.setIdentificationDocumentType("pas");
		} else if (documentType.equals(IdType.VISA)) {
			update.setIdentificationDocumentType("vis");
		} else {
			update.setIdentificationDocumentType("--");
		}

		update.setIssuingAuthority(booker.getIssuingAuthority());
		update.setLastName(booker.getLastName());
		update.setPassword("");
		update.setStreet(booker.getResidence().getStreet());
		update.setTaxCode(booker.getTaxCode());
		update.setZipCode(booker.getResidence().getZipCode());

		return update;
	}

}