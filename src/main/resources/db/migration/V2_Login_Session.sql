create table register(
id varchar(36) not null,
nama_depan varchar(50) not null,
nama_tengah varchar(50),
nama_belakang varchar(50) not null,
email varchar(255) not null,
username varchar(255) not null,
password varchar(255) not null,
confirm_password varchar(255) not null,
primary key(id)
)engine = InnoDB;

create table login(
id varchar(36) not null,
username varchar(255) not null,
password varchar(255) not null,
id_register varchar(36) not null,
foreign key(id_register) references register(id),
primary key(id)
)engine = InnoDB;


