--  Cho biết những giáo viên có lương lớn hơn lương trung bình của bộ môn mà giáo viên đó làm việc.
SELECT * 
FROM giaovien gv
where gv.LUONG > (select avg(gv1.LUONG) from giaovien gv1 where gv.MABM = gv1.MABM);

-- cách 2:
SELECT * 
FROM giaovien gv join (
	SELECT MABM, avg(LUONG) as ltb
	FROM giaovien
	group by MABM
) as tb_luong on gv.MABM = tb_luong.MABM
where gv.LUONG > tb_luong.ltb;

-- Cho biết họ tên những giáo viên có vai trò quản lý về mặt chuyên môn với các giáo viên khác
SELECT * 
FROM giaovien gv
where gv.MAGV IN (select GVQLCM from giaovien gv2 where GVQLCM is not null);


select *
from giaovien gv
where exists (select * from giaovien gv1 where gv.MAGV = gv1.GVQLCM);

SELECT distinct gv1.MAGV, gv1.HOTEN
FROM giaovien gv1 join giaovien gv2 on gv1.MAGV = gv2.GVQLCM;

-- 

select count(*) as sl_giaovien  from giaovien where MABM = 'MTT';


delimiter //
create procedure sp_GetNumberTeacher(
	IN pMABM varchar(200),
    OUT pMessage varchar(200)
)
begin
	DECLARE count INT DEFAULT 0;
    
    set count = 5;
	set pMessage = (select count(*) as sl_giaovien  from giaovien where MABM = pMABM);
end // 



