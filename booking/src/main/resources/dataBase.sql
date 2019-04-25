create schema bookingbd collate utf8_general_ci;

create table discount
(
	id bigint auto_increment
		primary key,
	percent bigint not null,
	min_visiting_count bigint default 1 not null,
	description varchar(45) null
);

create table role
(
	id bigint auto_increment
		primary key,
	role_name varchar(45) not null,
	permission bigint null
);

create table room_type
(
	id bigint auto_increment
		primary key,
	name varchar(45) not null,
	human_amount bigint not null,
	description varchar(255) null
);

create table service
(
	id bigint auto_increment
		primary key,
	name varchar(45) not null,
	price double default 0 not null,
	description varchar(255) null,
	status varchar(45) not null
);

create table subscribers
(
	id bigint null,
	email varchar(255) null,
	status bit null
);

create table user
(
	id bigint auto_increment
		primary key,
	login varchar(45) not null,
	password varchar(45) not null,
	id_role bigint not null,
	constraint idrole
		foreign key (id_role) references role (id)
);

create table client
(
	id bigint auto_increment
		primary key,
	id_user bigint not null,
	fio varchar(255) not null,
	phone varchar(45) not null,
	email varchar(45) not null,
	passport_num varchar(45) not null,
	count_visiting bigint default 0 not null,
	id_discount bigint null,
	need_call tinyint default 0 not null,
	last_message varchar(1000) null,
	constraint iddiscount
		foreign key (id_discount) references discount (id),
	constraint iduser
		foreign key (id_user) references user (id)
);

create index iddiscount_idx
	on client (id_discount);

create index iduser_idx
	on client (id_user);

create table feedback
(
	id bigint auto_increment
		primary key,
	mark varchar(45) not null,
	text varchar(255) null,
	id_client bigint not null,
	constraint idclients
		foreign key (id_client) references client (id)
);

create index idclients_idx
	on feedback (id_client);

create table hotel_room
(
	id bigint auto_increment
		primary key,
	room_amount bigint default 1 null,
	id_type bigint null,
	id_client bigint null,
	price double not null,
	picture_url varchar(255) default 'https://pp.userapi.com/c854028/v854028525/2b94f/DcPoMxV66Xk.jpg' not null,
	description varchar(255) default 'Пока нет описания' not null,
	date_from datetime null,
	date_to datetime null,
	constraint idclient
		foreign key (id_client) references client (id),
	constraint idtype
		foreign key (id_type) references room_type (id)
);

create table booking
(
	id bigint auto_increment
		primary key,
	id_room bigint not null,
	id_service bigint null,
	arrival_date datetime not null,
	leaving_date datetime not null,
	id_client bigint not null,
	human_amount bigint not null,
	constraint clients
		foreign key (id_client) references client (id),
	constraint idroom
		foreign key (id_room) references hotel_room (id),
	constraint idservice
		foreign key (id_service) references service (id)
);

create index idclient_idx
	on booking (id_client);

create index idroom_idx
	on booking (id_room);

create index idservice_idx
	on booking (id_service);

create index idclient_idx
	on hotel_room (id_client);

create index idtype_idx
	on hotel_room (id_type);

create index idrole_idx
	on user (id_role);

