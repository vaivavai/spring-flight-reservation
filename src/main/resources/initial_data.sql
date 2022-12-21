delete from reservation;
delete from flight;
delete from app_user;
delete from airport;

insert into airport (id, city, country, name) values
  ('3ba74066-2171-4458-a917-f5eca679c6b5', 'Vilnius', 'Lithuania', 'VNO'),
  ('492045d1-2683-4d0a-a60b-0ae1332ce3e8', 'Kaunas', 'Lithuania', 'KNO'),
  ('5c9c9e83-8667-4d6e-9f6b-8f3db7210686', 'Riga', 'Latvia', 'RIX'),
  ('7d5326cc-edc6-456a-ad59-c9fe39f248c7', 'London', 'United Kingdom', 'LCY');

insert into flight (id, price, departure_date, origin_airport, destination_airport) values
  ('4f6dfe69-a08c-4f35-affe-ccb45de1672c', 250.50,'2023-03-15 10:30:00','3ba74066-2171-4458-a917-f5eca679c6b5', '492045d1-2683-4d0a-a60b-0ae1332ce3e8'),
  ('04bef472-bedc-492e-a2f9-765156cf7a9f', 320.50,'2023-03-15 10:30:00','3ba74066-2171-4458-a917-f5eca679c6b5', '5c9c9e83-8667-4d6e-9f6b-8f3db7210686'),
  ('6ae7d30f-a7ba-4e36-87f7-2689a2b72f1e', 100.00,'2023-03-15 10:30:00','5c9c9e83-8667-4d6e-9f6b-8f3db7210686', '492045d1-2683-4d0a-a60b-0ae1332ce3e8'),
  ('0274f4e5-6a01-4bc7-9839-0fe939368323', 952.99,'2023-03-15 10:30:00','7d5326cc-edc6-456a-ad59-c9fe39f248c7', '3ba74066-2171-4458-a917-f5eca679c6b5');
 
insert into app_user (id, app_user_role, email, name, password) values
 ('7a1bfe69-a08c-4f35-affe-ccb45de1123a', 0, 'ann@gmail.com', 'Ann', 'ann34645'),
 ('3a3bfe33-a08c-4f35-affe-ccb45de1123a', 1, 'bill@gmail.com', 'Bill', 'billy333');
 
  
insert into reservation  (id, app_user_id, flight_id) values
 ('111bfe69-a08c-4f35-affe-ccb45de1123a', '7a1bfe69-a08c-4f35-affe-ccb45de1123a', '4f6dfe69-a08c-4f35-affe-ccb45de1672c'),
 ('222bfe33-a08c-4f35-affe-ccb45de1123a', '7a1bfe69-a08c-4f35-affe-ccb45de1123a', '0274f4e5-6a01-4bc7-9839-0fe939368323');
  
