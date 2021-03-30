INSERT INTO Airline(name) VALUES('항공사1');
INSERT INTO Airline(name) VALUES('항공사2');
INSERT INTO Airline(name) VALUES('항공사3');
INSERT INTO Airline(name) VALUES('항공사4');
INSERT INTO Airline(name) VALUES('항공사5');

INSERT INTO User(airline_id, email, password, mileage) VALUES(1L, 'email', 'password', 0);

INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time) VALUES (null, 1L, '출발지1', '도착지1', '2021-03-21', '2021-03-27');
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time) VALUES (null, 1L, '출발지2', '도착지2', '2021-03-22', '2021-03-27');
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time) VALUES (null, 2L, '출발지3', '도착지3', '2021-03-23', '2021-03-27');
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time) VALUES (null, 2L, '출발지4', '도착지4', '2021-03-24', '2021-03-27');
INSERT INTO Schedule(id, airline_id, arrivals, departures, arrival_time, departure_time) VALUES (null, 2L, '출발지5', '도착지5', '2021-03-25', '2021-03-27');