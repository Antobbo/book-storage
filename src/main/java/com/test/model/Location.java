package com.test.model;

public enum Location {
	DOWNSTAIRS(1),
	UPSTAIRS_FIRST_BEDROOM(2),
	UPSTAIRS_SECOND_BEDROOM(3),
	UPSTAIRS_SPARE_ROOM(4),
	ON_LOAN(5);
	
	private int value;
	
	private Location(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
