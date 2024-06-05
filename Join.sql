use application;
select name, surname, login, password, note_name, note_data from user left join notes on user.login = notes.login_user;