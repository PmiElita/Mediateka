package com.mediateka.comparator;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.mediateka.controller.UserController;
import com.mediateka.model.Book;
import com.mediateka.service.FormRecordService;

public class BookByFormRecordCount implements Comparator<Book>{
	private static Logger logger = Logger.getLogger(UserController.class);
	@Override
	public int compare(Book o1, Book o2) {
		
		try {
			Integer book1Count=FormRecordService.getFormRecordCountByBookId(o1.getId());
			Integer book2Count=FormRecordService.getFormRecordCountByBookId(o2.getId());
			return book1Count.compareTo(book2Count);
		} catch (Exception e) {
			logger.warn("wrong comparator",e);
		}
		return 0;
	}

}
