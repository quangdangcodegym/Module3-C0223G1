create table category(
	`id` int primary key,
    `name` varchar(255) 
);


-- Sửa lại cột id không trống và tự động tăng
alter table category
	change column `id` `id` int not null  auto_increment;

insert into category(`name`) values('Dien thoai');
insert into category(`name`) values('Dien thoai1');
insert into category(`name`) values('Dien thoai2');
insert into category(`name`) values('Dien thoai3');
insert into category(`name`) values('Dien thoai4');

-- 


-- Sửa giá trị tự động tăng trên cột khóa chính
alter table category auto_increment = 4;

-- Thêm ràng buộc check trên cột tuổi là phải lớn hơn 5
create table customer(
	id int not null primary key auto_increment,
    email varchar(255),
    phone char(11),
    age int,
    check (age > 5)
);

-- Thêm ràng buộc unique cho cột email
alter table customer
	add unique index (`email` ASC);
    
-- thêm 1 cột id_category vào bảng product
alter table product
	add id_category int not null;
    
-- thêm khóa ngoại cho bảng product
alter table product 
	add constraint `fk_id_category` foreign key (`id_category`)
    REFERENCES category (`id`);
  
  
  
DELETE FROM `test` WHERE (`id` = '3');	-- xóa dòng kèm điều kiện where
truncate table test;	-- xóa tất cả các dòng
drop table test;		-- xóa cả dữ liệu và cả phần định định nghĩa bảng

-- alias - as: bí danh
select *
from product as p join category as c on p.id_category = c.id;


select *
from product as p left join category as c on p.id_category = c.id;

-- lấy ra nhóm nào có trên 2 sản phẩm
SELECT id_category, count(*) as count_number
FROM c03_product_manager.product
group by id_category
having count_number >2;

-- lấy ra nhóm nào có từ 2 sản phẩm mà có thông tin bổ sung
SELECT id_category, count(*) as count_number
FROM c03_product_manager.product
where id_product_info is NOT NULL
group by id_category
having count_number >= 2;





  