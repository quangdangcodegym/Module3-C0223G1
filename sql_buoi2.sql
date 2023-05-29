-- Cho biết tên giáo viên và tên bộ môn mà giáo viên đó làm việc
select *
from giaovien gv join bomon bm on gv.MABM = bm.MABM;

select *
from giaovien gv, bomon bm
where gv.MABM = bm.MABM;

-- Với mỗn bộ môn cho biết bộ môn (MAMB) và số lượng giáo viên của bộ môn đó
select gv.MABM, count(*) as tongso_giaovien
from giaovien gv join bomon bm on gv.MABM = bm.MABM
group by MABM;

-- Với mỗi giáo viên, cho biết MAGV và số lượng đề tài mà giáo viên đó có tham gia [Còn chưa chuẩn]
select tgdt.MAGV, count(distinct MADT)
from thamgiadt tgdt
group by tgdt.MAGV;


-- Nâng cấp: Với mỗi giáo viên, cho biết MAGV và số lượng đề tài mà giáo viên đó có tham gia


-- Cho tên những giáo viên và số lượng đề tài của những GV tham gia từ 3 đề tài trở lên
select tgdt.MAGV, count(distinct MADT)
from thamgiadt tgdt
group by tgdt.MAGV
having count(distinct MADT) >=2;


-- Với mỗi bộ môn, cho biết tên bộ môn và số lượng giáo viên của bộ môn đó.
select bm.MABM, count(gv.MAGV)
from bomon bm left join giaovien gv on bm.MABM = gv.MABM
group by bm.MABM;

-- Cách 2: 
SELECT bm.MABM, ( select count(*)
			from giaovien where MABM = bm.MABM) as soluong_gv
FROM bomon bm;

-- Cho biết những giáo viên có lương lớn hơn lương của giáo viên có MAGV=‘001
select *
from giaovien gv
where luong > (select LUONG from giaovien gv1 where  gv1.MAGV = '001');

-- Cho những giáo viên có lương nhỏ nhất
select *
from giaovien gv
where luong = (select min(luong) from giaovien);

-- Cách 2: dùng toán tử ALL
SELECT * 
FROM c03_quanlydetai.giaovien
where LUONG <= ALL (select luong from giaovien);

--  Cho biết bộ môn (MABM) có đông giáo viên nhất
select MABM, count(*) as dem
from giaovien
group by MABM
having dem >= all (
		select count(*)
        from giaovien
        group by MABM
	);
-- Cách 2:
select MABM, count(*) as dem
from giaovien
group by MABM
having dem = (
			select max(temp.dem)
			from ( select count(*) as dem
					from giaovien
					group by MABM
				) as temp
);
-- Cho biết các giáo viên có tham gia đề tài
SELECT * 
FROM giaovien gv
where exists (select * from thamgiadt tgdt where gv.MAGV = tgdt.MAGV);

-- Cách 2: dùng join
SELECT distinct gv.MAGV, gv.HOTEN
FROM giaovien gv join thamgiadt tgdt on gv.MAGV = tgdt.MAGV;


    



