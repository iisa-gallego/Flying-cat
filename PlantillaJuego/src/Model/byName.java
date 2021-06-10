package Model;

import java.util.Comparator;

public class byName implements Comparator<player> {

	@Override
	public int compare(player o1, player o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}

}
