package uturismu.controller;

import static uturismu.controller.util.SessionCheck.isTourOperator;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import uturismu.bean.AccountBean;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageCreateBean;
import uturismu.bean.HolidayPackageSummaryBean;
import uturismu.bean.TourOperatorBean;
import uturismu.bean.TourOperatorUpdate;
import uturismu.bean.util.BeanMapping;
import uturismu.bean.util.DateGenerator;
import uturismu.controller.util.SessionCheck;
import uturismu.dto.Account;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.Status;
import uturismu.service.AdministratorService;
import uturismu.service.TourOperatorService;
import uturismu.service.UserService;

@Controller
@SessionAttributes("account")
public class TourOperatorController {

	@Autowired
	private TourOperatorService touroperatorService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdministratorService adminservice;

	@RequestMapping(value = "publish", method = RequestMethod.POST)
	public String publish(@RequestParam("id") String id, HttpSession session, Model model) {
		if (isTourOperator(session)) {
			HolidayPackage holiday = userService.getHolidayPackageByID(Long.valueOf(id));
			holiday.setStatus(Status.PUBLISHED);
			touroperatorService.updateHolidayPackage(holiday);
		}
		return "redirect:home";
	}

	@RequestMapping(value = "to/packages", method = RequestMethod.GET)
	public String showPackages(@RequestParam(value = "type", required = true) String type,
			HttpSession session, Model model) {

		if (!isTourOperator(session)) {
			return "redirect:/";
		}

		AccountBean account = (AccountBean) session.getAttribute("account");
		System.out.println(account.getEmail());
		List<HolidayPackage> result;

		if (type.equals("PUBLISHED")) {
			result = touroperatorService.getPublishedHolidayPackages(account.getUserId());
		} else if (type.equals("DRAFT")) {
			result = touroperatorService.getDraftHolidayPackages(account.getUserId());
		} else if (type.equals("EXPIRED")) {
			result = touroperatorService.getExpiredHolidayPackages(account.getUserId());
		} else {
			result = touroperatorService.getAllHolidayPackages(account.getUserId());
		}

		List<HolidayPackageSummaryBean> holidayList = BeanMapping.encode(result);
		model.addAttribute("menu", "defaultMenu.jsp");
		model.addAttribute("content", "content.jsp");
		model.addAttribute("holidayList", holidayList);
		model.addAttribute("image", "resources/img/testata2.gif");
		return "home";
	}

	@RequestMapping(value = "/updateTo", method = RequestMethod.GET)
	public String prepareUpdate(HttpSession session, Model model) {
		if (!SessionCheck.isTourOperator(session)) {
			return "redirect:/";
		}
		StringBuffer page = new StringBuffer("touroperator/updateAccount");
		AccountBean accountBean = (AccountBean) session.getAttribute("account");
		TourOperator tourOperator = userService.getTourOperatorById(accountBean.getUserId());
		Account account = tourOperator.getAccount();
		TourOperatorUpdate beanTouroperator = BeanMapping.encodeTourOperatorUpdate(account,
				tourOperator);
		List<CityBean> cities = (BeanMapping.encode(adminservice.getCities()));
		model.addAttribute("updateData", beanTouroperator);
		model.addAttribute("cities", cities);
		model.addAttribute("image", "resources/img/testata2.gif");
		return page.toString();
	}

	@RequestMapping(value = "/updateTo", params = "doUpdate", method = RequestMethod.POST)
	public String doUpdate(@Valid TourOperatorUpdate updateData, BindingResult result,
			HttpSession session, Model model) {
		if (!isTourOperator(session)) {
			return "redirect:/";
		}

		AccountBean beanAccount = (AccountBean) session.getAttribute("account");

		boolean passwordErr = false;
		String pwd = updateData.getPassword();
		if (!pwd.isEmpty()) {
			if (pwd.length() < 3 || pwd.length() > 15) {
				passwordErr = true;
			}
		}

		if (result.hasErrors() || passwordErr) {
			if (passwordErr) {
				result.addError(new ObjectError("password", " campo password vuoto "));
			}

			updateData.setEmail(beanAccount.getEmail());
			List<CityBean> cities = (BeanMapping.encode(adminservice.getCities()));
			model.addAttribute("cities", cities);
			model.addAttribute("updateData", updateData);
			model.addAttribute("image", "resources/img/testata2.gif");
			System.out.println("ABBIAMO ERRORI ");
			return "touroperator/updateAccount";
		}

		Account account = userService.getAccountByEmail(beanAccount.getEmail());
		TourOperator tourOperator = account.getTourOperator();

		if (!pwd.isEmpty()) {
			account.setPassword(updateData.getPassword());
		}

		tourOperator.setHolderName(updateData.getHolderName());
		tourOperator.setVatNumber(updateData.getVatNumber());
		tourOperator.setName(updateData.getName());

		City city = adminservice.getCityById(updateData.getCity());
		tourOperator.getHeadOffice().setCity(city);
		tourOperator.getHeadOffice().setStreet(updateData.getStreet());
		tourOperator.getHeadOffice().setZipCode(updateData.getZipCode());

		try {
			System.out.println("PREPARO IL SALVATAGGIO ");
			userService.update(account, tourOperator);
			System.out.println("SALVATAGGIO CONCLUSO");

		} catch (DataIntegrityViolationException e) {
			updateData.setEmail(beanAccount.getEmail());
			List<CityBean> cities = (BeanMapping.encode(adminservice.getCities()));

			result.addError(new ObjectError("vatNumber", " campo password vuoto "));
			model.addAttribute("cities", cities);
			model.addAttribute("errMessage", "Vat Number esistente");
			model.addAttribute("updateData", updateData);
			model.addAttribute("image", "resources/img/testata2.gif");
			System.out.println("ABBIAMO ERRORI ");
			return "touroperator/updateAccount";
		}

		System.out.println("ritorno");
		beanAccount = BeanMapping.encode(account, tourOperator);

		model.addAttribute("account", beanAccount);
		return "redirect:/home";
	}

	@RequestMapping(value = "/newPackage", params = "new")
	public String addPackage(HttpSession session, Model model) {
		if (!isTourOperator(session)) {
			return "home";
		}

		HolidayPackageCreateBean holidayBean = new HolidayPackageCreateBean();

		model.addAttribute("addData", holidayBean);
		model.addAttribute("days", DateGenerator.getDays());
		model.addAttribute("months", DateGenerator.getMonths());
		model.addAttribute("years", DateGenerator.getFutureYears());
		model.addAttribute("image", "resources/img/testata2.gif");

		return "holidaypackage/createNew";
	}

	@RequestMapping(value = "addPackage", params = "doAdd", method = RequestMethod.POST)
	public String makePackagePersistent(@Valid HolidayPackageCreateBean bean, BindingResult result,
			HttpSession session, Model model) {
		if (!isTourOperator(session)) {
			return "redirect:home";
		}
		if (result.hasErrors()) {
			
			model.addAttribute("addData", bean);
			model.addAttribute("days", DateGenerator.getDays());
			model.addAttribute("months", DateGenerator.getMonths());
			model.addAttribute("years", DateGenerator.getFutureYears());
			return "holidaypackage/createNew";
		}

		TourOperatorBean beanAccount = (TourOperatorBean) session.getAttribute("account");
		HolidayPackage pack = BeanMapping.getHolidayPackage(bean);
		Account account = userService.getAccountByEmail(beanAccount.getEmail());
		TourOperator to = account.getTourOperator();
		pack.setTourOperator(to);
		
		try{
			touroperatorService.createHolidayPackage(pack);
			
		}catch(DataIntegrityViolationException ex){
			System.out.println(ex.getMessage());
			
			model.addAttribute("addData", bean);
			model.addAttribute("days", DateGenerator.getDays());
			model.addAttribute("months", DateGenerator.getMonths());
			model.addAttribute("years", DateGenerator.getFutureYears());
			model.addAttribute("image", "resources/img/testata2.gif");
			return "holidaypackage/createNew";
			
		}

		System.out.println("OK MI e' ARRIVATO UN PACCHETTO");
		model.addAttribute("image", "resources/img/testata2.gif");
		return "redirect:/home";
	}

}
