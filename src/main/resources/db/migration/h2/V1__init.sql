
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) ,
  `last_name` varchar(255),
  `gender` varchar(255),
  `department` varchar(255),
  `type` varchar(255),
  `size` varchar(255),
  `license_plate` varchar(255),
  `date_of_birth` date,
  `is_for_handicap` bit,
   primary key (`id`)
  );
