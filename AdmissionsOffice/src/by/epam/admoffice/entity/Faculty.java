package by.epam.admoffice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Faculty extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int govSubsidizedCount;
	private int feePayingCount;
	private int dLearningCount;
	private List<Entrant> entrants = new ArrayList<Entrant>();

	public Faculty() {
	}

	public Faculty(int id, String name, int govSubsidizedCount,
			int feePayingCount, int dLearningCount) {
		super(id);
		this.setName(name);
		this.setGovSubsidizedCount(govSubsidizedCount);
		this.setFeePayingCount(feePayingCount);
		this.setdLearningCount(dLearningCount);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGovSubsidizedCount() {
		return govSubsidizedCount;
	}

	public void setGovSubsidizedCount(int govSubsidizedCount) {
		this.govSubsidizedCount = govSubsidizedCount;
	}

	public int getFeePayingCount() {
		return feePayingCount;
	}

	public void setFeePayingCount(int feePayingCount) {
		this.feePayingCount = feePayingCount;
	}

	public int getdLearningCount() {
		return dLearningCount;
	}

	public void setdLearningCount(int dLearningCount) {
		this.dLearningCount = dLearningCount;
	}

	public List<Entrant> getEntrants() {
		return Collections.unmodifiableList(this.entrants);
	}

	public void addEntrant(Entrant ent) {
		this.entrants.add(ent);
	}

	public void setEntrants(List<Entrant> entrants) {
		this.entrants = entrants;
	}

}
