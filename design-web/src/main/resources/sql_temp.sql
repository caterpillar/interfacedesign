select username,authority from authorities where username = ?;
select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id;
select username,password,enabled from users where username = ?;
