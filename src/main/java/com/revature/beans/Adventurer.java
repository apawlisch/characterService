package com.revature.beans;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("adventurer")
public class Adventurer implements Serializable {
	@PrimaryKey
	private String characterName;
	
	private Integer strength;
	private Integer dexterity;
	private Integer constitution;
	private Integer intelligence;
	private Integer wisdom;
	private Integer charisma;
	
	private Integer armorClass;
	private Integer initiative;
	private Integer maxHitPoints;
	private Integer currentHitPoints;
	private Integer speed;
	
	public Adventurer() {
		super();
	}
	
	public Adventurer(String characterName, Integer strength, Integer dexterity,
			Integer constitution, Integer intelligence, Integer wisdom, Integer charisma, Integer armorClass,
			Integer initiative, Integer maxHitPoints, Integer currentHitPoints, Integer speed) {
		this();
		this.characterName = characterName;
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.wisdom = wisdom;
		this.charisma = charisma;
		this.armorClass = armorClass;
		this.initiative = initiative;
		this.maxHitPoints = maxHitPoints;
		this.currentHitPoints = currentHitPoints;
		this.speed = speed;
	}
	
	public Adventurer(String characterName) {
		this();
		this.characterName = characterName;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getDexterity() {
		return dexterity;
	}

	public void setDexterity(Integer dexterity) {
		this.dexterity = dexterity;
	}

	public Integer getConstitution() {
		return constitution;
	}

	public void setConstitution(Integer constitution) {
		this.constitution = constitution;
	}

	public Integer getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getWisdom() {
		return wisdom;
	}

	public void setWisdom(Integer wisdom) {
		this.wisdom = wisdom;
	}

	public Integer getCharisma() {
		return charisma;
	}

	public void setCharisma(Integer charisma) {
		this.charisma = charisma;
	}

	public Integer getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(Integer armorClass) {
		this.armorClass = armorClass;
	}

	public Integer getInitiative() {
		return initiative;
	}

	public void setInitiative(Integer initiative) {
		this.initiative = initiative;
	}

	public Integer getMaxHitPoints() {
		return maxHitPoints;
	}

	public void setMaxHitPoints(Integer maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}

	public Integer getCurrentHitPoints() {
		return currentHitPoints;
	}

	public void setCurrentHitPoints(Integer currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((armorClass == null) ? 0 : armorClass.hashCode());
		result = prime * result + ((characterName == null) ? 0 : characterName.hashCode());
		result = prime * result + ((charisma == null) ? 0 : charisma.hashCode());
		result = prime * result + ((constitution == null) ? 0 : constitution.hashCode());
		result = prime * result + ((currentHitPoints == null) ? 0 : currentHitPoints.hashCode());
		result = prime * result + ((dexterity == null) ? 0 : dexterity.hashCode());
		result = prime * result + ((initiative == null) ? 0 : initiative.hashCode());
		result = prime * result + ((intelligence == null) ? 0 : intelligence.hashCode());
		result = prime * result + ((maxHitPoints == null) ? 0 : maxHitPoints.hashCode());
		result = prime * result + ((speed == null) ? 0 : speed.hashCode());
		result = prime * result + ((strength == null) ? 0 : strength.hashCode());
		result = prime * result + ((wisdom == null) ? 0 : wisdom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adventurer other = (Adventurer) obj;
		if (armorClass == null) {
			if (other.armorClass != null)
				return false;
		} else if (!armorClass.equals(other.armorClass))
			return false;
		if (characterName == null) {
			if (other.characterName != null)
				return false;
		} else if (!characterName.equals(other.characterName))
			return false;
		if (charisma == null) {
			if (other.charisma != null)
				return false;
		} else if (!charisma.equals(other.charisma))
			return false;
		if (constitution == null) {
			if (other.constitution != null)
				return false;
		} else if (!constitution.equals(other.constitution))
			return false;
		if (currentHitPoints == null) {
			if (other.currentHitPoints != null)
				return false;
		} else if (!currentHitPoints.equals(other.currentHitPoints))
			return false;
		if (dexterity == null) {
			if (other.dexterity != null)
				return false;
		} else if (!dexterity.equals(other.dexterity))
			return false;
		if (initiative == null) {
			if (other.initiative != null)
				return false;
		} else if (!initiative.equals(other.initiative))
			return false;
		if (intelligence == null) {
			if (other.intelligence != null)
				return false;
		} else if (!intelligence.equals(other.intelligence))
			return false;
		if (maxHitPoints == null) {
			if (other.maxHitPoints != null)
				return false;
		} else if (!maxHitPoints.equals(other.maxHitPoints))
			return false;
		if (speed == null) {
			if (other.speed != null)
				return false;
		} else if (!speed.equals(other.speed))
			return false;
		if (strength == null) {
			if (other.strength != null)
				return false;
		} else if (!strength.equals(other.strength))
			return false;
		if (wisdom == null) {
			if (other.wisdom != null)
				return false;
		} else if (!wisdom.equals(other.wisdom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adventurer [characterName=" + characterName + ", strength=" + strength + ", dexterity=" + dexterity
				+ ", constitution=" + constitution + ", intelligence=" + intelligence + ", wisdom=" + wisdom
				+ ", charisma=" + charisma + ", armorClass=" + armorClass + ", initiative=" + initiative
				+ ", maxHitPoints=" + maxHitPoints + ", currentHitPoints=" + currentHitPoints + ", speed=" + speed
				+ "]";
	}
	

}
