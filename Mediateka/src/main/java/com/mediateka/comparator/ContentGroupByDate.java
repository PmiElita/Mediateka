package com.mediateka.comparator;

import java.util.Comparator;

import com.mediateka.model.ContentGroup;

public class ContentGroupByDate implements Comparator<ContentGroup>{

	@Override
	public int compare(ContentGroup o1, ContentGroup o2) {
		// TODO Auto-generated method stub
		return o2.getCreationDate().compareTo(o1.getCreationDate());
	}

}
