package org.LTT.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.LTT.persistence.model.PeriodTimesheet;
import org.LTT.persistence.model.RegistrationPeriod;
import org.LTT.persistence.model.Reviews;
import org.LTT.web.viewmodel.MessageModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;

public class TimeValidation {

	public static boolean validationDateReview(@Validated Reviews reviews, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		boolean checkDate = true;
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date fromdatecv = null;
		Date todatecv = null;
		long formday = 0;
		long formmonth = 0;
		long today = 0;
		long tomonth = 0;
		try {
			fromdatecv = format.parse(reviews.getReviewsForm());
			todatecv = format.parse(reviews.getReviewTo());
			formday = fromdatecv.getDate();
			formmonth = fromdatecv.getMonth() + 1;
			today = todatecv.getDate();
			tomonth = todatecv.getMonth() + 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (formmonth > tomonth) {

			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("form date than to date ");
			redirectAttributes.addFlashAttribute("message", messageModel);
			checkDate = false;
		} else if (formmonth == tomonth) {
			if ((today) < formday) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("form day than to day  ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				checkDate = false;
			}

		}

		System.out.println("reviews.getReviewsForm() " + reviews.getReviewsForm());
		System.out.println("reviews.getReviewTo() " + reviews.getReviewTo());

		System.out.println("fromdatecv " + fromdatecv);
		System.out.println("todatecv " + todatecv);
		System.out.println("formmonth " + formmonth);
		System.out.println("tomonth " + tomonth);
		System.out.println("today " + today);
		System.out.println("formday " + formday);
		return checkDate;
	}

	public static boolean validationDate(@Validated PeriodTimesheet periodTimesheet, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		boolean checkDate = true;
		String pattern = "yyyy:MM:dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		String fromdate = (periodTimesheet.getFormDate()).toString();

		String todate = (periodTimesheet.getToDate()).toString();
		Date fromdatecv = null;
		Date todatecv = null;
		long formday = 0;
		long formmonth = 0;
		long today = 0;
		long tomonth = 0;
		try {
			fromdatecv = format.parse(fromdate);
			todatecv = format.parse(todate);
			formday = fromdatecv.getDate();
			formmonth = fromdatecv.getMonth() + 1;
			today = todatecv.getDate();
			tomonth = todatecv.getMonth() + 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (formmonth > tomonth) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("form date than to date ");
			redirectAttributes.addFlashAttribute("message", messageModel);
			checkDate = false;
		} else if (formmonth == tomonth) {
			if ((today) < formday) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("form day than to day  ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				checkDate = false;
			}

		}

		System.out.println("fromdate " + fromdate);
		System.out.println("todate " + todate);
		System.out.println("fromdatecv " + fromdatecv);
		System.out.println("todatecv " + todatecv);
		System.out.println("formmonth " + formmonth);
		System.out.println("tomonth " + tomonth);
		System.out.println("today " + today);
		System.out.println("formday " + formday);

		return checkDate;
	}

	public static boolean validation(@Validated RegistrationPeriod registrationPeriod, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		String pattern = "hh:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String FromDateMonday = registrationPeriod.getFromDateMonday().trim();
		String ToDateMonday = registrationPeriod.getToDateMonday().trim();
		String FromDateFriday = registrationPeriod.getFromDateFriday().trim();
		String ToDateFriday = registrationPeriod.getToDateFriday().trim();
		String ToDateThursday = registrationPeriod.getToDateThursday().trim();
		String FromDateThursday = registrationPeriod.getFromDateThursday().trim();
		String FromDateTuesday = registrationPeriod.getFromDateTuesday().trim();
		String ToDateTuesday = registrationPeriod.getToDateTuesday().trim();
		String FromDateWednesday = registrationPeriod.getFromDateWednesday().trim();
		String ToDateWednesday = registrationPeriod.getToDateWednesday().trim();
		boolean check = true;

		if (!Strings.isNullOrEmpty(FromDateMonday)) {
			System.out.println("registrationPeriod.getFromDateMonday()  = " + registrationPeriod.getFromDateMonday());
			long fromMondayHour = 0;
			long toMondayHour = 0;

			try {
				System.out.println(" destiny ToDateMonday  " + ToDateMonday);
				Date checkFromMonday = format.parse(FromDateMonday);
				Date checkToMonday = format.parse(ToDateMonday);
				System.out.println("fromMondayHour   = " + checkFromMonday.getHours());
				System.out.println("toMondayHour   = " + checkToMonday.getHours());
				fromMondayHour = checkFromMonday.getHours();
				toMondayHour = checkToMonday.getHours();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Strings.isNullOrEmpty(ToDateMonday) && (!Strings.isNullOrEmpty(FromDateMonday))) {
				System.out.println(" ToDateMonday.isEmpty() ");
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("ToMonday Not Null ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}

			else if (toMondayHour < fromMondayHour) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("Time to Monday than Time from Monday ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}

		}
		if (!Strings.isNullOrEmpty(ToDateMonday) && Strings.isNullOrEmpty(FromDateMonday)) {
			System.out.println(" ToDateMonday !=null ");

			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add(" From Monday Not Null ");
			redirectAttributes.addFlashAttribute("message", messageModel);
			check = false;

		}

		if (!Strings.isNullOrEmpty(FromDateThursday)) {

			long FromDateThursdayHour = 0;
			long toThursdayHour = 0;
			try {
				Date checkFromDateThursday = format.parse(FromDateThursday);
				Date checkToThursday = format.parse(ToDateThursday);

				FromDateThursdayHour = checkFromDateThursday.getHours();
				toThursdayHour = checkToThursday.getHours();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Strings.isNullOrEmpty(ToDateThursday) && !Strings.isNullOrEmpty(FromDateThursday)) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("ToDateThursday Not Null ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			} else if (toThursdayHour < FromDateThursdayHour) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("Time to Thursday than Time from Thursday ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}

		}
		if (!Strings.isNullOrEmpty(ToDateThursday)) {
			if (FromDateThursday.isEmpty()) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add(" From Thursday Not Null ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}
		}

		if (!Strings.isNullOrEmpty(FromDateFriday)) {

			long FromDateFridayHour = 0;
			long toFridayHour = 0;
			try {
				Date checkFromDateFriday = format.parse(FromDateFriday);
				Date checkToFriday = format.parse(ToDateFriday);

				FromDateFridayHour = checkFromDateFriday.getHours();
				toFridayHour = checkToFriday.getHours();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Strings.isNullOrEmpty(ToDateFriday) && !Strings.isNullOrEmpty(FromDateFriday)) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("ToDateFriday Not Null ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			} else if (toFridayHour < FromDateFridayHour) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("Time to Friday than Time from Friday ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}

		}
		if ((!Strings.isNullOrEmpty(ToDateFriday)) && (Strings.isNullOrEmpty(FromDateFriday))) {

			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add(" From Friday Not Null ");
			redirectAttributes.addFlashAttribute("message", messageModel);
			check = false;

		}

		if (!Strings.isNullOrEmpty(FromDateTuesday)) {
			long FromDateTuesdayHour = 0;
			long toTuesdayHour = 0;
			try {
				Date checkFromDateTuesday = format.parse(FromDateTuesday);
				Date checkToTuesday = format.parse(ToDateTuesday);

				FromDateTuesdayHour = checkFromDateTuesday.getHours();
				toTuesdayHour = checkToTuesday.getHours();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Strings.isNullOrEmpty(ToDateTuesday) && !Strings.isNullOrEmpty(FromDateTuesday)) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("ToDateTuesday Not Null ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			} else if (toTuesdayHour < FromDateTuesdayHour) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("Time to Tuesday than Time from Tuesday ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}

		}
		if ((!Strings.isNullOrEmpty(ToDateTuesday)) && (Strings.isNullOrEmpty(FromDateTuesday))) {
			System.out.println("ToDateTuesday  = " + ToDateTuesday);
			System.out.println("FromDateTuesday = " + FromDateTuesday);
			System.out.println(!Strings.isNullOrEmpty(ToDateTuesday));
			System.out.println(ToDateTuesday != null);
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add(" From Tuesday Not Null ");
			redirectAttributes.addFlashAttribute("message", messageModel);
			check = false;
		}

		if (!Strings.isNullOrEmpty(FromDateWednesday)) {
			long FromDateWednesdayHour = 0;
			long toWednesdayHour = 0;
			try {
				Date checkFromDateWednesday = format.parse(FromDateWednesday);
				Date checkToWednesday = format.parse(ToDateWednesday);

				FromDateWednesdayHour = checkFromDateWednesday.getHours();
				toWednesdayHour = checkToWednesday.getHours();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((ToDateWednesday == null) && (FromDateWednesday != null)) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("ToDateWednesday Not Null ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			} else if (toWednesdayHour < FromDateWednesdayHour) {
				MessageModel messageModel = new MessageModel();
				messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
				messageModel.getMessages().add("Time to Wednesday than Time from Wednesday ");
				redirectAttributes.addFlashAttribute("message", messageModel);
				check = false;
			}

		}
		// if (ToDateWednesday !=null) {
		// if (FromDateMonday.isEmpty()) {
		// MessageModel messageModel = new MessageModel();
		// messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
		// messageModel.getMessages().add(" From Wednesday Not Null ");
		// redirectAttributes.addFlashAttribute("message", messageModel);
		// check = false;
		// }
		// }

		return check;
	}

}
