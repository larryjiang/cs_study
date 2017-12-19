package cptt.chapter2.mini_front_end.lexer;

import java.util.HashMap;
import java.util.Map;

public enum Tag {
	AND(256),
	BASIC(257),
	BREAK(258),
	DO(259),
	ELSE(260),
	EQ(261),
	FALSE(262),
	GE(263),
	ID(264),
	IF(265),
	INDEX(266),
	LE(267),
	MINUS(268),
	NE(269),
	NUM(270),
	OR(271),
	REAL(272), 
	TEMP(273),
	TRUE(274),
	WHILE(275);

	private final int ordinal;
	
	private Tag(int ordinal) {
		this.ordinal = ordinal;
	}
	
	public int getOrdinal() {
		return ordinal;
	}
	
	public static final Map<Integer, Tag> maps = new HashMap<>();
	
	static {
		for(Tag tag : Tag.values()) {
			maps.put(tag.getOrdinal(), tag);
		}
	}
	
	public static Tag valueOf(int i) {
		if(maps.get(i) != null) {
			return maps.get(i);
		}
		throw new IllegalArgumentException("No corresponding tag for " + i);
	}
	
}
