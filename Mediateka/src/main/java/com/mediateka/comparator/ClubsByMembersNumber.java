package com.mediateka.comparator;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.mediateka.model.Club;
import com.mediateka.model.ClubEventMember;
import com.mediateka.model.enums.State;

import static com.mediateka.service.ClubEventMemberService.getClubEventMemberByClubId;

public class ClubsByMembersNumber implements Comparator<Club> {
	private static Logger logger = Logger.getLogger(ClubsByMembersNumber.class);

	@Override
	public int compare(Club club1, Club club2) {
		int result = 0;
		try {
			List<ClubEventMember> members1 = getClubEventMemberByClubId(club1
					.getId());
			List<ClubEventMember> members2 = getClubEventMemberByClubId(club2
					.getId());
			int number1 = 0;
			int number2 = 0;
			if (members1 != null)
				if (!members1.isEmpty())
					for (ClubEventMember member : members1)
						if (member.getState() == State.ACTIVE)
							number1++;
			if (members2 != null)
				if (!members2.isEmpty())
					for (ClubEventMember member : members2)
						if (member.getState() == State.ACTIVE)
							number2++;
			if (number1 < number2)
				result = 1;
			else if (number1 > number2)
				result = -1;
			return result;
		} catch (SQLException | ReflectiveOperationException e) {
			logger.warn(e);
			return result;
		}
	}
}