package Model;

import java.util.Comparator;

public class byDate implements Comparator<player>{

	@Override
	public int compare(player o1, player o2) {
		// TODO Auto-generated method stub
		return o1.getDate2().compareTo(o2.getDate2());
	}

}
