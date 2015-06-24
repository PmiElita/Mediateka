package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.FormRecord;

public class FormRecordsByDateFrom implements Comparator<FormRecord> {

	@Override
	public int compare(FormRecord f1, FormRecord f2) {
		if (f1.getDateFrom()==null){
			return -1;
		}
		if (f2.getDateFrom()==null){
			return 1;
		}
		long f1Date=f1.getDateFrom().getTime();
		long f2Date = f2.getDateFrom().getTime();
		if (f1Date>f2Date){
			return 1;
		}
		if (f1Date<f2Date){
			return -1;
		}
		return 0;
	}

}
