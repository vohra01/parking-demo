
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) ,
  `last_name` varchar(255),
  `gender` varchar(255),
  `company` varchar(255),
  `type` varchar(255),
  `size` varchar(255),
  `license_plate` varchar(255),
  `parking_date` date,
  `is_for_handicap` bit,
   primary key (`id`)
  );


INSERT INTO employee VALUES (1,'Kunal', 'Vohra', 'MALE','SG','CAR','SMALL','MH 12 MX 0734', CAST(GETDATE() as date), false );
