create table categories(
id varchar(36) not null,
nama_category varchar(36) not null,
deskripsi text,
primary key(id)
)engine=InnoDB;

create table toko(
id varchar(36) not null,
nama_toko varchar(255) not null,
lokasi varchar(255) not null,
primary key(id)
)engine=InnoDB;

create table payments(
id varchar(36) not null,
metode_pembayaran varchar(50) not null,
tanggal_pembayaran timestamp,
jumlah_pembayaran long default 0,
primary key(id)
)engine=InnoDB;

create table products(
id varchar(36) not null,
nama_product varchar(50) not null,
harga_product int default 0 not null,
quantity int default 0 not null,
category_id varchar(36) not null,
foreign key (category_id) references categories(id),
create_at timestamp,
primary key(id)
)engine=InnoDB;

create table customers(
id varchar(36) not null,
nama_customer varchar(255),
umur int default 0 not null,
nomor_telepon int not null,
email varchar(255) not null,
product_id varchar(36) not null,
payment_id varchar(36) not null unique,
foreign key (product_id) references products(id),
foreign key (payment_id) references payments(id),
unique(email),
primary key(id)
)engine=InnoDB;