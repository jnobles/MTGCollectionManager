CREATE TABLE cards (
	colorIdentity TEXT,
	colors TEXT,
	manaCost TEXT,
	manaValue FLOAT,
	name TEXT,
	number TEXT,
	power TEXT,
	subtypes TEXT,
	supertypes  TEXT,
	text TEXT,
	toughness TEXT,
	type TEXT,
	types TEXT,
	setCode TEXT,
	PRIMARY KEY (setCode, number)
);

CREATE TABLE physical_cards (
	condition TEXT,
	location TEXT,
	setCode TEXT,
	number TEXT,
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	FOREIGN KEY (setCode, number) REFERENCES cards(setCode, number)
);