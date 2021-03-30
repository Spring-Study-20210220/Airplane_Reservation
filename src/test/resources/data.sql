insert into airplane(departure, arrival, take_off_date, take_off_time, first_cnt, business_cnt, economy_cnt)
values ('Seoul', 'Busan', '2021-01-02', '11:11:00', 10, 10, 10);
insert into airplane(departure, arrival, take_off_date, take_off_time, first_cnt, business_cnt, economy_cnt)
values ('Seoul2', 'Busan2', '2021-01-02', '11:11:00', 10, 10, 10);
insert into airplane(departure, arrival, take_off_date, take_off_time, first_cnt, business_cnt, economy_cnt)
values ('Seoul3', 'Busan3', '2021-01-02', '11:11:00', 10, 10, 10);
insert into airplane(departure, arrival, take_off_date, take_off_time, first_cnt, business_cnt, economy_cnt)
values ('Seoul4', 'Busan4', '2021-01-02', '11:11:00', 10, 10, 10);

insert into ticket(seat_class, seat_number, price, airplane_id) values ('BUSINESS', 'A01', 10000, 1);
insert into ticket(seat_class, seat_number, price, airplane_id) values ('BUSINESS', 'A02', 10000, 1);
insert into ticket(seat_class, seat_number, price, airplane_id) values ('ECONOMY', 'B01', 500, 1);
insert into ticket(seat_class, seat_number, price, airplane_id) values ('ECONOMY', 'B02', 500, 1);
insert into ticket(seat_class, seat_number, price, airplane_id) values ('ECONOMY', 'B03', 500, 1);

insert into authority(authority_role) values ('USER');
insert into authority(authority_role) values ('ADMIN');

