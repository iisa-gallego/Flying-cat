package Model;

import java.util.Comparator;

public class byTime implements Comparator<player>{

	@Override
	public int compare(player o1, player o2) {
		// TODO Auto-generated method stub
		return o1.getTime().compareTo(o2.getTime());
	}

}
