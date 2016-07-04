-- 默认角色初始化
-- 管理员账号初始化
insert INTO
    users
      (id, name, password, enabled)
      VALUES (1, 'admin', '96e79218965eb72c92a549dd5a330112', 1);

insert INTO
    users
      (id, name, password, enabled)
      VALUES (2, 'first_user', '96e79218965eb72c92a549dd5a330112', 1);

insert
    into
        admin
        (id, version, email, join_time, mobile_phone, first_name, second_name, nick_name, photograph, user_id)
    values
        (1, 1, 'admin@126.com', now(), '13000000000', 'admin', 'designer', '管理员', '', 1);

insert
    into
        designer
        (id, version, email, join_time, mobile_phone, first_name, second_name, nick_name, photograph, user_id)
    values
        (1, 1, 'first_user@126.com', now(), '13000000001', '第', '一个', '设计员', '', 2);

-- 默认数据类型初始化
INSERT
INTO data_type
(name, default_value, description, is_customer)
VALUES
  ('String', 'a string value', '字符串', FALSE),
  ('Number', '1', '数字', FALSE),
  ('Boolean', 'false', '布尔', FALSE),
  ('Array', '[1,2,3]', '数组', FALSE),
  ('Null', '', '空值', FALSE);

