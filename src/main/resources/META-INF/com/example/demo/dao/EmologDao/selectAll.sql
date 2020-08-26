select *
from emolog
where
userid = /* user */1
and
friendid = /* friend */1
order by created_at
