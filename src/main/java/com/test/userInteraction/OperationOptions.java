package com.test.userInteraction;

public enum OperationOptions {
	READ(1),
	CREATE(2),
	UPDATE(3),
	DELETE(4),
	EXIT(0);
	
	
	private int value;
	
	private OperationOptions(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
