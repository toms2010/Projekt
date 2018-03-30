use aplisens;

create table users(
	username varchar(50) COLLATE UTF8_GENERAL_CI not null primary key,
	password varchar(50) COLLATE UTF8_GENERAL_CI not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) COLLATE UTF8_GENERAL_CI not null,
	authority varchar(50) COLLATE UTF8_GENERAL_CI not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);